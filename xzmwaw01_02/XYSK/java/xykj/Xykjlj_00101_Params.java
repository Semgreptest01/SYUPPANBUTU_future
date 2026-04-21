/*
 *
 *  修正履歴
 *  L001  2006/06/13 K.Sasaki   対象文字コード修正 EUC_JP→EUC-JP
 *
 */
package xykj;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public class Xykjlj_00101_Params extends XysfljParameters{

    //配置するＣｏｍｂｏＢｏｘ
//    public XykjljPUBLISHCOMCombo publishcomcombo;
    public String status = null ;
    public String usercd = null ;
    public String customertype = null ;
    public String customercd1  = null ;
	public String customercd2  = null ;
	public String customercd3  = null ;
	public String customercd4  = null ;
	public String customercd5  = null ;
	public String customercd6  = null ;
	public String customercd7  = null ;
	public String customercd8  = null ;
	public String customercd9  = null ;
	public String customercd10 = null ;  

	public String customernm1  = null ;
	public String customernm2  = null ;
	public String customernm3  = null ;
	public String customernm4  = null ;
	public String customernm5  = null ;
	public String customernm6  = null ;
	public String customernm7  = null ;
	public String customernm8  = null ;
	public String customernm9  = null ;
	public String customernm10 = null ;    

    public String selectline3 = null ;
    public String inout = null;
    public int NO_DATA_FLG = 0;
    final public static int COMMAND_NO_DATA = 1;
    final public static int COMMAND_DATA    = 0;

	private HttpServletRequest request;

  public Xykjlj_00101_Params(HttpServletRequest requestP){
    super("Sxykj00101",requestP);
	this.request = requestP;

    //出版社
//    publishcomcombo = new XykjljPUBLISHCOMCombo(this);
//    setControl(publishcomcombo);
	try{
		
	    if(getParameter("usercd") == null){
			usercd = "";
	    } else {
			usercd       = getParameter("usercd");
	    }
		if(getParameter("customertype") == null){
			customertype = "1";
		} else {
			customertype = getParameter("customertype");
		}
	
		customercd1  = getParameter("customercd1");
		customercd2  = getParameter("customercd2");
		customercd3  = getParameter("customercd3");
		customercd4  = getParameter("customercd4");
		customercd5  = getParameter("customercd5");
		customercd6  = getParameter("customercd6");
		customercd7  = getParameter("customercd7");
		customercd8  = getParameter("customercd8");
		customercd9  = getParameter("customercd9");
		customercd10 = getParameter("customercd10");

		customernm1  = getParameter("customernm1");
		customernm2  = getParameter("customernm2");
		customernm3  = getParameter("customernm3");
		customernm4  = getParameter("customernm4");
		customernm5  = getParameter("customernm5");
		customernm6  = getParameter("customernm6");
		customernm7  = getParameter("customernm7");
		customernm8  = getParameter("customernm8");
		customernm9  = getParameter("customernm9");
		customernm10 = getParameter("customernm10");

	    inout = getParameter("inout");
	}catch(UnsupportedEncodingException UE){}

      //検索前はデータ無に設定
    NO_DATA_FLG = COMMAND_NO_DATA;

  }

  /**
   * 日本語対応文字列取得<BR>
   * サーブレットは日本語対応していないため、明示的に画面上で使用されているコードから
   * @param       keyNameP    取得するパラメータ名
   * @return      String      取得した文字列
   * @exception   UnsupportedEncodingException    指定したエンコードをOSがサポートしていない場合に発生
   *                                              (クリティカルなエラー)。
   */

  public String getParameter(String keyNameP) throws UnsupportedEncodingException{

	  //Windows環境
//	  request.setCharacterEncoding("Shift_JIS");
	  //Solaris環境
//	  request.setCharacterEncoding("EUC_JP");
//	  request.setCharacterEncoding("UTF-8");

	  // 変換前の文字列取得
	  String engString = request.getParameter(keyNameP);

	  // Shift-JISからUniCodeへ変換します。
//	  return stringToJpn(engString);
	  return engString;
  }

  /**
   * 日本語変換<BR>
   * SJIS文字列を日本語へ変換します。
   * @param   orgString   変換する文字列
   * @return  String      日本語変換した文字列
   * @exception   UnsupportedEncodingException    指定したエンコードをOSがサポートしていない場合に発生
   *                                              (クリティカルなエラー)。
   */

  protected String stringToJpn(String orgString) throws UnsupportedEncodingException{

	  // Shift-JISからUniCodeへ変換します。
	  return orgString == null ? null
	                     //Windows環境
//	                     : new String(orgString.getBytes("8859_1"), "Shift_JIS"); 
	                     //Solaris環境
						 : new String(orgString.getBytes("EUC-JP"), "EUC-JP"); 
//						 : new String(orgString.getBytes("EUC_JP"), "EUC_JP");
	  
  }

}