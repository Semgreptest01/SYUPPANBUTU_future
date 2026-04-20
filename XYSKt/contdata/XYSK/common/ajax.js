// *----------------------------------------------------------------------------
// * システム		:	共通
// * スクリプト		:	Ajax
// * 会社名or所属名	:	富士ソフトＤＩＳ株式会社
// * 作成日			:	2006/03/03
// * 作成者			:	Hirata
// * *** 修正履歴 ***
// * No.	Date		Author		Description
// *----------------------------------------------------------------------------
/***********************************************************
 *関数名：getXmlHttp()
 *機能　：XMLHTTPオブジェクト取得
 ***********************************************************/
function getXmlHttp() {
	var xmlhttp = null;
	/*@cc_on
	@if ( @_jscript_version >= 5 )
		try {
			xmlhttp = new ActiveXObject( "Msxml2.XMLHTTP" );
		} catch( e ) {
			try {
				xmlhttp = new ActiveXObject( "Microsoft.XMLHTTP" );
			} catch( ex ) {
				xmlhttp = null;
			}
		}
	@end @*/
	if ( xmlhttp == null && typeof XMLHttpRequest != "undefined" ) {
		try {
			xmlhttp = new XMLHttpRequest();
		} catch ( e ) {
			xmlhttp = null;
		}
	}
	return xmlhttp;
}
/***********************************************************
 *関数名：sendAjaxRqst( url, prmArry, hndlrNm, gtFlg )
 *機能　：Ajaxでリクエスト送信
 *prmArryは
 *prmArry[ key ] = value;
 *の形で作られているとする
 *hndlrは
 *hndlr = function( responseText )
 *の形で作られているとする
 ***********************************************************/
function sendAjaxRqst( url, prmArry, hndlr, gtFlg ) {
	var xmlhttp = getXmlHttp();
	if ( xmlhttp != null ) {
		var tmpArry = new Array();
		for ( var ky in prmArry ) {
			tmpArry.push( ky + "=" + encodeURIComponent( prmArry[ ky ] ) );
		}
		var prmtr = tmpArry.join( "&" );
		
		xmlhttp.onreadystatechange = function() {
			if ( xmlhttp.readyState == 4 && xmlhttp.status == 200 ) {
				hndlr( decodeURIComponent( xmlhttp.responseText ) );
			}
		}
		
		var hdrNm = "Content-Type";
		var hdrVl = "application/x-www-form-urlencoded; charset=UTF-8";
		if ( gtFlg ) {
			xmlhttp.open( "GET", url + "?" + prmtr, true );
			xmlhttp.setRequestHeader( hdrNm, hdrVl );
			xmlhttp.send( null );
		} else {
			xmlhttp.open( "POST", url, true );
			xmlhttp.setRequestHeader( hdrNm, hdrVl );
			xmlhttp.send( prmtr );
		}
	}
}
