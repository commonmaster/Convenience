<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<style>

section {	
	padding:20px;
	width: 1180px;
	height:730px;
	margin:0px auto;	
}

#content_div{
	width:1160px;		
}

.product_box{
	display:inline-block;
	width:350px;
	height:140px;
	border:1px solid purple;
	margin-bottom:20px;
	margin-left:20px;
		
}

.product_img_div{
	width:100px;
	height:100px;	
	float:left;
	margin:20px;
	
}

img{
	width:100px;
	height:100px;
	
}

.product_title_div{
	width:190px;
	height:50px;	
	float:left;
	margin-top:20px;
	overflow:hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}



.product_price_div{
	width:190px;
	height:50px;	
	float:left;
	margin-top:10px;
	overflow:hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	display:inline-block;
	
}

.product_title_span {
	
}

table {
	margin: 0px auto;
	
}

#hasNoProductMessage_h1 {
	text-align: center;
	margin-top: 140px;
	color:blue;
}

.board_title_td a, .page_th a{
		text-decoration: none;
		color:black;
}

.page_th .page_num_a{
		width:20px;	
		height:20px;	
		display:inline-block;
	}

select {
    font-size: 1em;
    padding: 2px 5px;
    float:right;
    margin-right:20px;
}

#category_line_p{
	clear:both;
	
	padding:10px;
}

#search{
	float:left;
	margin-left:20px;
	height:30px;
}

.board_btn{
	padding:7.5px 15px;
	border-radius:10px;
	margin-left:10px;	
	font-weight:bold;
	background:#F8E0F1;
	border:0px;	
}

.page_th{		
		width:1066.4px;
		text-align:center;		
}

#currentPage_a{
		border:1px solid purple;
	}

</style>


</head>
<body>

	<nav>
		<%@include file="../include/nav_contents.jsp"%>
	</nav>
	<section>
		
		<div id="content_div">
		<form action="/products" name="category_form" id="category_form">	
			<select name="category" id="category">
   				<option value="0">ALL</option>
   			 	<option value="1">과자</option>
   				<option value="2">음료수</option>
    			<option value="3">아이스크림</option>   
			</select>
		</form>	
			<p id="category_line_p">
			
		
			<c:if test="${pageInfo.hasNoRecords()}">
				<h1 id="hasNoProductMessage_h1">죄송합니다. 제품이 없습니다.</h1>
			</c:if>
			
			<c:forEach var="product_record" items="${pageInfo.contents}">
				<div class="product_box" id="${product_record.barcode }">

					<div class="product_img_div">
						<img src="/productImg/${product_record.barcode }">
					</div>
					<div class="product_title_div">
						<span class="product_title_span"><b>${product_record.printNameUpperCase()}</b></span>
					</div>
					
					<div class="product_price_div">
						<span class="product_price_span">&#8361;<b>${product_record.price }</b></span>
					</div>
				</div>
			</c:forEach>
				
		
				<table>
						
				<c:if test="${pageInfo.hasRecords()}">			
						<tr>
							<th class="page_th"><c:if test="${pageInfo.startPage > pageInfo.SHOW_PAGE_COUNT}">
									<a href="products?pageNo=${pageInfo.startPage - 1}&category=${selectCategory}" class="page_str_a">[이전]</a>
								</c:if> 
								<c:forEach var="pNo" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
								<c:if test="${pNo == pageInfo.currentPage}">
											<a href="products?pageNo=${pNo}&category=${selectCategory}" id="currentPage_a" class="page_num_a">${pNo}</a>
											</c:if>
											<c:if test="${pNo != pageInfo.currentPage}">
											<a href="products?pageNo=${pNo}&category=${selectCategory}" class="page_num_a">${pNo}</a>
											</c:if>
								</c:forEach>
								<c:if test="${pageInfo.endPage < pageInfo.totalPageCount}">
									<a href="products?pageNo=${pageInfo.endPage + 1}&category=${selectCategory}" class="page_str_a">[다음]</a>
								</c:if>
							</th>	
						</tr>
				</c:if>	
				</table>
			
			

		</div>
	</section>
	
	<footer>
		<%@include file="../include/footer_contents.jsp"%>
	</footer>
	
</body>

<script>
	$().ready(function() {
		$(".product_box").on("click", function(e) {
			//alert(e.currentTarget.getAttribute("id"));
			var newWin = window.open("product?barcode="+e.currentTarget.getAttribute("id"),"newW",'top=200, left=650, width=540, height=400, status=no, menubar=no, toolbar=no, resizable=no');
			// resize = 크롬 안됨.. ㅠㅜ

		});

		$(".product_box").on("mouseover", function(e) {

			var ele = $(e.currentTarget);
			ele.css("box-shadow", "10px 5px 5px purple");
		});

		$(".product_box").on("mouseout", function(e) {

			var ele = $(e.currentTarget);
			ele.css("box-shadow", "0px 0px 0px purple");
		});
		
		$("#category").change(function(){
			$("#category_form").submit();
		});
		
		$("#category").find('option:eq(${selectCategory})').prop("selected", true);
		
		
	});	
	
</script>
</html>