/*
 *
 *  修正履歴
 *  L001  2006/05/12 K.Sasaki   ログ出力対応　ログ出力メソッドの呼び出し追加
 *
 */
package xysk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 非同期処理用クラス
 */
public abstract class Xysklj_ajax extends HttpServlet {

	private String getLineSeparator() {
		return "\n";
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doPost");
		PrintWriter	prnt = response.getWriter();

		prnt.print("2");
		 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doGet");
		PrintWriter	prnt = response.getWriter();
		
		prnt.print("1");
		 
	}
*/
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {


		String ret = "9:";
		PrintWriter	prnt = res.getWriter();
		res.setContentType("text/xml; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		try {
			/*
			String mode = req.getParameter("mode");
			
			// 時間取得
			if ("time".equals(mode)) {
				ret = "0:" + Xysflj_Common.curr_Date() + " " + Xysflj_Common.curr_Time();

				res.setContentType("text/xml; charset=UTF-8");
				req.setCharacterEncoding("UTF-8");

			}
			System.out.println("mode=" + mode + ",ret=" + ret);
			*/
			ret = business(req, res);
			
			// 結果出力
			prnt.print(ret);
			
		}
		catch(Exception e){
			XysfljParameters.redirectErrorPage(req,res,e);
		}
		finally {
			prnt.close();
		}
		
		return;
		
	}

	public abstract String business(HttpServletRequest	req,
			HttpServletResponse	res) throws Exception;

}
