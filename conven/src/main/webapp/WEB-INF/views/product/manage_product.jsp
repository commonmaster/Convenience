<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 정보(매니저)</title>
<style>
	section{
		width: 1180px;		
		margin:0px auto;
	}
	
	table{
		margin:0px auto;
	}
	
	
	#logo_img{
		
	}
	
	#product_img{
		
	}
	
	th,td{		
		hieght:55px;
		padding:5px;
		magin:2px;
	}
	
	.tit{
		background:#F8E0F1;
	}
	.tit_contents{
		background:#FAF5FA;
	}
	
	#intro{
		padding:5px;
		
	}
	
	.standard_btn{
	display:inline-block;
	padding:15px 0px;
	border-radius:10px;	
	width:180px;
	font-weight:bold;
	background:#F8E0F1;
	border:0px;
	font-size:1.2em;
	margin: 0px 10px;
	}
	
	.standard_btn:hover{
	background:#F5A9F2;
}
}
	
	
</style>
</head>
<body>

<section>
<table>
<tr><th colspan="5"><img id="logo_img" src="/img/logo_ju_info.jpg" width="450px"></th></tr>
<tr><th colspan="2" rowspan="4"><img src="/productImg/${product.barcode }" width="230" height="230" id="productImg"></th><th class="tit" width="60px">바코드</th><td width="180.4px" class="tit_contents">${product.barcode }</td><td rowspan="5"><textarea rows="19" cols="50" name="intro" id="intro" placeholder="제품 소개 없음" readonly="readonly">${product.intro }</textarea></tr>
<tr><th class="tit">제품명</th><td class="tit_contents">${product.name }</td></tr>
<tr><th class="tit">종류</th><td class="tit_contents">
	<c:if test="${product.type == 1}">과자</c:if>
	<c:if test="${product.type == 2}">음료수</c:if>
	<c:if test="${product.type == 3}">아이스크림</c:if>
</td></tr>
<tr><th class="tit">가격</th><td class="tit_contents">${product.price }</td></tr>
<tr><th height="48px" class="tit" width="60px">상태</th>
<td class="tit_contents">
<c:if test="${product.isExcluded == 1}">
	판매중지
</c:if>
<c:if test="${product.isExcluded == 0}">
	판매중
</c:if>
<c:if test="${product.isExcluded == null}">
	보류
</c:if>
</td><th class="tit">제조사</th><td class="tit_contents">${product.provider }</td></tr>
<tr>
	<th colspan="5" height="180px">
		<c:set var="pageNo" value="${empty param.pageNo ? '1':param.pageNo}"/>
		<c:set var="searchContent" value="${empty param.searchContent ? null :param.searchContent}"/>
		<button onclick="location.href='/manage_product_modify?barcode=${product.barcode}&pageNo=${pageNo}&searchContent=${searchContent}'" class="standard_btn">수정</button>&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="deleteConfirm()" class="standard_btn">삭제</button>
		
			<a onclick="location.href='/manage_products?pageNo=${pageNo}&searchContent=${searchContent }'" class="standard_btn">판매목록</a>
		
		
			<a onclick="location.href='/manage_products_ex?pageNo=${pageNo}&searchContent=${searchContent }'" class="standard_btn">제외목록</a>
		
	</th>
</tr>

</table>

</section>
</body>
<script>
function deleteConfirm(){
		
		if(confirm("정말 삭제하시겠습니까?")){
			location.href="/manage_product_delete?barcode=${product.barcode}&isExcluded=${product.isExcluded}";
		}
	}
</script>
</html>