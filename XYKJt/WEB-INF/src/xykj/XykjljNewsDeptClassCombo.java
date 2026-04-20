package xykj;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class XykjljNewsDeptClassCombo extends XysfljCommonCombo{
//	final static public String NAME = "select_DEPT/CLASS";
	final static public String NAME = "select_deptclass";
	final static public int TYPE = XysfljParameters.ESSENTIAL;

	public XykjljNewsDeptClassCombo (XysfljParameters p){
		super(p, NAME);
		setOnChange("clear1(document.form1);news_dept_subclass_search(1);");
		setStyle("width : 190px;");

		comboElements.put("00000","");

		try{
			String sql = "";
			sql += " SELECT   A.ＤＥＰＴ || B.ＣＬＡＳＳ DEPTCLASS_CD       ";
			sql += "         ,A.ＤＥＰＴ名 || '/' || B.ＣＬＡＳＳ名 DEPTCLASS_NAME  ";
			sql += " FROM     UPM_新聞_ＤＥＰＴ A ,";
			sql += "          UPM_新聞_ＣＬＡＳＳ B ";
			sql += " WHERE    A.ＤＥＰＴ = B.ＤＥＰＴ    ";
			sql += " ORDER BY B.ＤＥＰＴ ,              ";
			sql += "          B.ＣＬＡＳＳ              ";
//			getData(sql,"DEPTCLASS_CD","DEPTCLASS_NAME");
			getData(sql,"DEPTCLASS_CD","DEPTCLASS_NAME", null);
		}
		catch(XysfljDB.DBException e){
			//データ取得でのDBエラー
			param.addMessageByCode(1000);
			param.executeErrorRoutine(e);
		}
	}

}

