<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>회원 정보</title>
 <style>
 	.info_table{
 		margin:0px auto;
 		border:0;
 		margin-bottom:20px;
 	
 	} 	
 	caption{
 		text-align:left;
 		font-size:1.5em;
 		font-style:italic;
 		font-weight:bold;
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
		<caption>회원정보</caption>
		<tr><td id="caption_line" width="500px"></td></tr>
	</table>	
	
	<table class="info_table" id="join_table_content">	
		
		
		<tr><td><span class="tit">아이디</span><br>
				<input type="text" name="id" id="id" maxlength="14" readonly="readonly" value="${member.id}"><br>
				<span id="id_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">비밀번호</span><br>
				<input type="password" name="password" id="password" maxlength="14" onblur="password_check(this)" placeholder="">
				<input type="button" value="비밀번호 변경" id="pwd_change_btn" onclick="openPwdChange()"><br>
				<span id="password_message" class="message"></span>
			</td>
		</tr>
		
		<tr><td><span class="tit">이름</span><br>
				<input type="text" name="name" id="name" maxlength="20" onblur="name_check(this)" placeholder="최대  20자" value="${member.name }"><br>
				<span id="name_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">이메일</span><br>
				<input type="email" id="email" name="email" onblur="email_std_check(this)" value="${member.email }"><br>
				<span id="email_message" class="message"></span>
			</td>
		</tr>
		<tr>
			<td align="center"><input type="submit" id="submit_btn" value="정보 변경"><br><br><br>
								<a href="/withdraw" target="_parent">회원 탈퇴</a>
			</td>
		<tr>	
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

function password_check(ele){
	
	var value = ele.value;
	var message_ele = document.getElementById("password_message");
	
	if(value.length == 0){
		message_ele.innerHTML = "필수입력사항입니다.";
		correctInputPW = false;
		
	}
	else{
		if(value.indexOf(" ") != -1){
			message_ele.innerHTML = "공백은 입력할 수 없습니다.";
			correctInputPW = false;
		}
		else{
			message_ele.innerHTML = "";
			correctInputPW = true;
		}		
	}	
}

function name_check(ele){
	
	var value = ele.value;
	var message_ele = document.getElementById("name_message");
	
	if(value.length == 0){
		message_ele.innerHTML = "필수입력사항입니다.";
		correctInputName = false;
		
	}
	else{
		if(value.indexOf(" ") != -1){
			message_ele.innerHTML = "공백은 입력할 수 없습니다.";
			correctInputName = false;
		}
		else{
			message_ele.innerHTML = "";
			correctInputName = true;
		}		
	}	
}

function email_std_check(ele){
	
	var message_ele = document.getElementById("email_message");
	var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	
	if (regEmail.test(ele.value) === true) {
		message_ele.innerHTML = "";
		correctInputEmail = true;
	}
	else{
		message_ele.innerHTML = "올바르지 못한 이메일 형식입니다.";
		correctInputEmail = false;
	}	
}

function submitCheck(){
	console.log("correctInputPW: " + correctInputPW);
	console.log("correctInputName: " + correctInputName);
	console.log("correctInputEmail: " + correctInputEmail);
	
	return correctInputPW && correctInputName && correctInputEmail;
}



</script>

</html>