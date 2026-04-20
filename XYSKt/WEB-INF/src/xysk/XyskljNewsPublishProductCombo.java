package xysk;

import java.util.Vector;

public class XyskljNewsPublishProductCombo extends XysfljCommonCombo{
	static public String NAME = "select_product";
	public XyskljNewsPublishProductCombo(XysfljParameters p){
		super(p,NAME);
		setOnChange("button_sts_chg();");
		setStyle("width : 340px;");
		comboElements.put("000000","");
		try{
			String inout = null;
			inout = param.getRequestParameter("inout");
			String selectedPublishcom = param.getValue(XyskljNewsPUBLISHCOMCombo.NAME);
			String sql = "";
			if ( inout == null || inout.equals("") || inout.equals("0") ) {
//				System.out.println("ローソンユーザー =" + inout);
				sql = " SELECT  商品コード product_cd         ";
				sql += "        ,商品名 product_nn             ";
				sql += " FROM    UPM_新聞社別商品              ";
//				sql += " WHERE   新聞社コード = '" + selectedPublishcom + "' ";
				sql += " WHERE   新聞社コード = ? ";
				sql += " GROUP BY  商品コード            ";
				sql += "        ,商品名            ";
				sql += " ORDER BY  商品コード            ";
				sql += "        ,商品名            ";
//				getData(sql,"product_cd","product_nn");
				
				String [][] params = new String [1][2];
				params[0][0] = Xysflj_Dbcom.DB_STR;
				params[0][1] = selectedPublishcom;

				getData(sql,"product_cd","product_nn", params);
			} else {
				Vector TorihikiCD = param.getLoginUserTorihikisakiCD();
				for( int i=0; i<= (TorihikiCD.size()-1); i++ ) {
					if ( TorihikiCD.elementAt(i+1) == null || TorihikiCD.elementAt(i+1).equals("") || TorihikiCD.elementAt(i+1).equals("1") ) {
//						System.out.println("種別1 = " + TorihikiCD.elementAt(i+1));
						sql = " SELECT  商品コード product_cd         ";
						sql += "        ,商品名 product_nn             ";
						sql += " FROM    UPM_新聞社別商品              ";
//						sql += " WHERE   新聞社コード = '" + selectedPublishcom + "' ";
						sql += " WHERE   新聞社コード = ? ";
						sql += " GROUP BY  商品コード            ";
						sql += "        ,商品名            ";
						sql += " ORDER BY  商品コード            ";
						sql += "        ,商品名            ";
//						getData(sql,"product_cd","product_nn");

						String [][] params = new String [1][2];
						params[0][0] = Xysflj_Dbcom.DB_STR;
						params[0][1] = selectedPublishcom;

						getData(sql,"product_cd","product_nn", params);
					}else{
//					System.out.println("種別2 = " + TorihikiCD.elementAt(i+1));
//						System.out.println("XyskljPUBLISHBRANDCombo=" + TorihikiCD.elementAt(i));
						sql = " SELECT A.商品コード product_cd ";
						sql += "      , B.商品名 product_nn ";
						sql += " FROM   UPT_取引先_商品 A ";
						sql += "      , UPM_新聞社別商品 B ";
//						sql += " WHERE A.取引先コード='" + TorihikiCD.elementAt(i) + "'";
//						sql += "   AND B.新聞社コード = '" + selectedPublishcom + "' ";
						sql += " WHERE A.取引先コード= ? ";
						sql += "   AND B.新聞社コード = ? ";
//						System.out.println(selectedPublishcom);
						sql += "   AND A.商品コード =B.商品コード ";
						sql += " GROUP BY  A.商品コード ";
						sql += "         , B.商品名 ";
						sql += " ORDER BY  A.商品コード ";
						sql += "         , B.商品名 ";
//						System.out.println("1回目 = " + i);
//						getData(sql,"product_cd","product_nn");
						
						String [][] params = new String [2][2];
						params[0][0] = Xysflj_Dbcom.DB_STR;
						params[0][1] = (String)TorihikiCD.elementAt(i);
						params[1][0] = Xysflj_Dbcom.DB_STR;
						params[1][1] = selectedPublishcom;

						getData(sql,"product_cd","product_nn", params);

//						System.out.println("2回目 = " + i);
					}
					i=i+1;
				}
			}
		}
		catch(XysfljDB.DBException e){
			//データ取得でのDBエラー
			param.addMessageByCode(1000);
			param.executeErrorRoutine(e);
		}
		//comboElements.put("","その他");
	}
}
