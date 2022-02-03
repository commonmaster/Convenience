<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품등록</title>
<style>
	
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
	
	#intro{
		padding:5px;
		
	}
	
	.standard_btn{
	padding:7.5px 15px;
	border-radius:10px;
	margin:0px 50px;
	font-weight:bold;
	background:#F8E0F1;
	border:0px;
	font-size:1.2em;
	
}
	
	
</style>
</head>
<body>


<table border="1">
<tr><th colspan="5"><img id="logo_img" src="img/logo_ju2.jpg"></th></tr>
<tr><th colspan="2" rowspan="4"><img src="/productImg/${product.barcode }" width="230" height="230" id="productImg"></th><th class="tit">바코드</th><td width="180.4px">${product.barcode }</td><td rowspan="5"><textarea rows="19" cols="50" name="intro" id="intro" placeholder="제품 소개" readonly="readonly"></textarea></tr>
<tr><th class="tit">제품명</th><td>${product.name }</td></tr>
<tr><th class="tit">종류</th><td>${product.type }</td></tr>
<tr><th class="tit">가격</th><td>${product.price }</td></tr>
<tr><th height="48px" class="tit">상태</th><td width="180.4px"><select><option value="0">판매중</option><option value="1">제외됨</option></select></td><th class="tit">제조사</th><td>${product.provider }</td></tr>
<tr><th colspan="5" height="180px"><button onclick="history.back()" class="standard_btn">수정</button><button onclick="history.back()" class="standard_btn">목록</button></th></tr>

</table>


</body>
</html>