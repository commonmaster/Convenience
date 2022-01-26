<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 확인창</title>
<style>
	table{
		margin: 150px auto;
	}
	
	#ok_btn{
		padding:5px;
		border-radius:5px;
		color:white;
		font-weight:bold;
		background:purple;
	}
	
	td{
		padding:5px;
	}
</style>
</head>
<body>
	<form action="/withdraw" method="post">
	<table>
		<tr><td><b>정말로 탈퇴하시겠습니까?<br>
				동의한다면 확인버튼을 누르세요.</b></td><tr>
		<tr><td align="center"><input type="submit" id="ok_btn" value="이에 동의 하며 탈퇴합니다"></td></tr>		
	</table>
	</form>
</body>
</html>