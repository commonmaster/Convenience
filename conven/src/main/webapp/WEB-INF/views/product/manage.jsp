<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리메뉴</title>
<style>
	table{
		margin: 0px auto;
	}
	
	#logo_img{
		width:280px;
	}
	
	td{
		padding:5px;
	}
	
	
	.big_btn{
		background:purple;
		color:white;
		width:300px;
		height:100px;
		font-size:2em;
		border-radius:15px;
	}
</style>
</head>
<body>
<table>
<tr><th><img id="logo_img" src="img/logo_ju.jpg"><th><tr>
<tr><td><button class="big_btn" onclick="location.href='/manage_product_add'">물품등록</button></td></tr>
<tr><td><button class="big_btn" onclick="location.href='/manage_products'">물품목록(진열)</button></td></tr>
<tr><td><button class="big_btn">물품목록(제외)</button></td></tr>
</table>

</body>
</html>