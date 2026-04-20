<%@ page contentType="text/html; charset=UTF-8" language="java" import="xysk.*"%>
<%@ include file="Xysflj_page_header.jsp"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META http-equiv="Content-Style-Type" content="text/css">
<!--
// *---------------------------------------------------------------------
// * システム　　名称 : 出版物管理システム
// * 画面名称		  : DEPT-CLASS-SUBCLASS-発行形態　別　検索（Xysklj_01201J.jsp）
// * 会社名or所属名   : 富士ソフトABC 株式会社
// * 作成日 		  : 2003/07/01 11:00:00
// * 作成者 		  : M.Fukuuchi
// * *** 修正履歴 ***
// * No.  Date		  Aouther		Description
// * 01   2003/09/22  Y.Takabayashi Ctrlキー・禁止
// * 02   2003/12/18  A.Miura コンボボックスの連動制御・ユーザー制御
// * 03   2006/05/11  K.Sasaki      hidden項目の追加 ①screenId ②screenNm ③objectNm
// * 03   2014/12/03  T.Katayama  Win8,IE11対応
// *---------------------------------------------------------------------
-->
<STYLE >
<!--
 TD{font-size=9pt;}
 INPUT{ime-mode:inactive;text-align="left";}
 INPUT.ON_RIGHT{ime-mode:active;text-align="right";}
 INPUT.OF_RIGHT{ime-mode:inactive;text-align="right";}
 INPUT.ON_LEFT{ime-mode:active;text-align="left";}
 INPUT.OF_LEFT{ime-mode:inactive;text-align="left";}
 TEXTAREA{ime-mode:active;font-size=8pt;overflow:auto;}
 INPUT.BUTTON{width:5EM; font-size=10pt; text-align:center; 'ＭＳゴシック';height=20;}
 INPUT.DATE_BTN{width:1.5EM; font-size=8pt; text-align:center; 'ＭＳゴシック';height=20;}
-->
</STYLE>
<link rel="stylesheet" type="text/css" href="ie10view.css" />
<TITLE>出版物管理システム</TITLE>
<SCRIPT >
<!--
$help_path		   = "<%= XysfljGenericRules.CONT_PATH_XYSK%>help/Xysklj_01201.htm";
-->
</SCRIPT>
<SCRIPT src="<%= XysfljGenericRules.CONT_PATH_XYSK%>common/common.js"></SCRIPT>
<SCRIPT src="<%= XysfljGenericRules.CONT_PATH_XYSK%>common/jquery.slim.min.js"></SCRIPT>
<SCRIPT src="<%= XysfljGenericRules.CONT_PATH_XYSK%>common/ajax.js"></SCRIPT>
<SCRIPT src="<%= XysfljGenericRules.CONT_PATH_XYSK%>common/publishcom_brand.js"></SCRIPT>
<SCRIPT src="<%= XysfljGenericRules.CONT_PATH_XYSK%>common/publishbrand_saledate.js"></SCRIPT>
<SCRIPT src="<%= XysfljGenericRules.CONT_PATH_XYSK%>common/selectline1_selectline2.js"></SCRIPT>
<SCRIPT src="<%= XysfljGenericRules.CONT_PATH_XYSK%>common/selectline2_selectline3.js"></SCRIPT>
<SCRIPT src="<%= XysfljGenericRules.CONT_PATH_XYSK%>common/Xysflj_button_common.js"></SCRIPT>
<SCRIPT >
<!--
// ************************
// *** Ctrl+Nキー禁止対応
// ************************
	$submit_sw = "0";			// 送信中 SWITCH OFF
	Ctrl_Detect();				// Ctrlキー・禁止
function Ctrl_Detect(){
	document.onkeydown=keydown;
	if(document.layers)document.captureEvents(Event.KEYDOWN)
}
function keydown(e) {
	//--キ－コ－ドを文字に直す
	var keyName=String.fromCharCode(getKEYCODE(e))
	var char_code = escape( keyName );
	if (event.ctrlKey){
		if (char_code == "N") {
			alert("Ctrl+N は使用できません。");
			return false;
		}
	}
}
function getKEYCODE(e){
	//--押されたキ－コ－ドを返す
	if(document.layers) return e.which
	if(document.all)	return event.keyCode
} 
	// ****************
	// *** 初期処理
	// ****************
	// 2003/12/18 miura:ポップアップメッセージを削除
	function initial(){
		document.title = G_name( "Sxysk01201" );	// *** 画面名称取得 ***
		resizeTo(1024,740);		// 画面初期表示リサイズ
		focus();				// 自画面へフォーカス
		self.moveTo(0,0);		// Window 位置を0,0へ移動
/*
		if(document.form1.CommandType) {
		if(document.form1.CommandType.value == "<%= Xysklj_01201_Params.COMMAND_REFERENCE %>"){
			document.form1.select_Item.value = <%=request.getParameter("select_Item")%>;
			document.form1.select_selectline1.value = <%=request.getParameter("selectline1")%>;
			document.form1.select_selectline2.value = <%=request.getParameter("selectline2")%>;
			document.form1.select_selectline3.value = <%=request.getParameter("selectline3")%>;

			if (document.form1.select_selectline1.value != 0) {
				pop_selectline2(document.form1, document.form1.select_selectline1.value);
				document.form1.select_selectline2.value = <%=request.getParameter("selectline2")%>;
			} else {
				document.form1.select_selectline2.disabled = true;
			}
			
			if (document.form1.select_selectline2.value != 0) {
				pop_selectline3(document.form1, document.form1.select_selectline1.value + document.form1.select_selectline2.value);
				document.form1.select_selectline3.value = <%=request.getParameter("selectline3")%>;
			} else {
				document.form1.select_selectline3.disabled = true;
			}
		}
		else{
			focus();					// 自画面へフォーカス
			//無効
			document.form1.select_brand.disabled = true;
			document.form1.select_saledate.disabled = true;
			document.form1.select_selectline2.disabled = true;
			document.form1.select_selectline3.disabled = true;
		}
		}
		*/
		// 2003/12/18 miura:検索ボタン・ダウンロードボタンの制御追加
//		button_sts_chg();
/*
		//取引先対応
		if ( document.form1.select_publishcom.value != 0 &&
			document.form1.select_publishcom.disabled == true ) {
			publishcom_brand_search(1);
			alert("取引先対応");
		}*/
		stawatch(); //2014/7/3 katayama：時計修正
		//var tmInt = setInterval(timer, 1000);

	}

	function timer() {

		var prmArry = new Array();
		prmArry[ "mode" ] = "time";
		var ajaxHndlr = function( rspnsTxt ) {
			//alert("rspnsTxt=" + rspnsTxt.substr(2));
			// ------------------------------------------------------------
			// rspnsTxtは、先頭１文字にステータスが返る。
			// 0:OK
			// 1:データなし
			// 9:その他のERROR
			// ------------------------------------------------------------
			var err_msg = "";
			if( rspnsTxt.substr(0,1) == "9" ) {
				// エラーなので何もしない
				return;
			}
	
			//通常結果表示用
			if( rspnsTxt.substr(0,1) == "0" ) {
				// OK処理
				var res = rspnsTxt.substr(2);
				$('td[id="_times"]').html(res);
				return;
			}

			if( rspnsTxt.substr(0,1) == "1" ) {
				// NG処理
				return;
			}
		};
		sendAjaxRqst( "/XYSK/Xysklj_times", prmArry, ajaxHndlr, true );
	}

// --------------------------------------
// ダウンロード ボタンが押された場合
// --------------------------------------
// 2003/12/18 miura:ポップアップメッセージを削除

	function download_chk(){

		document.form1.allitem.value = document.form1.select_Item.value;
		document.form1.selectline1.value = document.form1.select_selectline1.value;
		if (document.form1.select_selectline2.value == "") {
			document.form1.selectline2.value = "00";
		} else {
			document.form1.selectline2.value = document.form1.select_selectline2.value;
		}
		if (document.form1.select_selectline3.value == "") {
			document.form1.selectline3.value = "00";
		} else {
			document.form1.selectline3.value = document.form1.select_selectline3.value;
		}

		document.form1.CommandType.value = "<%= Xysklj_01201_Params.COMMAND_DOWNLOAD %>";
		document.form1.method = "POST";
		document.form1.action = "<%= XysfljGenericRules.DOWNLOAD_PATH_XYSK%>Xysklj_01201Jd";
		document.form1.submit();
		document.form1.action = "";
	}	// ﾀﾞｳﾝﾛｰﾄﾞ が押された時
// ****************************************
// *** ＨＥＬＰを表示する（静的コンテンツ）
// ****************************************
	function help_chk() {
	   window.open( $help_path, 'help', 'width=500, height=400,scrollbars=yes, status=yes,resizable=yes');
	}
// --------------------------------------
// 検索 ボタンが押された場合
// --------------------------------------
	function button1_func(){

		// 各コントロールをロックする
		lockControlSubmit(document.form1);
		
		document.form1.allitem.value = document.form1.select_Item.value;
		document.form1.selectline1.value = document.form1.select_selectline1.value;
		if (document.form1.select_selectline2.value == "") {
			document.form1.selectline2.value = "00";
		} else {
			document.form1.selectline2.value = document.form1.select_selectline2.value;
		}
		if (document.form1.select_selectline3.value == "") {
			document.form1.selectline3.value = "00";
		} else {
			document.form1.selectline3.value = document.form1.select_selectline3.value;
		}
		document.form1.CommandType.value = "<%= Xysklj_01201_Params.COMMAND_REFERENCE %>";
		document.form1.submit_button0.disabled = true;
		document.form1.submit();
	}
// --------------------------------------------
//	CLEAR1
//	発行形態,銘柄,発売日のクリア
//	2003/12/18 miura: 検索ボタン・ダウンロードボタン制御追加
// --------------------------------------------
	function clear1(inForm) {
	   clear_issueform(inForm);
	   clear_brand(inForm);
	   clear_saledate(inForm);
	   button_sts_chg();
	}
// --------------------------------------------
//	CLEAR2
//	銘柄,発売日のクリア
//	2003/12/18 miura: 検索ボタン・ダウンロードボタン制御追加
// --------------------------------------------
	function clear2(inForm) {
	   clear_brand(inForm);
	   clear_saledate(inForm);
	   button_sts_chg();
	}
// --------------------------------------------
//	CLEAR3
//	発売日のクリア
//	2003/12/18 miura: 検索ボタン・ダウンロードボタン制御追加
// --------------------------------------------
	function clear3(inForm) {
	   clear_saledate(inForm);
	   button_sts_chg();
	}
// --------------------------------------------
//	change_item
//	全項目選択時
// --------------------------------------------
	function change_item(inForm) {
		document.form1.select_selectline1.options.selectedIndex = 0;
		clear_selectline2(inForm);
		inForm.select_selectline2.disabled = true; //無効
		clear_salectline3(inForm);
		inForm.select_selectline3.disabled = true; //無効
	}
// --------------------------------------------
//	change_publishcom
//	出版社選択時
// --------------------------------------------
	function change_publishcom(inForm) {
	   clear_saledate(inForm);
	}

// --------------------------------------
// 2003/12/18 miura: ボタン無効化処理追加
// --------------------------------------
	function button_disable(){
		document.form1.submit_button0.disabled=true; 	// 検索ボタンの無効化
		Button1_disable(0,0,0,0,0,0,0,0,0);				// 通常ボタン１～９の無効化
		Button2_disable(0,1,0,0,0,0,0,0,0);				// 共通ボタンの無効化
	}
// --------------------------------------
// 2003/12/18 miura: ボタン有効化処理追加
// --------------------------------------
	function button_enable(){
		document.form1.submit_button0.disabled=false; 	// 検索ボタンの有効化
		Button1_enable(0,0,0,0,0,0,0,0,0);				// 通常ボタン１～９の有効化
		Button2_enable(0,1,0,0,0,0,0,0,0);				// 共通ボタンの有効化
	}

// --------------------------------------------
//	button_sts_chg(必ず必要)
//	検索条件によりボタンの状態を変更する
//	2003/12/18 miura: 検索・ダウンロードボタン制御追加
// --------------------------------------------
	function button_sts_chg() {
		if (document.form1.select_saledate.value == 0 ||	// 発売日が選ばれていないか
			(document.form1.select_Item.value == 0 && document.form1.select_selectline1.value == 0)) {	// 項目または1行目が選ばれていない
			button_disable();								// 検索ボタンとダウンロードボタンの無効化
		} else {
			button_enable();								// 検索ボタンとダウンロードボタンの有効化
		}
	}

-->
</SCRIPT>
</HEAD>
<BODY style="margin-top:5px; margin-left:10px; background-color:#F0FFFF;" onload="initial();" scroll="yes"
 oncontextmenu="return false"><!-- *** 本番時は、左記の記述を上記BODYタグに追加すること *** -->
<FORM method="post" name="form1" target="_top" action="Xysklj_01201J.jsp">

<SCRIPT >
<!--
	Header("Sxysk01201","",975,0,0,0,0,1);
//			|			|	| | | | |
//			|			|	| | | | +---> 日付時刻 SW
//			|			|	| | | +-----> HELP SW
//			|			|	| | +-------> 印刷 SW
//			|			|	| +---------> 終了 SW
//			|			|	+-----------> ﾒﾆｭｰ SW
//			|			+---------------> ヘッダー横幅（通常は、975）
//			+---------------------------> 画面ＩＤ
	msg_start( 975,0 ); 			   // メッセージ表示領域 ***START***
<%/*param.addMessage("123","456");*/%>
	msg_end("","","","","","","","");
-->
</SCRIPT>
<TABLE CLASS="ie10tb31" style="table-layout:fixed; width:970px; border-spacing:0;">
<TBODY><TR style="text-align:right;">
<TD style="padding:0;"></TD>
</TR></TBODY>
</TABLE>
<TABLE CLASS="ie10tb41" style="table-layout:fixed; width:970px; border-spacing:0;">
<COL style="WIDTH:30px"><COL style="WIDTH:150px"><COL style="WIDTH:30px"><COL style="WIDTH:200px"><COL style="WIDTH:30px"><COL style="WIDTH:200px"><COL style="WIDTH:100px">
<TBODY><TR>
	<TD style="padding:0;">項目</TD>
	<TD style="padding:0;">
		<SELECT name="select_Item" style="width : 140px;" onChange="change_item(document.form1);button_sts_chg();">
			<OPTION value="0" selected>
			<OPTION value="1">全項目
		</SELECT>
	</TD>
	<TD style="padding:0;">出版社</TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;" colspan="2">発売日
	</TD>
	<TD style="padding:0;"></TD>
</TR><TR>
	<TD style="padding:0;">又は</TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;">銘柄</TD>
	<TD style="padding:0;" colspan="3"></TD>
	<TD style="padding:0;">
		<input type="button" name="submit_button0"
			 class="BUTTON" value="検索" onClick="button1_func()">	
	</TD>
</TR><TR>
	<TD style="padding:0;">１行目</TD>
	<TD style="padding:0;">
		<SELECT name="select_selectline1" style="width:140px;"onChange="pop_selectline2(document.form1, this.value);button_sts_chg();">
			<OPTION value="0" selected>
			<OPTION value="1">仕入数
			<OPTION value="2">販売数
			<OPTION value="3">販売率
			<OPTION value="4">配本店数
			<OPTION value="5">売切店舗数
			<OPTION value="6">売切店発生率
			<OPTION value="7">返品数
			<OPTION value="8">欠品数
			<OPTION value="9">PSA仕入
			<OPTION value="10">PSA販売
		</SELECT>	
	</TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
</TR><TR>
	<TD style="padding:0;">２行目</TD>
	<TD style="padding:0;">
		<SELECT name="select_selectline2" style="width:140px;"onChange="pop_selectline3(document.form1, select_selectline1.value + this.value);">
		<OPTION value="00" selected></OPTION>
		</SELECT>			
	</TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
</TR><TR>
	<TD style="padding:0;">３行目</TD>
	<TD style="padding:0;">
		<SELECT name="select_selectline3" style="width : 140px;">
		<OPTION value="00" selected></OPTION>
		</SELECT>
	</TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
	<TD style="padding:0;"></TD>
</TR></TBODY>
</TABLE>
<BR>
<SCRIPT >
<!--
	Button1_set(975,"","","","","","","","","");
	Button2_set(1,1,1,0,0,0,0,0,0);
-->
</SCRIPT>
<BR>
<TABLE CLASS="ie10tb61" style="table-layout:fixed; border:1; border-spacing:0;">
<TR></TR>
<BR>
<%
String test = "<script>alert('ttt' + \"rrrr\");</script>";
String test2 = "select * from test where aaa='test' and bbb=ggg;";

%>
<%--=Xysklj_Common.htmlEnc(test)--%>
<%--=Xysklj_Common.sqlEnc(test2)--%>
<TR></TR>
</TABLE>
</BODY>
</HTML>
<!--
 ************************
 *** 業務画面 *** END ***
 ************************
-->
<SCRIPT >
<!--
   Screen_end();	// ****** 業務画面枠・終了指示 ******
-->
</SCRIPT>
<INPUT type ="hidden" name="allitem" value="<%=request.getParameter("allitem")%>" >
<INPUT type ="hidden" name="selectline1" value="<%=request.getParameter("selectline1")%>" >
<INPUT type ="hidden" name="selectline2" value="<%=request.getParameter("selectline2")%>" >
<INPUT type ="hidden" name="selectline3" value="<%=request.getParameter("selectline3")%>" >
<INPUT type ="hidden" name="inout" value="<%=Xysklj_Common.htmlEnc(request.getParameter("inout"))%>" >
<%--<INPUT type ="hidden" name="inout" value="<%=request.getParameter("inout")%>" >--%>
<!-- 2006/05/11変更(E10K移行対応) -->
<INPUT type ="hidden" name="screenId" value="SXYSK01201" >
<INPUT type ="hidden" name="screenNm" value="120" >
</FORM>
</BODY>
</HTML>
<%@ include file="Xysflj_page_footer.jsp"%>
