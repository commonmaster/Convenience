<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean isSuccess = (boolean)request.getAttribute("isSuccess");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 변경 결과창</title>
<style>
	
	table{
		margin:20px auto;
	}
	
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
	<tr><td><%if(isSuccess){ %>
				<b>회원정보 변경 완료</b>
			<%}else{ %>
				<b>회원정보 변경 실패</b><br>
				(비밀번호 불일치)
			<%} %>
		</td></tr>
	<tr><td align="center"><button id="ok_btn" onclick="location.href='/member'">확인</button></td></tr>	

</table>

</body>
</html>