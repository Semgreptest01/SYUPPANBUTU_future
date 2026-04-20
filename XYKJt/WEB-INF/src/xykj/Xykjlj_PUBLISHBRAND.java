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

public class Xykjlj_PUBLISHBRAND extends XysfljParameters{

	static Vector TorihikisakiCD = new Vector();
	static String inout = null;
	public Xykjlj_PUBLISHBRAND(HttpServletRequest request){
		super("Sxykj00000",request);
		TorihikisakiCD = super.getLoginUserTorihikisakiCD();
		inout = super.getRequestParameter("inout");

	}
	
	public String createPublishBrandJS() throws XysfljDB.DbConnectException {
		String selectedpublishcom = getRequestParameter("publishcom_cd");

		Vector brandName = new Vector();
		Vector brandmCode = new Vector();
//		Xysflj_Dbcom dbCom = new Xysflj_Dbcom();

        XysfljDB dbCom = getDbConnection();

//		Xysflj_Common.publishbrandList(dbCom,"",selectedpublishcom,brandmCode,brandName);
//		Xykjlj_Common.publishbrandList(dbCom,"",selectedpublishcom,brandmCode,brandName);
		publishbrandList(dbCom,"",selectedpublishcom,brandmCode,brandName);
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0;i<brandmCode.size();i++){
			String n = brandName.elementAt(i).toString();
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
	* メソッド名		publishbrandList
	* @param		Xysflj_Dbcom	dbcom
	* @param		String	sosiki_cd
	* @param		String	publishcom_cd
	* @param		Vector	brand_codes
	* @param		Vector	brand_names
	* @return		boolean ( true:OK false:ERR )
	********************************************************************************
	*/
	public static boolean publishbrandList(XysfljDB dbcom,
							String	sosiki_cd,
							String	publishcom_cd,
							Vector	brand_codes,
							Vector	brand_names	)	{
		try{																		// try
			String sql = "";
			brand_codes.removeAllElements();
			brand_names.removeAllElements();
			brand_codes.addElement("000000");
			brand_names.addElement("");
			if ( inout == null || inout.equals("") || inout.equals("0") ) {
					sql = " SELECT  銘柄コード brancd_cd         ";
					sql += "        ,銘柄名 brancd_nn             ";
					sql += " FROM    UPM_出版社別銘柄              ";
//					sql += " WHERE   出版社コード = '" + publishcom_cd + "' ";
					sql += " WHERE   出版社コード = ? ";
					sql += " GROUP BY  銘柄コード            ";
					sql += "        ,銘柄名            ";
					sql += " ORDER BY  銘柄コード            ";
					sql += "        ,銘柄名            ";
					try{																	// try
						// 検索データ件数取得
//						System.out.println( sql );											// *** DEBUG ***
//						ResultSet rs = dbcom.selectTable(sql);								// Execute Query

						String[][] params = new String[1][2];
						params[0][0] = Xysflj_Dbcom.DB_STR;
						params[0][1] = publishcom_cd;

						ResultSet rs = dbcom.selectTable(sql, params);								// Execute Query

						while(rs.next()){													// +++ rs.next +++
							brand_codes.addElement(rs.getString("brancd_cd"));
							// **** E10K移行時変更対応(2006/06/08) ***
							brand_names.addElement(rs.getString("brancd_cd") + " " + Xysflj_Common.getStrCvt( rs.getString("brancd_nn") ));
//							brand_names.addElement(rs.getString("brancd_cd") + " " + rs.getString("brancd_nn"));
							// **** END ******************************
//							System.out.println("publishbrandList1 = " + brand_codes + "," + brand_names);
						}																	// +++ next End +++
						rs.close();															// Close
						dbcom.connectionClose();											// Disconnect
					}catch(SQLException e){													// SQL Error
						throw new Exception();												// throw Exception
					}
			}else{
			for( int i=0; i<= (TorihikisakiCD.size()-1); i++ ) {
				if ( TorihikisakiCD.elementAt(i+1) == null || TorihikisakiCD.elementAt(i+1).equals("") || TorihikisakiCD.elementAt(i+1).equals("1") ) {
//				System.out.println("種別1 = " + TorihikisakiCD.elementAt(i+1));
					sql = " SELECT  銘柄コード brancd_cd         ";
					sql += "        ,銘柄名 brancd_nn             ";
					sql += " FROM    UPM_出版社別銘柄              ";
//					sql += " WHERE   出版社コード = '" + publishcom_cd + "' ";
					sql += " WHERE   出版社コード = ? ";
					sql += " GROUP BY  銘柄コード            ";
					sql += "        ,銘柄名            ";
					sql += " ORDER BY  銘柄コード            ";
					sql += "        ,銘柄名            ";
					try{																	// try
						// 検索データ件数取得
//						System.out.println( sql );											// *** DEBUG ***
//						ResultSet rs = dbcom.selectTable(sql);								// Execute Query

						String[][] params = new String[1][2];
						params[0][0] = Xysflj_Dbcom.DB_STR;
						params[0][1] = publishcom_cd;

						ResultSet rs = dbcom.selectTable(sql, params);								// Execute Query

						while(rs.next()){													// +++ rs.next +++
							brand_codes.addElement(rs.getString("brancd_cd"));
							// **** E10K移行時変更対応(2006/06/08) ***
							brand_names.addElement(rs.getString("brancd_cd") + " " + Xysflj_Common.getStrCvt( rs.getString("brancd_nn") ));
//							brand_names.addElement(rs.getString("brancd_cd") + " " + rs.getString("brancd_nn"));
							// **** END ******************************
//							System.out.println("publishbrandList2 = " + brand_codes + "," + brand_names);
						}																	// +++ next End +++
						rs.close();															// Close
						dbcom.connectionClose();											// Disconnect
					}catch(SQLException e){													// SQL Error
						throw new Exception();												// throw Exception
					}
				} else {
//						System.out.println("Xykjlj_PUBLISHBRAND=" + TorihikisakiCD.elementAt(i));
//						System.out.println("種別2 = " + TorihikisakiCD.elementAt(i+1));
						sql = " SELECT A.商品コード brancd_cd ";
						sql += "      , B.銘柄名 brancd_nn ";
						sql += " FROM   UPT_取引先_商品 A ";
						sql += "      , UPM_出版社別銘柄 B ";
//						sql += " WHERE A.取引先コード='" + TorihikisakiCD.elementAt(i) + "'";
//						sql += "   AND B.出版社コード = '" + publishcom_cd + "' ";
						sql += " WHERE A.取引先コード= ? ";
						sql += "   AND B.出版社コード = ? ";
						sql += "   AND A.商品コード =B.銘柄コード ";
						sql += " GROUP BY  A.商品コード ";
						sql += "         , B.銘柄名 ";
						sql += " ORDER BY  A.商品コード ";
						sql += "         , B.銘柄名 ";
						try{																	// try
							// 検索データ件数取得
//							System.out.println( sql );											// *** DEBUG ***
//							ResultSet rs = dbcom.selectTable(sql);								// Execute Query

							String[][] params = new String[2][2];
							params[0][0] = Xysflj_Dbcom.DB_STR;
							params[0][1] = (String)TorihikisakiCD.elementAt(i);
							params[1][0] = Xysflj_Dbcom.DB_STR;
							params[1][1] = publishcom_cd;

							ResultSet rs = dbcom.selectTable(sql, params);								// Execute Query

							while(rs.next()){													// +++ rs.next +++
								brand_codes.addElement(rs.getString("brancd_cd"));
								// **** E10K移行時変更対応(2006/06/08) ***
								brand_names.addElement(rs.getString("brancd_cd") + " " + Xysflj_Common.getStrCvt( rs.getString("brancd_nn") ));
//								brand_names.addElement(rs.getString("brancd_cd") + " " + rs.getString("brancd_nn"));
								// **** END ******************************
//							System.out.println("publishbrandList3 = " + brand_codes + "," + brand_names);
							}																	// +++ next End +++
							rs.close();															// Close
							dbcom.connectionClose();											// Disconnect
						}catch(SQLException e){													// SQL Error
							throw new Exception();												// throw Exception
						}
					}
					i=i+1;
				}
			}
			// *************************************
			
			// * 読み込みデータをバッファに設定する
			// *************************************
//		try{																	// try
//			// 検索データ件数取得
//			System.out.println( sql );											// *** DEBUG ***
//			ResultSet rs = dbcom.selectTable(sql);								// Execute Query
//			brand_codes.removeAllElements();
//			brand_names.removeAllElements();
//			brand_codes.addElement("000000");
//			brand_names.addElement("");
//			while(rs.next()){													// +++ rs.next +++
//				brand_codes.addElement(rs.getString("brancd_cd"));
//				brand_names.addElement(rs.getString("brancd_cd") + " " + rs.getString("brancd_nn"));
//			}																	// +++ next End +++
//			rs.close();															// Close
//			dbcom.connectionClose();											// Disconnect
//		}catch(SQLException e){													// SQL Error
//			throw new Exception();												// throw Exception
//		}
			return true;															// Normal return

		}catch(Exception e){														// catch
			return false;															// Abnormal return
		}																			//
	}


}
