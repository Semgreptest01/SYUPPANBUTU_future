<%
}
catch(Exception e){
	if(null != pageParameters){
		pageParameters.exit();
	}
	XysfljParameters.redirectErrorPage(request, response, e);
}
finally{
	if(null != pageParameters){
		pageParameters.exit();
	}
}
%>
