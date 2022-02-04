<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Home</title>

<style>

section {
	
	padding:20px;
	width: 1180px;	
	margin:0px auto;	
}

#content_div{
	width:1160px;	
}

#logo_img{
	width:300px;
}


#building_img{
	width:500px;
	
}

.align_center{
	text-align:center;
}

</style>


</head>
<body>
	<nav>
		<%@include file="../include/nav_contents.jsp" %>
	</nav>	
	
	<section>
		<div id="content_div">
			<div class="align_center">
				<img id="logo_img" src="img/logo_ju.jpg">
				<h1>어서오세요! CU 같은 편의점 JU입니다!!</h1>		
				<img src="img/building_ju.png" id="building_img">
			</div>		
		</div>		
	</section>
	<footer>
		<%@include file="../include/footer_contents.jsp" %>
	</footer>

</body>
</html>