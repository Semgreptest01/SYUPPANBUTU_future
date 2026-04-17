package xykj;

public class XykjljBRANDCombo extends XysfljCommonCombo{
	static public String NAME = "select_brand";
	public XykjljBRANDCombo(XysfljParameters p){
		super(p,NAME);
		// 2003/12/18 miura:検索ボタンとダウンロードボタンの制御を追加
		setOnChange("brand_saledate_search(1);button_sts_chg();");
		setStyle("width : 340px;");

		comboElements.put("000000","");
		
		try{
			String selectedDept = param.getValue(XykjljDeptClassCombo.NAME);
			String selectedSubclass = param.getValue(XykjljSUBCLASSCombo.NAME);
			String selectedIssueform = param.getValue(XykjljISSUEFORMCombo.NAME);
			String sql = "";
			sql += " SELECT  ＤＥＰＴ                 ";
			sql += "        ,ＣＬＡＳＳ               ";
			sql += "        ,ＳＵＢＣＬＡＳＳ                ";
			sql += "        ,発行形態              ";
			sql += "        ,銘柄コード  brancd_cd          ";
			sql += "        ,銘柄名      brancd_nn       ";
			sql += " FROM    UPM_ＤＥＰＴ別銘柄                ";
//			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = '" + selectedDept + "' ";
//			sql += " AND     ＳＵＢＣＬＡＳＳ = '" + selectedSubclass + "' ";
//			sql += " AND     発行形態 = '" + selectedIssueform + "' ";
			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = ? ";
			sql += " AND     ＳＵＢＣＬＡＳＳ = ? ";
			sql += " AND     発行形態 = ? ";
			sql += " GROUP BY  ＤＥＰＴ            ";
			sql += "        ,ＣＬＡＳＳ            ";
			sql += "        ,ＳＵＢＣＬＡＳＳ            ";
			sql += "        ,発行形態            ";
			sql += "        ,銘柄コード            ";
			sql += "        ,銘柄名            ";
			sql += " ORDER BY  ＤＥＰＴ            ";
			sql += "        ,ＣＬＡＳＳ            ";
			sql += "        ,ＳＵＢＣＬＡＳＳ            ";
			sql += "        ,発行形態            ";
			sql += "        ,銘柄コード            ";
			sql += "        ,銘柄名            ";

//			getDataCodeName(sql,"brancd_cd","brancd_nn");

			String[][] params = new String[3][2];
			params[0][0] = Xysflj_Dbcom.DB_STR;
			params[0][1] = selectedDept;
			params[1][0] = Xysflj_Dbcom.DB_STR;
			params[1][1] = selectedSubclass;
			params[2][0] = Xysflj_Dbcom.DB_STR;
			params[2][1] = selectedIssueform;

			getDataCodeName(sql,"brancd_cd","brancd_nn", params);
		}
		catch(XysfljDB.DBException e){
			//データ取得でのDBエラー
			param.addMessageByCode(1000);
			param.executeErrorRoutine(e);
		}
		//comboElements.put("","その他");
	}
}
