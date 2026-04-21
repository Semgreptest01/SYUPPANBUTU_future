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


public class Xykjlj_BRAND extends XysfljParameters{
	public Xykjlj_BRAND(HttpServletRequest request){
		super("Sxykj00000",request);
		
	}
	
	public String createBrandJS() throws XysfljDB.DbConnectException {
		String selectedDept = getRequestParameter("dept_cd");
		String selectedSubclass = getRequestParameter("subclass_cd");
		String selectedIssueform = getRequestParameter("issueform_cd");

		Vector brandName = new Vector();
		Vector brandmCode = new Vector();
//		Xysflj_Dbcom dbCom = new Xysflj_Dbcom();

        XysfljDB dbCom = getDbConnection();
		
//		Xysflj_Common.brandList(dbCom,"",selectedDept,selectedSubclass,selectedIssueform,brandmCode,brandName);
//		Xykjlj_Common.brandList(dbCom,"",selectedDept,selectedSubclass,selectedIssueform,brandmCode,brandName);
		brandList(dbCom,"",selectedDept,selectedSubclass,selectedIssueform,brandmCode,brandName);
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0;i<brandmCode.size();i++){
			String n = "";
			if (null == brandName.elementAt(i)){
				n = "";
			} else{
				n = brandName.elementAt(i).toString();
			}
			String c = brandmCode.elementAt(i).toString();
			if(0 != i){
				sb.append(",");
			}
			sb.append("\"('");
			sb.append(n);
			sb.append("','");
			sb.append(c);
			sb.append("')\"\r\n");
		}
		/*
		String sql = "";
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT SV ");
		sb.append("       ,KEY_KOMOKU_NN ");
		sb.append(" FROM ");
		sb.append("        UY007M ");
		sb.append("  ");
		sb.append("  ");
		*/
		return XysfljGenericRules.exchangeOutString(sb.toString());
	}
	/**
	********************************************************************************
	* メソッド名		brandList
	* @param		Xysflj_Dbcom	dbcom
	* @param		String	sosiki_cd
	* @param		String	dept_cd
	* @param		String	subclass_cd
	* @param		String	issueform_cd
	* @param		Vector	brand_codes
	* @param		Vector	brand_names
	* @return		boolean ( true:OK false:ERR )
	********************************************************************************
	*/
	public static boolean brandList(XysfljDB dbcom,
							String	sosiki_cd,
							String	dept_cd,
							String	subclass_cd,
							String	issueform_cd,
							Vector	brand_codes,
							Vector	brand_names	)	{
		try{																		// try
			String sql = "";
			sql += " SELECT  ＤＥＰＴ                 ";
			sql += "        ,ＣＬＡＳＳ               ";
			sql += "        ,ＳＵＢＣＬＡＳＳ                ";
			sql += "        ,発行形態              ";
			sql += "        ,銘柄コード  brancd_cd          ";
			sql += "        ,銘柄名      brancd_nn       ";
			sql += " FROM    UPM_ＤＥＰＴ別銘柄                ";
//			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = '" + dept_cd + "' ";
//			sql += " AND     ＳＵＢＣＬＡＳＳ = '" + subclass_cd + "' ";
//			sql += " AND     発行形態 = '" + issueform_cd + "' ";
			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = ? ";
			sql += " AND     ＳＵＢＣＬＡＳＳ = ? ";
			sql += " AND     発行形態 = ? ";
			sql += " GROUP BY  ＤＥＰＴ            ";
			sql += "        ,ＣＬＡＳＳ            ";
			sql += "        ,ＳＵＢＣＬＡＳＳ            ";
			sql += "        ,発行形態            ";
			sql += "        ,銘柄コード            ";
			sql += "        ,銘柄名            ";
			sql += " ORDER BY ＤＥＰＴ ";
			sql += "        ,ＣＬＡＳＳ            ";
			sql += "        ,ＳＵＢＣＬＡＳＳ            ";
			sql += "        ,発行形態            ";
			sql += "        ,銘柄コード            ";
			sql += "        ,銘柄名            ";
			// *************************************
			
			// * 読み込みデータをバッファに設定する
			// *************************************
			try{																	// try
//				if (!get_Connect_user(dbcom, sosiki_cd)) {							// 接続ユーザID/PASS取得
//					throw new Exception();											// throw Exception
//				}
//				System.out.println( "*** DB Connection Call ***" );					// *** DEBUG ***
//				Connection connection = dbcom.DBConnect(DB_USER, DB_PASS);			// DB connection
				// 検索データ件数取得
//				System.out.println( "*** SQL Quesry ***" );							// *** DEBUG ***
//				System.out.println( sql );											// *** DEBUG ***
//				ResultSet rs = dbcom.querySQL(connection, sql);						// Execute Query
//                ResultSet rs = dbcom.selectTable(sql);                     // Execute Query
				String[][] params = new String[3][2];
				params[0][0] = Xysflj_Dbcom.DB_STR;
				params[0][1] = dept_cd;
				params[1][0] = Xysflj_Dbcom.DB_STR;
				params[1][1] = subclass_cd;
				params[2][0] = Xysflj_Dbcom.DB_STR;
				params[2][1] = issueform_cd;
				
				ResultSet rs = dbcom.selectTable(sql, params);                     // Execute Query
				brand_codes.removeAllElements();
				brand_names.removeAllElements();
				brand_codes.addElement("000000");
				brand_names.addElement("");
				while(rs.next()){													// +++ rs.next +++
					brand_codes.addElement(rs.getString("brancd_cd"));
//					brand_names.addElement(rs.getString("brancd_nn"));
					// **** E10K移行時変更対応(2006/06/08) ***
					brand_names.addElement(rs.getString("brancd_cd") + " " + Xysflj_Common.getStrCvt( rs.getString("brancd_nn") ));
//					brand_names.addElement(rs.getString("brancd_cd") + " " + rs.getString("brancd_nn"));
					// **** END ******************************
				}																	// +++ next End +++
				rs.close();															// Close

//				dbcom.DBDisconnect(connection);										// Disconnect
//				connection = null;													// Connection = null
                dbcom.connectionClose();                                     // Disconnect

			}catch(SQLException e){													// SQL Error
				throw new Exception();												// throw Exception
			}

			return true;															// Normal return

		}catch(Exception e){														// catch
			return false;															// Abnormal return
		}																			//
	}

	
}
