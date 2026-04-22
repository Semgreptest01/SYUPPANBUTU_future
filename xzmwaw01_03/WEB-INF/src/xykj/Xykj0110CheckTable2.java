package xykj;


import java.util.Enumeration;
import java.util.Vector;


public class Xykj0110CheckTable2 extends XysfljTableTag {


	public Xykjlj_01101_Params param = null;

	//-----表の項目数分作成----->
	public InputDataCol labelCol; // label

	//<-------------------------
	private Vector tableHeaders = new Vector();
	private int ct = 0;	


	public Xykj0110CheckTable2(Xykjlj_01101_Params p) {
		super("Xykj0110CheckTable2", p);
		param = p;

	 	ct = param.getCommandType();

		dataRow = new DataRow();
		//-----表の項目数分作成----->
		labelCol = new InputDataCol("Label", 275, "A");
		addHeader(labelCol); // label

		//<-------------------------
	    //検索以外の場合は処理を行わない
		if ((Xykjlj_01101_Params.COMMAND_REFERENCE == ct)  ||
			(Xykjlj_01101_Params.COMMAND_DOWNLOAD == ct)	&&			
			(param.NO_DATA_FLG == param.COMMAND_DATA)){
			InputPlan d = new InputPlan();
			d.setData();
			tableElements.add(d);
		}
	}


	private class DataRow extends LinePatern {
		public DataRow() {
			super("データ", 50);
		}
	}
	

	public String createTag() throws NoDataException, SpanException {

	    //検索以外の場合は処理を行わない
		if (((Xykjlj_01101_Params.COMMAND_REFERENCE == ct)  ||
			(Xykjlj_01101_Params.COMMAND_DOWNLOAD == ct))	&&			
			(param.NO_DATA_FLG == param.COMMAND_DATA)){

			StringBuffer tag = new StringBuffer();
			tag.append(createStartTag());
			tag.append(createDataTag());
			tag.append(createEndTag());
			return XysfljGenericRules.exchangeOutString(tag);
		}
		else{
			return "";
		}

	}
	

	public class InputDataCol extends DataCol {
		final private String COL_ID;

		public String editValueWithFontTag(String val) {
			return val;
		}

		public InputDataCol(String s, int n, String t) {
			super(s, n);
			COL_ID = t;
		}
	}



		public class DataCol extends LinePatern {
		public DataCol(String s, int i) {
			super(s, i);
		}
	}


		public class InputPlan extends TableData {
		public InputPlan() {
			// 表に合わせ定義
			setData("");
		}

		public void setData(String labelCol) {
			// 空値をセット
			setLabelCol(labelCol);

		}





		public void setData() {
			setLabelCol("Ctrl+A：全選択　Ctrl+C：コピー　Ctrl+V：貼り付け");
		}

		//Setter(表にて使用する項目分定義しておく)
		public void setLabelCol(String s) {
			put(labelCol.getName(), s);
		}

		//Getter(表にて使用する項目分定義しておく)
		public String getLabelCol() throws NoDataException {
			return get(labelCol.getName());
		}

	}
}