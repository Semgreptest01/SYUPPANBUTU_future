package xykj;

public class XykjljNewsProductCombo extends XysfljCommonCombo{
	static public String NAME = "select_product";
	public XykjljNewsProductCombo(XysfljParameters p){
		super(p,NAME);
		setOnChange("button_sts_chg();");
		setStyle("width : 340px;");

		comboElements.put("000000","");
		
		try{
			String selectedDept = param.getValue(XykjljNewsDeptClassCombo.NAME);
			String selectedSubclass = param.getValue(XykjljNewsSUBCLASSCombo.NAME);
			String selectedIssueform = param.getValue(XykjljNewsISSUEFORMCombo.NAME);
			String sql = "";
			sql += " SELECT  ＤＥＰＴ                 ";
			sql += "        ,ＣＬＡＳＳ               ";
			sql += "        ,ＳＵＢＣＬＡＳＳ                ";
			sql += "        ,発行形態              ";
			sql += "        ,商品コード  product_cd          ";
			sql += "        ,商品名      product_nn       ";
			sql += " FROM    UPM_新聞_ＤＥＰＴ別商品 ";
//			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = '" + selectedDept + "' ";
//			sql += " AND     ＳＵＢＣＬＡＳＳ = '" + selectedSubclass + "' ";
//			sql += " AND     発行形態 = '" + selectedIssueform + "' ";
			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = ? ";
			sql += " AND     ＳＵＢＣＬＡＳＳ = ? ";
			sql += " AND     発行形態 = ? ";
			sql += " GROUP BY  ＤＥＰＴ        ";
			sql += "        ,ＣＬＡＳＳ        ";
			sql += "        ,ＳＵＢＣＬＡＳＳ  ";
			sql += "        ,発行形態          ";
			sql += "        ,商品コード        ";
			sql += "        ,商品名            ";
			sql += " ORDER BY  ＤＥＰＴ        ";
			sql += "        ,ＣＬＡＳＳ        ";
			sql += "        ,ＳＵＢＣＬＡＳＳ  ";
			sql += "        ,発行形態          ";
			sql += "        ,商品コード        ";
			sql += "        ,商品名            ";

//			getData(sql,"product_cd","product_nn");
			String[][] params = new String[3][2];
			params[0][0] = Xysflj_Dbcom.DB_STR;
			params[0][1] = selectedDept;
			params[1][0] = Xysflj_Dbcom.DB_STR;
			params[1][1] = selectedSubclass;
			params[2][0] = Xysflj_Dbcom.DB_STR;
			params[2][1] = selectedIssueform;

			getData(sql,"product_cd","product_nn", params);
		}
		catch(XysfljDB.DBException e){
			//データ取得でのDBエラー
			param.addMessageByCode(1000);
			param.executeErrorRoutine(e);
		}
		//comboElements.put("","その他");
	}
}
