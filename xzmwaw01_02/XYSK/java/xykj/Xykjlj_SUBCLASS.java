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

public class Xykjlj_SUBCLASS extends XysfljParameters{
	public Xykjlj_SUBCLASS(HttpServletRequest request){
		super("Sxykj00000",request);
		
	}
	
	public String createSubclassJS() throws XysfljDB.DbConnectException {
		String selectedDept = getRequestParameter("dept_cd");

		Vector subclassName = new Vector();
		Vector subclassCode = new Vector();
//		Xysflj_Dbcom dbCom = new Xysflj_Dbcom();
        XysfljDB dbCom = getDbConnection();
		
//		Xysflj_Common.subclassList(dbCom,"",selectedDept,subclassCode,subclassName);
//		Xykjlj_Common.subclassList(dbCom,"",selectedDept,subclassCode,subclassName);
		subclassList(dbCom,"",selectedDept,subclassCode,subclassName);
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0;i<subclassCode.size();i++){
			String n = subclassName.elementAt(i).toString();
			String c = subclassCode.elementAt(i).toString();
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
	* メソッド名		subclassList
	* @param		Xysflj_Dbcom	dbcom
	* @param		String	sosiki_cd
	* @param		String	dept_cd
	* @param		Vector	subclass_codes
	* @param		Vector	subclass_names
	* @return		boolean ( true:OK false:ERR )
	********************************************************************************
	*/
	public static boolean subclassList(XysfljDB dbcom,
							String	sosiki_cd,
							String	dept_cd,
							Vector	subclass_codes,
							Vector	subclass_names	)	{
		try{																		// try
			String sql	=	"SELECT ＳＵＢＣＬＡＳＳ subclass_cd,ＳＵＢＣＬＡＳＳ名 subclass_nn"								// SELECT String
						+	"  FROM UPM_ＳＵＢＣＬＡＳＳ "											//
//						+	" WHERE ＤＥＰＴ || ＣＬＡＳＳ = '" + dept_cd + "'"
						+	" WHERE ＤＥＰＴ || ＣＬＡＳＳ = ? "
						+	"  ORDER BY ＤＥＰＴ , ＣＬＡＳＳ , ＳＵＢＣＬＡＳＳ";

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

				String[][] params = new String[1][2];
				params[0][0] = Xysflj_Dbcom.DB_STR;
				params[0][1] = dept_cd;

                ResultSet rs = dbcom.selectTable(sql, params);                     // Execute Query
                
                subclass_codes.removeAllElements();
				subclass_names.removeAllElements();
				subclass_codes.addElement("00");
				subclass_names.addElement("");
				while(rs.next()){													// +++ rs.next +++
					subclass_codes.addElement(rs.getString("subclass_cd"));
//					subclass_names.addElement(rs.getString("subclass_nn"));
					// **** E10K移行時変更対応(2006/06/08) ***
					subclass_names.addElement(rs.getString("subclass_cd") + " " + Xysflj_Common.getStrCvt( rs.getString("subclass_nn") ));
//					subclass_names.addElement(rs.getString("subclass_cd") + " " + rs.getString("subclass_nn"));
					// **** END ******************************
				}																	// +++ next End +++
				rs.close();															// Close

//		dbcom.DBDisconnect(connection);										// Disconnect
//		connection = null;													// Connection = null
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
