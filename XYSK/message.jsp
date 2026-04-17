<%@ page contentType="text/html;charset=UTF-8"%>
<dialog id="cfmMsgArea" >
	<table>
		<col style="width:45px">
		<col style="width:300px">
		<tr>
			<%--<td class="icon">&#x2139;</td>--%>
			<td class="icon nonpoint">&#x2753;</td>
			<td class="nonpoint" id="msg1">ここにメッセージが出ます</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:right;">
			<button id="cok" formmethod="dialog" style="padding: 3px 0px; padding-top: 4px; padding-right: 0px; padding-bottom: 3px; padding-left: 0px;" >OK</button>
			<button id="cng" formmethod="dialog" style="padding: 3px 0px; padding-top: 0.8px; padding-right: 0px; padding-bottom: 2.4px;  padding-left: 0px; " >キャンセル</button>
			</td>
		</tr>
	</table>
</dialog>

<dialog id="errMsgArea" >
	<table>
		<col style="width:45px">
		<col style="width:300px">
		<tr>
			<%--<td class="icon">&#x274C;</td>--%>
			<%--<td class="icon">&#x26D4;</td>--%>
			<td class="icon nonpoint">&#x26A0;</td>
			<td class="nonpoint" id="msg2">ここにメッセージが出ます</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:right;">
			<button id="aok" formmethod="dialog" >OK</button>
			</td>
		</tr>
	</table>
</dialog>
