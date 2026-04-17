/*
 *
 *  修正履歴
 *  L001  2006/06/08 K.Sasaki   文字化け対応 名称関連取得時にメソッドXysflj_Common.getStrCvt()を介す
 *
 */
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
public class XysfljDRCombo extends XysfljComboTag{
	final static public String NAME = "DRSection";
	final static public int TYPE = XysfljParameters.ESSENTIAL;

	private String jsDRList = "";
	
	final private String tableName;
	final private String colCode;
	final private String colName;
	final private String msColName;

	public XysfljDRCombo (XysfljParameters p,String tName,String code,String name,String ms){
		super(NAME,p);
		tableName = tName;
		colCode = code;
		colName = name;
		msColName = ms;
		setOnChange("changeDRSection(this.form);");
	}
	public void createElements(String ms)
		throws XysfljDB.DBException{
		getData(ms);
	}
	public void getData(String ms)
		throws XysfljDB.DBException{
		try{
			XysfljDB tdb = param.getDbConnection();
			String sql = "";
			sql += " SELECT  "  + colCode          ;
			sql += "         ," + colName          ;
			sql += "         ," + msColName        ;
			sql += "         ,YUKO_SDATE          ";
			sql += "         ,YUKO_EDATE          ";
			sql += " FROM    " + tableName;
			sql += " WHERE   " + colCode +" <> '000000'      ";
			sql += "          AND TEN = '000000'  ";
			sql += " ORDER BY " + msColName + ",OUT_SEQ," + colCode ;
//			ResultSet rs = tdb.selectTable(sql);
			ResultSet rs = tdb.selectTable(sql, null);

			StringBuffer sb = new StringBuffer();
			String beforDiv = "";
			int optIndex = 1;
			while(null != rs && rs.next()){
				String code = rs.getString(colCode);
				// **** E10K移行時変更対応(2006/06/08) ***
				String name = Xysflj_Common.getStrCvt( rs.getString(colName) );
//				String name = rs.getString(colName);
				// **** END ******************************
				String div = rs.getString(msColName);
				String startDate = rs.getString("YUKO_SDATE");
				String endDate = rs.getString("YUKO_EDATE");
				
				if(false == beforDiv.equals(div)){
					if(false == beforDiv.equals("")){
						sb.append("}\r\nelse ");
					}
					optIndex = 1;
					sb.append("if(form.ManagementSection.options[form.ManagementSection.selectedIndex].value == \"" + div + "\"){\r\n");
				}
				sb.append("\tform.DRSection.options[" + optIndex + "] = new Option(\"" + name + "\",\"" + code + "\");\r\n");

				DRData o = new DRData(code,name,startDate,endDate);
				if(true == ms.equals(div)){
					comboElements.put(code,o);
				}
				beforDiv = div;
				optIndex++;
			}
			if(false == beforDiv.equals("")){
				sb.append("}\r\n");
				jsDRList = XysfljGenericRules.exchangeOutString(sb);
			}
		}
		catch(SQLException e){
			//rs.nextのエラー
			throw new XysfljDB.ResultAnalyzeException(e);
		}
	}
	
	public String getDRList(){
		return jsDRList;
	}
	public class DRComboData extends ComboData{
		public DRComboData(){}
		public String getName(String v){
			DRData o = (DRData)get(v);
			return o.getName();
		}
		public String getStartDate(String v){
			DRData o = (DRData)get(v);
			return o.getStartDate();
		}
		public String getEndDate(String v){
			DRData o = (DRData)get(v);
			return o.getEndDate();
		}
	}

	static public class DRData{
		private String code = "";
		private String name = "";
		private String startDate = "";
		private String endDate = "";
		public DRData(String c, String n, String sd, String ed){
			code = c;
			name = n;
			startDate = sd;
			endDate = ed;
		}
		public String getCode(){
			return code;
		}
		public String getName(){
			return name;
		}
		public String getStartDate(){
			return startDate;
		}
		public String getEndDate(){
			return endDate;
		}
		public String toString(){
			return getName();
		}
	}
}
