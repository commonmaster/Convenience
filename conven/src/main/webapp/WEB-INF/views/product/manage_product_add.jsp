<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품등록(매니저)</title>
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
	
	input{
		padding:3px;
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

<form action="/manage_product_add" method="post" enctype="multipart/form-data" onsubmit="return checkSubmit()">
<table>
<tr><th colspan="5"><img id="logo_img" src="/img/logo_ju2.jpg"><span>제품 등록</span></th></tr>
<tr><th colspan="2" rowspan="4"><img src="img/noimg.png" width="230" height="230" id="productImg"></th><th class="tit" width="60px">바코드</th><td><input type="number" name="barcode" id="barcode"></td><td rowspan="5"><textarea rows="19" cols="50" name="intro" id="intro" placeholder="제품 소개"></textarea></tr>
<tr><th class="tit">제품명</th><td><input type="text" name="name" id="name" autocomplete="off"></td></tr>
<tr><th class="tit">종류</th><td><select name="type" id="type">   				
   			 	<option value="1">과자</option>
   				<option value="2">음료수</option>
    			<option value="3">아이스크림</option>   
			</select></td></tr>
<tr><th class="tit">가격</th><td><input type="text" name="price" id="price"></td></tr>
<tr><td colspan="2" align="center" height="48px"><input type="file" id="productImgFile" name="productImgFile"></td><th class="tit">제조사</th><td><input type="text" name="provider" id="provider" ></td></tr>
<tr><th colspan="5" height="180px"><input type="submit" value="등록" class="standard_btn"><button onclick="history.back()" class="standard_btn">취소</button></th></tr>


</table>

</form>
</body>
<script>
var correctFile = false;

function 그림파일읽어출력하기(이벤트) {
	correctFile = false;
	var fileInput=이벤트.target;
	//change이벤트.target(대상)은 <input type="file" name="profileFile" id="profileFile"/>
    var 선택된그림파일관리객체 = fileInput.files[0]; 
    var 선택된그림size = 선택된그림파일관리객체.size;
     /*
    if(선택된그림size > 1024*10){
    	alert("10K 요량초과!");
    	fileInput.value="";
    	return ;
    }
     */
    if (!선택된그림파일관리객체.type.match('image.*')) {
       alert("욱! 그림이 아니예요!");
       fileInput.value="";
       correctFile = false;
       return;
    }
    var 파일리더 = new FileReader();
         파일리더.onload = function(선택된그림파일관리객체) {
  	  var imgFile=document.getElementById("productImg");    	  
  	  imgFile.src=선택된그림파일관리객체.currentTarget.result;
    }
         
    파일리더.readAsDataURL(선택된그림파일관리객체);
    correctFile = true;
    return;
}
document.querySelector("#productImgFile").addEventListener("change", 그림파일읽어출력하기, false);

function checkSubmit(){
	
	var barcode = document.getElementById("barcode");
	
	var value = barcode.value;
	
	if(value.trim()== ""){
		barcode.focus();
		return false;
	}
	
	var name = document.getElementById("name");
	value = name.value;
	
	if(value.trim()== ""){
		name.focus();
		return false;
	}
		
	var price = document.getElementById("price");
	value = price.value;
	
	if(value.trim()== ""){
		price.focus();
		return false;
	}
	
	var provider = document.getElementById("provider");
	value = provider.value;
	
	if(value.trim()== ""){
		provider.focus();
		return false;
	}
	
	if(!correctFile){
		alert("그림파일을 등록해주세요");
		return false;
	}
	
	return true;
	
	
	
	
	
	
}


</script>
</html>