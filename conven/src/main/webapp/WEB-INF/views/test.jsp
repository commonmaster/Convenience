<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/my.css" />
<style>

.product_box{
	display:inline-block;
	width:350px;
	height:140px;
	border:1px solid purple;
	margin-bottom:20px;
	margin-left:30px;	
	background:red;
	
}

.product_img_div{
	width:120px;
	height:120px;	
	float:left;
	margin:10px;
	background:green;
}

img{
	width:115px;
	height:115px;
	object-fit: contain;
}

.product_title_div{
	width:200px;
	height:55px;	
	float:left;
	margin-top:10px;
	overflow:hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	background:blue;

}

.product_price_div{
	width:200px;
	height:55px;	
	float:left;
	margin-top:10px;
	overflow:hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	background:pink;
}

#product_page_table{
	margin:0px auto;
}

#hasNoProductMessage_h1{
	text-align:center;
	margin-top:140px;
}

select {
    font-size: 1em;
    padding: 2px 5px;
}

</style>
<script>
	$().ready(function(){
		$(".product_box").on("click", function(e){
			alert(e.currentTarget.getAttribute("id"));
		});
		
		$(".product_box").on("mouseover", function(e){
			
			var ele = $(e.currentTarget);
			ele.css(
				"box-shadow", "10px 5px 5px purple"
			);
		});
		
		$(".product_box").on("mouseout", function(e){
			
			var ele = $(e.currentTarget);
			ele.css(
				"box-shadow", "0px 0px 0px purple"
			);
		});		
	});
	
</script>

</head>
<body>
	
	<nav>
		<div id="login_text_divbox">
		<c:set var="isLogin" value="${empty id}"/>
		<c:if test="${!isLogin}">
			<span class="login_text" onclick="login()"><a href="#">000님 환영합니다.</a></span>
		</c:if>
		<c:if test="${isLogin}">
			<span class="login_text" onclick="login()"><a href="#">로그인</a></span>
		</c:if>
		</div>
		<div id="menu_divbox">
		<ul id="menu">
			<li><a href="main">Home</a></li>
			
			<li><a href="product">물품 안내</a></li>
			<li><a href="board">자유게시판</a></li>
		</ul>
		</div>
	</nav>
	
	<section>
		<div id="content1">
				

<select name="catagory" id="catagory">
    <option value=""> ALL </option>
    <option value="snack">과자</option>
    <option value="drink">음료수</option>
    <option value="icecream">아이스크림</option>
   
</select><p>
				<div class="product_box">

					<div class="product_img_div">
						aaaaaaa
					</div>
					<div class="product_title_div">
						<span class="product_title_span">aaaaaaaa</span>
					</div>
					<div class="product_price_div">
						<span class="product_price_span">1500</span>
					</div>
				</div>
				<div class="product_box">

					<div class="product_img_div">
						aaaaaaa
					</div>
					<div class="product_title_div">
						<span class="product_title_span">aaaaaaaa</span>
					</div>
					<div class="product_price_div">
						<span class="product_price_span">1500</span>
					</div>
				</div>
				<div class="product_box">

					<div class="product_img_div">
						aaaaaaa
					</div>
					<div class="product_title_div">
						<span class="product_title_span">aaaaaaaa</span>
					</div>
					<div class="product_price_div">
						<span class="product_price_span">1500</span>
					</div>
				</div>
				<div class="product_box">

					<div class="product_img_div">
						aaaaaaa
					</div>
					<div class="product_title_div">
						<span class="product_title_span">aaaaaaaa</span>
					</div>
					<div class="product_price_div">
						<span class="product_price_span">1500</span>
					</div>
				</div>
				<div class="product_box">

					<div class="product_img_div">
						aaaaaaa
					</div>
					<div class="product_title_div">
						<span class="product_title_span">aaaaaaaa</span>
					</div>
					<div class="product_price_div">
						<span class="product_price_span">1500</span>
					</div>
				</div>
				<div class="product_box">

					<div class="product_img_div">
						aaaaaaa
					</div>
					<div class="product_title_div">
						<span class="product_title_span">aaaaaaaa</span>
					</div>
					<div class="product_price_div">
						<span class="product_price_span">1500</span>
					</div>
				</div>
				<div class="product_box">

					<div class="product_img_div">
						aaaaaaa
					</div>
					<div class="product_title_div">
						<span class="product_title_span">aaaaaaaa</span>
					</div>
					<div class="product_price_div">
						<span class="product_price_span">1500</span>
					</div>
				</div>
				<div class="product_box">

					<div class="product_img_div">
						aaaaaaa
					</div>
					<div class="product_title_div">
						<span class="product_title_span">aaaaaaaa</span>
					</div>
					<div class="product_price_div">
						<span class="product_price_span">1500</span>
					</div>
				</div>
				<div class="product_box">

					<div class="product_img_div">
						aaaaaaa
					</div>
					<div class="product_title_div">
						<span class="product_title_span">aaaaaaaa</span>
					</div>
					<div class="product_price_div">
						<span class="product_price_span">1500</span>
					</div>
				</div>
				<table id="product_page_table">
					<tfoot>
						<tr>
							<th width="1000px">1&nbsp;&nbsp;2&nbsp;&nbsp;3&nbsp;&nbsp;4&nbsp;&nbsp;5&nbsp;&nbsp;</th>
					</tfoot>
				</table>
				
		</div>
	</section>

	<footer>
		<div>
			상호명 : 무인편의점 / 주소 : 서울시 은평구 <br> <br> TEL : (02) 1234-1234 /
			Made by JH
		</div>
	</footer>
	

</body>
</html>