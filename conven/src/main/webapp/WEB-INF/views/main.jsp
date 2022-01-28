<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Document</title>
<link rel="stylesheet" type="text/css" href="/css/main.css" />

<style>

</style>

</head>
<body>
	
	<nav>
		<div id="login_text_divbox">
		
		<c:if test="${empty conven_session_id}">
			<span class="login_text">
				<a href="/login" target="content_iframe">로그인</a>
			</span>	
		</c:if>
		<c:if test="${!empty conven_session_id}">
				
			<span><a href="/logout" id="logining_text2">로그아웃</a></span>
			<span><a href="/member" id="logining_text3" target="content_iframe">회원정보</a></span>			
			<span id="logining_text1"><b>${name}</b> 님 환영합니다!</span>			
		</c:if>
		
		
		</div>
		<div id="menu_divbox">
		<ul id="menu">
			<li><a href="/home" target="content_iframe">Home</a></li>
			
			<li><a href="/products" target="content_iframe">물품 안내</a></li>
			<li><a href="/boards" target="content_iframe">자유게시판</a></li>
		</ul>
		</div>
	</nav>
	
	<section>
		<iframe id="content_iframe" name="content_iframe" src="/home" >		
		</iframe>
	</section>

	<footer>
		<div>
			상호명 : 무인편의점 / 주소 : 서울시 은평구 <br> <br> TEL : (02) 1234-1234 /
			Made by JH
		</div>
	</footer>
	

</body>
</html>