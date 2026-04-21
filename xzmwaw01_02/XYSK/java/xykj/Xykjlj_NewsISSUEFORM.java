/*
 *
 *  修正履歴
 *  L001  2006/06/08 K.Sasaki   文字化け対応 名称関連取得時にメソッドXysflj_Common.getStrCvt()を介す
 *
 */
 package xykj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

public class Xykjlj_NewsISSUEFORM extends XysfljParameters{
	public Xykjlj_NewsISSUEFORM(HttpServletRequest request){
		super("Sxykj00000",request);
		
	}
	
	public String createIssueformJS() throws XysfljDB.DbConnectException {
		String selectedDept = getRequestParameter("dept_cd");
		String selectedSubclass = getRequestParameter("subclass_cd");

		Vector issueformName = new Vector();
		Vector issueformCode = new Vector();

        XysfljDB dbCom = getDbConnection();
		
		issueformList(dbCom,"",selectedDept,selectedSubclass,issueformCode,issueformName);
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0;i<issueformCode.size();i++){
			String n = issueformName.elementAt(i).toString();
			String c = issueformCode.elementAt(i).toString();
			if(0 != i){
				sb.append(",");
			}
			sb.append("\"('");
			sb.append(n);
			sb.append("','");
			sb.append(c);
			sb.append("')\"\r\n");
		}
		return XysfljGenericRules.exchangeOutString(sb.toString());
	}

	/**
	********************************************************************************
	* メソッド名		issueformList
	* @param		Xysflj_Dbcom	dbcom
	* @param		String	sosiki_cd
	* @param		String	dept_cd
	* @param		String	subclass_cd
	* @param		Vector	issueform_codes
	* @param		Vector	issueform_names
	* @return		boolean ( true:OK false:ERR )
	********************************************************************************
	*/
	public static boolean issueformList(XysfljDB dbcom,
							String	sosiki_cd,
							String	dept_cd,
							String	subclass_cd,
							Vector	issueform_codes,
							Vector	issueform_names	)	{
		try{																		// try
			String sql = "";
			sql += " SELECT  DISTINCT A.発行形態 issueform_cd               ";
			sql += "        ,A.発行形態名 issueform_nn             ";
			sql += " FROM    UPM_新聞_発行形態 A                ";
			sql += "        ,UPM_新聞_ＤＥＰＴ別商品 B               ";
			sql += " WHERE   A.発行形態 = B.発行形態  ";
//			sql += " AND     B.ＤＥＰＴ || B.ＣＬＡＳＳ = '" + dept_cd + "' ";
//			sql += " AND     B.ＳＵＢＣＬＡＳＳ = '" + subclass_cd + "' ";
			sql += " AND     B.ＤＥＰＴ || B.ＣＬＡＳＳ = ? ";
			sql += " AND     B.ＳＵＢＣＬＡＳＳ = ? ";
			sql += " ORDER BY A.発行形態            ";
			sql += "        ,A.発行形態名            ";
			// *************************************
			
			// * 読み込みデータをバッファに設定する
			// *************************************
			try{																	// try
				// 検索データ件数取得
//				ResultSet rs = dbcom.selectTable(sql);								// Execute Query
				
				String[][] params = new String[2][2];
				params[0][0] = Xysflj_Dbcom.DB_STR;
				params[0][1] = dept_cd;
				params[1][0] = Xysflj_Dbcom.DB_STR;
				params[1][1] = subclass_cd;

				ResultSet rs = dbcom.selectTable(sql, params);								// Execute Query

				issueform_codes.removeAllElements();
				issueform_names.removeAllElements();
				issueform_codes.addElement("0");
				issueform_names.addElement("");
				while(rs.next()){													// +++ rs.next +++
					issueform_codes.addElement(rs.getString("issueform_cd"));
					// **** E10K移行時変更対応(2006/06/08) ***
					issueform_names.addElement( Xysflj_Common.getStrCvt( rs.getString("issueform_nn") ));
//					issueform_names.addElement(rs.getString("issueform_nn"));
					// **** END ******************************
				}																	// +++ next End +++
				rs.close();															// Close

				dbcom.connectionClose();											// Disconnect

			}catch(SQLException e){													// SQL Error
				throw new Exception();												// throw Exception
			}

			return true;															// Normal return

		}catch(Exception e){														// catch
			return false;															// Abnormal return
		}																			//
	}


}
