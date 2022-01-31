<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>권한 없음 에러</title>
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
	<tr><th>권한이 없는 사용자 입니다.</th></tr>
	<tr><th><button id="ok_btn" onclick="location.href='/main'">확인</button></th></tr>
</table>

</body>
</html>