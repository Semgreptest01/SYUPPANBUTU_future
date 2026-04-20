/**
*--------------------------------------------------------------------------------
* クラス名		Xykj0010DetailTable.class
* *** 修正履歴 ***
* No.  Date 	   Aouther		Description
* 01   2006/06/05  O.Ogawara	削除処理修正 executeUpdateを使用
* 02  2006/06/08 K.Sasaki		文字化け対応 名称関連取得時にメソッドXysflj_Common.getStrCvt()を介す
*--------------------------------------------------------------------------------
*/
package xykj;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;


public class Xykj0010DetailTable extends XysfljTableTag {

  private Xykjlj_00101_Params param;

  public String[] user_cd     = new String[10];           //利用者コード
  public String[] customer_cd = new String[10];           //取引先コード
  public String[] customer_nm = new String[10];           //取引先名

  public String login_user_id;                   //利用者コード
  public String usercd;                          //利用者コード
  public String customertype;                    //取引先種別
  public String[] customer = new String[10];    //取引先コード
  public String[] customernm = new String[10];  //取引先名

  public String kensaku;				 //検索ボタン押下時『1』それ以外『0』
  public String next;					 //次へボタン押下時『1』それ以外『0』
  public String before;                 //前へボタン押下時『1』それ以外『0』
  public String toroku;                 //登録ボタン押下時『1』それ以外『0』
  public String clear;                 //クリアボタン押下時『1』それ以外『0』
  public String kensaku_err;            //検索エラー時『1』それ以外『0』
  public String err_type;                //JavaScriptにて発生したエラーの種類
  public String[] err_cust_no = new String[10];              //エラーになった取引先コードの番号
  
  public String alertmsg;               //アラートメッセージ
  public String err_flg;                //エラーフラグ
  public String kensaku_flg;            //検索結果が０件の場合は『1』それ以外は『0』
  public String new_flg;                //紐付けマスタへ登録しようとしているデータが新規の場合『1』それ以外『0』


  public Xykj0010DetailTable(Xykjlj_00101_Params p) throws UnsupportedEncodingException {
    super("Xykj0010DetailTable", p);
    param = p;

    int st = param.getStatus();

	try {
	  //DBからの値を取得
	  getDbData();
	} catch (XysfljDB.DBException e) {
	  //データ取得でのDBエラー
	  param.addMessageByCode(1000);
	  param.executeErrorRoutine(e);
	}
  }

  private void getDbData() throws XysfljDB.DBException, UnsupportedEncodingException {
    param = param;
    usercd = param.getParameter("usercd");
	customertype = param.getParameter("customertype");
	next = param.getParameter("next");
	before = param.getParameter("before");
	kensaku = param.getParameter("kensaku");
	toroku = param.getParameter("toroku");
	clear = param.getParameter("clear");
	kensaku_err = param.getParameter("kensaku_err");
	
	login_user_id = param.getLoginUserID();
	
	customer[0] = param.getParameter("customer1");
	customer[1] = param.getParameter("customer2");
	customer[2] = param.getParameter("customer3");
	customer[3] = param.getParameter("customer4");
	customer[4] = param.getParameter("customer5");
	customer[5] = param.getParameter("customer6");
	customer[6] = param.getParameter("customer7");
	customer[7] = param.getParameter("customer8");
	customer[8] = param.getParameter("customer9");
	customer[9] = param.getParameter("customer10");

	customernm[0] = param.getParameter("customernm1");
	customernm[1] = param.getParameter("customernm2");
	customernm[2] = param.getParameter("customernm3");
	customernm[3] = param.getParameter("customernm4");
	customernm[4] = param.getParameter("customernm5");
	customernm[5] = param.getParameter("customernm6");
	customernm[6] = param.getParameter("customernm7");
	customernm[7] = param.getParameter("customernm8");
	customernm[8] = param.getParameter("customernm9");
	customernm[9] = param.getParameter("customernm10");
	
	err_type = param.getParameter("err_type");

    getTotalDataFromDB();
  }

  public Vector getDetailTableData() {
    return tableElements;
  }

  public void getTotalDataFromDB() throws XysfljDB.DBException {
    try {

		XysfljDB tdb = param.getDbConnection();
	    ResultSet rs = null;
	    int cnt = 0;
	    String today = "";

		today = getToday();
	   
	    //クリア処理
	    if(clear != null && clear.equals("1")){  
			if(err_type != null && err_type.equals("1")){
				param.addMessage("利用者コードに数字以外の文字が入力されています。");
			}
	    	if(err_type != null && err_type.equals("2")){
				param.addMessage("利用者コードの桁数が足りません。");
	    	}
			if(err_type != null && err_type.equals("3")){
				param.addMessage("検索結果は０件です。");
			}
			if(err_type != null && err_type.equals("4")){
				param.addMessage("利用者コードに数字以外の文字が入力されています。");
			}
			if(err_type != null && err_type.equals("5")){
				param.addMessage("利用者コードに数字以外の文字が入力されています。");
			}
			if(err_type != null && err_type.equals("6")){
				param.addMessage("利用者コードに数字以外の文字が入力されています。");
			}
			if(err_type != null && err_type.equals("7")){
				param.addMessage("利用者コードに数字以外の文字が入力されています。");
			}
			if(err_type != null && err_type.equals("8")){
				param.addMessage("利用者コードを指定してください。");
			}
	    	//検索ボタン押下時にエラーが発生した場合は利用者コードをクリアしない
	        if(kensaku_err != null && !kensaku_err.equals("1")){
				usercd = "";
	        }
			customertype = "";
	        for(int i=0; i<10; i++){
				customer_cd[i] = "";
			    customer_nm[i] = "";
	        }
	        return;
	    }
	
	    if(toroku!=null && toroku.equals("1")){
		    //登録処理
		    if(!createData(today)){
/*
                for(int i=0; i<10; i++){
					customer_cd[i] = customer[i];
					customer_nm[i] = "";
                }
*/         
/*
                getCustomerNm();
*/
			    return;
		    }
		    //登録処理を行った後、表示するデータを取得するために検索フラグをたてる
            kensaku = "1";
//		    return;
	    }

		if(usercd == null){
			for(int i=0; i<10; i++){
				customer_cd[i] = "";
				customer_nm[i] = "";
			}
			return;
		}

        if(next!=null && next.equals("1") || 
           before != null && before.equals("1") ||
           kensaku != null && kensaku.equals("1")){
           	//取引先種別取得
	        rs = getCustomerType(next, before, kensaku);
	
			while (rs.next()) {
				customertype = rs.getString("種別区分");
				cnt++;
			}
	
	        if(cnt > 1){
	        	err_flg = "1";
				alertmsg = "マスタテーブルに不正なデータが存在するためシステム管理者にお問い合わせください。";
				param.addMessage("マスタテーブルに不正なデータが存在するためシステム管理者にお問い合わせください。");
				customertype = "";
				for(int i=0; i<10; i++){
					customer_cd[i] = "";
					customer_nm[i] = "";
				}
	        	return;
	        }
	
			rs.close();
        }

		if(cnt == 0 && toroku != null && !toroku.equals("1")){
			for(int i=0; i<10; i++){
				user_cd[i]   = "";
				customer_cd[i] = "";
				customer_nm[i] = "";
			}
			if(next!=null && next.equals("1")){
				err_flg = "1";
				alertmsg = "次の利用者コードは存在しません。";
				return;
			}
			if(before!=null && before.equals("1")){
				err_flg = "1";
				alertmsg = "前の利用者コードは存在しません。";
				return;
			}
		}

	    if(customertype!=null && next!=null && 
		   customertype.equals("1") && next.equals("1")){
		   	//種別区分『1』、次へボタン押下時、取引先取得
			rs = getNextCustomer1(today);
	    }
		if(customertype!=null && next!=null && 
		   customertype.equals("2") && next.equals("1")){
			//種別区分『2』、次へボタン押下時、取引先取得
			rs = getNextCustomer2();
		}
		if(customertype!=null && before!=null && 
		   customertype.equals("1") && before.equals("1")){
		   	//種別区分『1』、前へボタン押下時、取引先取得
			rs = getBeforeCustomer1(today);
		}
		if(customertype!=null && before!=null && 
		   customertype.equals("2") && before.equals("1")){
            //種別区分『2』、前へボタン押下時、取引先取得
			rs = getBeforeCustomer2();
		}
		if(customertype!=null && kensaku!=null && 
		   customertype.equals("1") && kensaku.equals("1")){
            //種別区分『1』、検索ボタン押下時、取引先取得
		    rs = getKensakuCustomer1(today);
		} else if(customertype!=null && kensaku!=null && 
		           customertype.equals("2") && kensaku.equals("1")) {
            //種別区分『2』、検索ボタン押下時、取引先取得
			rs = getKensakuCustomer2();
		}

        cnt = 0;
	    //パフォーマンスチューニング
	    rs.setFetchSize(1000);
		while ( rs.next()) {
			user_cd[cnt]     = rs.getString("利用者コード");
			customer_cd[cnt] = rs.getString("取引先コード");
			// **** E10K移行時変更対応(2006/06/08) ***
			customer_nm[cnt] = Xysflj_Common.getStrCvt( rs.getString("取引先名") );
//			customer_nm[cnt] = rs.getString("取引先名");
			// **** END ******************************
			cnt++;
		}
		if(cnt != 0){
		    usercd = user_cd[0];
		} 
	    for(int i=cnt; i<10; i++){
	    	user_cd[i]   = "";
			customer_cd[i] = "";
			customer_nm[i] = "";
	    }
	    
	    if(cnt == 0 && toroku != null && !toroku.equals("1")){
			kensaku_flg = "1";
/*
	    	if(next!=null && next.equals("1")){
				alertmsg = "次の利用者コードは存在しません。";
	    	}
			if(before!=null && before.equals("1")){
				alertmsg = "前の利用者コードは存在しません。";
			}
*/
			if(kensaku!=null && kensaku.equals("1")){
				alertmsg = "検索結果は０件です。";
			}
	    }
	    
	    rs.close();
		
    } catch (SQLException e) {
      //rs.nextのエラー
      throw new XysfljDB.ResultAnalyzeException(e);
    } catch (Exception e) {
    }
  }

  protected String getToday() {
	String today = "";
	ResultSet rs = null;

	try{
	  XysfljDB tdb = param.getDbConnection();
	  // 当日日付取得
	  String sql = "";				//SQL

	  sql += "SELECT  to_char(作成日時,'YYYY/MM/DD HH24:MI:SS') 当日日付 ";
	  sql += "FROM UPC_コントロール ";
	  sql += "WHERE 制御種別 = '01'";
//	  rs = tdb.selectTable(sql);
	  rs = tdb.selectTable(sql, null);

      while(rs.next()){
	      today = rs.getString("当日日付");
      }
	  
	  rs.close();

//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	}
	return today;
  }

  public String getHimodukeTbl() throws XysfljDB.DBException {
	ResultSet rs = null;
	String sql = "";
    int cnt = 0;
    StringBuffer tag = new StringBuffer();

	try{
	  XysfljDB tdb = param.getDbConnection();
	  // 次へボタン押下時
	  // 紐付けTBLより全レコードを取得
	  sql = createGetHimodukeTbl(); //SQL
	  
//	  rs = tdb.selectTable(sql);
	  rs = tdb.selectTable(sql, null);
	  
	  while (rs.next()) {
/*
		  tag.append(createHiddenTag(rs.getString("利用者コード"), 
		                  rs.getString("取引先コード"), 
		                  rs.getString("種別区分"), 
		                  cnt));
*/
          tag.append(createHiddenTag(rs.getString("利用者コード"),  
				                     cnt));
		  cnt++;
	  }
	  
	  rs.close();

	} catch (Exception e) {
	}

	return XysfljGenericRules.exchangeOutString(tag);
  }

  protected String createGetHimodukeTbl() {
	String s = "";
	
	s += "SELECT DISTINCT 利用者コード ";
	s += "FROM   UPM_利用者_取引";
/*
	s += "SELECT 利用者コード, ";
	s += "取引先コード, ";
	s += "種別区分 ";
	s += "FROM   UPM_利用者_取引_TEST";
*/	
	return s;
  }

  public StringBuffer createHiddenTag(String h_user, int j) {

	  StringBuffer tag = new StringBuffer();
	  
	  tag.append("<INPUT type =\"hidden\" name=\"h_user\" id=\"" + j + "\" value=\"" + h_user + "\" >" );

	  return tag;
  }

  protected ResultSet getCustomerType(String n, String b, String k) throws XysfljDB.DBException {
	ResultSet rs = null;
	String sql = "";

	try{
	  XysfljDB tdb = param.getDbConnection();
	  
	  if(kensaku != null && kensaku.equals("1")){
		  //SQL作成
		  sql = createSqlGetCustomerTypeK();
	  } 
	  if(before != null && before.equals("1")){
		  //SQL作成
		  sql = createSqlGetCustomerTypeB();
	  } 
	  if(next!=null && next.equals("1")){
		  //SQL作成
		  sql = createSqlGetCustomerTypeN();
	  } 
	  
//	  rs = tdb.selectTable(sql);
	  String[][] params = new String[1][2];
	  params[0][0] = Xysflj_Dbcom.DB_STR;
	  params[0][1] = usercd;

	  rs = tdb.selectTable(sql, params);
	  
	} catch (Exception e) {
	}
	return rs;
  }

  protected String createSqlGetCustomerTypeK() {
	String s = "";
	s += "SELECT DISTINCT 種別区分 ";
	s += "FROM   UPM_利用者_取引 ";
//	s += "WHERE  利用者コード = '" + usercd + "'";
	s += "WHERE  利用者コード = ? ";

	return s;
  }

  protected String createSqlGetCustomerTypeN() {
	String s = "";
	s += "SELECT DISTINCT 種別区分 ";
	s += "FROM UPM_利用者_取引 A, ";
	s += "(SELECT MIN(利用者コード) 利用者コード ";
	s += "FROM UPM_利用者_取引 ";
//	s += "WHERE 利用者コード > '" + usercd + "') B ";
	s += "WHERE 利用者コード > ?) B ";
	s += "WHERE A.利用者コード = B.利用者コード";
/*
	s += "SELECT DISTINCT 種別区分 ";
	s += "FROM UPM_利用者_取引 A, ";
	s += "(SELECT DISTINCT 利用者コード ";
	s += "FROM UPM_利用者_取引 ";
	s += "WHERE 利用者コード > '" + usercd + "' ";
	s += "AND ROWNUM = 1 ";
	s += "ORDER BY 利用者コード) B ";
	s += "WHERE A.利用者コード = B.利用者コード";
*/
	return s;
  }

  protected String createSqlGetCustomerTypeB() {
	String s = "";
	s += "SELECT DISTINCT 種別区分 ";
    s += "FROM UPM_利用者_取引 A, ";
	s += "(SELECT MAX(利用者コード) 利用者コード ";
	s += "FROM UPM_利用者_取引 ";
//	s += "WHERE 利用者コード < '" + usercd + "') B ";
	s += "WHERE 利用者コード < ?) B ";
	s += "WHERE A.利用者コード = B.利用者コード";
/*
	s += "SELECT DISTINCT 種別区分 ";
	s += "FROM UPM_利用者_取引 A, ";
	s += "(SELECT DISTINCT 利用者コード ";
	s += "FROM UPM_利用者_取引 ";
	s += "WHERE 利用者コード < '" + usercd + "' ";
	s += "AND ROWNUM = 1 ";
	s += "ORDER BY 利用者コード) B ";
	s += "WHERE A.利用者コード = B.利用者コード";
*/
	return s;
  }

  protected ResultSet getNextCustomer1(String t) throws XysfljDB.DBException {
	ResultSet rs = null;

  	try{
      XysfljDB tdb = param.getDbConnection();
	  // 次へボタン押下時
	  // 取引先コードおよび取引先名取得
	  String sql = ""; //SQL
	  sql += "SELECT ";
	  sql += "A.利用者コード, ";
	  sql += "A.取引先コード, ";
	  sql += "NVL(C.メーカー名, 'マスタにデータが存在しません') 取引先名 ";
	  sql += "FROM UPM_利用者_取引 A left outer join  (SELECT メーカーコード,  メーカー名  FROM XMM_メーカー  WHERE TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') BETWEEN 有効開始日 AND 有効終了日  AND メーカー種別 = '3') C   on  A.取引先コード = C.メーカーコード, ";
//	  sql += "FROM UPM_利用者_取引 A,";
	  sql += "(SELECT DISTINCT 利用者コード ";
	  sql += "FROM UPM_利用者_取引 ";
//	  sql += "WHERE 利用者コード > '" + usercd + "' ";
	  sql += "WHERE 利用者コード > ? ";
	  sql += "AND ROWNUM = 1 ";
	  sql += "ORDER BY 利用者コード) B ";
//	  sql += "(SELECT メーカーコード, ";
//	  sql += "メーカー名 ";
//	  sql += "FROM XMM_メーカー ";
//	  sql += "WHERE TO_DATE('" + t + "', 'YYYY/MM/DD HH24:MI:SS') BETWEEN 有効開始日 AND 有効終了日 ";
//	  sql += "AND メーカー種別 = '3') C ";
	  sql += "WHERE A.利用者コード = B.利用者コード ";
//	  sql += "AND A.種別区分 = '" + customertype + "' ";
//	  sql += "AND C.メーカーコード (+) = A.取引先コード";
//	  rs = tdb.selectTable(sql);
	  String[][] params = new String[2][2];
	  params[0][0] = Xysflj_Dbcom.DB_STR;
	  params[0][1] = t;
	  params[1][0] = Xysflj_Dbcom.DB_STR;
	  params[1][1] = usercd;
	  System.out.println("確認用3:"+t);
	  System.out.println("確認用4:"+usercd);
	  rs = tdb.selectTable(sql, params);
	} catch (Exception e) {
	}

    return rs;
  }
  
  protected ResultSet getNextCustomer2() {
	ResultSet rs = null;

	try{
	  XysfljDB tdb = param.getDbConnection();
	  // 次へボタン押下時
	  // 取引先コードおよび取引先名取得
	  String sql = ""; //SQL
	  sql += "SELECT ";
	  sql += "A.利用者コード, ";
	  sql += "A.取引先コード, ";
	  sql += "NVL(C.取引先名, 'マスタにデータが存在しません') 取引先名 ";
	  sql += "FROM UPM_利用者_取引 A left outer join (SELECT 取引先コード, 取引先コードＣＤ, 取引先名 FROM XIM_取引先＿ＣＯＰＹ２ WHERE 社コード = '000081') C on  SUBSTR(A.取引先コード, 6, 1) = C.取引先コードＣＤ  ";
//	  sql += "FROM UPM_利用者_取引 A,";
	  sql += "AND C.取引先コード = '0' || SUBSTR(A.取引先コード, 1, 5)," ;
	  sql += "(SELECT DISTINCT 利用者コード ";
	  sql += "FROM UPM_利用者_取引 ";
//	  sql += "WHERE 利用者コード > '" + usercd + "' ";
	  sql += "WHERE 利用者コード > ? ";
	  sql += "AND ROWNUM = 1 ";
	  sql += "ORDER BY 利用者コード) B ";
//	  sql += "(SELECT 取引先コード, ";
//	  sql += "取引先コードＣＤ, ";
//	  sql += "取引先名 ";
//	  sql += "FROM XIM_取引先＿ＣＯＰＹ２ ";
//	  sql += "WHERE 社コード = '000081') C ";
	  sql += "WHERE A.利用者コード = B.利用者コード ";
//	  sql += "AND A.種別区分 = '" + customertype + "' ";
//	  sql += "AND C.取引先コード || C.取引先コードＣＤ (+) = '0' || A.取引先コード " ;
//	  sql += "AND C.取引先コード (+) = '0' || SUBSTR(A.取引先コード, 1, 5)" ;
//	  sql += "AND C.取引先コードＣＤ (+) = SUBSTR(A.取引先コード, 6, 1)";
//	  rs = tdb.selectTable(sql);
	  String[][] params = new String[1][2];
	  params[0][0] = Xysflj_Dbcom.DB_STR;
	  params[0][1] = usercd;

	  rs = tdb.selectTable(sql, params);
//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	}
	return rs;
  }

  protected ResultSet getBeforeCustomer1(String t) {
	ResultSet rs = null;

	try{
	  XysfljDB tdb = param.getDbConnection();
	  // 次へボタン押下時
	  // 取引先コードおよび取引先名取得
	  String sql = ""; //SQL
	  sql += "SELECT ";
	  sql += "A.利用者コード, ";
	  sql += "A.取引先コード, ";
	  sql += "NVL(C.メーカー名 , 'マスタにデータが存在しません') 取引先名 ";
	  sql += "FROM UPM_利用者_取引 A left outer join (SELECT メーカーコード, メーカー名 FROM XMM_メーカー WHERE TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') BETWEEN 有効開始日 AND 有効終了日 AND メーカー種別 = '3') C on  A.取引先コード = C.メーカーコード, ";
//	  sql += "FROM UPM_利用者_取引 A,";
	  sql += "(SELECT DISTINCT 利用者コード ";
	  sql += "FROM UPM_利用者_取引 ";
//	  sql += "WHERE 利用者コード < '" + usercd + "' ";
	  sql += "WHERE 利用者コード < ? ";
	  sql += "AND ROWNUM = 1 ";
	  sql += "ORDER BY 利用者コード DESC) B ";
//	  sql += "(SELECT メーカーコード, ";
//	  sql += "メーカー名 ";
//	  sql += "FROM XMM_メーカー ";
//	  sql += "WHERE TO_DATE('" + t + "', 'YYYY/MM/DD HH24:MI:SS') BETWEEN 有効開始日 AND 有効終了日 ";
//	  sql += "AND メーカー種別 = '3') C ";
	  sql += "WHERE A.利用者コード = B.利用者コード ";
//	  sql += "AND A.種別区分 = '" + customertype + "' ";
//	  sql += "AND C.メーカーコード (+) = A.取引先コード";
//	  rs = tdb.selectTable(sql);
	  String[][] params = new String[2][2];
	  params[0][0] = Xysflj_Dbcom.DB_STR;
	  params[0][1] = t;
	  params[1][0] = Xysflj_Dbcom.DB_STR;
	  params[1][1] = usercd;
	  System.out.println("確認用:"+params);
	  rs = tdb.selectTable(sql, params);
//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	}
	return rs;
  }

  protected ResultSet getBeforeCustomer2() {
	ResultSet rs = null;

	try{
	  XysfljDB tdb = param.getDbConnection();
	  // 次へボタン押下時
	  // 取引先コードおよび取引先名取得
	  String sql = ""; //SQL
	  sql += "SELECT ";
	  sql += "A.利用者コード, ";
	  sql += "A.取引先コード, ";
	  sql += "NVL(C.取引先名, 'マスタにデータが存在しません') 取引先名 ";
	  sql += "FROM UPM_利用者_取引 A left outer join (SELECT 取引先コード, 取引先コードＣＤ, 取引先名 FROM XIM_取引先＿ＣＯＰＹ２ WHERE 社コード = '000081') C on  '0' || SUBSTR(A.取引先コード, 1, 5) = C.取引先コード ";
//	  sql += "FROM UPM_利用者_取引 A,";
	  sql += "AND  SUBSTR(A.取引先コード, 6, 1) = C.取引先コードＣＤ, ";
	  sql += "(SELECT DISTINCT 利用者コード ";
	  sql += "FROM UPM_利用者_取引 ";
//	  sql += "WHERE 利用者コード < '" + usercd + "'";
	  sql += "WHERE 利用者コード < ? ";
	  sql += "AND ROWNUM = 1 ";
	  sql += "ORDER BY 利用者コード DESC) B ";
//	  sql += "(SELECT 取引先コード, ";
//	  sql += "取引先コードＣＤ, ";
//	  sql += "取引先名 ";
//	  sql += "FROM XIM_取引先＿ＣＯＰＹ２ ";
//	  sql += "WHERE 社コード = '000081') C ";
	  sql += "WHERE A.利用者コード = B.利用者コード ";
//	  sql += "AND A.種別区分 = '" + customertype + "' ";
//	  sql += "AND C.取引先コード (+) = '0' || SUBSTR(A.取引先コード, 1, 5)";
//	  sql += "AND C.取引先コードＣＤ (+) = SUBSTR(A.取引先コード, 6, 1) ";
//	  rs = tdb.selectTable(sql);
	  String[][] params = new String[1][2];
	  params[0][0] = Xysflj_Dbcom.DB_STR;
	  params[0][1] = usercd;

	  rs = tdb.selectTable(sql, params);

//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	}
	return rs;
  }
  
  protected ResultSet getKensakuCustomer1(String t) {
	ResultSet rs = null;

	try{
	  XysfljDB tdb = param.getDbConnection();
	  // 次へボタン押下時
	  // 取引先コードおよび取引先名取得
	  String sql = ""; //SQL
	  sql += "SELECT ";
	  sql += "A.利用者コード, ";
	  sql += "A.取引先コード, ";
	  sql += "NVL(B.メーカー名, 'マスタにデータが存在しません') 取引先名 ";
	  sql += "FROM UPM_利用者_取引 A left outer join (SELECT メーカーコード, メーカー名 FROM XMM_メーカー WHERE TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS') BETWEEN 有効開始日 AND 有効終了日 AND メーカー種別 = '3') B on  A.取引先コード = B.メーカーコード ";
//	  sql += "FROM UPM_利用者_取引 A,";
//	  sql += "(SELECT メーカーコード, ";
//	  sql += "メーカー名 ";
//	  sql += "FROM XMM_メーカー ";
//	  sql += "WHERE TO_DATE('" + t + "', 'YYYY/MM/DD HH24:MI:SS') BETWEEN 有効開始日 AND 有効終了日 ";
//	  sql += "AND メーカー種別 = '3') B ";
//	  sql += "WHERE A.利用者コード = '" + usercd + "' ";
	  sql += "WHERE A.利用者コード = ? ";
//	  sql += "AND A.種別区分 = '" + customertype + "' ";
//	  sql += "AND B.メーカーコード (+) = A.取引先コード";
//	  rs = tdb.selectTable(sql);
	  String[][] params = new String[2][2];
	  params[0][0] = Xysflj_Dbcom.DB_STR;
	  params[0][1] = t;
	  params[1][0] = Xysflj_Dbcom.DB_STR;
	  params[1][1] = usercd;
	  rs = tdb.selectTable(sql, params);

//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	}
	return rs;
  }

  protected ResultSet getKensakuCustomer2() {
	ResultSet rs = null;

	try{
	  XysfljDB tdb = param.getDbConnection();
	  // 次へボタン押下時
	  // 取引先コードおよび取引先名取得
	  String sql = ""; //SQL
	  sql += "SELECT ";
	  sql += "A.利用者コード, ";
	  sql += "A.取引先コード, ";
	  sql += "NVL(B.取引先名, 'マスタにデータが存在しません') 取引先名 ";
	  sql += "FROM UPM_利用者_取引 A left outer join (SELECT 取引先コード, 取引先コードＣＤ, 取引先名 FROM XIM_取引先＿ＣＯＰＹ２ WHERE 社コード = '000081') B on '0' || SUBSTR(A.取引先コード, 1, 5) = B.取引先コード  AND SUBSTR(A.取引先コード, 6, 1) = B.取引先コードＣＤ ";
//	  sql += "FROM UPM_利用者_取引 A,";
//	  sql += "(SELECT 取引先コード, ";
//	  sql += "取引先コードＣＤ, ";
//	  sql += "取引先名 ";
//	  sql += "FROM   XIM_取引先＿ＣＯＰＹ２ ";
//	  sql += "WHERE  社コード = '000081') B ";
//	  sql += "WHERE A.利用者コード = '" + usercd + "'";
	  sql += "WHERE A.利用者コード = ? ";
//	  sql += "AND A.種別区分 = '" + customertype + "'";
//	  sql += "AND B.取引先コード (+) = '0' || SUBSTR(A.取引先コード, 1, 5)";
//	  sql += "AND B.取引先コードＣＤ (+) = SUBSTR(A.取引先コード, 6, 1)";
//	  rs = tdb.selectTable(sql);
	  String[][] params = new String[1][2];
	  params[0][0] = Xysflj_Dbcom.DB_STR;
	  params[0][1] = usercd;

	  rs = tdb.selectTable(sql, params);

//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	}
	return rs;
  }
  protected boolean chkUserCd(String t) {

	String s_rs = "";
	String customer_rs = "";
	String t2 = "";
	
	try{
		
		XysfljDB tdb = param.getDbConnection();
		Connection cn = tdb.getConnection();
		t2 = t.substring(0, 4) + t.substring(5, 7) + t.substring(8, 10); 
		
		CallableStatement cstmt = null;           // SQL Statement
//		cstmt = cn.prepareCall("begin OSM111.smzzop101.main(?, ?, ?, ?); end;");	// Oracle Stored Function Call
//		cstmt = cn.prepareCall("begin smzzop101.main(?, ?, ?, ?); end;");	// Oracle Stored Function Call
		cstmt = cn.prepareCall("begin UPBBOP902.main(?, ?, ?, ?); end;");	// Oracle Stored Function Call
		cstmt.setString (1, usercd);						
		cstmt.setString (2, t2);
		cstmt.registerOutParameter (3, Types.CHAR); 								
		cstmt.registerOutParameter (4, Types.CHAR); 
		cstmt.execute();						     // 実行		
		s_rs = cstmt.getString(3);					 // 結果取得
		System.out.println("user:" + usercd + " s_rs:" + s_rs); // debug
        if(!s_rs.equals("00")){
        	return false;
        }

	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	return true;
  }
  
  protected boolean createData(String t) {

    try{

	  XysfljDB tdb = param.getDbConnection();

	  //利用者コード存在有無
	  if(!chkUserCd(t)){
		  err_flg = "1";
		  alertmsg = "存在しない利用者コードです。";
		  err_type = "7";
		  param.addMessage("存在しない利用者コードです。");
		  for(int i=0; i<10; i++){
		      customer_cd[i] = customer[i];
		      customer_nm[i] = "";
		  }
		  return false;
	  }

	  //取引先マスタ存在チェック
	  if(!checkTbl(t)){
	  	  err_flg = "1";
	      return false;
	  }
	  
	  //紐付けマスタ存在チェック
//	  chkHimodukeTbl();
	  
	  //紐付けマスタ行ロックを取得
	  if(!getHimodukeTblLock()){
		tdb.rollback();
		return false;
	  }
	  
	  //紐付けマスタ削除
	  if(!delHimodukeTbl()){
		tdb.rollback();
		return false;
	  }
	  
	  //紐付けマスタデータ挿入
	  if(!insHimodukeTbl(t)){
	  	tdb.rollback();
	  	return false;
	  }
	  
	  //コミット
	  tdb.commit();
	  
    } catch (Exception e) {
	}
	
	return true;
  }

  protected boolean checkTbl(String t) {
	ResultSet rs = null;
	int cust_cnt = 0;
	int num = 0;
	boolean[] cust_chk = new boolean[10];
	String[] sql = new String[10]; //SQL
	String merr_flg = "0";
	int msg_i = 0;
	try{

      //取引先コードが何件入力されているかカウントする
      for(int i=0; i<10; i++){
		if(customer[i] != null && !customer[i].equals("")){
			cust_chk[i] = true;
			cust_cnt++;
		} else {
			cust_chk[i] = false;
		}
      }
	  XysfljDB tdb = param.getDbConnection();
	  //取引先種別が『1』の場合
	  if(customertype != null && customertype.equals("1")){
	  	for(int i=0; i<10; i++){
			// メーカーコードチェック
			sql[i] = createChkSqlMaker(t, i);
	  	}
      //取引先種別が『2』の場合
	  } else if(customertype != null && customertype.equals("2")) {
		for(int i=0; i<10; i++){
			// 取引先コードチェック
			sql[i] = createChkSqlCustomer(i);
		}
	  } 
      
	  for(int i=0; i<10; i++){
	  	//sqlがnullまたは""の場合は入力sqlを発行しない
	  	if(sql[i]!=null && !sql[i].equals("")){
//			rs = tdb.selectTable(sql[i]);
			String[][] params = new String[1][2];
			params[0][0] = Xysflj_Dbcom.DB_STR;
			params[0][1] = customer[i];

			rs = tdb.selectTable(sql[i], params);
/*
			while (rs.next()) {
				num = rs.getInt("num");
			}
*/
			if(!rs.next()){
				customer_cd[i] = customer[i];
				customer_nm[i] = "マスタに存在しません";
				alertmsg = "入力された取引先コードがマスタに存在しません。";
				err_type = "6";
				err_cust_no[i] = "1";
				msg_i = i + 1;
				param.addMessage("入力された取引先コード" + msg_i + "がマスタに存在しません。");
				merr_flg = "1";
			} else {
				customer_cd[i] = customer[i];
				// **** E10K移行時変更対応(2006/06/08) ***
				customer_nm[i] = Xysflj_Common.getStrCvt( rs.getString("取引先名") );
//				customer_nm[i] = rs.getString("取引先名");
				// **** END ******************************
			}
			rs.close();
/*
			if(num==0){
				customer_cd[i] = customer[i];
				customer_nm[i] = "取引先マスタに存在しません";
				alertmsg = "入力された取引先コードがマスタに存在しません。";
				merr_flg = "1";
			} else {
				customer_cd[i] = customer[i];
				customer_nm[i] = customernm[i];
			}
*/
	  	} else {
			customer_cd[i] = "";
			customer_nm[i] = "";
	  	}
	  }
      
      //インスタンス破棄
//      rs = null;
//      sql = null;
//	  num = 0;
/*
      if(num != cust_cnt){
      	alertmsg = "入力された取引先コードがマスタに存在しません。";

      }
*/ 
/*
      // 紐付けテーブルチェック
      sql = createChkSqlHimoduke();

	  rs = tdb.selectTable(sql);

	  while (rs.next()) {
		  num = rs.getInt("num");
	  }
	  
	  if(num != cust_cnt){
		return false;
	  }
*/
	  //インスタンス破棄
	  rs = null;
	  sql = null;
	  num = 0;
	   
	  if(merr_flg=="1"){
	  	return false;
	  }
	   
      return true;

//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	  // 仮にfalseを返すが最終的にはエラー処理を入れること
	  return false;
	}
  }
  
  protected String createChkSqlCustomer(int i) {
  	String s = "";
	//取引先コードがnullの場合
	if(customer[i]==null || customer[i].equals("")){
		s="";
		return s;
	}
  	
	s += "SELECT 取引先名 ";
	s += "FROM   XIM_取引先＿ＣＯＰＹ２ ";
	s += "WHERE  社コード = '000081' AND ";
//	s += "取引先コード ||  取引先コードＣＤ = '0' || '" + customer[i] + "'";
	s += "取引先コード ||  取引先コードＣＤ = '0' || ? ";

/*
	s += "SELECT COUNT(*) num ";
	s += "FROM   XIM_取引先＿ＣＯＰＹ２ ";
	s += "WHERE  社コード = '000081' AND (";
	for(int i=0; i<9; i++){
	  s += "(取引先コード || 取引先コードＣＤ = '0' || '" + customer[i] + "') OR"; 
	}
	
	s += "(取引先コード || 取引先コードＣＤ = '0' || '" + customer[9] + "'))";
*/
	return s;
  }
  
  protected String createChkSqlMaker(String t, int i) {
	String s = "";
	//取引先コードがnullの場合
	if(customer[i]==null || customer[i].equals("")){
		s="";
		return s;
	}
	
	s += "SELECT メーカー名 取引先名 ";
	s += "FROM XMM_メーカー ";
	s += "WHERE TO_DATE('" + t + "', 'YYYY/MM/DD HH24:MI:SS') BETWEEN 有効開始日 AND 有効終了日 ";
//	s += "AND メーカーコード = '" + customer[i] + "' ";
	s += "AND メーカーコード = ? ";
	s += "AND メーカー種別 = '3'";
/*
	s += "SELECT COUNT(*) num ";
	s += "FROM XMM_メーカー ";
	s += "WHERE TO_DATE('" + t + "') BETWEEN 有効開始日 AND 有効終了日 ";
	s += "AND メーカーコード IN (";
	for(int i=0; i<9; i++){
	  s += "'" + customer[i] + "', ";
	}
	
	s += "'" + customer[9] + "')";
*/
	return s;
  }
/*
  protected String createChkSqlHimoduke() {
	String s = "";
	s += "SELECT COUNT(*) num ";
	s += "FROM UPM_利用者_取引 ";
	s += "WHERE 利用者コード = '" + usercd + "' ";
	s += "AND 取引先コード IN (";
	for(int i=0; i<9; i++){
	  s += "'" + customer[i] + "', ";
	}
	
	s += "'" + customer[9] + "')";

	return s;
  }
*/
/*
  protected void chkHimodukeTbl() {
	ResultSet rs = null;
	int cust_cnt = 0;
	int num = 0;
	try{

	  XysfljDB tdb = param.getDbConnection();
	  String sql = ""; //SQL

	  // 取引先コードチェック
	  sql = createChkHimodukeSql();
	  
	  rs = tdb.selectTable(sql);
      
	  while ( rs.next()) {
		  num = rs.getInt("num");
	  }
	  
	  if(num == 0){
	  	new_flg = "1";
	  }
      
	  //インスタンス破棄
	  rs = null;
	  sql = null;
	  num = 0;
	  
	  return;
//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	  // 仮にfalseを返すが最終的にはエラー処理を入れること
	  return;
	}
  }
*/
/*
  protected String createChkHimodukeSql() {
  	
	String s = "";
	s += "SELECT COUNT(*) num";
	s += "FROM UPM_利用者_取引 ";
	s += "WHERE 利用者コード = '" + usercd + "' ";

	return s;
  }
*/

  protected boolean getHimodukeTblLock() {
	ResultSet rs = null;
	int cust_cnt = 0;
	int num = 0;
	try{

	  XysfljDB tdb = param.getDbConnection();
	  String sql = ""; //SQL

	  // 取引先コードチェック
      sql = createLockSql();
	  
//	  rs = tdb.selectTable(sql);
	  String[][] params = new String[1][2];
	  params[0][0] = Xysflj_Dbcom.DB_STR;
	  params[0][1] = usercd;

	  rs = tdb.selectTable(sql, params);

	  //インスタンス破棄
	  rs = null;
	  sql = null;
	  num = 0;
	  
	  return true;
//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	  // 仮にfalseを返すが最終的にはエラー処理を入れること
	  return false;
	}
  }


  protected String createLockSql() {
  	
	String s = "";
	s += "SELECT 取引先コード ";
	s += "FROM UPM_利用者_取引 ";
//	s += "WHERE 利用者コード = '" + usercd + "' ";
	s += "WHERE 利用者コード = ? ";
	s += "FOR UPDATE NOWAIT ";

	return s;
  }

  protected boolean delHimodukeTbl() {
	ResultSet rs = null;
	int cust_cnt = 0;

	try{

	  XysfljDB tdb = param.getDbConnection();
	  String sql = ""; //SQL

	  // 取引先コードチェック
	  sql = createDelHimodukeTblSql();

	  // ******************* E10K移行 01修正***********************
//	  rs = tdb.selectTable(sql);
//	  cust_cnt = tdb.updateTable(sql);
	  String[][] params = new String[1][2];
	  params[0][0] = Xysflj_Dbcom.DB_STR;
	  params[0][1] = usercd;

	  cust_cnt = tdb.updateTable(sql, params);
      // ******************* E10K移行 01修正 END ******************
	  //インスタンス破棄
	  rs = null;
	  sql = null;
	  
	  return true;
//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	  return false;
	}
  }

  protected String createDelHimodukeTblSql() {
  	
	String s = "";
	s += "DELETE FROM UPM_利用者_取引 ";
//	s += "WHERE 利用者コード = '" + usercd + "'";
	s += "WHERE 利用者コード = ? ";

	return s;
  }

  protected boolean insHimodukeTbl(String t) {
	int cust_cnt = 0;
	int r = 0;
	String today = "";

	today = getToday();
	try{

	  XysfljDB tdb = param.getDbConnection();
	  String sql = ""; //SQL

      for(int i=0; i<10; i++){
      	if(customer[i] != null && !customer[i].equals("")){
      		//重複した取引先コードはINSERTしない
      		for(int j=0; j<=i; j++){
      			if(customer[j].equals(customer[i]) && j!=i){
      				break;
      			}
      			if(j==i){
				    // UPM_利用者_取引へデータ挿入
				    sql = createInsHimodukeTblSql(t, i);
//				    r = tdb.updateTable(sql);
					String[][] params = new String[6][2];
					params[0][0] = Xysflj_Dbcom.DB_STR;
					params[0][1] = usercd;
					params[1][0] = Xysflj_Dbcom.DB_STR;
					params[1][1] = customer[i];
					params[2][0] = Xysflj_Dbcom.DB_STR;
					params[2][1] = customertype;
					params[3][0] = Xysflj_Dbcom.DB_STR;
					params[3][1] = login_user_id;						
					params[4][0] = Xysflj_Dbcom.DB_STR;
					params[4][1] = login_user_id;		
					params[5][0] = Xysflj_Dbcom.DB_STR;
					params[5][1] = login_user_id;
					
				    r = tdb.updateTable(sql, params);
      			}
      		}
      	}
      }
      
	  //インスタンス破棄
	  sql = null;
	  
	  return true;
//	  } catch (SQLException e) {
	  //rs.nextのエラー
//	  throw new XysfljDB.ResultAnalyzeException(e);
	} catch (Exception e) {
	  return false;
	}
  }
  
  protected String createInsHimodukeTblSql(String t, int custmer_nm) {
	
	String s = "";
	s += "INSERT INTO UPM_利用者_取引 ";
	s += "(利用者コード, ";
	s += "取引先コード, ";
	s += "種別区分, ";
	s += "作成者ＩＤ, ";
	s += "作成日時, ";
	s += "最終更新者ＩＤ, ";
	s += "最終更新日時, ";
	s += "最終更新ログインＩＤ ";
	s += ") ";
	s += "VALUES ";
//	s += "('" + usercd + "', ";
//	s += "'" + customer[custmer_nm] + "', ";
//	s += "'" + customertype + "', ";
//	s += "'" + login_user_id + "', ";
	s += "(? , ";
	s += "? , ";
	s += "? , ";
	s += "? , ";
//	s += "TO_DATE('? ', 'YYYY/MM/DD HH24:MI:SS'), ";
	s += "TO_DATE('" + t + "', 'YYYY/MM/DD HH24:MI:SS'), ";
//	s += "'" + login_user_id + "', ";
	s += "? , ";
//	s += "TO_DATE('? ', 'YYYY/MM/DD HH24:MI:SS'), ";
	s += "TO_DATE('" + t + "', 'YYYY/MM/DD HH24:MI:SS'), ";
//	s += "'" + login_user_id + "')";
	s += "? )";
/*
	s += "INSERT INTO UPM_利用者_取引 ";
	s += "(利用者コード, ";
	s += "取引先コード, ";
	s += "種別区分, ";
	s += "作成者ＩＤ, ";
	s += "作成日時, ";
	s += "最終更新者ＩＤ, ";
	s += "最終更新日時, ";
	s += "最終更新ログインＩＤ ";
	s += ") ";
	s += "VALUES ";
	s += "('" + usercd + "', ";
	s += "'" + customer[custmer_nm] + "', ";
	s += "'" + customertype + "', ";
	s += "'SYS', ";
	s += "TO_DATE('" + t + "', 'YYYY/MM/DD HH24:MI:SS'), ";
	s += "'SYS', ";
	s += "TO_DATE('" + t + "', 'YYYY/MM/DD HH24:MI:SS'), ";
	s += "'SYS')";
*/
	return s;
  }
/*
  protected String getCustomerCd(String t, int i) {
	ResultSet rs = null;
	int cust_cnt = 0;
	String s = "";
	try{
	    XysfljDB tdb = param.getDbConnection();
	  	
		s += "SELECT 取引先名 ";
		s += "FROM XIM_取引先＿ＣＯＰＹ２ ";
		s += "WHERE 社コード = '000081' ";
		s += "AND 取引先コード ||  取引先コードＣＤ = '0' || '" + customer[i] + "'";
		s += "UNION ";
		s += "SELECT メーカー名 取引先名 ";
		s += "FROM XMM_メーカー ";
		s += "WHERE メーカーコード = '" + customer[i] + "' ";
		s += "AND TO_DATE('" + t + "', 'YYYY/MM/DD HH24:MI:SS') BETWEEN 有効開始日 AND 有効終了日 ";
		s += "AND メーカー種別 = '3'";
		
	} catch (Exception e) {
	}
	return s;
  }
*/
}