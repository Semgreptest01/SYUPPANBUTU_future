<!-- 
//JavaScript Code
//-----------------------------------------------------------------------------------#
// HTML_Name	: 共通・チェック・ファンクション Java-Script ( common.js )
// Name 		: Y.Takabayashi
// Date 		: 2002/01/21
// No.	Date			Name			Description
//	1.	2002/01/25	Y.Takabayashi	Menu_head()ファンクションのバグ修正
//									「アンカーとなっていなかった」のをアンカーに修正
//	2.	2006/04/21	K.Sasaki		$servlet_path(OAS用)追加
//	3.	2006/04/24	K.Sasaki		var A_PASS = "/NASApp/XYKJ/contdata/XYKJ/class/"; 追加
//-----------------------------------------------------------------------------------#
// 使用方法
// (2)<HEAD>～</HEAD>に以下のスクリプト指定をします。
// <SCRIPT language="JavaScript" src="../../contdata/XYDL/common/common.js"></SCRIPT>
//-----------------------------------------------------------------------------------
// メッセージ出力（メッセージＩＤからメッセージ文字列を取得します）
// msg("X0001" , "xxxxxx");
//		| | 	  |
//		| | 	  +--->補足文を付加することが出来ます。
//		| +---> Message_id
//		+-----> Message区分（W:ワーニング F:fatal ... )
// 見つからない場合、"F0000 エラーが有ります。" が表示されます。
//-----------------------------------------------------------------------------------
$submit_sw = "0";
$servlet_path			= "./";						// ***** OAS ****
//$servlet_path			= "/XYKJ/servlet/xykj.";	// ***** tomcat ****
//$servlet_path			= "./";						// ***** iAS ****
//-----------------------------------------------------------------------------------
function msg( m_id, add_msg ){
		wk = msg_get( m_id );
		if( add_msg == "" ){
				alert( wk );
				return;
		}else{
				alert( wk + add_msg );
				return;
		}
		alert( wk );
}
// ************************
// *** Ctrlキー禁止対応
// ************************
function Ctrl_Detect(){
	$submit_sw = "0";		// 送信中 SWITCH OFF
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
//-----------------------------------------------------------------------------------
// 自画面・自動クローズ
//-----------------------------------------------------------------------------------
function auto_close(){
	tid = null;
	if(tid==null){
		tid=setTimeout('top.close();',600000);	// １０分で自動クローズ
	}
}
//-----------------------------------------------------------------------------------
// 業務画面枠・開始指示
// Screen_start();
//-----------------------------------------------------------------------------------
function Screen_start( w_size ){
   document.write('<TABLE style="table-layout:fixed;width:' + w_size + 'px; border:0; border-spacing:0;">');
   document.write('<COL id="menu_col" style="width:0.1"><TBODY><TR><TD style="text-valign:top; padding:0;">');
}
//-----------------------------------------------------------------------------------
// 業務画面枠・終了指示
// Screen_start();
//-----------------------------------------------------------------------------------
function Screen_end(){
   document.write('</TD></TR></TBODY></TABLE>');
}
//-----------------------------------------------------------------------------------
// 通常ボタン配置指示
// Button1_set( 975,"1","2","3","4","5","6","7","8","9");
//				|	 |
//				|	 +---> ボタン名称
//				+--------> width size
//-----------------------------------------------------------------------------------
function Button1_set( w_size,b1,b2,b3,b4,b5,b6,b7,b8,b9 ){
   var size1 = w_size - 430;
   document.write('<TABLE style="table-layout:fixed; width:' + w_size + 'px; border:0; border-spacing:0;">');
   document.write('<COL style="WIDTH:' + size1 + 'px"><COL style="WIDTH:430px">');
   document.write('<TBODY><TR style="text-valign:top;"><TD style="text-align:right; padding:0;">');
   if( b1 != "" ){
	  document.write('<INPUT type="button" name="submit_button1" value="' + b1 + '" class="BUTTON" onclick="submit_chk1();">');
   }
   if( b2 != "" ){
	  document.write('<INPUT type="button" name="submit_button2" value="' + b2 + '" class="BUTTON" onclick="submit_chk2();">');
   }
   if( b3 != "" ){
	  document.write('<INPUT type="button" name="submit_button3" value="' + b3 + '" class="BUTTON" onclick="submit_chk3();">');
   }
   if( b4 != "" ){
	  document.write('<INPUT type="button" name="submit_button4" value="' + b4 + '" class="BUTTON" onclick="submit_chk4();">');
   }
   if( b5 != "" ){
	  document.write('<INPUT type="button" name="submit_button5" value="' + b5 + '" class="BUTTON" onclick="submit_chk5();">');
   }
   if( b6 != "" ){
	  document.write('<INPUT type="button" name="submit_button6" value="' + b6 + '" class="BUTTON" onclick="submit_chk6();">');
   }
   if( b7 != "" ){
	  document.write('<INPUT type="button" name="submit_button7" value="' + b7 + '" class="BUTTON" onclick="submit_chk7();">');
   }
   if( b8 != "" ){
	  document.write('<INPUT type="button" name="submit_button8" value="' + b8 + '" class="BUTTON" onclick="submit_chk8();">');
   }
   if( b9 != "" ){
	  document.write('<INPUT type="button" name="submit_button9" value="' + b9 + '" class="BUTTON" onclick="submit_chk9();">');
   }
   document.write('</TD>');
}
//-----------------------------------------------------------------------------------
// 共通ボタン・表示指示（0:表示しない　1:表示する 2:無効で表示）
// Button2_set(1,1,1,1,1,1,1,1,1);
//			   | | | | | | | | |
//			   | | | | | | | | +---> → 		  ---> right_chk()	  に連動
//			   | | | | | | | +-----> ← 		  ---> left_chk()	  に連動
//			   | | | | | | +-------> ↓ 		  ---> down_chk()	  に連動
//			   | | | | | +---------> ↑ 		  ---> up_chk() 	  に連動
//			   | | | | +-----------> ヘルプ 	  ---> help_chk()	  に連動
//			   | | | +-------------> 印刷		  ---> print_chk()	  に連動
//			   | | +---------------> メニューへ   ---> menu_chk()	  に連動
//			   | +-----------------> ﾀﾞｳﾝﾛｰﾄﾞ	  ---> download_chk() に連動
//			   +-------------------> 閉じる 	  ---> top_close()	  に連動
//-----------------------------------------------------------------------------------
function Button2_set( sw1,sw2,sw3,sw4,sw5,sw6,sw7,sw8,sw9 ){
   document.write('<TD style="text-align:right;">');
   if( sw1 == 1 ){
	  document.write('<INPUT type="button" name="close_button" value="閉じる" class="BUTTON" onclick="top_close();">');
   }
   if( sw1 == 2 ){
	  document.write('<INPUT type="button" name="close_button" value="閉じる" class="BUTTON" onclick="top_close();" disabled>');
   }
   if( sw2 == 1 ){
	  document.write('<INPUT type="button" name="dl_button" value="ﾀﾞｳﾝﾛｰﾄﾞ" class="BUTTON" onclick="download_chk();">');
   }
   if( sw2 == 2 ){
	  document.write('<INPUT type="button" name="dl_button" value="ﾀﾞｳﾝﾛｰﾄﾞ" class="BUTTON" onclick="download_chk();" disabled>');
   }
//   if( sw3 == 1 ){
//	  document.write('<INPUT type="button" name="mn_button" value="ﾒﾆｭｰへ" class="BUTTON" onclick="menu_chk();">');
//   }
//   if( sw3 == 2 ){
//	  document.write('<INPUT type="button" name="mn_button" value="ﾒﾆｭｰへ" class="BUTTON" onclick="menu_chk();" disabled>');
//   }
   if( sw4 == 1 ){
	  document.write('<INPUT type="button" name="prt_button" value="印刷" class="BUTTON" onclick="print_chk();">');
   }
   if( sw4 == 2 ){
	  document.write('<INPUT type="button" name="prt_button" value="印刷" class="BUTTON" onclick="print_chk();" disabled>');
   }
   if( sw5 == 1 ){
	  document.write('<INPUT type="button" name="help_button" value="ヘルプ" class="BUTTON" onclick="help_chk();">');
   }
   if( sw5 == 2 ){
	  document.write('<INPUT type="button" name="help_button" value="ヘルプ" class="BUTTON" onclick="help_chk();" disabled>');
   }
   if( sw6 == 1 ){
	  document.write('<INPUT type="button" name="up_button" value="↑" onclick="up_chk();">');
   }
   if( sw6 == 2 ){
	  document.write('<INPUT type="button" name="up_button" value="↑" onclick="up_chk();" disabled>');
   }
   if( sw7 == 1 ){
	  document.write('<INPUT type="button" name="down_button" value="↓" onclick="down_chk();">');
   }
   if( sw7 == 2 ){
	  document.write('<INPUT type="button" name="down_button" value="↓" onclick="down_chk();" disabled>');
   }
   if( sw8 == 1 ){
	  document.write('<INPUT type="button" name="left_button" value="←" onclick="left_chk();">');
   }
   if( sw8 == 2 ){
	  document.write('<INPUT type="button" name="left_button" value="←" onclick="left_chk();" disabled>');
   }
   if( sw9 == 1 ){
	  document.write('<INPUT type="button" name="right_button" value="→" onclick="right_chk();">');
   }
   if( sw9 == 2 ){
	  document.write('<INPUT type="button" name="right_button" value="→" onclick="right_chk();" disabled>');
   }
   document.write('</TD></TR>');
   document.write('</TBODY></TABLE>');
}
// **************************************
// *** 印刷 ボタンの無効化
// **************************************
function print_disable(){
	document.form1.prt_button.disabled=true;	   // 印刷・ボタンの無効化
}
// **************************************
// *** 印刷 ボタンの有効化
// **************************************
function print_enable(){
	document.form1.prt_button.disabled=false;	   // 印刷・ボタンの有効化
}
//-----------------------------------------------------------------------------------
// 通常ボタン・無効指示（0:なにもしない　1:無効化する）
// Button1_disable(1,1,1,1,1,1,1,1,1);
//-----------------------------------------------------------------------------------
function Button1_disable( sw1,sw2,sw3,sw4,sw5,sw6,sw7,sw8,sw9 ){
   if( sw1 == 1 ){
	  document.form1.submit_button1.disabled=true;	 // ボタン１の無効化
   }
   if( sw2 == 1 ){
	  document.form1.submit_button2.disabled=true;	 // ボタン２の無効化
   }
   if( sw3 == 1 ){
	  document.form1.submit_button3.disabled=true;	 // ボタン３の無効化
   }
   if( sw4 == 1 ){
	  document.form1.submit_button4.disabled=true;	 // ボタン４の無効化
   }
   if( sw5 == 1 ){
	  document.form1.submit_button5.disabled=true;	 // ボタン５の無効化
   }
   if( sw6 == 1 ){
	  document.form1.submit_button6.disabled=true;	 // ボタン６の無効化
   }
   if( sw7 == 1 ){
	  document.form1.submit_button7.disabled=true;	 // ボタン７の無効化
   }
   if( sw8 == 1 ){
	  document.form1.submit_button8.disabled=true;	 // ボタン８の無効化
   }
   if( sw9 == 1 ){
	  document.form1.submit_button9.disabled=true;	 // ボタン９の無効化
   }
}
//-----------------------------------------------------------------------------------
// 共通ボタン・無効指示（0:なにもしない　1:無効化する）
// Button2_disable(1,1,1,1,1,1,1,1,1);
//-----------------------------------------------------------------------------------
function Button2_disable( sw1,sw2,sw3,sw4,sw5,sw6,sw7,sw8,sw9 ){
   if( sw1 == 1 ){
	  document.form1.close_button.disabled=true;	   // 閉じる・ボタンの無効化
   }
   if( sw2 == 1 ){
	  document.form1.dl_button.disabled=true; 	   // ﾀﾞｳﾝﾛｰﾄﾞ・ボタンの無効化
   }
//   if( sw3 == 1 ){
//	  document.form1.mn_button.disabled=true; 	   // メニューへ・ボタンの無効化
//   }
   if( sw4 == 1 ){
	  document.form1.prt_button.disabled=true;	   // 印刷・ボタンの無効化
   }
   if( sw5 == 1 ){
	  document.form1.help_button.disabled=true;	   // ヘルプ・ボタンの無効化
   }
   if( sw6 == 1 ){
	  document.form1.up_button.disabled=true; 	   // ↑・ボタンの無効化
   }
   if( sw7 == 1 ){
	  document.form1.down_button.disabled=true;	   // ↓・ボタンの無効化
   }
   if( sw8 == 1 ){
	  document.form1.left_button.disabled=true;			// ←・ボタンの無効化
   }
   if( sw9 == 1 ){
	  document.form1.right_button.disabled=true;		// →・ボタンの無効化
   }
}
//-----------------------------------------------------------------------------------
// 通常ボタン・有効指示（0:なにもしない　1:有効化する）
// Button1_enable(1,1,1,1,1,1,1,1,1);
//-----------------------------------------------------------------------------------
function Button1_enable( sw1,sw2,sw3,sw4,sw5,sw6,sw7,sw8,sw9 ){
   if( sw1 == 1 ){
	  document.form1.submit_button1.disabled=false;	  // ボタン１の有効化
   }
   if( sw2 == 1 ){
	  document.form1.submit_button2.disabled=false;	  // ボタン２の有効化
   }
   if( sw3 == 1 ){
	  document.form1.submit_button3.disabled=false;	  // ボタン３の有効化
   }
   if( sw4 == 1 ){
	  document.form1.submit_button4.disabled=false;	  // ボタン４の有効化
   }
   if( sw5 == 1 ){
	  document.form1.submit_button5.disabled=false;	  // ボタン５の有効化
   }
   if( sw6 == 1 ){
	  document.form1.submit_button6.disabled=false;	  // ボタン６の有効化
   }
   if( sw7 == 1 ){
	  document.form1.submit_button7.disabled=false;	  // ボタン７の有効化
   }
   if( sw8 == 1 ){
	  document.form1.submit_button8.disabled=false;	  // ボタン８の有効化
   }
   if( sw9 == 1 ){
	  document.form1.submit_button9.disabled=false;	  // ボタン９の有効化
   }
}
//-----------------------------------------------------------------------------------
// 共通ボタン・有効指示（0:なにもしない　1:有効化する）
// Button2_disable(1,1,1,1,1,1,1,1,1);
//-----------------------------------------------------------------------------------
function Button2_enable( sw1,sw2,sw3,sw4,sw5,sw6,sw7,sw8,sw9 ){
   if( sw1 == 1 ){
	  document.form1.close_button.disabled=false; 		// 閉じる・ボタンの有効化
   }
   if( sw2 == 1 ){
	  document.form1.dl_button.disabled=false;			// ﾀﾞｳﾝﾛｰﾄﾞ・ボタンの有効化
   }
//   if( sw3 == 1 ){
//	  document.form1.mn_button.disabled=false;			// メニューへ・ボタンの有効化
//   }
   if( sw4 == 1 ){
	  document.form1.prt_button.disabled=false;			// 印刷・ボタンの有効化
   }
   if( sw5 == 1 ){
	  document.form1.help_button.disabled=false;		// ヘルプ・ボタンの有効化
   }
   if( sw6 == 1 ){
	  document.form1.up_button.disabled=false;			// ↑・ボタンの有効化
   }
   if( sw7 == 1 ){
	  document.form1.down_button.disabled=false;		// ↓・ボタンの有効化
   }
   if( sw8 == 1 ){
	  document.form1.left_button.disabled=false;		// ←・ボタンの有効化
   }
   if( sw9 == 1 ){
	  document.form1.right_button.disabled=false;	 	// →・ボタンの有効化
   }
}
//-----------------------------------------------------------------------------------
// メッセージＨＴＭＬ出力・開始指示
// msg_start( 740, 1);
//			  |    |
//			  |    +--->（0:MENUなし 1:MENUあり）
//			  +--------> width size
//-----------------------------------------------------------------------------------
function msg_start( w_size, w_sw ){
   document.write('<TABLE style="width:' + w_size + 'px; border-spacing:0;"><TBODY><TR style="text-valign:top;"><TD style="text-align:left; padding:0;">');
   if( w_sw != "0" ){
	  document.write('<INPUT type="button" value="MENU" onclick="this.value=menu_on_off(this.value);"');
	  document.write(' style={width:3EM; font-size:8pt; text-align:center; "ＭＳゴシック";height:20px;}>');
   }
   document.write('<TEXTAREA rows="1" cols="68" id="msg_out"');
   document.write(' style="color:red;background-color:#F0FFFF;height:20px;font-size:8pt;overflow:auto;overflow-x:hidden;"');
   //document.write(' onfocus="this.style.height=50"');
   //document.write(' onblur="this.style.height=20;" readonly>');
   document.write(" onfocus=\"cngheight('msg_out','50px');\"");
   document.write(" onblur=\"cngheight('msg_out', '20px');\" readonly>");
}

function cngheight(id, val) {
	document.getElementById(id).style.height = val;
}
//-----------------------------------------------------------------------------------
// メッセージＨＴＭＬ出力
// msg_print("X0001" , "xxxxxx");
//			   |	   |
//			   |	   +---> Message2
//			   +---> Message1（エラー・コード等に使用）
//-----------------------------------------------------------------------------------
function msg_print( m_id, add_msg ){
   document.write( m_id + " " + add_msg + "\n" );
//	 document.write( m_id + " " + add_msg );
}
//-----------------------------------------------------------------------------------
// メッセージＨＴＭＬ出力・終了指示
// msg_end( '取消','登録','TEST2','TEST3','TEST4','TEST5','TEST6','TEST7');
//			  | 	 |		|		|		|		|		|		|
//			  | 	 |		|		|		|		|		|		+--->submit_button7 に表示する名称
//			  | 	 |		|		|		|		|		+--->submit_button6 に表示する名称
//			  | 	 |		|		|		|		+--->submit_button5 に表示する名称
//			  | 	 |		|		|		+--->submit_button4 に表示する名称
//			  | 	 |		|		+--->submit_button3 に表示する名称
//			  | 	 |		+--->submit_button2 に表示する名称
//			  | 	 +--->submit_button1 に表示する名称
//			  -------->reset_button  に表示する名称
// ※名称なしだとボタンを配置しません。
//-----------------------------------------------------------------------------------
function msg_end( res_nm, nm1, nm2, nm3, nm4, nm5, nm6, nm7, nm8, nm9){
   document.write('</TEXTAREA>');
   document.write('</TD><TD style="text-align:right;">');
   if( res_nm != '' ){
	  document.write('<INPUT type="button" name="reset_button" value="' + res_nm + '" class="BUTTON" onclick="input_clear();">');
   }
   if( nm1 != '' ){
	  document.write('<INPUT type="button" name="submit_button1" value="' + nm1 + '" class="BUTTON" onclick="submit_chk1();">');
   }
   if( nm2 != '' ){
	  document.write('<INPUT type="button" name="submit_button2" value="' + nm2 + '" class="BUTTON" onclick="submit_chk2();">');
   }
   if( nm3 != '' ){
	  document.write('<INPUT type="button" name="submit_button3" value="' + nm3 + '" class="BUTTON" onclick="submit_chk3();">');
   }
   if( nm4 != '' ){
	  document.write('<INPUT type="button" name="submit_button4" value="' + nm4 + '" class="BUTTON" onclick="submit_chk4();">');
   }
   if( nm5 != '' ){
	  document.write('<INPUT type="button" name="submit_button5" value="' + nm5 + '" class="BUTTON" onclick="submit_chk5();">');
   }
   if( nm6 != '' ){
	  document.write('<INPUT type="button" name="submit_button6" value="' + nm6 + '" class="BUTTON" onclick="submit_chk6();">');
   }
   if( nm7 != '' ){
	  document.write('<INPUT type="button" name="submit_button7" value="' + nm7 + '" class="BUTTON" onclick="submit_chk7();">');
   }
   document.write('</TD></TR>');
   document.write('</TBODY></TABLE>');
}
//-----------------------------------------------------------------------------------
// メッセージＧＥＴ
// wk = msg_get("X0001");
//			|
//			+-----> Message区分（N:Normal W:Warning F:fatal S:System )
// 見つからない場合、"F0000 エラーが有ります。" が返されます。
//-----------------------------------------------------------------------------------
function msg_get( m_id ){
		msg_id			=		new Array();
		msg_nn			=		new Array();
		i=0;msg_id[i]="N0000";	msg_nn[i]="N0000 次の入力をしてください。";

		i++;msg_id[i]="F0000";	msg_nn[i]="F0000 エラーが有ります。";
		i++;msg_id[i]="F0001";	msg_nn[i]="F0001 入力に誤りが有ります。";
		i++;msg_id[i]="F0002";	msg_nn[i]="F0002 入力に誤りが有ります。（数値以外不可）";
		i++;msg_id[i]="F0003";	msg_nn[i]="F0003 入力に誤りが有ります。（最大値）";
		i++;msg_id[i]="F0004";	msg_nn[i]="F0004 入力に誤りが有ります。（最小値）";
		i++;msg_id[i]="F0005";	msg_nn[i]="F0005 日付が正しくありません。";
		i++;msg_id[i]="F0006";	msg_nn[i]="F0006 月は、１～１２の範囲であること。";
		i++;msg_id[i]="F0007";	msg_nn[i]="F0007 日は、１～３１の範囲であること。";
		i++;msg_id[i]="F0008";	msg_nn[i]="F0008 日付が正しくありません。（月日エラー）";
		i++;msg_id[i]="F0009";	msg_nn[i]="F0009 閏年チェック・エラー";
		i++;msg_id[i]="F0010";	msg_nn[i]="F0010 入力に誤りが有ります。（マイナス不可）";
		i++;msg_id[i]="F0011";	msg_nn[i]="F0011 入力に誤りが有ります。（空白、ZERO不可）";
		i++;msg_id[i]="F0012";	msg_nn[i]="F0012 入力に誤りが有ります。（バイト数チェック）";
		i++;msg_id[i]="F0013";	msg_nn[i]="F0013 開始日≦終了日であること。";
		i++;msg_id[i]="F0014";	msg_nn[i]="F0014 入力に誤りが有ります。（空白不可）";
		i++;msg_id[i]="F0015";	msg_nn[i]="F0015 入力に誤りが有ります。（整数値以外不可）";
		i++;msg_id[i]="F0016";	msg_nn[i]="F0016 入力に誤りが有ります。（全角カナ以外不可）";
		i++;msg_id[i]="F0017";	msg_nn[i]="F0017 入力に誤りが有ります。（全角以外不可）";
		i++;msg_id[i]="F0018";	msg_nn[i]="F0018 入力に誤りが有ります。（半角英数字以外不可）";
		i++;msg_id[i]="F0019";	msg_nn[i]="F0019 半角文字が混在しています。";
		i++;msg_id[i]="F0020";	msg_nn[i]="F0020 半角カナが混在しています。";
		i++;msg_id[i]="F0021";	msg_nn[i]="F0021 エラー行は、分割できません。";
		i++;msg_id[i]="F0022";	msg_nn[i]="F0022 この行は、分割できません。";
		i++;msg_id[i]="F0023";	msg_nn[i]="F0023 数量が１以下の行は、分割できません。";
		i++;msg_id[i]="F0024";	msg_nn[i]="F0024 最大行を超える分割は、できません。";
		i++;msg_id[i]="F0025";	msg_nn[i]="F0025 ";
		i++;msg_id[i]="F0026";	msg_nn[i]="F0026 使用禁止文字が有ります。";
		i++;msg_id[i]="F0027";	msg_nn[i]="F0027 カナ文字入力不可文字があります。";

		i++;msg_id[i]="S0000";	msg_nn[i]="S0000 システムエラーです。";
		i++;msg_id[i]="S0001";	msg_nn[i]="S0001 ＮＯ＿ＤＡＴＡ＿ＦＯＵＮＤ！！";
		i++;msg_id[i]="S0002";	msg_nn[i]="S0002 ＤＢ更新（INSERT）エラーです。";
		i++;msg_id[i]="S0003";	msg_nn[i]="S0003 ＤＢ更新（UPDATE）エラーです。";
		i++;msg_id[i]="S0004";	msg_nn[i]="S0004 ＤＢ更新（DELETE）エラーです。";
		i++;msg_id[i]="S0005";	msg_nn[i]="S0005 データ取得エラー（too_many_rows等）です。";
		i++;msg_id[i]="S0006";	msg_nn[i]="S0006 その他のシステムエラーです。";
		i++;msg_id[i]="S0007";	msg_nn[i]="S0007 ログ出力・更新エラーです。";
		i++;msg_id[i]="S0008";	msg_nn[i]="S0008 ログ出力・セキュリティＤＢ切替処理中です。";
		i++;msg_id[i]="S0009";	msg_nn[i]="S0009 ログ出力・セキュリティシステムトラブルのためサービス停止中です。";
		i++;msg_id[i]="S0010";	msg_nn[i]="S0010 ログ出力・セキュリティシステムマシン保守のためサービス停止中です。";
		i++;msg_id[i]="S0011";	msg_nn[i]="S0011 ログ出力・チェック・ファンクションが見つかりません。";
		i++;msg_id[i]="S0012";	msg_nn[i]="S0012 サービス時間外です。";
		i++;msg_id[i]="S0013";	msg_nn[i]="S0013 バッチ処理中のためサービス停止中です。";
		i++;msg_id[i]="S0014";	msg_nn[i]="S0014 トラブルのためサービス停止中です。";
		i++;msg_id[i]="S0015";	msg_nn[i]="S0015 マシン保守のためサービス停止中です。";
		i++;msg_id[i]="S0016";	msg_nn[i]="S0016 セキュリティＤＢ切替処理中です。";
		i++;msg_id[i]="S0017";	msg_nn[i]="S0017 セキュリティシステムトラブルのためサービス停止中です。";
		i++;msg_id[i]="S0018";	msg_nn[i]="S0018 セキュリティシステムマシン保守のためサービス停止中です。";
		i++;msg_id[i]="S0019";	msg_nn[i]="S0019 排他制御エラー（終了して下さい）。";

		i++;msg_id[i]="W0001";	msg_nn[i]="W0001 更新せずに遷移します。";
		i++;msg_id[i]="W0002";	msg_nn[i]="W0002 最新のバージョンを使用して下さい。!!";
		i++;msg_id[i]="W0003";	msg_nn[i]="W0003 Microsoft Internet Explorerを使用して下さい。";
		i++;msg_id[i]="W0004";	msg_nn[i]="W0004 データがありません。";
		i++;msg_id[i]="W0005";	msg_nn[i]="W0005 小数点以下一部を切り捨てました。";
		tbl_len 				=		msg_id.length;
		tbl_nn = "F0000 エラーが有ります。";
		for( i=0;i<tbl_len;i++ ){
				if( msg_id[i] == m_id ){
						tbl_nn = msg_nn[i];
						return tbl_nn;
				}
		}
   return tbl_nn;
}
//-----------------------------------------------------------------------------------
// 共通ヘッダー出力
// Header(Sxybbnnn,"",w_size,x,x,x,x,x)
//			|	   |  | 	 | | | | |
//			|	   |  | 	 | | | | +---> 日付時刻 SW
//			|	   |  | 	 | | | +-----> HELP SW
//			|	   |  | 	 | | +-------> 印刷 SW
//			|	   |  | 	 | +---------> 終了 SW
//			|	   |  | 	 +-----------> ﾒﾆｭｰ SW
//			|	   |  +-----> ヘッダー横幅（通常は、740）
//			|	   +-----> 画面補足文字列
//			+------------> 画面ＩＤ
//-----------------------------------------------------------------------------------
function Header( g_id, g_txt, w_size, sw1, sw2, sw3, sw4, sw5 ){

//	******************* Tomcat / iAS の修正個所 START *******************************

//	var A_PASS = "/NASApp/XYKJ/contdata/XYKJ/class/";	// ***** OAS   用のAPPLET_PASS、*****
	var A_PASS = "/XYKJ/contdata/XYKJ/class/";	// ***** OAS   用のAPPLET_PASS、*****
//	var A_PASS = "/contdata/XYKJ/class/";				// ***** iAS APPLET_PASS *****

//	******************* Tomcat / iAS の修正個所 END   *******************************

	g_name = G_name( g_id );	// *** 画面名称取得 ***
	document.write('<TABLE style="width:' + w_size + 'px; background-color:#0000FF;" >');
	document.write('<TR style="height:22px;">');
	document.write('<TD style="background-color:#0000FF;width:150px;height:20px;text-align:left;color:white;font-weight:bold; ＭＳゴシック;white-space:nowrap;">');
	document.write('画面ＩＤ：' + g_id + '</TD>');
	document.write('<TD id="gamen_nn" style="background-color:#0000FF;height:20px;text-align:left;color:white;font-weight:bold; ＭＳゴシック;white-space:nowrap;">');
	document.write('画面名：' + g_name + g_txt + '</TD>');
	document.write('<TD style="background-color:#0000FF;width:140px;height:20px;text-align:center;color:white; ＭＳゴシック;white-space:nowrap;">');
	if( sw1 == 1 ){   // ﾒﾆｭｰ ＳＷ？
		document.write('<A HREF="javascript:menu_func();" TARGET=_parent STYLE="text-decoration:none"><SPAN TITLE="メニューへ戻ります。"><div style="color:yellow;"> ﾒﾆｭｰ </div></SPAN></A>');
	}
	if( sw2 == 1 ){   // 終了 ＳＷ？
		document.write('<A HREF="javascript:top_close();" TARGET=_parent STYLE="text-decoration:none"><SPAN TITLE="この画面を閉じます。"><div style="color:yellow;"> 終了 </div></SPAN></A>');
	}
	if( sw3 == 1 ){   // 印刷 ＳＷ？
		document.write('<A HREF="javascript:print_func();" TARGET=_parent STYLE="text-decoration:none"><SPAN TITLE="この画面をハードコピー印刷します。"><div style="color:yellow;"> 印刷 </div></SPAN></A>');
	}
	if( sw4 == 1 ){   // HELP ＳＷ？
		document.write('<A HREF="javascript:help_func();" TARGET=_parent STYLE="text-decoration:none"><SPAN TITLE="ヘルプ画面を開きます。"><div style="color:red;"> HELP </div></SPAN></A>');
	}
	if( sw5 == 1 ){   // 日付時刻 ＳＷ？
		document.write('</TD>');
		document.write('<TD id="_times" style="background-color:#B0C4DE;width:146px;height:20px;text-align:center;color:black;font-size:10pt;font-family:Gulim;white-space:nowrap;">');
		document.write('<span id="current" style="font-size:10pt;color:#000000;background-color:#B0C4DE;"></span>');
	}
	document.write('</TD>');
	document.write('</TR>');
	document.write('</TABLE>');
}
//-----------------------------------------------------------------------------------
// 共通ヘッダー出力
// Replace_Header(Sxybbnnn,"xxxx")
//				  | 		|
//				  | 		+-----> 画面補足文字列
//				  +---------------> 画面ＩＤ
//-----------------------------------------------------------------------------------
function Replace_Header( g_id, g_txt ){
	g_name = G_name( g_id );	// *** 画面名称取得 ***
	document.all.gamen_nn.innerText = '画面名：' + g_name + g_txt;
}
//-----------------------------------------------------------------------------------
// 共通メニュー・スタート指示
// Menu_start()
//-----------------------------------------------------------------------------------
function Menu_start(){
	var x0 = 160;
	var x1 = x0 - 2;
	var x2 = x0 - 4;
	document.write('<TABLE style="table-layout:fixed; width:' + x0 + 'px; border:1; border-spacing:0;" >');
	document.write('<COL style="WIDTH:' + x1 + 'px"><TBODY><TR><TD style="text-valign:top; padding:0;">');
	document.write('<DIV style="width: ' + x2 + 'px; height: 440px; overflow:auto;">');
	document.write('<TABLE style="table-layout:fixed; width:' + x2 + 'px; border-spacing:0;" class="MENU" >');
	document.write('<COL style="WIDTH:4px"><COL style="WIDTH:110px"><TBODY>');
}
//-----------------------------------------------------------------------------------
// 共通メニュー・ヘッダー出力
// Menu_head(SXEbbnnn)
//			  |
//			  +---> 画面ＩＤ
//-----------------------------------------------------------------------------------
function Menu_head( g_id ){
	g_name = G_name( g_id );	// *** 画面名称取得 ***
	s_name = S_name( g_id );	// *** Servlet_id取得 ***
	s_name2 = "'" + $servlet_path + s_name + "'";
	document.write('<TR><TD colspan="2" style="font-size:8pt;text-align:left;white-space:nowrap; padding:0;"><A href="javascript:subopen(' + s_name2 + ');">' + g_name + '</A></TD></TR>');
}
//-----------------------------------------------------------------------------------
// 共通メニュー行・出力
// Menu_data(SXEbbnnn)
//			  |
//			  +---> 画面ＩＤ
//-----------------------------------------------------------------------------------
function Menu_data( g_id ){
	g_name = G_name( g_id );	// *** 画面名称取得 ***
	s_name = S_name( g_id );	// *** Servlet_id取得 ***
	s_name2 = "'" + $servlet_path + s_name + "'";
	document.write('<TR><TD style="white-space:nowrap; padding:0;">&nbsp;</TD><TD style="font-size:8pt;text-align:left;white-space:nowrap; padding:0;"><A href="javascript:subopen(' + s_name2 + ');">' + g_name + '</A></TD></TR>');
}
//-----------------------------------------------------------------------------------
// 共通メニュー・終了指示
// Menu_end()
//-----------------------------------------------------------------------------------
function Menu_end(){
	document.write('</TBODY></TABLE></DIV></TBODY></TABLE>');
	document.write('</TD><TD style="text-align:left; text-valign:top; padding:0;">');
}
//-----------------------------------------------------------------------------------
// 共通メニュー　ＯＮ／ＯＦＦ
// menu_on_off();
//-----------------------------------------------------------------------------------
function menu_on_off( wk ){
   if( wk == 'MENU' ){
	  document.all.menu_col.style.width=165;
	  return '閉じる';
   }else{
	  document.all.menu_col.style.width=0.1;
	  return 'MENU';
   }
}
//-----------------------------------------------------------------------------------
// 画面名称取得
// G_name(SXEbbnnn)
//			|
//			+---> 画面ＩＤ
//-----------------------------------------------------------------------------------
function G_name( g_id ){
	gamen_id			=	new Array();
	gamen_nn			=	new Array();
//	********** << XYSF >> ***********************************************************
	i=0;gamen_id[i]="Sxysf00000";	gamen_nn[i]="ダミー・画面";
	i=0;gamen_id[i]="Sxysf01101";	gamen_nn[i]="共通・ＤＲ－ＳＶ検索・画面";
	i++;gamen_id[i]="Sxysf01201";	gamen_nn[i]="共通・ＳＶ－店舗検索・画面";
	i++;gamen_id[i]="Sxysf01301";	gamen_nn[i]="共通・ＤＥＰＴ－ＣＬＡＳＳ検索・画面";
	i++;gamen_id[i]="Sxysf01401";	gamen_nn[i]="共通・ＣＬＡＳＳ－ＳＵＢＣＬＡＳＳ検索・画面";
	i++;gamen_id[i]="Sxysf01501";	gamen_nn[i]="共通・ＤＲ－店舗検索・画面";
	i++;gamen_id[i]="Sxysf01601";	gamen_nn[i]="共通・ＤＲ－オーナー検索・画面";
	i++;gamen_id[i]="Sxysf99991";	gamen_nn[i]="共通・サービス時間チェック・画面";
	i++;gamen_id[i]="Sxysf99999";	gamen_nn[i]="共通・エラー・画面";
//	********** << XYDL >> ***********************************************************
	i++;gamen_id[i]="Sxydl01901";	gamen_nn[i]="190 預り金実績照会";
	i++;gamen_id[i]="Sxydl01902";	gamen_nn[i]="190 預り金実績照会（ＰＳＡ）";
	i++;gamen_id[i]="Sxydl01903";	gamen_nn[i]="190 預り金実績照会・コンテンツ別";
	i++;gamen_id[i]="Sxydl01904";	gamen_nn[i]="190 預り金実績照会（ＰＳＡ）・コンテンツ別";
	i++;gamen_id[i]="Sxydl02101";	gamen_nn[i]="210 ＤＥＰＴ別管理（速報一覧）";
	i++;gamen_id[i]="Sxydl02102";	gamen_nn[i]="210 ＤＥＰＴ別管理（売上・荒利）";
	i++;gamen_id[i]="Sxydl02103";	gamen_nn[i]="210 ＤＥＰＴ別管理（仕入）";
	i++;gamen_id[i]="Sxydl02104";	gamen_nn[i]="210 ＤＥＰＴ別管理（在庫）";
	i++;gamen_id[i]="Sxydl02201";	gamen_nn[i]="220 ＤＥＰＴ別管理（月次）売上・荒利";
	i++;gamen_id[i]="Sxydl02202";	gamen_nn[i]="220 ＤＥＰＴ別管理（月次）仕入";
	i++;gamen_id[i]="Sxydl02203";	gamen_nn[i]="220 ＤＥＰＴ別管理（月次）在庫";
	i++;gamen_id[i]="Sxydl02301";	gamen_nn[i]="230 営業速報";
	i++;gamen_id[i]="Sxydl02401";	gamen_nn[i]="240 値引クーポン実績照会（日別）";
	i++;gamen_id[i]="Sxydl02501";	gamen_nn[i]="250 値引クーポン実績照会（週別）";
	i++;gamen_id[i]="Sxydl02601";	gamen_nn[i]="260 取消・返金・中止状況";
	i++;gamen_id[i]="Sxydl04101";	gamen_nn[i]="410 指定商品発注状況";
	i++;gamen_id[i]="Sxydl04201";	gamen_nn[i]="420 新商品未導入店一覧";
	i++;gamen_id[i]="Sxydl06101";	gamen_nn[i]="610 店別売上概況情報";
	i++;gamen_id[i]="Sxydl07101";	gamen_nn[i]="710 ＳＶ別入金状況確認";
	i++;gamen_id[i]="Sxydl07201";	gamen_nn[i]="720 入金ワーニング";
//	********** << XBKK >> ***********************************************************
	i++;gamen_id[i]="Sxbkk00101";	gamen_nn[i]="010 個店カルテ";
	i++;gamen_id[i]="Sxbkk03101";	gamen_nn[i]="310 マスター入力";
	i++;gamen_id[i]="Sxbkk03201";	gamen_nn[i]="320 一覧表表示";
	i++;gamen_id[i]="Sxbkk03301";	gamen_nn[i]="330 帳票出力";
//	********** << XBTY >> ***********************************************************
	i++;gamen_id[i]="Sxbty01101";	gamen_nn[i]="110 個店予算入力";
	i++;gamen_id[i]="Sxbty01201";	gamen_nn[i]="120 個店予算実績比較表";
	i++;gamen_id[i]="Sxbty01301";	gamen_nn[i]="130 帳票出力";
	i++;gamen_id[i]="Sxbty01311";	gamen_nn[i]="131 個店予算表";
	i++;gamen_id[i]="Sxbty01321";	gamen_nn[i]="個店予算実績比較表";
	i++;gamen_id[i]="Sxbty01331";	gamen_nn[i]="月別実績一覧表";
	i++;gamen_id[i]="Sxbty01401";	gamen_nn[i]="個店別予算未入力店一覧";
	i++;gamen_id[i]="Sxbty01501";	gamen_nn[i]="達成率異常店舗一覧";
	i++;gamen_id[i]="Sxbty01601";	gamen_nn[i]="予算修正状況一覧";
	i++;gamen_id[i]="Sxbty03101";	gamen_nn[i]="個人別数値項目評価";
	i++;gamen_id[i]="Sxbty03201";	gamen_nn[i]="所属別数値項目評価";
//	********** << XBKM >> ***********************************************************
	i++;gamen_id[i]="Sxbkm01101";	gamen_nn[i]="加盟店持株会その１";
	i++;gamen_id[i]="Sxbkm01201";	gamen_nn[i]="加盟店持株会その２";
//	********** << XBHS >> ***********************************************************
	i++;gamen_id[i]="Sxbhs01101";	gamen_nn[i]="計画入力";
	i++;gamen_id[i]="Sxbhs01201";	gamen_nn[i]="計画・実績照会";
//	********** << XYKJ >> ***********************************************************
/* ADD START KOGA 2004/03/17 */
	i++;gamen_id[i]="Sxykj00101";	gamen_nn[i]="010 利用者コード～取引先コード登録";
/* ADD START KOGA 2004/03/17 */
	i++;gamen_id[i]="Sxykj01101";	gamen_nn[i]="110 DEPT-CLASS-SUBCLASS-発行形態";
	i++;gamen_id[i]="Sxykj01201";	gamen_nn[i]="120 出版社－発行形態";
	i++;gamen_id[i]="Sxykj01301";	gamen_nn[i]="130 出版社　銘柄／発売日 (全国）別";
	i++;gamen_id[i]="Sxykj02101";	gamen_nn[i]="210 DEPT-CLASS-SUBCLASS-発行形態";
	i++;gamen_id[i]="Sxykj02201";	gamen_nn[i]="220 新聞社－発行形態";
	i++;gamen_id[i]="Sxykj02301";	gamen_nn[i]="230 新聞社　商品 (全国）別";

	gamen_len			=	gamen_id.length;
	gamen_name = "";
	for( i=0;i<gamen_len;i++ ){
		if( gamen_id[i] == g_id ){
			gamen_name = gamen_nn[i];
		}
	}
   return gamen_name;
}
// --------------------------------------
// SUB-WINDOW OPEN
// SW(0:確認なし　1:確認あり）
// --------------------------------------
function subopen( apurl, sw ){
	if( sw == 0 ){
		document.form1.action= apurl;
		document.form1.target='_self';
		document.form1.method = "POST";
		document.form1.submit();
		return;
	}
	subopen_flag=confirm("選択の業務画面に移動しますか？\n現在の画面が更新系画面の場合、\n入力途中のデータは無効になります。");
	if( subopen_flag == true ){
		document.form1.action= apurl;
		document.form1.target='_self';
		document.form1.method = "POST";
		document.form1.submit();
	}
}
// --------------------------------------
// APP-WINDOW OPEN
// --------------------------------------
function app_open( apurl ){
	para=' Width=800, Height=600, top=0, left=0, scrollbars=yes, resizable=yes, titlebar=no , status=yes';
//	***************** 以下をServlet用に修正する。 ************************
	sub_win=window.open( apurl,"app",para);

}
//-----------------------------------------------------------------------------------
// 終了ファンクション
//-----------------------------------------------------------------------------------
function top_close(){
//		  top_close_flag=confirm("終了しますか？");
//		  if( top_close_flag == true ){
				top.close();
//		  }
}
//-----------------------------------------------------------------------------------
// 印刷ファンクション
//-----------------------------------------------------------------------------------
function print_func(){
		print_func_flag=confirm("印刷しますか？");
		if( print_func_flag == true ){
				print();
		}
}
//-----------------------------------------------------------------------------------
// バージョン・チェック
// Call Format : ver_chk();
// Return　(2:IE3 or NETSCAPE2	  3:IE4 or NETSCAPE3	4:IE5 or NETSCAPE4）
//-----------------------------------------------------------------------------------
function ver_chk(){
/*
   ver=0;// 2:IE3 or NETSCAPE2	  3:IE4 or NETSCAPE3	4:IE5 or NETSCAPE4
   ver = navigator.appVersion.charAt(0);
   navi=navigator.appName;
   if(navi=="Microsoft Internet Explorer"){
	  ver++;
   }
   if(ver<4){
				msg('W0002','（ このバージョンは、'+ver+'です。）');
   }
   if(ver>=4){
	  if(navi=="Microsoft Internet Explorer"){
	  }else{
				msg('W0003','（ これは、'+navi+'です。）');
	  }
   }
   return ver;
*/
   var ret = 9;
   var navi = window.navigator.userAgent.toLowerCase();
   var ver = window.navigator.appVersion.toLowerCase();
   
   if (navi.indexOf("edge") >= 0) {
	   ret = 0;
   } else if (navi.indexOf("msie") >= 0) {
	   msg('W0002','（ このバージョンは、' + ver + 'です。）');
   } else {
	   msg('W0003','（ これは、' + navi + 'です。）');
   }
   
   return ret;

}
//-----------------------------------------------------------------------------------
// NUMERIC CHECK（アンサーバックなし）
// コールフォーマット：num_chk(n,1);
//							   | |
//							   | +---> alert_sw
//							   +--->Field_name
// Return　(true:エラーなし　false:エラーあり）
//-----------------------------------------------------------------------------------
function num_chk(n,alert_sw){
   var nn = n;
   if(isNaN(nn.value)){
	  if(alert_sw == 1){msg('F0002','');}		// F0002 入力に誤りが有ります。（数値入力）
	  nn.style.background='pink';nn.focus();
	  return false;
   }else{
	  nn.style.background='white';
	  return true;
   }
}
//-----------------------------------------------------------------------------------
// 日付時刻計算
// コールフォーマット：wk = date_calc(n);
//						|			  |
//						|			  |
//						|			  +--->Field_name
//						|
//						+--->1970/01/01 00:00:00～のミリ秒を返します。
// 【注意】
// ・入力する日付は YY/MM/DD YYYY/MM/DD YY-MM-DD YYY-MM-DD のいずれかの形式であること。
// ・エラーの場合は、-9999999999999を返します。（１３桁）
//	 (例) if( date_calc(aaaa) == -9999999999999 ){
//			 alert("***日付エラー***");
//		  }
// ・0000/00/00、9999/99/99 はＯＫとします。
//-----------------------------------------------------------------------------------
function date_calc(n){
   var nn = n;
   dateStr = nn.value;
   var datePat = /^(\d{2}|\d{4})(\/|-)(\d{1,2})\2(\d{1,2})$/;
   var matchArray = dateStr.match(datePat); // is the format ok?
   if (matchArray == null) {
	  return -9999999999999; // *** ERROR ***
   }
   year  = matchArray[1]; // parse date into variables
   month = matchArray[3];
   day	 = matchArray[4];
   if( date_chk(nn,0) ){
	  wk = Date.UTC( year, month, day, 0, 0, 0);
	  return wk;
   }else{
	  return -9999999999999; // *** ERROR ***
   }
}
//-----------------------------------------------------------------------------------
// MIN MAX CHECK
// コールフォーマット：min_max_chk(n,min,max);
//								   |  |   |
//								   |  |   +-->最大値
//								   |  +--->最小値
//								   +---> Field_name
// Return　(true:エラーなし　false:エラーあり）
//-----------------------------------------------------------------------------------
function min_max_chk(n,min,max){
   var nn = n;
		if(isNaN(nn.value)){
				msg('F0002','');		// F0002 入力に誤りが有ります。（数値入力）
				nn.style.background='pink';nn.focus();
				return false;
		}else{
				if( nn.value > max ){
						msg('F0003','');		// F0003 入力に誤りが有ります。（最大値）
						nn.style.background='pink';nn.focus();
						return false;
		}
				if( nn.value < min ){
						msg('F0004','');		// F0004 入力に誤りが有ります。（最小値）
						nn.style.background='pink';nn.focus();
						return false;
		}
				nn.style.background='white';
				return true;
   }

}
//---------------------------------------------------
// ３桁カンマ編集
// (例) wk = com3( "12345");
//					  |
//					  +--- 編集したい元データ
//---------------------------------------------------
function com3(in_data){
		var v_result = "";
		var v_offset = 0;		// 0:>=0, 1:<0
		var v_point;			// decimal point

		in_data = "" + parseFloat(in_data);
		if (in_data < 0){v_offset = 1;}
		v_point = in_data.indexOf(".", 0);

		if (v_point >=0){
				v_result = in_data.substring(v_point, in_data.length);
				v_point = v_point - 1;
		} else {
				v_point = in_data.length - 1;
		}

		for (var i=v_point; i>=v_offset;){
				for (var j=0; j<3; j++){
						v_result = in_data.charAt(i--) + v_result;
						if (i < v_offset){break;}
				}
				if (j == 3){v_result = "," + v_result;}
		}

		if (v_offset == 1){v_result = "-" + v_result;}
		return(v_result);
}

/***********************************************************
 *関数名：com3_chk(n)
 *				   |
 *				   |
 *				   |
 *				   +--->Field_name
 *
 *機能　：３桁カンマ編集
 *
 *Return　(true:エラーなし　false:エラーあり）
 ***********************************************************/

function com3_chk(n){
   var nn = n;
   var in_data;
   var v_result = "";
   var v_offset = 0;	   // 0:>=0, 1:<0
   var v_point; 		   // decimal point

	if( num_chk(nn,1) == false ){ return false;}

		if (nn.value == "") {
				nn.style.background='white';
		} else if (nn.style.background != 'pink') {
				in_data = nn.value;

				in_data = "" + parseFloat(in_data);
				if (in_data < 0){v_offset = 1;}
				v_point = in_data.indexOf(".", 0);

				if (v_point >=0){
						v_result = in_data.substring(v_point, in_data.length);
						v_point = v_point - 1;
				} else {
						v_point = in_data.length - 1;
				}

				for (var i=v_point; i>=v_offset;){
						for (var j=0; j<3; j++){
								v_result = in_data.charAt(i--) + v_result;
								if (i < v_offset){break;}
						}
						if (j == 3){v_result = "," + v_result;}
				}

				if (v_offset == 1){v_result = "-" + v_result;}

				nn.value = v_result;
				return true;

		}
}
//---------------------------------------------------------
// NUMERIC CHECK ＆ アンサーバック処理
// fix( nn, zero_flg, "999.99", 1);
//		|	 |			|		|
//		|	 |			|		+---> alert_sw
//		|	 |			+--->fmt
//		|	 +---> 'N' or 'Y'
//		+--->Field_name
// n	 :Element no.
// zero_flg:"N"（前ゼロ埋なし）
// fmt例【2001.11.30仕様見直し】
//	   -999.99 : 正・負入力可能。-999.99 から 999.99までＯＫ
//	   999.99  : 正のみ入力可能。0 から 999.99までＯＫ
//	   X	   : 文字列（注：レングスチェックはしません）
// Return　(true:エラーなし　false:エラーあり）
//---------------------------------------------------------
function fix(n, zero_flg, fmt, alert_sw){
   var nn = n;
   cond = fmt.substr(0,1);
   max = fmt.length;
   if(max < 1){return;}
   wk = nn.value;
   if( cond == "X" ){
	  nn.style.background='white';
	  return true;
   }
   if(isNaN(nn.value)){
				if(alert_sw == 1){msg('F0002','');} 	// F0002 入力に誤りが有ります。（数値入力）
				nn.style.background='pink';nn.focus();
				return false;
   }
   if(cond=='9'){
	  if(wk<0){
				if(alert_sw == 1){msg('F0010','');} 	// F0010 入力に誤りが有ります（マイナス不可）
				nn.style.background='pink';nn.focus();
				return false;
	  }
   }
   if(cond=='+' || cond=='0'){
				alert ("fix関数はフォーマット" + fmt + "のサポートはできません！")
				if(alert_sw == 1){msg('S0000','');} 	// システムエラー
				nn.style.background='pink';nn.focus();
				return false;
   }
// *** 整数部、小数部・分割処理 ***
   seisu_len =fmt.length;
   shousu_len=0;
   for(i=0;i<fmt.length;i++){
	  if(fmt.substr(i,1)=='.'){
		 seisu_len=i;
		 shousu_len=fmt.length-i-1;
	  }
   }
   if(cond=='-'){
	  seisu_len = seisu_len - 1; //負入力可能項目の場合先頭1バイトは符号用なので１を引く
   }
   if(shousu_len=='0' && Math.floor(nn.value) != nn.value ){
				if(alert_sw == 1){msg('F0015','');} 	// F0015 入力に誤りが有ります。（整数値入力）
				nn.style.background='pink';nn.focus();
				return false;
   }
// *** 範囲チェック ***
   if( shousu_len > 0 ){
	  seisu_max = 0;wk1 = 1;
	  for(i=0;i<seisu_len;i++){
		 wk1 = wk1 * 10 ;
		 seisu_max = wk1;
	  }
	  shousu_max = 0;wk1 = 1;
	  for(i=0;i<shousu_len;i++){
		 wk1 = wk1 * 10 ;
		 shousu_max = wk1;
	  }
	  if( seisu_max > 0 ){ seisu_max--; }
	  if( shousu_max > 0 ){ shousu_max--; wk2= parseFloat("0." + shousu_max);}
	  wk1 = seisu_max + wk2;
	  if( Number(nn.value) > wk1 ){
		   if(alert_sw == 1){
				msg('F0003','');		// F0003 入力に誤りが有ります。（最大値）
		   }
		   nn.style.background='pink';nn.focus();
		   return false;
	  }
	  wk2 = -1 * wk1;
	  if( Number(nn.value) < wk2 ){
		   if(alert_sw == 1){
				msg('F0004','');		// F0004 入力に誤りが有ります。（最小値）
		   }
		   nn.style.background='pink';nn.focus();
		   return false;
	  }
   }
// *** 少数点以下０埋め編集 ***
   i_str = Number( nn.value ) + ''; //きれいな数字に直す
   i_sei_str = Math.floor(Math.abs(i_str)) + '';
   i_sho_len = 0;
   if (i_sei_str == Math.abs(i_str)) {
	   if (shousu_len==0){
		   i_sho_str = '';
	   }else{
		   i_sho_str = ('.0000000000').substr(0,shousu_len+1);
	   }
   }else{
	   i_sho_str = "";i_sho_flg = "0";
	   for (i=0;i<i_str.length;i++){
		   if(i_str.substr(i,1)=="."){i_sho_flg="1"}
		   if(i_sho_flg=="1"){i_sho_str=i_sho_str+i_str.substr(i,1);i_sho_len++}
	   }
	   i_sho_len--;
	   i_sho_str = (i_sho_str + '0000000000').substr(0,shousu_len+1);
   }
// *** 少数点以上０埋め編集 ***
   if(zero_flg=='N'){
   }else{
	   i_sei_str = '00000000000000000000' + i_sei_str;
	   i_sei_str = i_sei_str.substr(i_sei_str.length-seisu_len,seisu_len);
   }
//
   if (i_str>=0) {
	   nn.value = i_sei_str + i_sho_str;
   }else{
	   nn.value = '-' + i_sei_str + i_sho_str;
   }
   if (i_sho_len>shousu_len) {
				if(alert_sw == 1){msg('W0005','');} 	// W0005 小数点以下一部を切り捨てました。
				nn.style.background='pink';nn.focus();
				return false;
   }
   nn.style.background='white';
   return true;
}
//-----------------------------------------------------------------------------------
// バイト数チェック
// コールフォーマット：StrLength(n,MaxSize,StrType,alert_sw)
//								 | |		   |   |
//								 | |		   |   +--->alert_sw 0or1
//								 | |		   +--->1b(半角のみ):all(全半角)
//								 | +--->最大バイト数
//								 +--->Field_name
//
// Return　(true:エラーなし　false:エラーあり）
// 注意　StrTypeが 1b , all 以外の場合は、何もせずに抜ける。
//-----------------------------------------------------------------------------------
function StrLength(n,MaxSize,StrType,alert_sw){
   var nn = n;
   var nCnt   = 0;																				   //.初期値
   var strMoji="";

   strMoji = frm.elements[n].value; 											   //対象文字列の取得

   switch (StrType){
   //半角文字のみの場合
   case "1b":
		   nCnt = strMoji.length;												   //対象文字数の取得
		   //.サイズが指定バイト数を越えている場合
		   if(nCnt > MaxSize){
				   if(alert_sw == 1){
						   msg('F0012',''); 	   // F0012 入力に誤りが有ります（バイト数チェック）
						   nn.style.background='pink';nn.focus();
						   nn.focus();
				   }
				   return false;
		   }
		   else{
				   nn.style.background='white';
				   return true;
		   }
   break;

		//半角・全角文字の場合
		case "all":
				//.1文字ずつ取り出して全角か半角か判断する。
				for(i=0; i<strMoji.length; i++){
						if (escape(strMoji.charAt(i)).length >= 4){
								nCnt+=2;
						}
						else{
								nCnt++;
						}
				}

				//.サイズが指定バイト数を越えている場合
				if(nCnt > MaxSize){
						if(alert_sw == 1){
								msg('F0012','');		// F0012 入力に誤りが有ります（バイト数チェック）
						}
								nn.style.background='pink';nn.focus();
								nn.focus();
								return false;
				}
				else{
						nn.style.background='white';

						return true;
				}
		break;
		}
}
//-----------------------------------------------------------------------------------
// 開始日≦終了日チェック
// コールフォーマット：yuko_chk(FromDate,ToDate,alert_sw);
//								  | 		  | 	 |
//								  | 		  | 	 +--->alert_sw 0or1
//								  | 		  +--->終了日Field_name
//								  +--->開始日Field_name
//								
// Return　(true:エラーなし　false:エラーあり）
//-----------------------------------------------------------------------------------
function yuko_chk(FromDateNo,ToDateNo,alert_sw) {
   var nn1 = FromDateNo;
   var nn2 = ToDateNo;
   wk1 = date_calc(nn1);
   wk2 = date_calc(nn2);
   if( wk1 == -9999999999999 ){
		if(alert_sw == 1){
			msg('F0005','');		// F0005 日付が正しくありません。
		}
		nn1.style.background='pink';
		nn1.focus();
		return false;
	}
	if( wk2 == -9999999999999 ){
		if(alert_sw == 1){
			msg('F0005','');		// F0005 日付が正しくありません。
		}
		nn2.style.background='pink';
		nn2.focus();
		return false;
	}
		if( wk1 > wk2 ){
				if(alert_sw == 1){
						msg('F0013','');		// F0013 開始日≦終了日であること。
				}
				nn1.style.background='pink';nn1.focus();
				nn2.style.background='pink';
				return false;
		}
		else{
//				nn1.style.background='white';
//				nn2.style.background='white';
				return true;
		}
}
//-----------------------------------------------------------------------------------
// Nullチェック
// コールフォーマット：NullCheck(frm,n,alert_sw);
//									 |	 |	 |
//								 |	 |	 +--->alert_sw 0or1
//								 |	 +--->Element no.
//								 +--->form name
// Return　(true:エラーなし　false:エラーあり）
//-----------------------------------------------------------------------------------
function NullCheck(frm,n,alert_sw){

				var strName,nCnt,i;

				if(frm.elements[n].type!='text'){
						return true;
				}

				strName = frm.elements[n].value ;
				nCnt = strName.length ;

				for (i = 0; i < nCnt; i++){
						strName = strName.replace(" ","") ; 					//半角空白をNULL
						strName = strName.replace("　","") ;			//全角空白をNULL
				}

				if(strName == ""){
						if(alert_sw == 1){
								msg('F0014','');		// F0014 入力に誤りが有ります（空白不可）
						}
								frm.elements[n].style.background='pink';frm.elements[n].focus();
								return false;
				}
				else{
						frm.elements[n].style.background='white';
						return true;
				}
}
//-----------------------------------------------------------------------------------
// Nullチェック
// コールフォーマット：NullCheck(n,alert_sw);
//								 |	 |	 |
//								 |	 +--->alert_sw 0or1
//								 +--->Field_name
//								 
// Return　(true:エラーなし　false:エラーあり）
//-----------------------------------------------------------------------------------
function NullCheck2(n,alert_sw){
   var nn = n;
   var strName,nCnt,i;

   if(nn.type!='text'){
	  return true;
   }

   strName = nn.value ;
   nCnt = strName.length ;

	for (i = 0; i < nCnt; i++){
	   strName = strName.replace(" ","") ;			   //半角空白をNULL
	   strName = strName.replace("　","") ; 		   //全角空白をNULL
	}

	if(strName == ""){
	   if(alert_sw == 1){
		  msg('F0014','');		  // F0014 入力に誤りが有ります（空白不可）
	   }
	   nn.style.background='pink';nn.focus();
	   return false;
	}
	else{
	   nn.style.background='white';
	   return true;
	}
}
//------------------------------------------
// ラスト文字削除
// コールフォーマット：wk = chop("abcdef");
//									|
//								入力文字列
// ※wk には、"abcde" が返る。
// Return　(結果文字列）
//------------------------------------------
function chop(in_data){
   len=in_data.length;
   if(len>0){len--;}
   wk=in_data.substr(0,len);
   return wk;
}
/***********************************************************
 *関数名：Hankakukana_chk(String)
 *機能　：半角カナチェック関数
 *引数　：String・・・PString　入力した文字列
 *戻り値：true	・・・半角カナ無し
 *		：false ・・・半角カナ有り
 ***********************************************************/
function Hankakukana_chk( PString ) {

		/*
		 * 【変数宣言】
		 */
		var str;
		var nCode;
		var strCode1;
		var strCode2;

		for (i=0; i<PString.length; i++) {

				/*
				 * 【取得文字列をエンコード】
				 */
				str = escape(PString.charAt(i));

				if (str.substr(0,4)=="%uFF") {

						strCode1 = str.charAt(4);
						strCode2 = str.charAt(5);

						nCode1 = parseInt(strCode1,16);
						nCode2 = parseInt(strCode2,16);

						if ((6 <= nCode1) && (nCode1 <= 9) && (0 <= nCode2) && (nCode2 <= 15)) {

								/* 半角カナあり */
//alert("半角カナあり");
								return false;

						}
				}
		}

		/* 半角カナなし */
//alert("半角カナなし");
		return true;

}
/***********************************************************
 *関数名：Zenkakukana_chk(n,alert_sw);
 *						  |   |
 *						  |   +-->アラートSW 0 or 1
 *						  |
 *						  +---> Field_name
 *
 *機能　：全角カナチェック関数
 *戻り値：true	・・・全角カナ有り
 *		：false ・・・全角カナ無し
 ***********************************************************/
function Zenkakukana_chk(n,alert_sw) {

		/*
		 * 【変数宣言】
		 */
   var nn = n;
   var str;
   var nCode;
   var strCode1;
   var strCode2;
   var strRet
   var strKana; 				   // 入力文字列格納用

   if (nn.value == "") {
		   nn.style.background='white';
   } else {
		   strKana = nn.value;

//alert(strKana.length);

   /*
	*	   全角スペーストリム処理
	*/
	for (i=0; i<=strKana.length; i++) {
	   strKana = strKana.replace("　", "");
//alert(strKana +":"+i );
	}
	for (i=0; i<strKana.length; i++) {
	/*
	 * 【取得文字列をエンコード】
	 */
		str = escape(strKana.charAt(i));

		if (str.substr(0,4) == "%u30") {

		   strCode1 = str.charAt(4);
		   strCode2 = str.charAt(5);

		   nCode1 = parseInt(strCode1,16);
		   nCode2 = parseInt(strCode2,16);

		   /* 全角カナの場合 */
		  if ((10 <= nCode1) && (nCode1 <= 15) && (0 <= nCode2) && (nCode2 <= 15)) {
			  nn.style.background='white';
			  strRet = true;

			  /* 全角カナ以外ある */
			  } else {
				 if(alert_sw == 1){
					 msg('F0016','');		 // F0016 入力に誤りが有ります。（全角カナ入力）
				 }
					 nn.style.background='pink';nn.focus();
					 strRet = false;
					 break;
				 }

			   } else {
				 if(alert_sw == 1){
					  msg('F0016','');		  // F0016 入力に誤りが有ります。（全角カナ入力）
				 }
				 nn.style.background='pink';nn.focus();
				 strRet = false;
				 break;
				 }

			}

		}
//alert(strRet);
		return strRet;

}
//-----------------------------------------------------------------------------------
// 日付・チェック（YYYY/MM/DD）
// call format : date_chk(n,1);
//						  | |
//						  | |
//						  | +---> alert_sw
//						  +--->Field_name
// Return　(true:エラーなし　false:エラーあり）
//-----------------------------------------------------------------------------------
function date_chk(n,alert_sw){
   var nn = n;
   dateStr = nn.value;
   var datePat = /^(\d{4})(\/)(\d{1,2})\2(\d{1,2})$/;
   var matchArray = dateStr.match(datePat); // is the format ok?

   if (dateStr != "") {

		   if (matchArray == null) {
				   if(alert_sw == 1){msg('F0005','');}	   // F0005 日付が正しくありません。
				   nn.style.background='pink';nn.focus();
				   return false;
		   }
		   year  = matchArray[1]; // parse date into variables
		   month = matchArray[3];
		   day = matchArray[4];
		   if (month < 1 || month > 12) { // check month range
			  if(alert_sw == 1){msg('F0006','');}  // F0006 月は、１～１２の範囲であること。
			  nn.style.background='pink';nn.focus();
			  return false;
		   }
		   if (day < 1 || day > 31) {
			  if(alert_sw == 1){msg('F0007','');}  // F0007 日は、１～３１の範囲であること。
			  nn.style.background='pink';
			  return false;
		   }
		   if ((month==4 || month==6 || month==9 || month==11) && day==31) {
			  if(alert_sw == 1){msg('F0008','');}  // F0008 日付が正しくありません。（月日エラー）
			  nn.style.background='pink';nn.focus();
			  return false
		   }
		   if (month == 2) { // check for february 29th
			  var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
			  if (day>29 || (day==29 && !isleap)) {
						   if(alert_sw == 1){msg('F0009','');}	   // F0009 閏年チェック・エラー
						   nn.style.background='pink';nn.focus();
						   return false;
			  }
		   }

   }

   nn.style.background='white';
   return true;  // date is valid

}
/***********************************************************
 *関数名：DateFormat_chk(n)
 *						 |
 *						 |
 *						 |
 *						 +--->Field_name
 *
 *機能　：日付フォーマットチェック関数
 *	  1.「YYYY/M/D」等を「YYYY/MM/DD」に変換
 * 　注）日付チェック(date_chk)にて正しい場合のみ使用
 *Return　(true:エラーなし　false:エラーあり）
 ***********************************************************/
function DateFormat_chk(n) {

		/*
		 * 【変数宣言】
		 */
		var nn = n;
		var Cnt ;
		var Flag ;
		var CheckText;			/* チェック文字列格納 */
		var InTextLen;			/* 文字数格納 */
		var AnsDate;			/* 比較用加工値格納 */
		var InText_y;			/* 年 */
		var InText_m;			/* 月 */
		var InText_d;			/* 日 */

		/*
		 * 【初期値セット】
		 */
		Flag = 0 ;
		InText_y = ""
		InText_m = "";
		InText_d = "";

		if (nn.value == "") {
				nn.style.background='white';
		} else if (nn.style.background != 'pink') {
				CheckText = nn.value;
				InTextLen = CheckText.length;

				/*
				 * 【日付を年、月、日に分割】
				 */
				InText = CheckText.split("/");
				InText_y = InText[0];							/* 年を格納 */
				InText_m = InText[1];							/* 月を格納 */
				InText_d = InText[2];							/* 日を格納 */

				/*
				 * 【YYYY/MM/DDのフォーマットに作成】
				 */
				AnsDate = InText_y ;

				/*
				 * 一桁の場合は、０を加える
				 */
				if ( InText_m.length == 1 ) {
						InText_m = '0' + InText_m;
				}

				AnsDate = AnsDate + "/" + InText_m ;

				/*
				 * 一桁の場合は、０を加える
				 */
				if ( InText_d.length == 1 ) {
						InText_d = '0' + InText_d;
				}

				AnsDate = AnsDate+ "/" + InText_d ;

//alert(AnsDate);

				nn.value = AnsDate;
				return true;
		}
}
/***********************************************************
 *関数名：Hankakueisuji_chk(n,alert_sw);
 *							|	|
 *							|	+-->アラートSW 0 or 1
 *							|
 *							+---> Field_name
 *
 *機能　：半角英数字チェック関数
 *戻り値：true	・・・すべて半角英数字
 *		：false ・・・半角英数字以外あり
 ***********************************************************/
function Hankakueisuji_chk(n,alert_sw) {
		var nn = n;
		var strMoji = "";

		strMoji = nn.value;

		if (strMoji != "") {

				//1文字ずつ取り出して全角か判断する。
				for(i=0; i<strMoji.length; i++) {
						if (escape(strMoji.charAt(i)).length >= 4){
								if(alert_sw == 1){
										msg('F0018','');		// F0018 入力に誤りが有ります。（半角英数字入力）
								}
								nn.style.background='pink';
								return false;
						}
				}

				if (!Hankakukana_chk(strMoji)) {
						if(alert_sw == 1){
								msg('F0018','');		// F0018 入力に誤りが有ります。（半角英数字入力）
						}
						nn.style.background='pink';nn.focus();
						return false;

				} else {
						nn.style.background='white';
						return true;
				}

		} else {
				nn.style.background='white';
				return true;
		}

}
/***********************************************************
 *関数名：Han_chk(n,alert_sw);
 *				  |  |
 *				  |  +-->アラートSW 0 or 1
 *				  +---> Field_name
 *機能　：半角チェック関数（半角文字が1文字でもあるとエラー）
 *戻り値：true	・・・すべて全角
 *		：false ・・・半角文字あり
 ***********************************************************/
function Han_chk(n,alert_sw) {
   var nn = n;
	if(!Zenkaku_chk(nn,alert_sw))
	{
		 if(alert_sw == 1){
			  nn.style.background='pink';nn.focus();
			  return false;
		 }
		 return false;
	}
	nn.style.background='white';
	return true;
}
/***********************************************************
 *関数名：Zenkaku_chk(n,alert_sw);
 *					  |   |
 *					  |   +-->アラートSW 0 or 1
 *					  |
 *					  +---> Field_name
 *
 *			  ±×÷を追加
 *			  全角文字チェックを追加
 *
 *機能　：全角チェック関数
 *戻り値：true	・・・すべて全角
 *		：false ・・・全角以外あり
 ***********************************************************/
function Zenkaku_chk(n,alert_sw) {

		var nn = n;
		var strMoji = "";

		strMoji = nn.value;

		if (strMoji != "") {

				//1文字ずつ取り出して全角か判断する。
				for(i=0; i<strMoji.length; i++) {
						if( escape(strMoji.charAt(i)) == "%0D" ){i++;continue;} 	// 改行はＯＫとする。
						if (!Zenkaku_char_chk(strMoji.charAt(i))) {
								   if(alert_sw == 1){
										 msg('F0026','');		 // F0026 使用禁止文字が混在しています。
								}
								nn.style.background='pink';nn.focus();
								return false;
						}

						if (escape(strMoji.charAt(i)).length <= 4 && escape(strMoji.charAt(i)) != "%B1" && escape(strMoji.charAt(i)) != "%D7" && escape(strMoji.charAt(i)) != "%F7"){
								if(alert_sw == 1){
										msg('F0017','');		// F0017 入力に誤りが有ります。（全角以外不可）
								}
								nn.style.background='pink';nn.focus();
								return false;
						}


				}

				if (!Hankakukana_chk(strMoji)) {
						if(alert_sw == 1){
								msg('F0017','');		// F0017 入力に誤りが有ります。（全角以外不可）
						}
						nn.style.background='pink';nn.focus();
						return false;

				} else {
						nn.style.background='white';
						return true;
				}

		} else {
				nn.style.background='white';
				return true;
		}
}
/***********************************************************
 *関数名：Hankana_chk(n,alert_sw);
 *					  |  |
 *					  |  +-->アラートSW 0 or 1
 *					  +---> Field_name
 *機能　：半角カナチェック関数（半角カナ文字が1文字でもあるとエラー）
 *戻り値：true	・・・ＯＫ
 *		：false ・・・半角カナ文字あり
 ***********************************************************/
function Hankana_chk(n,alert_sw) {
		var nn = n;
		var strMoji = "";
		var Emsg = "";
		var kana_no = "";
		var err_flg = 0;
		strMoji = nn.value;
		if (strMoji != "") {
				//1文字ずつ取り出して半角カナか判断する。
				for(i=0; i<strMoji.length; i++) {
						if (escape(strMoji.charAt(i)).length >= 4){
								if( escape(strMoji.charAt(i)).substr(2,2) == "FF" ){
										kana_no = escape(strMoji.charAt(i)).substr(4,2);
										if( kana_no == "61" ){ err_flg = 1; }
										if( kana_no == "62" ){ err_flg = 1; }
										if( kana_no == "63" ){ err_flg = 1; }
										if( kana_no == "64" ){ err_flg = 1; }
										if( kana_no == "65" ){ err_flg = 1; }
										if( kana_no == "66" ){ err_flg = 1; }
										if( kana_no == "67" ){ err_flg = 1; }
										if( kana_no == "68" ){ err_flg = 1; }
										if( kana_no == "69" ){ err_flg = 1; }
										if( kana_no == "6A" ){ err_flg = 1; }
										if( kana_no == "6B" ){ err_flg = 1; }
										if( kana_no == "6C" ){ err_flg = 1; }
										if( kana_no == "6D" ){ err_flg = 1; }
										if( kana_no == "6E" ){ err_flg = 1; }
										if( kana_no == "6F" ){ err_flg = 1; }
										if( kana_no == "70" ){ err_flg = 1; }
										if( kana_no == "71" ){ err_flg = 1; }
										if( kana_no == "72" ){ err_flg = 1; }
										if( kana_no == "73" ){ err_flg = 1; }
										if( kana_no == "74" ){ err_flg = 1; }
										if( kana_no == "75" ){ err_flg = 1; }
										if( kana_no == "76" ){ err_flg = 1; }
										if( kana_no == "77" ){ err_flg = 1; }
										if( kana_no == "78" ){ err_flg = 1; }
										if( kana_no == "79" ){ err_flg = 1; }
										if( kana_no == "7A" ){ err_flg = 1; }
										if( kana_no == "7B" ){ err_flg = 1; }
										if( kana_no == "7C" ){ err_flg = 1; }
										if( kana_no == "7D" ){ err_flg = 1; }
										if( kana_no == "7E" ){ err_flg = 1; }
										if( kana_no == "7F" ){ err_flg = 1; }
										if( kana_no == "80" ){ err_flg = 1; }
										if( kana_no == "81" ){ err_flg = 1; }
										if( kana_no == "82" ){ err_flg = 1; }
										if( kana_no == "83" ){ err_flg = 1; }
										if( kana_no == "84" ){ err_flg = 1; }
										if( kana_no == "85" ){ err_flg = 1; }
										if( kana_no == "86" ){ err_flg = 1; }
										if( kana_no == "87" ){ err_flg = 1; }
										if( kana_no == "88" ){ err_flg = 1; }
										if( kana_no == "89" ){ err_flg = 1; }
										if( kana_no == "8A" ){ err_flg = 1; }
										if( kana_no == "8B" ){ err_flg = 1; }
										if( kana_no == "8C" ){ err_flg = 1; }
										if( kana_no == "8D" ){ err_flg = 1; }
										if( kana_no == "8E" ){ err_flg = 1; }
										if( kana_no == "8F" ){ err_flg = 1; }
										if( kana_no == "90" ){ err_flg = 1; }
										if( kana_no == "91" ){ err_flg = 1; }
										if( kana_no == "92" ){ err_flg = 1; }
										if( kana_no == "93" ){ err_flg = 1; }
										if( kana_no == "94" ){ err_flg = 1; }
										if( kana_no == "95" ){ err_flg = 1; }
										if( kana_no == "96" ){ err_flg = 1; }
										if( kana_no == "97" ){ err_flg = 1; }
										if( kana_no == "98" ){ err_flg = 1; }
										if( kana_no == "99" ){ err_flg = 1; }
										if( kana_no == "9A" ){ err_flg = 1; }
										if( kana_no == "9B" ){ err_flg = 1; }
										if( kana_no == "9C" ){ err_flg = 1; }
										if( kana_no == "9D" ){ err_flg = 1; }
										if( kana_no == "9E" ){ err_flg = 1; }
										if( kana_no == "9F" ){ err_flg = 1; }
										if(alert_sw == 1 && err_flg == 1){
												i++;
												Emsg = "（　" + i + "　文字目）";
												msg('F0020',Emsg);		// F0020 半角カナが混在しています。
												nn.style.background='pink';
												return false;
										}
										if(err_flg == 1){
												return false;
										}
								}
						}
				}
		}
		nn.style.background='white';
		return true;
}
/***********************************************************
 *関数名：Radio_ck_box(frm,n,from,to);
 *						|  |  |   |
 *						|  |  |   +--> To Element no.
 *						|  |  +--> From Element no.
 *						|  +---> Element no.
 *						+--->Form Name
 *機能　：連続するElement の Check BoxのRadio Button制御をする。
 *戻り値：なし
 ***********************************************************/
function Radio_ck_box(frm, n, from, to) {
		var i=0;
		if( frm.elements[n].checked==true ){
				for(i=from;i<to+1;i++){
						frm.elements[i].value="0";
						frm.elements[i].checked=false;
				}
				frm.elements[n].value="1";
				frm.elements[n].checked=true;
		}else{
				frm.elements[n].value="0";
				frm.elements[n].checked=false;
		}
}
/********************************************************************************
 *関数名：G_Radio_ck_box(frm,n,from1,to1,from2,to2);
 *						  |  |	|	 |	  |    |
 *						  |  |	|	 |	  |    +---> To Group2 Element no.
 *						  |  |	|	 |	  +---> From Group2 Element no.
 *						  |  |	|	 +--> To Group1 Element no.
 *						  |  |	+--> From Group1 Element no.
 *						  |  +---> Element no.
 *						  +---> Form Name
 *機能　：連続する２つのグループElement の Check BoxのRadio Button制御をする。
 *戻り値：なし
 *制限　：Group1とGroup2のElementは、連続していること。
 ********************************************************************************/
function G_Radio_ck_box(frm, n, from1, to1, from2, to2) {
		var i=0;
		if( frm.elements[n].checked==true ){
				if( n < from2){
						for(i=from2;i<to2+1;i++){
								frm.elements[i].value="0";
								frm.elements[i].checked=false;
						}
				}else{
						for(i=from1;i<to1+1;i++){
								frm.elements[i].value="0";
								frm.elements[i].checked=false;
						}
				}
				frm.elements[n].value="1";
				frm.elements[n].checked=true;
		}else{
				frm.elements[n].value="0";
				frm.elements[n].checked=false;
		}
}
/***********************************************************
 *関数名：Zenkaku_char_chk ( zenkaku_char );
 *							  |
 *							  |
 *							  |
 *							  |
 *							  | 
 *							  +--->全角文字
 *
 *機能　：全角文字チェック関数
 *戻り値：true	・・・使用可能な文字
 *		：false ・・・使用不可な文字
 ***********************************************************/
function Zenkaku_char_chk( zenkaku_char ) {

		var char_code = escape( zenkaku_char );

		if ((char_code == "%B1") || (char_code == "%F7") || (char_code == "%D7")) {
				return true; //変則全角文字（チェックＯＫ）
		}

		if ((char_code >= "%u2150") && (char_code <= "%u218F")) {
				return false; //ローマ数字など
		}
		if ((char_code >= "%u2460") && (char_code <= "%u24FF")) {
				return false; //囲み数字（まる囲み数字）など
		}
		if ((char_code >= "%u3220") && (char_code <= "%u32FF")) {
				return false; //囲み文字（かっこ付株）など
		}
		if ((char_code >= "%u3300") && (char_code <= "%u4DFF")) {
				return false; //年号、単位など（
		}
		if (char_code == "%uFF5E") {
				return false; //"～" 波線
		}
		if (char_code == "%u2225") {
				return false; //"∥" 二重縦線
		}
		if (char_code == "%uFF0D") {
				return false; //"－" マイナス符号
		}
		if (char_code == "%uFFE0") {
				return false; //"￠" セント記号
		}
		if (char_code == "%uFFE1") {
				return false; //"￡" ポンド記号
		}
		if (char_code == "%uFFE2") {
				return false; //"￢" ノット記号
		}

		if (char_code == "%B4") {
				return false; //"´" (2001.11.21追加)
		}
		if (char_code == "%A8") {
				return false; //"¨"(2001.11.21追加)
		}
		if (char_code == "%A7") {
				return false; //"§"(2001.11.21追加)
		}
		if (char_code == "%B6") {
				return false; //"¶"(2001.11.21追加)
		}
		if (char_code == "%B0") {
				return false; //"°"(2001.11.21追加)
		}

		if (char_code == "%u2116") {
				return false; //"№" (2001.11.19追加)
		}
		if (char_code == "%u2121") {
				return false; //"℡" (2001.11.19追加)
		}
		if (char_code == "%u2211") {
				return false; //"∑" (2001.11.19追加)
		}
		if (char_code == "%u222E") {
				return false; //"∮" (2001.11.19追加)
		}
		if (char_code == "%u221F") {
				return false; //"∟" (2001.11.19追加)
		}
		if (char_code == "%u22BF") {
				return false; //"⊿" (2001.11.19追加)
		}
		if (char_code == "%uFFE4") {
				return false; //"" (2001.11.19追加)
		}
		if (char_code == "%u301D") {
				return false; //"〝 " ダブルクォーテーションみたいな記号(2001.11.19追加)
		}
		if (char_code == "%u301F") {
				return false; //"〟" ダブルクォーテーションみたいな記号(2001.11.19追加)
		}
		if (char_code == "%uFF07") {
				return false; //"" シングルクォーテーションみたいな記号(2001.11.19追加)
		}
		if (char_code == "%uFF02") {
				return false; //"" ダブルクォーテーションみたいな記号(2001.11.19追加)
		}
		return true;		  //使用可能文字
}
/***********************************************************
 *関数名：Kinsoku_chk(n,alert_sw);
 *					  |   |
 *					  |   +-->アラートSW 0 or 1
 *					  |
 *					  +---> Field_name
 *
 *機能　：使用禁止文字があるかどうかチェックする。
 *戻り値：true	・・・使用禁止文字なし
 *		：false ・・・使用禁止文字あり
 ***********************************************************/
function Kinsoku_chk(n,alert_sw) {
		var nn = n;
		var strMoji = "";
		var char_code = "";
		var err_sw = 0;
		strMoji = nn.value;
		if( Hankana_chk(n,alert_sw) == false ){
			return false;
		}
		if (strMoji != "") {
			//1文字ずつ取り出して判断する。
			for(i=0; i<strMoji.length; i++) {
				char_code = escape(strMoji.charAt(i));
//				if( char_code == "%0D" ){i++;continue;} 									// 改行					ＯＫ
//				if ((char_code >= "%u0000") && (char_code <= "%u0066")) { i++;continue; }	// 英数字				ＯＫ
//				if (char_code == "%uFF5E") {i++;continue;}									// "～" 波線			ＯＫ
//				if (char_code == "%u2225") {i++;continue;}									// "∥" 二重縦線		ＯＫ
//				if (char_code == "%uFF0D") {i++;continue;}									// "－" マイナス符号	ＯＫ

				if ((char_code >= "%u0066") && (char_code <= "%u009D")) { err_sw = 1; }		// 半角カナ				エラー
				if ((char_code >= "%u2150") && (char_code <= "%u218F")) { err_sw = 1; }		// ローマ数字など		エラー
				if ((char_code >= "%u2460") && (char_code <= "%u24FF")) { err_sw = 1; }		// 囲み数字（まる囲み数字）など
				if ((char_code >= "%u3220") && (char_code <= "%u32FF")) { err_sw = 1; }		// 囲み文字（かっこ付株）など
				if ((char_code >= "%u3300") && (char_code <= "%u4DFF")) { err_sw = 1; }		// 年号、単位など
				if (char_code == "%uFFE0") { err_sw = 1; }									// "￠" セント記号
				if (char_code == "%uFFE1") { err_sw = 1; }									// "￡" ポンド記号
				if (char_code == "%uFFE2") { err_sw = 1; }									// "￢" ノット記号
				if (char_code == "%B4") { err_sw = 1; }										// "´" (2001.11.21追加)
				if (char_code == "%A8") { err_sw = 1; }										// "¨"(2001.11.21追加)
				if (char_code == "%A7") { err_sw = 1; }										// "§"(2001.11.21追加)
				if (char_code == "%B6") { err_sw = 1; }										// "¶"(2001.11.21追加)
				if (char_code == "%B0") { err_sw = 1; }										// "°"(2001.11.21追加)
				if (char_code == "%u2116") { err_sw = 1; }									// "№" (2001.11.19追加)
				if (char_code == "%u2121") { err_sw = 1; }									// "℡" (2001.11.19追加)
				if (char_code == "%u2211") { err_sw = 1; }									// "∑" (2001.11.19追加)
				if (char_code == "%u222E") { err_sw = 1; }									// "∮" (2001.11.19追加)
				if (char_code == "%u221F") { err_sw = 1; }									// "∟" (2001.11.19追加)
				if (char_code == "%u22BF") { err_sw = 1; }									// "⊿" (2001.11.19追加)
				if (char_code == "%uFFE4") { err_sw = 1; }									// "" (2001.11.19追加)
				if (char_code == "%u301D") { err_sw = 1; }									// "〝 " ダブルクォーテーションみたいな記号(2001.11.19追加)
				if (char_code == "%u301F") { err_sw = 1; }									// "〟" ダブルクォーテーションみたいな記号(2001.11.19追加)
				if (char_code == "%uFF07") { err_sw = 1; }									// "" シングルクォーテーションみたいな記号(2001.11.19追加)
				if (char_code == "%uFF02") { err_sw = 1; }									// "" ダブルクォーテーションみたいな記号(2001.11.19追加)
			}
			if(err_sw == 0){
				nn.style.background='white';
				return true;
			}else{
				if(alert_sw == 1){
					msg('F0026','');		 // F0026 使用禁止文字が混在しています。
				}
				nn.style.background='pink';nn.focus();
				return false;
			}
		} else {
				nn.style.background='white';
				return true;
		}
}
//-20140703 add----------------------------------------------------------------------#
function currentTime(){

	var myD   = new Date();
	var Year0  = myD.getYear();
	var Year4 = (Year0 < 2000) ? Year0+1900 : Year0;
	var Mon0  = myD.getMonth()+1;
	var Date0 = myD.getDate();
	var Hour0 = myD.getHours();
	var Min0  = myD.getMinutes();
	var Sec0  = myD.getSeconds();
	var date0 = Year4 + "/" + adj(Mon0) + "/" + adj(Date0) + " " + adj(Hour0) + ":" + adj(Min0) + ":" + adj(Sec0);
	var span = document.getElementById( 'current' );
	span.innerHTML = date0;
}

function adj(ival){
	var ret = (ival < 10) ? '0' + ival : ival;
	return ret;
}

function stawatch() {
	currentTime();
	setTimeout(stawatch, 1000);
}

function newErr(msg) {
	var msg2 = document.getElementById("msg2");
	msg2.textContent = msg; // メッセージ上書き
	alt.showModal(); //モーダル表示
	
}

function newCfm(msg) {
	var msg1 = document.getElementById("msg1");
	msg1.textContent = msg; // メッセージ上書き
	cfm.showModal(); //モーダル表示
	
}

function newFcs(obj, sel) {
	if (obj != undefined) {
		if (sel) {
			// 選択
			if ( obj.tagName == "INPUT" || obj.tagName == "TEXTAREA" ) {
				obj.select();
			}
			obj.focus();
			// スクロール
			obj.scrollIntoView({
				  behavior: 'smooth',
				  block: 'start',
				  inline: 'nearest'
			});
		}
	}
	
}
//-----------------------------------------------------------------------------------#
// -->
