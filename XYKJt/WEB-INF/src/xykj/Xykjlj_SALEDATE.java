/*
 *
 *  修正履歴
 *  L001  2006/12/01 S.Sawada   発売日２重表示対応
 *
 */
package xykj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

public class Xykjlj_SALEDATE extends XysfljParameters{
	public Xykjlj_SALEDATE(HttpServletRequest request){
		super("Sxykj00000",request);
		
	}
	
	public String createSaledateJS() throws XysfljDB.DbConnectException {
		String selectedDept = getRequestParameter("dept_cd");
		String selectedSubclass = getRequestParameter("subclass_cd");
		String selectedIssueform = getRequestParameter("issueform_cd");
		String selectedBrand = getRequestParameter("brand_cd");

		Vector saletdateName = new Vector();
		Vector saletdateCode = new Vector();
//		Xysflj_Dbcom dbCom = new Xysflj_Dbcom();
        XysfljDB dbCom = getDbConnection();
		
//		Xysflj_Common.saledateList(dbCom,"",selectedDept,selectedSubclass,selectedIssueform,selectedBrand,saletdateCode,saletdateName);
//		Xykjlj_Common.saledateList(dbCom,"",selectedDept,selectedSubclass,selectedIssueform,selectedBrand,saletdateCode,saletdateName);
		saledateList(dbCom,"",selectedDept,selectedSubclass,selectedIssueform,selectedBrand,saletdateCode,saletdateName);
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0;i<saletdateCode.size();i++){
			String n = saletdateName.elementAt(i).toString();
			String c = saletdateCode.elementAt(i).toString();
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
	* メソッド名		saledateList
	* @param		Xysflj_Dbcom	dbcom
	* @param		String	sosiki_cd
	* @param		String	dept_cd
	* @param		String	subclass_cd
	* @param		String	issueform_cd
	* @param		String	brand_cd
	* @param		Vector	saledate_codes
	* @param		Vector	saledate_names
	* @return		boolean ( true:OK false:ERR )
	********************************************************************************
	*/
	public static boolean saledateList(XysfljDB dbcom,
							String	sosiki_cd,
							String	dept_cd,
							String	subclass_cd,
							String	issueform_cd,
							String	brand_cd,
							Vector	saledate_codes,
							Vector	saledate_names	)	{
		try{																		// try
			String sql = "";
//L001 CHGS
			sql += " SELECT  to_char(発売日,'YYYY/MM/DD') saledate   ";
			sql += " , to_char(発売日,'YYYY/MM/DD') || '  ' || substr(ＪＡＮコード, 5,5) || '-' ||substr(ＪＡＮコード,10,2) saledateZhn   ";
			sql += " , ＪＡＮコード jan";
//L001 CHGE
			sql += " FROM    UPM_ＤＥＰＴ別銘柄                ";
//			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = '" + dept_cd + "' ";
//			sql += " AND     ＳＵＢＣＬＡＳＳ = '" + subclass_cd + "' ";
//			sql += " AND     発行形態 = '" + issueform_cd + "' ";
//			sql += " AND     銘柄コード = '" + brand_cd + "' ";
			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = ? ";
			sql += " AND     ＳＵＢＣＬＡＳＳ = ? ";
			sql += " AND     発行形態 = ? ";
			sql += " AND     銘柄コード = ? ";
			sql += " ORDER BY  発売日            ";
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

				String[][] params = new String[4][2];
				params[0][0] = Xysflj_Dbcom.DB_STR;
				params[0][1] = dept_cd;
				params[1][0] = Xysflj_Dbcom.DB_STR;
				params[1][1] = subclass_cd;
				params[2][0] = Xysflj_Dbcom.DB_STR;
				params[2][1] = issueform_cd;
				params[3][0] = Xysflj_Dbcom.DB_STR;
				params[3][1] = brand_cd;

                ResultSet rs = dbcom.selectTable(sql, params);                     // Execute Query

                saledate_codes.removeAllElements();
				saledate_names.removeAllElements();
				saledate_codes.addElement("0000000000");
				saledate_names.addElement("");
				while(rs.next()){													// +++ rs.next +++
//L001 CHGS
					if((rs.getString("jan").substring(0,3)).equals("491")){
					saledate_codes.addElement(rs.getString("saledateZhn"));
					saledate_names.addElement(rs.getString("saledateZhn"));
					}else{
					saledate_codes.addElement(rs.getString("saledate"));
					saledate_names.addElement(rs.getString("saledate"));
					}
//L001 CHGE
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
