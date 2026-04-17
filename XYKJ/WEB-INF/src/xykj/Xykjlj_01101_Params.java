package xykj;

import javax.servlet.http.HttpServletRequest;

public class Xykjlj_01101_Params extends XysfljParameters{

    //配置するＣｏｍｂｏＢｏｘ
	public XykjljDeptClassCombo Dept_Class;
	XykjljSUBCLASSCombo subclassCombo = null;
	XykjljISSUEFORMCombo IssueformCombo = null;
	XykjljBRANDCombo BrandformCombo = null;
	XykjljSALEDATECombo SaledateCombo = null;
    public String status = null ;
    public String allitem = null ;
    public String selectline1 = null ;
    public String selectline2 = null ;
    public String selectline3 = null ;
	public int NO_DATA_FLG = 0;
    final public static int COMMAND_NO_DATA	= 1;
	final public static int COMMAND_DATA		= 0;

	public Xykjlj_01101_Params(HttpServletRequest request){
		super("Sxykj01101",request);
		
		
		//ＤＥＰＴ／ＣＬＡＳＳ
		Dept_Class = new XykjljDeptClassCombo(this);				
		setControl(Dept_Class);

		//ＳＵＢＣＬＡＳＳ
		subclassCombo = new XykjljSUBCLASSCombo(this);
		setControl(subclassCombo);

		//発行形態
		IssueformCombo = new XykjljISSUEFORMCombo(this);
		setControl(IssueformCombo);
		
		//銘柄
		BrandformCombo = new XykjljBRANDCombo(this);
		setControl(BrandformCombo);

		//発売日
		SaledateCombo = new XykjljSALEDATECombo(this);
		setControl(SaledateCombo);

    	allitem = getRequestParameter("allitem");
    	selectline1 = getRequestParameter("selectline1");
    	selectline2 = getRequestParameter("selectline2");
    	selectline3 = getRequestParameter("selectline3");

		//検索前はデータ無に設定		
		NO_DATA_FLG = COMMAND_NO_DATA;    	

	}
}
