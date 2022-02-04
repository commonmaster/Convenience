<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>회원 정보</title>
 <style>
 	.info_table{
 		margin:30px auto 0px auto;
 		border:0;
 		margin-bottom:20px;
 	
 	} 	
 	caption{
 		text-align:left;
 		font-size:1.5em;
 		font-style:italic;
 		font-weight:bold;
 		padding:10px;
 	}
 	
 	.float_right{
 		float:right;
 	}
 	
 	.board_btn{
	padding:7.5px 15px;
	border-radius:10px;
	margin-left:10px;
	font-weight:bold;
	background:#F8E0F1;
	border:0px;
	
	}
 	
 	#caption_line{
 		border-top: 1px solid purple;
 	}
 	
 	td{
 		padding:5px;
 	}
 	
 	.tit{
 		font-weight:bold;
 	}
 	 	
 	#id, input[type=email], #name{
 		width:380px;
 		height:20px;
 	}
 	
 	#password{
 		width:275px;
 		height:20px;
 	} 
 	
 	#pwd_change_btn{
 		padding:5px 8px;
 		color:white;
 		border-radius:5px;
 		font-weight:bold;
 		background:purple;
 		border:0;
 	}
 	
 	#submit_btn, #withdraw_btn{
 		width:390px;
 		padding:5px;
 		background:purple;
 		color:white;
 		font-weight:bold;
 		border-radius:5px;
 	}
 	
 	.message{
 		color:red;
 		font-size:0.8em;
 	}
				

 </style>
 </head>
 <body>
  
  <form action="/member" method="post" onsubmit="return submitCheck()">
	<table class="info_table">
		<caption>회원정보<span class="float_right"><button class="board_btn" onclick="location.href='/main'">메인</button></span></caption>
		<tr><td id="caption_line" width="500px"></td></tr>
	</table>	
	
	<table class="info_table" id="join_table_content">	
		
		
		<tr><td><span class="tit">아이디</span><br>
				<input type="text" name="id" id="id" maxlength="14" readonly="readonly" value="${member.id}"><br>
				<span id="id_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">비밀번호</span><br>
				<input type="password" name="password" id="password" maxlength="14" placeholder="변경시 비밀번호 입력">
				<input type="button" value="비밀번호 변경" id="pwd_change_btn" onclick="openPwdChange()"><br>
				<span id="password_message" class="message"></span>
			</td>
		</tr>
		
		<tr><td><span class="tit">이름</span><br>
				<input type="text" name="name" id="name" maxlength="20" onblur="name_check()" placeholder="최대  20자" value="${member.name }"><br>
				<span id="name_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">이메일</span><br>
				<input type="email" id="email" name="email" onblur="email_std_check()" value="${member.email }"><br>
				<span id="email_message" class="message"></span>
			</td>
		</tr>
		
		<tr>
			
			<td align="center">
				<input type="submit" id="submit_btn" value="정보 변경"><br><br><br>
				<c:if test="${member.id != 'admin'}">
					<a href="/withdraw" target="_parent">회원 탈퇴</a>
				</c:if>
			</td>
		</tr>	
	
	</table>
  </form>
  
</body>
<script>

var correctInputPW = false;
var correctInputName = true;
var correctInputEmail = true;

function openPwdChange(){
	
	var win = window.open("/pwd_change","pwdChange","width=230,height=390,top=200,left=800");
	
}

function password_check(){
	
	var ele = document.getElementById("password");
	var message_ele = document.getElementById("password_message");
	
	
	var value = ele.value;
	var pattern = /^[0-9a-zA-Z]{4}[0-9a-zA-Z]*$/;
	
	if(value.length == 0){
		//message_ele.innerHTML = "필수입력사항입니다.";
		ele.focus();
		return false;
		
	}
	else{
		message_ele.innerHTML = "";
		return true;
	}	
}

function name_check(){
	
	var ele = document.getElementById("name");
	var value = ele.value;
	var message_ele = document.getElementById("name_message");
	var pattern = /^[0-9a-zA-Z가-힣]([_-]?[0-9a-zA-Z가-힣])*$/;
	
	if(value.length == 0){
		message_ele.innerHTML = "필수입력사항입니다.";
		ele.focus();
		return false;
		
	}
	else{
		if(pattern.test(value)){
			
			message_ele.innerHTML = "";
			return true;
		}
		else{
			message_ele.innerHTML = "1~20자 한글, 영문대소문자, 숫자, 특수문자( _ )(-)만 사용가능합니다.";	
			ele.focus();
			return false;
		}		
	}	
}

function email_std_check(){
	
	var ele = document.getElementById("email");
	var value = ele.value;
	var message_ele = document.getElementById("email_message");
	var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	
	if (regEmail.test(value) === true) {
		message_ele.innerHTML = "";
		return true;
	}
	else{
		message_ele.innerHTML = "올바르지 못한 이메일 형식입니다.";
		ele.focus();
		return false;
	}	
}

function submitCheck(){
	
	
	return password_check() && name_check() && email_std_check();
}



</script>

</html>