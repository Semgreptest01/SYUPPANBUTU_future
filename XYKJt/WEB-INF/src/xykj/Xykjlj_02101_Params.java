package xykj;

import javax.servlet.http.*;

public class Xykjlj_02101_Params extends XysfljParameters{

    //配置するＣｏｍｂｏＢｏｘ
	public XykjljNewsDeptClassCombo Dept_Class;
	XykjljNewsSUBCLASSCombo subclassCombo = null;
	XykjljNewsISSUEFORMCombo IssueformCombo = null;
	XykjljNewsProductCombo ProductformCombo = null;
    public String status = null ;
    public String allitem = null ;
    public String selectline1 = null ;
    public String selectline2 = null ;
    public String selectline3 = null ;
	public int NO_DATA_FLG = 0;
    final public static int COMMAND_NO_DATA	= 1;
	final public static int COMMAND_DATA		= 0;
	final public static int printDays		= 14;

	public Xykjlj_02101_Params(HttpServletRequest request){
		super("Sxykj02101",request);
		
		
		//ＤＥＰＴ／ＣＬＡＳＳ
		Dept_Class = new XykjljNewsDeptClassCombo(this);
		setControl(Dept_Class);

		//ＳＵＢＣＬＡＳＳ
		subclassCombo = new XykjljNewsSUBCLASSCombo(this);
		setControl(subclassCombo);

		//発行形態
		IssueformCombo = new XykjljNewsISSUEFORMCombo(this);
		setControl(IssueformCombo);
		
		//商品
		ProductformCombo = new XykjljNewsProductCombo(this);
		setControl(ProductformCombo);

    	allitem = getRequestParameter("allitem");
    	selectline1 = getRequestParameter("selectline1");
    	selectline2 = getRequestParameter("selectline2");
    	selectline3 = getRequestParameter("selectline3");

		//検索前はデータ無に設定		
		NO_DATA_FLG = COMMAND_NO_DATA;    	

	}
}
