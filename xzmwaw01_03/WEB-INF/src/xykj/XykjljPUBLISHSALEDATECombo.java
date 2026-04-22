/*
 *
 *  修正履歴
 *  L001  2006/12/01 Y.Okamura  発売日２重表示対応
 *
 */
package xykj;

public class XykjljPUBLISHSALEDATECombo extends XysfljCommonCombo{
	static public String NAME = "select_saledate";
	public XykjljPUBLISHSALEDATECombo(XysfljParameters p){
		super(p,NAME);
//L001 CHGS
        setStyle("width : 165px;");
//L001 CHGE
		// 2003/12/18 miura:検索ボタンとダウンロードボタンの制御追加
		setOnChange("button_sts_chg();");
		comboElements.put("0000000000","");

		try{
			String selectedPublish = param.getValue(XykjljPUBLISHCOMCombo.NAME);
			String selectedPublishBrand = param.getValue(XykjljPUBLISHBRANDCombo.NAME);
			String sql = "";
//L001 CHGS
			sql += " SELECT  to_char(発売日,'YYYY/MM/DD') saledate   ";
			sql += " , to_char(発売日,'YYYY/MM/DD') || '  ' ||  substr(ＪＡＮコード,5,5) || '-' || substr(ＪＡＮコード,10,2) saledateZ   ";
			sql += " , ＪＡＮコード jan ";
//L001 CHGE
			sql += " FROM    UPM_出版社別銘柄                ";
//			sql += " WHERE   出版社コード = '" + selectedPublish + "' ";
//			sql += " AND     銘柄コード = '" + selectedPublishBrand + "' ";
			sql += " WHERE   出版社コード = ? ";
			sql += " AND     銘柄コード = ? ";
			sql += " ORDER BY  発売日            ";
//			getsaleData(sql,"saledate","saledateZ");

			String[][] params = new String[2][2];
			params[0][0] = Xysflj_Dbcom.DB_STR;
			params[0][1] = selectedPublish;
			params[1][0] = Xysflj_Dbcom.DB_STR;
			params[1][1] = selectedPublishBrand;

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
