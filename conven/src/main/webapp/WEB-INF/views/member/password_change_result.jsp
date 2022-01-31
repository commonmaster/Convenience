<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean isSuccess = (boolean)request.getAttribute("isSuccess");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경 결과창</title>
<style>
	
	#ok_btn{
 		padding:5px 8px;
 		color:white;
 		border-radius:5px;
 		font-weight:bold;
 		background:purple;
 		border:0;
 		
 	}
</style>
</head>
<body>
<table>
	<tr><th><%if(isSuccess){ %>
				<b>비밀번호 변경 완료</b>
			<%}else{ %>
				<b>기존 비밀번호 불일치</b>
			<%} %>
		</th></tr>
	<tr><th><button id="ok_btn" onclick="window.close()">확인</button></th></tr>	

</table>

</body>
</html>