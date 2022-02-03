<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>물품 상세</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 <style>
 	table{
 		border-collapse:collapse;
 		
 	}
 
	td {
		text-align:center;
		padding:5px;
	}
	
	#product_detail_img{
		width:180px;
		height:180px;	
		
	}
	
	#product_detail_img_td{
		width:200px;
		height:200px;
		
	}
	
	#product_detail_intro_td{
		text-align:left;
		padding:5px;
		overflow:auto;
	}
	
 </style>
 </head>


 <body>
  <table border="1">
  	<tr>
  		<td colspan="2" id="product_detail_img_td"><img src="/productImg/${product.barcode}" id="product_detail_img"></td><td width="300px" id="product_detail_intro_td">${product.intro }</td>
	</tr>
	<tr><td id="product_detail_name_t_td">제품명</td><td id="product_detail_name_td" 	colspan="2">${product.name }</td></tr>
	<tr><td width=60px;>종&nbsp;&nbsp;&nbsp;류</td><td colspan="2">${product.type }</td></tr>
	<tr><td>가&nbsp;&nbsp;&nbsp;격 </td><td colspan="2">${product.price }</td></tr>
	<tr><td>제조사</td><td colspan="2">${product.provider }</td></tr>
	<tr><td>바코드</td><td colspan="2">${product.barcode }</td></tr>
  </table>
 </body>
</html>
</body>
</html>