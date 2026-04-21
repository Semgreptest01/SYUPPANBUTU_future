package xykj;

public class XykjljNewsISSUEFORMCombo extends XysfljCommonCombo{
	static public String NAME = "select_issueform";
	public XykjljNewsISSUEFORMCombo(XysfljParameters p){
		super(p,NAME);
		setOnChange("clear2(document.form1);news_issueform_product_search(1);");
        setStyle("width : 190px;");

		comboElements.put("0","");
        
		try{
    		String selectedDept = param.getValue(XykjljNewsDeptClassCombo.NAME);
			String selectedSubclass = param.getValue(XykjljNewsSUBCLASSCombo.NAME);
			String sql = "";
			sql += " SELECT DISTINCT  A.発行形態 issueform_cd ";
			sql += "        ,A.発行形態名 issueform_nn        ";
			sql += " FROM    UPM_新聞_発行形態 A              ";
			sql += "        ,UPM_新聞_ＤＥＰＴ別商品 B        ";
			sql += " WHERE   A.発行形態 = B.発行形態  ";
//			sql += " AND     B.ＤＥＰＴ || B.ＣＬＡＳＳ = '" + selectedDept + "' ";
//			sql += " AND     B.ＳＵＢＣＬＡＳＳ = '" + selectedSubclass + "' ";
			sql += " AND     B.ＤＥＰＴ || B.ＣＬＡＳＳ = ? ";
			sql += " AND     B.ＳＵＢＣＬＡＳＳ = ? ";
			sql += " ORDER BY A.発行形態            ";
			sql += "        ,A.発行形態名           ";
//			getData(sql,"issueform_cd","issueform_nn");

			String[][] params = new String[2][2];
			params[0][0] = Xysflj_Dbcom.DB_STR;
			params[0][1] = selectedDept;
			params[1][0] = Xysflj_Dbcom.DB_STR;
			params[1][1] = selectedSubclass;

			getData(sql,"issueform_cd","issueform_nn", params);

		}
		catch(XysfljDB.DBException e){
			//データ取得でのDBエラー
			param.addMessageByCode(1000);
			param.executeErrorRoutine(e);
		}
		//comboElements.put("","その他");
	}
}
