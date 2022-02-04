<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 리스트(매니저)</title>
<style>

	section{
		width: 1180px;
		border:1px solid red;
		margin:0px auto;
	}

	table{
	margin: 0px auto;
	border: 0;}
	
	#btn_line{
		padding:5px 10px;
	}
	
	#back_main{
		display:inline-block;
		margin-top:-100px;
		margin-left:300px;
		background:purple;
		color:white;
		padding:10px;
		border-radius:10px;		
	}
	
	.tit{
		background:#F8E0F1;
	}	
	
	th,td{padding:5px;}
	
	td{height:30px;}
	
	input{
		width:380px;
		padding:5px;
	}
	
	button{
		padding:5px;
	}
	
	#hasNoRecord{
		color:blue;
	}
	
	.product_barcode_td, .product_name_td, .product_type_td, .product_price_td, .product_provider_td{
		text-align:center;
	}
	
	#contents_p_space{
		height:76px;
	}
	
	.page_th{
		height:52px;
	}
	
	.page_th .page_num_a {
		width: 20px;
		height: 20px;
		display: inline-block;
	}

	.page_str_a {
		width: 40px;
	}
	
	.product_name_td a, .page_th a {
		text-decoration: none;
		color: black;
	}
	
	#currentPage_a {
		border: 1px solid purple;
	}
	
	#search_btn {
		width: 52px;
		height: 32px;
		border: 0px;
		background: purple;
		color: white;
		font-weight: bold;
	}
	
		
	
}
	
</style>
</head>
<body>
<section>

<table>
<thead>
<tr><th colspan="5"><img id="logo_img" src="/img/logo_ju_nosale.jpg" width="450px"></th></tr>
<tr><td colspan="5" align="right" id="btn_line"><a id="back_main" onclick="location.href='/manage'">관리 메인</a></td></tr>
<tr><th width="130px" class="tit">바코드</th><th width="380px" class="tit">제품명</th><th width="100px" class="tit">종류</th><th width="90px" class="tit">가격</th><th width="240px" class="tit">제조사</th></tr>
</thead>
<tfoot>
<tr><td colspan="5" id="contents_p_space"></td></tr>
<c:if test="${pageInfo.hasRecords()}">
	<tr>
		<th colspan="5" class="page_th">
			<c:if test="${pageInfo.startPage > pageInfo.SHOW_PAGE_COUNT}">
				<a href="manage_products_ex?pageNo=${pageInfo.startPage - 1}&searchContent=${searchContent}" class="page_str_a">[이전]</a>
			</c:if> 
			<c:forEach var="pNo" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
				<c:if test="${pNo == pageInfo.currentPage}">
					<a href="manage_products_ex?pageNo=${pNo}&searchContent=${searchContent}" id="currentPage_a" class="page_num_a">${pNo}</a>
				</c:if>
				<c:if test="${pNo != pageInfo.currentPage}">
					<a href="manage_products_ex?pageNo=${pNo}&searchContent=${searchContent}"	class="page_num_a">${pNo}</a>
				</c:if>
				</c:forEach> <c:if test="${pageInfo.endPage < pageInfo.totalPageCount}">
					<a href="manage_products_ex?pageNo=${pageInfo.endPage + 1}&searchContent=${searchContent}" class="page_str_a">[다음]</a>
				</c:if>
	</tr>
</c:if>


</tfoot>
<tbody>	
	<c:if test="${pageInfo.hasNoRecords()}">
		<th colspan="5" id="hasNoRecord" height="300px">판매중지 제품이 없습니다.</th>
	</c:if>
	<c:forEach var="product_record" items="${pageInfo.contents}">
		<tr>
			<td class="product_barcode_td">${product_record.barcode}</td>
			<td class="product_name_td">
				<a href="manage_product?barcode=${product_record.barcode}&pageNo=${pageInfo.currentPage}&searchContent=${searchContent}">
					<c:out value="${product_record.name}" /></a>
			</td>
			<td class="product_type_td">
			<c:if test="${product_record.type == 1}">과자</c:if>
			<c:if test="${product_record.type == 2}">음료수</c:if>
			<c:if test="${product_record.type == 3}">과자</c:if>
			</td>
			<td class="product_price_td">${product_record.price}</td>
			<td class="product_provider_td">${product_record.provider}</td>
		</tr>
	</c:forEach>
</tbody>

</table>

<div align="center">
	<form action="/manage_products_ex">
		<input type="text" name="searchContent" placeholder="제품명 검색" value=${searchContent}>&nbsp;<input type="submit" value="검색" id="search_btn" >
	</form>
</div>
</section>

</body>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$().ready(
		function() {
			$(".product_name_td a, .page_th a").on("mouseover", function(e) {
				var ele = $(e.target);
				ele.css({
					"text-decoration" : "underline"
				});
			});

			$(".product_name_td a, .page_th a").on("mouseout", function(e) {
				var ele = $(e.target);
				ele.css({
					"text-decoration" : "none"
				});
			});
		});	
</script>

</html>