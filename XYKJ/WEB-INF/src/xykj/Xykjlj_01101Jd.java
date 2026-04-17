/*
 *
 *  修正履歴
 *  L001  2006/05/11 K.Sasaki   ログ出力対応　ログ出力メソッドの呼び出し追加
 *
 */
package xykj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * (ダウンロード)用クラス
 */
public class Xykjlj_01101Jd extends HttpServlet {

	private String getLineSeparator() {
		return "\n";
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.addHeader("Content-Security-Policy", "frame-ancestors 'none'");
		res.addHeader("X-Frame-Options", "DENY");
		Vector csv1 = new Vector();
		Vector csv2 = new Vector();
		Vector csv3 = new Vector();
		int checkintsize = 0;
		int headintsize = 0;
		int csvintsize = 0;
		
		//フォームデータ解析
		Xykjlj_01101_Params param = null;
	
		try {

			param = new Xykjlj_01101_Params(req);
			Xykj0110CheckTable checkTable = new Xykj0110CheckTable(param);
			Vector vcheckdata = checkTable.csvdata();
			if (vcheckdata != null) {
				checkintsize = 1;
			}
	
			//データ明細
			Xykj0110DetailTable detailTable = new Xykj0110DetailTable(param);
			Vector csvheaddata = detailTable.csvHeaddata();
			if (csvheaddata != null) {
				headintsize = 5 + detailTable.maxday;
			}
	
			//データ明細
			Vector csvdata = detailTable.csvdata();
//			if (csvdata != null) {
			if ( detailTable.maxday != 0 ) {
				// CSV形式に編集(ヘッダー部)
				if (Xysflj_Common.csv_Out(checkintsize, vcheckdata, csv1) != true) {
				}
				// CSV形式に編集
				if (Xysflj_Common.csv_Out(headintsize, csvheaddata, csv2) != true) {
				}
				csvintsize = 5 + detailTable.maxday;

				// CSV形式に編集
				if (Xysflj_Common.csv_Out(csvintsize, csvdata, csv3) != true) {
				}

			}

// 2006/05/11変更(E10K移行対応)
		// ***** ﾛｸﾞ出力処理 START *****************************************************************************
			param.putLog( 9,			 											// 状態（初期表示時:0 ﾀﾞｳﾝﾛｰﾄﾞ:9）
						(String)req.getParameter("screenId"), 						// 画面ＩＤ
						XysfljGenericRules.SCREEN_NM_110, 							// 画面名称
						XysfljGenericRules.SCREEN_NM_110);							// 画面MSG
		// ***** ﾛｸﾞ出力処理  END  ****************************************************************************

			// CSVファイルを出力
//			csvDownload(res, "Xykj1101.csv", csv1,csv2,csv3, getLineSeparator());
			csvDownload(res, "Xykj1101.csv", csv1,csv2,csv3, getLineSeparator(),detailTable.maxday);
	
		}
		catch(Exception e){
			XysfljParameters.redirectErrorPage(req,res,e);
		}
		finally {
			//DB CLOSE
			if (null != param) {
				param.exit();
			}
		}
		
	}

	/*
	********************************************************************************
	* メソッド名		csvDownload
	* @deprecated		ＣＳＶファイル形式ダウンロード
	* @param		HttpServletResponse	dbcom
	* @param		String	fileName
	* @param		Vector	csvData
	* @param		String	kaigyo_cd
	* @return		boolean ( true:OK false:ERR )
	********************************************************************************
	*/
	public static boolean csvDownload(HttpServletResponse response,
							String	fileName,
							Vector	csvData1,
							Vector	csvData2,
							Vector	csvData3,
							String	kaigyo_cd,
							int	maxday) {
		try{																		// try

//			response.setContentType("application/octet-stream;charset=Shift_JIS");
//			response.setHeader("Content-Disposition","attachmentl; filename=" + fileName);
//			response.setContentType("application/data-download;charset=UTF-8");
			response.setContentType("application/data-download;charset=Shift_JIS");
			response.setHeader("Content-Disposition"," filename=" + fileName);

			PrintWriter out=response.getWriter();

			if (maxday != 0){
				out.print(",,県、銘柄別販売実績" + kaigyo_cd );
			}
			Enumeration data1 = csvData1.elements();
			while(data1.hasMoreElements()){
				out.print( Xysflj_Common.csvSetStrCvt((String)data1.nextElement()) + kaigyo_cd );
			}
			Enumeration data2 = csvData2.elements();
			while(data2.hasMoreElements()){
				out.print( Xysflj_Common.csvSetStrCvt((String)data2.nextElement()) + kaigyo_cd );
			}
			Enumeration data3 = csvData3.elements();
			while(data3.hasMoreElements()){
				out.print( Xysflj_Common.csvSetStrCvt((String)data3.nextElement()) + kaigyo_cd );
			}
			out.close();

			return true;															// Normal return

		}catch(Exception e){														// catch
			return false;															// Abnormal return
		}																			//
	}



}
