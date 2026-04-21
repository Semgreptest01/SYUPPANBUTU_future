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
public class XykjljDeptClassCombo extends XysfljComboTag{
//	final static public String NAME = "select_DEPT/CLASS";
	final static public String NAME = "select_deptclass";
	final static public int TYPE = XysfljParameters.ESSENTIAL;

	final static String TOTAL = "";
	public XykjljDeptClassCombo (XysfljParameters p){
		super(NAME,p);
		try{
			getData();
		}
		catch(XysfljDB.DBException e){
			//データ取得でのDBエラー
			param.addMessageByCode(1000);
			param.executeErrorRoutine(e);
		}
	}
	private void getData()
		throws XysfljDB.DBException{
		try{		
//			setOnChange("clear1(document.form1);pop_subclass(this.form);");
			setOnChange("clear1(document.form1);dept_subclass_search(1);");
            setStyle("width : 190px;");

//			XysfljDB tdb = Xysflj_Dbcom.DBConnect();
			XysfljDB tdb = param.getDbConnection();
			String sql = "";
			sql += " SELECT   A.ＤＥＰＴ || B.ＣＬＡＳＳ DEPTCLASS_CD       ";
			sql += "         ,A.ＤＥＰＴ名 || '/' || B.ＣＬＡＳＳ名 DEPTCLASS_NAME  ";
			sql += " FROM     UPM_ＤＥＰＴ A ,          ";
			sql += "          UPM_ＣＬＡＳＳ B          ";
			sql += " WHERE    A.ＤＥＰＴ = B.ＤＥＰＴ    ";
			sql += " ORDER BY B.ＤＥＰＴ ,              ";
			sql += "          B.ＣＬＡＳＳ              ";
//			ResultSet rs = tdb.selectTable(sql);
			ResultSet rs = tdb.selectTable(sql, null);
			while( rs.next()){
				comboElements.put("00000","");
				String DEPTCLASS_CD = rs.getString("DEPTCLASS_CD");
				// **** E10K移行時変更対応(2006/06/08) ***
				String DEPTCLASS_NAME = Xysflj_Common.getStrCvt( rs.getString("DEPTCLASS_NAME") );
//				String DEPTCLASS_NAME = rs.getString("DEPTCLASS_NAME");
				// **** END ******************************
				comboElements.put(DEPTCLASS_CD,DEPTCLASS_CD + " " + DEPTCLASS_NAME);
			}
			rs.close();
		}
		catch(SQLException e){
			//rs.nextのエラー
			throw new XysfljDB.ResultAnalyzeException(e);
		}
	}

}

