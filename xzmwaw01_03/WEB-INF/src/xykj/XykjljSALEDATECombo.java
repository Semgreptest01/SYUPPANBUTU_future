/*
 *
 *  修正履歴
 *  L001  2006/12/01 S.Sawada   発売日２重表示対応
 *
 */
package xykj;

public class XykjljSALEDATECombo extends XysfljCommonCombo{
	static public String NAME = "select_saledate";
	public XykjljSALEDATECombo(XysfljParameters p){
		super(p,NAME);
//L001 CHGS
//        setStyle("width : 140px;");
        setStyle("width : 200px;");
//L001 CHGE
		// 2003/12/18 miura:検索ボタンとダウンロードボタンの制御を追加
		setOnChange("button_sts_chg();");
		comboElements.put("0000000000","");

		try{
    		String selectedDept = param.getValue(XykjljDeptClassCombo.NAME);
			String selectedSubclass = param.getValue(XykjljSUBCLASSCombo.NAME);
			String selectedIssueform = param.getValue(XykjljISSUEFORMCombo.NAME);
			String selectedBrand = param.getValue(XykjljBRANDCombo.NAME);
			String sql = "";
//L001 CHGS
			sql += " SELECT  to_char(発売日,'YYYY/MM/DD') saledate   ";
			sql += " , to_char(発売日,'YYYY/MM/DD') || '  ' || substr(ＪＡＮコード, 5,5) || '-' ||substr(ＪＡＮコード,10,2) saledateZ   ";
			sql += " , ＪＡＮコード jan ";
//L001 CHGE
			sql += " FROM    UPM_ＤＥＰＴ別銘柄                ";
//			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = '" + selectedDept + "' ";
//			sql += " AND     ＳＵＢＣＬＡＳＳ = '" + selectedSubclass + "' ";
//			sql += " AND     発行形態 = '" + selectedIssueform + "' ";
//			sql += " AND     銘柄コード = '" + selectedBrand + "' ";
			sql += " WHERE   ＤＥＰＴ || ＣＬＡＳＳ = ? ";
			sql += " AND     ＳＵＢＣＬＡＳＳ = ? ";
			sql += " AND     発行形態 = ? ";
			sql += " AND     銘柄コード = ? ";
			sql += " ORDER BY  発売日            ";
//			getsaleData(sql,"saledate","saledateZ");
			
			String[][] params = new String[4][2];
			params[0][0] = Xysflj_Dbcom.DB_STR;
			params[0][1] = selectedDept;
			params[1][0] = Xysflj_Dbcom.DB_STR;
			params[1][1] = selectedSubclass;
			params[2][0] = Xysflj_Dbcom.DB_STR;
			params[2][1] = selectedIssueform;
			params[3][0] = Xysflj_Dbcom.DB_STR;
			params[3][1] = selectedBrand;

			getsaleData(sql,"saledate","saledateZ", params);

		}
		catch(XysfljDB.DBException e){
			//データ取得でのDBエラー
			param.addMessageByCode(1000);
			param.executeErrorRoutine(e);
		}
		//comboElements.put("","その他");
	}
}
