<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>menu</title>

<style>
body {
	padding: 0px;
	margin: 0px;
}

#bigbox {
	border: 1px solid blue;
}

#nav {
	text-align: center;	
	margin-bottom:-4px;
	
}

.login_text a {
	font-size: 15px;
	float: right;
	margin-top:30px;
	margin-right:50px;
	text-decoration: none;
	color: gray;
	font-weight: bold;
}

#logining_text1{
	font-size: 18px;
	float: right;
	margin-top:30px;
	margin-right:20px;
}

#logining_text2{	
	float: right;
	margin-top:30px;
	margin-right:50px;
	text-decoration: none;
	color:blue;
}

#logining_text3{	
	float: right;
	margin-top:30px;
	margin-right:10px;
	text-decoration: none;
	color:blue;
}

#login_text_divbox{
	width:1220px;
	margin:0px auto;
}

#menu_divbox{
	clear:both;
}

#menuimg, #menu {
	display: inline-block;
	border-radius: 15px;
}

#menuimg {
	width: 56.4px;
	position: absolute;
	margin-top: 63.4px;
}

#menu {
	background: purple;
	width: 1220px;
	
	margin-top:20px;
	margin-bottom:0px;
	
	
}

#menu li {
	float: left;
	width: 305px;
	list-style-type: none;

}

#menu li a {
	text-decoration: none;
	color: white;
	font-size: 20px;
	padding: 15px 0px;
	display: inline-block;
	font-weight: bold;
	text-align: center;
}	

#menu li a:hover {
	color: black;
}

#content_iframe {
	width: 1180px;
	height: 770px;	
	overflow: hidden;
	border:0px;
}

.long_text a:hover {
	text-decoration: underline;
}
</style>

</head>
<body>	
	<div id="nav">
		<div id="login_text_divbox">
		
		<c:if test="${empty conven_session_id}">
			<span class="login_text">
				<a href="/login">로그인</a>
			</span>	
		</c:if>
		<c:if test="${!empty conven_session_id}">
				
			<span><a href="/logout" id="logining_text2">로그아웃</a></span>
			<span><a href="/member" id="logining_text3">회원정보</a></span>			
			<span id="logining_text1"><b>${name}</b> 님 환영합니다!</span>			
		</c:if>		
		
		</div>
		<div id="menu_divbox">
		<ul id="menu">
			<li><a href="/main">Home</a></li>			
			<li><a href="/products">물품 안내</a></li>
			<li><a href="/boards">자유게시판</a></li>
			<!-- 관리자라면 to do -->
			<li><a href="/manage">물품 관리</a></li>
			
		</ul>
		</div>
	</div>
	
	

</body>
</html>