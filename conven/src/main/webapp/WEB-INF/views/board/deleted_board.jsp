<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		margin: 20px auto;
	}
	
	.board_btn{
	padding:7.5px 15px;
	border-radius:10px;
	margin-left:10px;
	font-weight:bold;
	background:purple;
	border:0px;
	border:1px solid purple;
	cursor:pointer;
}

	.board_btn:hover{
	background:#B404AE;
	}
</style>
</head>
<body>
<table>
<tr><td><h3 align="center">삭제된 게시물입니다.</h3></td></tr>
<tr><td><button onclick="location.href='/boards'" class="board_btn">확인</button></td></tr>
</table>
</body>
</html>