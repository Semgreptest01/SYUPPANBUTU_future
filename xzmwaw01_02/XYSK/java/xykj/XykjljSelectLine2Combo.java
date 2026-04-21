package xykj;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Vector;
import java.util.Enumeration;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class XykjljSelectLine2Combo extends XysfljComboTag{
	final static public String NAME = "select_selectline2";
	final static public int TYPE = XysfljParameters.ESSENTIAL;

	final public static String Init   	= "00";
	final public static String Stock_num  = "1";
	final public static String Sale_num   = "2";
	final public static String Sale_rate	= "3";
	final public static String Book_store = "4";
	final public static String Piece_num  = "5";
	final public static String Piece_rate  = "6";
	final public static String Reurn_num  = "7";
	final public static String Missing_num  = "8";
	final public static String Stock_psa  = "9";
	final public static String Sale_psa  = "10";

	final public static String Stock_num_nn  = "仕入数";
	final public static String Sale_num_nn   = "販売数";
	final public static String Sale_rate_nn	= "販売率";
	final public static String Book_store_nn = "配本店";
	final public static String Piece_num_nn  = "売切店数";
	final public static String Piece_rate_nn  = "売切店発生率";
	final public static String Reurn_num_nn  = "返品数";
	final public static String Missing_num_nn  = "欠品数";
	final public static String Stock_psa_nn  = "仕入PSA";
	final public static String Sale_psa_nn  = "販売PSA";

	public XykjljSelectLine2Combo (XysfljParameters p){
		super(NAME,p);
        setStyle("width : 140px;");

   		String SelectLine1 = param.getValue(XykjljSelectLine1Combo.NAME);

//		setOnChange("clear1(document.form1);pop_div(this.form);");

		comboElements.put(Init,"");
		if (!SelectLine1.equals(Stock_num_nn)) {
			comboElements.put(Stock_num,Stock_num_nn);
		}
		if (!SelectLine1.equals(Sale_rate_nn)) {
			comboElements.put(Sale_rate,Sale_rate_nn);
		}
		if (!SelectLine1.equals(Book_store_nn)) {
			comboElements.put(Book_store,Book_store_nn);
		}
		if (!SelectLine1.equals(Piece_num_nn)) {
			comboElements.put(Piece_num,Piece_num_nn);
		}
		if (!SelectLine1.equals(Piece_rate_nn)) {
			comboElements.put(Piece_rate,Piece_rate_nn);
		}
		if (!SelectLine1.equals(Reurn_num_nn)) {
			comboElements.put(Reurn_num,Reurn_num_nn);
		}
		if (!SelectLine1.equals(Missing_num_nn)) {
			comboElements.put(Missing_num,Missing_num_nn);
		}
		if (!SelectLine1.equals(Stock_psa_nn)) {
			comboElements.put(Stock_psa,Stock_psa_nn);
		}
		if (!SelectLine1.equals(Sale_psa_nn)) {
			comboElements.put(Sale_psa,Sale_psa_nn);
		}

	}

}

