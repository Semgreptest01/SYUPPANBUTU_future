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

public class Xykjlj_NewsSUBCLASS extends XysfljParameters{
	public Xykjlj_NewsSUBCLASS(HttpServletRequest request){
		super("Sxykj00000",request);
		
	}
	
	public String createSubclassJS() throws XysfljDB.DbConnectException {
		String selectedDept = getRequestParameter("dept_cd");

		Vector subclassName = new Vector();
		Vector subclassCode = new Vector();
        XysfljDB dbCom = getDbConnection();
		
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
			String sql	=	"SELECT ＳＵＢＣＬＡＳＳ subclass_cd,"
						+	" ＳＵＢＣＬＡＳＳ名 subclass_nn"
						+	"  FROM UPM_新聞_ＳＵＢＣＬＡＳＳ "
//						+	" WHERE ＤＥＰＴ || ＣＬＡＳＳ = '" + dept_cd + "'"
						+	" WHERE ＤＥＰＴ || ＣＬＡＳＳ = ? "
						+	"  ORDER BY ＤＥＰＴ , ＣＬＡＳＳ , ＳＵＢＣＬＡＳＳ";

			// *************************************
			// * 読み込みデータをバッファに設定する
			// *************************************
			try{																	// try
				// 検索データ件数取得
//				ResultSet rs = dbcom.selectTable(sql);								// Execute Query

				String[][] params = new String[1][2];
				params[0][0] = Xysflj_Dbcom.DB_STR;
				params[0][1] = dept_cd;

				ResultSet rs = dbcom.selectTable(sql, params);								// Execute Query

				subclass_codes.removeAllElements();
				subclass_names.removeAllElements();
				subclass_codes.addElement("00");
				subclass_names.addElement("");
				while(rs.next()){													// +++ rs.next +++
					subclass_codes.addElement(rs.getString("subclass_cd"));
					// **** E10K移行時変更対応(2006/06/08) ***
					subclass_names.addElement( Xysflj_Common.getStrCvt( rs.getString("subclass_nn") ));
//					subclass_names.addElement(rs.getString("subclass_nn"));
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
