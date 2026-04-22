<%@ page contentType="text/html; charset=UTF-8" language="java" import="xykj.*"%>
<% 
response.setHeader("X-Frame-Options", "DENY");
response.setHeader("Content-Security-Policy", "frame-ancestors 'none';");
%>
<%@ include file="Xysflj_page_header.jsp"%>
<%
	//フォームデータ解析
	Xykjlj_NewsPublishProduct param = new Xykjlj_NewsPublishProduct(request);
	pageParameters = param;
%>
<!DOCTYPE HTML >
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META http-equiv="Content-Style-Type" content="text/css">
<!--
// *---------------------------------------------------------------------
// * システム　　名称 : ＭＳＩ　システム
// * 画面名称		  : 共通・ＤＲ－ＳＶ検索・画面（Xykjlj_publishproductJ.jsp）
// * 会社名or所属名   : ＣＶＳシステム事業部
// * 作成日 		  : 2003/04/16 12:00:00
// * 作成者 		  : 
// * *** 修正履歴 ***
// * No.  Date		  Aouther	   Description
// * 01   2003/09/22  Y.Takabayashi Ctrlキー・禁止
// * 02   2006/06/08  O.Ogawara    pageParameters処理追加(コネクションクローズ対応)
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
 INPUT.BUTTON{width:5EM; font-size=10pt; text-align:center; 'ＭＳゴシック';height=20}
 INPUT.DATE_BTN{width:1.5EM; font-size=8pt; text-align:center; 'ＭＳゴシック';height=20}
-->
</STYLE>
<TITLE>出版物管理システム</TITLE>
<SCRIPT src="<%= XysfljGenericRules.CONT_PATH_XYKJ%>common/common.js"></SCRIPT>
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
// ＳＶ配列
var Array_product =  new Array(
<%=param.createPublishProductJS()%>
);
// ****************
// *** 初期処理 ***
// ****************
function initial() {
	resizeTo(285,30); 		// 画面初期表示リサイズ
	self.moveTo(558,110); 	// Window 位置を0,0へ移動
	opener.$submit_sw = "0";			// 送信中 SWITCH OFF
	opener.Array_product = Array_product;
	opener.pop_product(opener.form1);
	top.close();
}
// -->
</SCRIPT>
</HEAD>
<BODY style="margin-top:0px; margin-left:0px; background-color:#F0FFFF;" onload="initial();" scroll="no"
 oncontextmenu="return false"><!-- *** 本番時は、左記の記述を上記BODYタグに追加すること *** -->
<FORM method=post name="form1" target="_top">
<TABLE style="table-layout:fixed; width:290px; border:0; border-spacing:0;">
<TBODY>
</TBODY>
</TABLE>
</form>
</body>
</html>
<%@ include file="Xysflj_page_footer.jsp"%>