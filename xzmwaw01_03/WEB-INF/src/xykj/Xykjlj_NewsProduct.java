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


public class Xykjlj_NewsProduct extends XysfljParameters{
	public Xykjlj_NewsProduct(HttpServletRequest request){
		super("Sxykj00000",request);
		
	}
	
	public String createProductJS() throws XysfljDB.DbConnectException {
		String selectedDept = getRequestParameter("dept_cd");
		String selectedSubclass = getRequestParameter("subclass_cd");
		String selectedIssueform = getRequestParameter("issueform_cd");

		Vector productName = new Vector();
		Vector productmCode = new Vector();

        XysfljDB dbCom = getDbConnection();
		
		productList(dbCom,"",selectedDept,selectedSubclass,selectedIssueform,productmCode,productName);
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0;i<productmCode.size();i++){
			String n = "";
			if (null == productName.elementAt(i)){
				n = "";
			} else{
				n = productName.elementAt(i).toString();
			}
			String c = productmCode.elementAt(i).toString();
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
	* メソッド名		productList
	* @param		Xysflj_Dbcom	dbcom
	* @param		String	sosiki_cd
	* @param		String	dept_cd
	* @param		String	subclass_cd
	* @param		String	issueform_cd
	* @param		Vector	product_codes
	* @param		Vector	product_names
	* @return		boolean ( true:OK false:ERR )
	********************************************************************************
	*/
	public static boolean productList(XysfljDB dbcom,
							String	sosiki_cd,
							String	dept_cd,
							String	subclass_cd,
							String	issueform_cd,
							Vector	product_codes,
							Vector	product_names	)	{
		try{																		// try
			String sql = "";
			sql += " SELECT  ＤＥＰＴ                ";
			sql += "        ,ＣＬＡＳＳ              ";
			sql += "        ,ＳＵＢＣＬＡＳＳ        ";
			sql += "        ,発行形態              ";
			sql += "        ,商品コード  product_cd  ";
			sql += "        ,商品名      product_nn  ";
			sql += " FROM    UPM_新聞_ＤＥＰＴ別商品 ";
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
			sql += "        ,商品コード            ";
			sql += "        ,商品名            ";
			sql += " ORDER BY  ＤＥＰＴ            ";
			sql += "        ,ＣＬＡＳＳ            ";
			sql += "        ,ＳＵＢＣＬＡＳＳ            ";
			sql += "        ,発行形態            ";
			sql += "        ,商品コード            ";
			sql += "        ,商品名            ";
			// *************************************
			
			// * 読み込みデータをバッファに設定する
			// *************************************
			try{																	// try
				// 検索データ件数取得
//				ResultSet rs = dbcom.selectTable(sql);								// Execute Query
				
				String[][] params = new String[3][2];
				params[0][0] = Xysflj_Dbcom.DB_STR;
				params[0][1] = dept_cd;
				params[1][0] = Xysflj_Dbcom.DB_STR;
				params[1][1] = subclass_cd;
				params[2][0] = Xysflj_Dbcom.DB_STR;
				params[2][1] = issueform_cd;
				System.out.println("deptcd:" + dept_cd);
				System.out.println("subclass_cd:" + subclass_cd);
				System.out.println("issueform_cd:" + issueform_cd);
				ResultSet rs = dbcom.selectTable(sql, params);								// Execute Query

				product_codes.removeAllElements();
				product_names.removeAllElements();
				product_codes.addElement("000000");
				product_names.addElement("");
				while(rs.next()){													// +++ rs.next +++
					product_codes.addElement(rs.getString("product_cd"));
					// **** E10K移行時変更対応(2006/06/08) ***
					product_names.addElement( Xysflj_Common.getStrCvt( rs.getString("product_nn") ));
//					product_names.addElement(rs.getString("product_nn"));
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
