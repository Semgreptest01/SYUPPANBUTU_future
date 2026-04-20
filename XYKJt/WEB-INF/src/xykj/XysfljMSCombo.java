package xykj;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class XysfljMSCombo extends XysfljComboTag{
	final static public int TYPE = XysfljParameters.ESSENTIAL;
	
	final private String tableName;
	final private String colCode;
	final private String colName;

	final static String TOTAL = "";
	public XysfljMSCombo (XysfljParameters p,String comboName,String tName,String code,String name){
		super(comboName,p);
		asManagementSection();
		tableName = tName;
		colCode = code;
		colName = name;
	}
	
	protected void getData()
		throws XysfljDB.DBException{
		try{
			XysfljDB tdb = param.getDbConnection();
			String userMs = param.getLoginUserMS();

			String sql = "";
			sql += " SELECT   " + colCode    ;
			sql += "         ," + colName    ;
			sql += " FROM   " + tableName    ;
			sql += " WHERE    DR = '000000' ";
			if(false == userMs.equals("")){
				sql += "      AND UNEI_DIV = '" + userMs + "' ";
			}
			sql += " ORDER BY OUT_SEQ       ";
//			ResultSet rs = tdb.selectTable(sql);
			ResultSet rs = tdb.selectTable(sql, null);
			while(null != rs && rs.next()){
				comboElements.put(rs,colCode,colName);
			}
		}
		catch(SQLException e){
			//rs.nextのエラー
			throw new XysfljDB.ResultAnalyzeException(e);
		}
	}
}

