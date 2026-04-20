<%@ page contentType="text/html;charset=UTF-8" %>
<% 
response.setHeader("X-Frame-Options", "DENY");
response.setHeader("Content-Security-Policy", "frame-ancestors 'none';");
%>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="xysk.Xysflj_Common" %>

<!DOCTYPE HTML >
<!--
// *---------------------------------------------------------------------
// * システム　　名称 : ＭＳＩシステム
// * 画面名称		  : エラー画面（Xysflj_99999J.jsp）
// * 会社名or所属名   : ＣＶＳシステム事業部
// * 作成日 		  : 03/07/11 00:00:00
// * 作成者 		  : A.Konishi
// * *** 修正履歴 ***
// * No.  Date		  Aouther		Description
// * 01   2003/08/30  Y.Takabayashi Deploy確認
// * 02   2003/09/10  Y.Takabayashi Ctrlキー・禁止
// * 03   2003/09/16  Y.Takabayashi 右クリック・禁止
// *---------------------------------------------------------------------
-->
<%
	String DEBUG_SW 		= Xysflj_Common.DEBUG_SW;						// DEBUG SWITCH
	String CON_SW			= Xysflj_Common.CON_SW; 						// 1:JNDI(iAS)　2:Tomcat　3:J2SDKEE
	String CHAR_SET 		= Xysflj_Common.CHAR_SET;						// Shift_Jis or x-euc-jp
	String CONT_PATH		= Xysflj_Common.CONT_PATH;						// Static File PATH
	String SERVLET_PATH 	= Xysflj_Common.SERVLET_PATH;					// Servlet PATH
	String JSP_PATH 		= Xysflj_Common.JSP_PATH;						// JSP PATH
	Enumeration msg_box = ((Vector)request.getAttribute("msg_box")).elements();
%>
<HTML lang="ja">
<HEAD>
<meta charset="utf-8">
<!--<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META http-equiv="Content-Style-Type" content="text/css">-->
<STYLE >
<!--
 TD{font-size=10pt;}
 INPUT{ime-mode:inactive;text-align="left";}
 INPUT.ON_RIGHT{ime-mode:active;text-align="right";}
 INPUT.OF_RIGHT{ime-mode:inactive;text-align="right";}
 INPUT.ON_LEFT{ime-mode:active;text-align="left";}
 INPUT.OF_LEFT{ime-mode:inactive;text-align="left";}
 TEXTAREA{ime-mode:active;font-size=8pt;overflow:auto;resize :none;}
 INPUT.BUTTON{width:5EM; font-size=10pt; text-align:center; 'ＭＳゴシック';height=20}}
 INPUT.DATE_BTN{width:1.5EM; font-size=8pt; text-align:center; 'ＭＳゴシック';height=20}}
-->
</STYLE>
<link rel="stylesheet" type="text/css" href="ie10view.css" />
<TITLE>出版物管理システム</TITLE>

<SCRIPT src="<%= CONT_PATH %>common/common.js"></SCRIPT>
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
// *** 初期処理 ***
// ****************
function initial() {
// **** Servlet処理でエラーとなった場合には、以下の記述でエラー項目をpink設定する。****
//	 document.form1.elements[eno0].style.background='pink';
//
//
	resizeTo(700,510);					// 画面初期表示リサイズ
	focus();							// 自画面へフォーカス
	self.moveTo(0,0);					// Window 位置を0,0へ移動
	if( undefined != opener ) {
		if( undefined != opener.$submit_sw ) { 
			opener.$submit_sw = "0";		// 送信中 SWITCH OFF
		}
	}
	stawatch(); //2014/7/3 katayama：時計修正
}
// --------------------------------------
// エラー・リセット（必ず必要）
// --------------------------------------
function input_clear() {
}
// -->
</SCRIPT>
</HEAD>
<BODY style="margin-top:5px; margin-left:10px; background-color:#F0FFFF;" onload="initial();" scroll="yes"
 oncontextmenu="return false"><!-- *** 本番時は、左記の記述を上記BODYタグに追加すること *** -->
<FORM method=post name="form1" action="<%=SERVLET_PATH%>xxxx" target="_top">

<SCRIPT >
<!--	
	Header("Sxysf99999","",650,0,1,1,0,1);
//			|			|  |   | | | | |
//			|			|  |   | | | | +---> 日付時刻 SW
//			|			|  |   | | | +-----> HELP SW
//			|			|  |   | | +-------> 印刷 SW
//			|			|  |   | +---------> 終了 SW
//			|			|  |   +-----------> ﾒﾆｭｰ SW
//			|			|  +---------------> ヘッダー横幅（通常は、975）
//			|			+---------------> 画面補足文字列
//			+---------------------------> 画面ＩＤ
-->
</SCRIPT>


<TABLE style="width:690px; border-spacing:0;"><TBODY><TR style="text-valign:top;"><TD style="text-align:left; padding:0;">
<TEXTAREA rows="30" cols="105" id="msg_out" style="color:red;background-color:#F0FFFF;font-size:8pt;overflow:auto;resize :none;"
 readonly>
<%	while(msg_box.hasMoreElements()){ %>
<%=(String)msg_box.nextElement()%>
<%	} %>
</TEXTAREA>
</TD><TD style="text-align:right; padding:0;">
</TD></TR>
</TBODY></TABLE>
<TABLE style="width:690px; border-spacing:0;"><TBODY>
<TR><TD style="padding:0;"><BR></TD></TR>
<TR><TD style="color:red; padding:0;">
　サーバー処理でエラーが発生しました。（上記内容を確認してください。）
</TD>
</TR>
</TBODY></TABLE>

</FORM>
</BODY>
</HTML>
