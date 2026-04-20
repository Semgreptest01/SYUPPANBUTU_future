package xykj;

public class XykjljNewsSUBCLASSCombo extends XysfljCommonCombo{
	static public String NAME = "select_subclass";
	public XykjljNewsSUBCLASSCombo(XysfljParameters p){
		super(p,NAME);
		setOnChange("clear2(document.form1);news_subclass_issueform_search(1);");
        setStyle("width : 190px;");

		comboElements.put("00","");

		try{
			String selectedDept = param.getValue(XykjljNewsDeptClassCombo.NAME);
			String sql = "";
			sql += " SELECT  ＳＵＢＣＬＡＳＳ subclass_cd             ";
			sql += "        ,ＳＵＢＣＬＡＳＳ名 subclass_nn            ";
			sql += " FROM    UPM_新聞_ＳＵＢＣＬＡＳＳ              ";
//			sql += " WHERE    ＤＥＰＴ || ＣＬＡＳＳ = '" + selectedDept + "' ";
			sql += " WHERE    ＤＥＰＴ || ＣＬＡＳＳ = ? ";
			sql += " ORDER BY ＤＥＰＴ            ";
			sql += "        ,ＣＬＡＳＳ            ";
			sql += "        ,ＳＵＢＣＬＡＳＳ            ";
//			getData(sql,"subclass_cd","subclass_nn");
			String[][] params = new String[1][2];
			params[0][0] = Xysflj_Dbcom.DB_STR;
			params[0][1] = selectedDept;

			getData(sql,"subclass_cd","subclass_nn", params);
		}
		catch(XysfljDB.DBException e){
			//データ取得でのDBエラー
			param.addMessageByCode(1000);
			param.executeErrorRoutine(e);
		}
		//comboElements.put("","その他");
	}
}
