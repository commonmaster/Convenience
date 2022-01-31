<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성자 불일치  에러</title>
<style>
	table{
		margin:40px auto;		
	}
	th{
		padding:10px;
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
	<tr><th>접속한 유저는 작성자가 아닙니다.</th></tr>
	<tr><th><button id="ok_btn" onclick="location.href='/main'">확인</button></th></tr>
</table>

</body>
</html>