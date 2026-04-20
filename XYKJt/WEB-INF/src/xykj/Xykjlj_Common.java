/**
*-------------------------------------------------------------------------------
* クラス名			Xysflj_Common.class
* システム名称		ＭＳＩシステム
* 名称				システム・共通クラス
* 会社名or所属名	ＣＶＳシステム事業部
* 作成日			2003/06/10 00:00:00
* @author			A.Konishi
* @since			1.0
* @version			1.0
* *** 修正履歴 ***
* No.  Date        Aouther      Description
* 01  2006/06/08 K.Sasaki		文字化け対応 名称関連取得時にメソッドXysflj_Common.getStrCvt()を介す
*-------------------------------------------------------------------------------
*/
package xykj;

public class Xykjlj_Common extends Xysflj_Common {

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
/*
	public static boolean subclassList(Xysflj_Dbcom dbcom,
							String	sosiki_cd,
							String	dept_cd,
							Vector	subclass_codes,
							Vector	subclass_names	)	{
		try{																		// try
			String sql	=	"SELECT ＳＵＢＣＬＡＳＳ subclass_cd,ＳＵＢＣＬＡＳＳ名 subclass_nn"								// SELECT String
						+	"  FROM UPM_ＳＵＢＣＬＡＳＳ "											//
						+	" WHERE ＤＥＰＴ || ＣＬＡＳＳ = '" + dept_cd + "'"
						+	"  ORDER BY ＤＥＰＴ , ＣＬＡＳＳ , ＳＵＢＣＬＡＳＳ";

			// *************************************
			// * 読み込みデータをバッファに設定する
			// *************************************
			try{																	// try
//				if (!get_Connect_user(dbcom, sosiki_cd)) {							// 接続ユーザID/PASS取得
//					throw new Exception();											// throw Exception
//				}
//				System.out.println( "*** DB Connection Call ***" );					// *** DEBUG ***
				Connection connection = dbcom.DBConnect(DB_USER, DB_PASS);			// DB connection
				// 検索データ件数取得
//				System.out.println( "*** SQL Quesry ***" );							// *** DEBUG ***
//				System.out.println( sql );											// *** DEBUG ***
				ResultSet rs = dbcom.querySQL(connection, sql);						// Execute Query
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

				dbcom.DBDisconnect(connection);										// Disconnect
				connection = null;													// Connection = null
			}catch(SQLException e){													// SQL Error
				throw new Exception();												// throw Exception
			}

			return true;															// Normal return

		}catch(Exception e){														// catch
			return false;															// Abnormal return
		}																			//
	}
no used */
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
/*
	public static boolean issueformList(Xysflj_Dbcom dbcom,
							String	sosiki_cd,
							String	dept_cd,
							String	subclass_cd,
							Vector	issueform_codes,
							Vector	issueform_names	)	{
		try{																		// try
			String sql = "";
			sql += " SELECT  DISTINCT A.発行形態 issueform_cd               ";
			sql += "        ,A.発行形態名 issueform_nn             ";
			sql += " FROM    UPM_発行形態 A                ";
			sql += "        ,UPM_ＤＥＰＴ別銘柄 B               ";
			sql += " WHERE   A.発行形態 = B.発行形態  ";
			sql += " AND     B.ＤＥＰＴ || B.ＣＬＡＳＳ = '" + dept_cd + "' ";
			sql += " AND     B.ＳＵＢＣＬＡＳＳ = '" + subclass_cd + "' ";
			sql += " ORDER BY A.発行形態            ";
			sql += "        ,A.発行形態名            ";
			// *************************************
			
			// * 読み込みデータをバッファに設定する
			// *************************************
			try{																	// try
//				if (!get_Connect_user(dbcom, sosiki_cd)) {							// 接続ユーザID/PASS取得
//					throw new Exception();											// throw Exception
//				}
//				System.out.println( "*** DB Connection Call ***" );					// *** DEBUG ***
				Connection connection = dbcom.DBConnect(DB_USER, DB_PASS);			// DB connection
				// 検索データ件数取得
//				System.out.println( "*** SQL Quesry ***" );							// *** DEBUG ***
//				System.out.println( sql );											// *** DEBUG ***
				ResultSet rs = dbcom.querySQL(connection, sql);						// Execute Query
				issueform_codes.removeAllElements();
				issueform_names.removeAllElements();
				issueform_codes.addElement("0");
				issueform_names.addElement("");
				while(rs.next()){													// +++ rs.next +++
					issueform_codes.addElement(rs.getString("issueform_cd"));
//					issueform_names.addElement(rs.getString("issueform_nn"));
					// **** E10K移行時変更対応(2006/06/08) ***
					issueform_names.addElement(rs.getString("issueform_cd") + " " + Xysflj_Common.getStrCvt( rs.getString("issueform_nn") ));
//					issueform_names.addElement(rs.getString("issueform_cd") + " " + rs.getString("issueform_nn"));
					// **** END ******************************
				}																	// +++ next End +++
				rs.close();															// Close

				dbcom.DBDisconnect(connection);										// Disconnect
				connection = null;													// Connection = null
			}catch(SQLException e){													// SQL Error
				throw new Exception();												// throw Exception
			}

			return true;															// Normal return

		}catch(Exception e){														// catch
			return false;															// Abnormal return
		}																			//
	}
no used */
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
/*
	public static boolean brandList(Xysflj_Dbcom dbcom,
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
			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = '" + dept_cd + "' ";
			sql += " AND     ＳＵＢＣＬＡＳＳ = '" + subclass_cd + "' ";
			sql += " AND     発行形態 = '" + issueform_cd + "' ";
			sql += " GROUP BY  ＤＥＰＴ            ";
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
				Connection connection = dbcom.DBConnect(DB_USER, DB_PASS);			// DB connection
				// 検索データ件数取得
//				System.out.println( "*** SQL Quesry ***" );							// *** DEBUG ***
//				System.out.println( sql );											// *** DEBUG ***
				ResultSet rs = dbcom.querySQL(connection, sql);						// Execute Query
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

				dbcom.DBDisconnect(connection);										// Disconnect
				connection = null;													// Connection = null
			}catch(SQLException e){													// SQL Error
				throw new Exception();												// throw Exception
			}

			return true;															// Normal return

		}catch(Exception e){														// catch
			return false;															// Abnormal return
		}																			//
	}
no used */
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
/*
	public static boolean saledateList(Xysflj_Dbcom dbcom,
							String	sosiki_cd,
							String	dept_cd,
							String	subclass_cd,
							String	issueform_cd,
							String	brand_cd,
							Vector	saledate_codes,
							Vector	saledate_names	)	{
		try{																		// try
			String sql = "";
			sql += " SELECT  to_char(発売日,'YYYY/MM/DD') saledate   ";
			sql += " FROM    UPM_ＤＥＰＴ別銘柄                ";
			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = '" + dept_cd + "' ";
			sql += " AND     ＳＵＢＣＬＡＳＳ = '" + subclass_cd + "' ";
			sql += " AND     発行形態 = '" + issueform_cd + "' ";
			sql += " AND     銘柄コード = '" + brand_cd + "' ";
			sql += " ORDER BY  発売日            ";
			// *************************************
			
			// * 読み込みデータをバッファに設定する
			// *************************************
			try{																	// try
//				if (!get_Connect_user(dbcom, sosiki_cd)) {							// 接続ユーザID/PASS取得
//					throw new Exception();											// throw Exception
//				}
//				System.out.println( "*** DB Connection Call ***" );					// *** DEBUG ***
				Connection connection = dbcom.DBConnect(DB_USER, DB_PASS);			// DB connection
				// 検索データ件数取得
//				System.out.println( "*** SQL Quesry ***" );							// *** DEBUG ***
//				System.out.println( sql );											// *** DEBUG ***
				ResultSet rs = dbcom.querySQL(connection, sql);						// Execute Query
				saledate_codes.removeAllElements();
				saledate_names.removeAllElements();
				saledate_codes.addElement("0000000000");
				saledate_names.addElement("");
				while(rs.next()){													// +++ rs.next +++
					saledate_codes.addElement(rs.getString("saledate"));
					saledate_names.addElement(rs.getString("saledate"));
				}																	// +++ next End +++
				rs.close();															// Close

				dbcom.DBDisconnect(connection);										// Disconnect
				connection = null;													// Connection = null
			}catch(SQLException e){													// SQL Error
				throw new Exception();												// throw Exception
			}

			return true;															// Normal return

		}catch(Exception e){														// catch
			return false;															// Abnormal return
		}																			//
	}
no used */
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
/*
	public static boolean publishbrandList(Xysflj_Dbcom dbcom,
							String	sosiki_cd,
							String	publishcom_cd,
							Vector	brand_codes,
							Vector	brand_names	)	{
		try{																		// try
			String sql = "";
			sql += " SELECT  銘柄コード brancd_cd         ";
			sql += "        ,銘柄名 brancd_nn             ";
			sql += " FROM    UPM_出版社別銘柄              ";
			sql += " WHERE   出版社コード = '" + publishcom_cd + "' ";
			sql += " GROUP BY  銘柄コード            ";
			sql += "        ,銘柄名            ";
			// *************************************
			
			// * 読み込みデータをバッファに設定する
			// *************************************
			try{																	// try
//				if (!get_Connect_user(dbcom, sosiki_cd)) {							// 接続ユーザID/PASS取得
//					throw new Exception();											// throw Exception
//				}
//				System.out.println( "*** DB Connection Call ***" );					// *** DEBUG ***
				Connection connection = dbcom.DBConnect(DB_USER, DB_PASS);			// DB connection
				// 検索データ件数取得
//				System.out.println( "*** SQL Quesry ***" );							// *** DEBUG ***
//				System.out.println( sql );											// *** DEBUG ***
				ResultSet rs = dbcom.querySQL(connection, sql);						// Execute Query
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

				dbcom.DBDisconnect(connection);										// Disconnect
				connection = null;													// Connection = null
			}catch(SQLException e){													// SQL Error
				throw new Exception();												// throw Exception
			}

			return true;															// Normal return

		}catch(Exception e){														// catch
			return false;															// Abnormal return
		}																			//
	}
no used */
	/**
	********************************************************************************
	* メソッド名		publishsaledateList
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
/*
	public static boolean publishsaledateList(Xysflj_Dbcom dbcom,
							String	sosiki_cd,
							String	publishcom_cd,
							String	brancd_cd,
							Vector	saledate_codes,
							Vector	saledate_names	)	{
		try{																		// try
			String sql = "";
			sql += " SELECT  to_char(発売日,'YYYY/MM/DD') saledate   ";
			sql += " FROM    UPM_出版社別銘柄              ";
			sql += " WHERE   出版社コード = '" + publishcom_cd + "' ";
			sql += " AND     銘柄コード = '" + brancd_cd + "' ";
			sql += " ORDER BY  発売日            ";
			// *************************************
			
			// * 読み込みデータをバッファに設定する
			// *************************************
			try{																	// try
//				if (!get_Connect_user(dbcom, sosiki_cd)) {							// 接続ユーザID/PASS取得
//					throw new Exception();											// throw Exception
//				}
//				System.out.println( "*** DB Connection Call ***" );					// *** DEBUG ***
				Connection connection = dbcom.DBConnect(DB_USER, DB_PASS);			// DB connection
				// 検索データ件数取得
//				System.out.println( "*** SQL Quesry ***" );							// *** DEBUG ***
//				System.out.println( sql );											// *** DEBUG ***
				ResultSet rs = dbcom.querySQL(connection, sql);						// Execute Query
				saledate_codes.removeAllElements();
				saledate_names.removeAllElements();
				saledate_codes.addElement("0000000000");
				saledate_names.addElement("");
				while(rs.next()){													// +++ rs.next +++
					saledate_codes.addElement(rs.getString("saledate"));
					saledate_names.addElement(rs.getString("saledate"));
				}																	// +++ next End +++
				rs.close();															// Close

				dbcom.DBDisconnect(connection);										// Disconnect
				connection = null;													// Connection = null
			}catch(SQLException e){													// SQL Error
				throw new Exception();												// throw Exception
			}

			return true;															// Normal return

		}catch(Exception e){														// catch
			return false;															// Abnormal return
		}																			//
	}
no used */
}																					// * End of Common Class *
