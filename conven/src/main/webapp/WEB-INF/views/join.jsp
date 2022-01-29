<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>회원 가입</title>
 <style>
 	
 	.join_table{
 		margin-left:auto;
 		margin-right:auto;
 		border:0;
 		margin-bottom:20px;
 	
 	}
 	
 	#jointable1{
 		margin-top:130px;
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
 	
 	#id{
 		width:305px;
 		height:20px;
 	} 	
 	
 	input[type=password], input[type=email], #name{
 		width:380px;
 		height:20px;
 	}
 	
 	#duple_btn{
 		padding:5px 8px;
 		color:white;
 		border-radius:5px;
 		font-weight:bold;
 		background:purple;
 		border:0;
 	}
 	
 	#submit_btn{
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
  
  <form action="/join" method="post" onsubmit="return submitCheck()">
	<table class="join_table" id="jointable1">
		<caption>회원가입</caption>
		<tr><td id="caption_line" width="500px"></td></tr>
	</table>	
	
	<table class="join_table" id="join_table_content">	
		
		
		<tr><td><span class="tit">아이디</span><br>
				<input type="text" name="id" id="id" maxlength="14" readonly="readonly" placeholder="중복 검사를 실행하세요" value="${id}">&nbsp;<input type="button" value="중복검사" onclick="openIdDuplecation()" id="duple_btn"><br>
				<span id="id_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">비밀번호</span><br>
				<input type="password" name="password" id="password" maxlength="14" onblur="password_check()"><br>
				<span id="password_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">비밀번호확인</span><br>
				<input type="password" id="password2" maxlength="14" onblur="password2_check()"><br>
				<span id="password2_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">이름</span><br>
				<input type="text" name="name" id="name" maxlength="20" onblur="name_check()" placeholder="최대  20자"><br>
				<span id="name_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">이메일</span><br>
				<input type="email" id="email" name="email" onblur="email_std_check()"><br>
				<span id="email_message" class="message"></span>
			</td>
		</tr>
		<tr>
			<td align="center"><input type="submit" id="submit_btn" value="가 입"></td>
		<tr>	
	</table>
  </form>
  
</body>
<script>


function idGet(value){
	
	var ele = document.getElementById("id");
	ele.value = value;
	correctInputID = true;
}

function openIdDuplecation(){
		
	var win = window.open("/duplication","duple","width=350,height=250,top=200,left=800");
	var ele = document.getElementById("id");
	
	correctInputID = false;
	ele.value = "";
}

function password_check(){
	
	var ele = document.getElementById("password");
	var password2_ele = document.getElementById("password2");
	var message_ele = document.getElementById("password_message");
	var message_ele2 = document.getElementById("password2_message");
	
	var value = ele.value;
	var pattern = /^[0-9a-zA-Z]{4}[0-9a-zA-Z]*$/;
	
	if(value.length == 0){
		message_ele.innerHTML = "필수입력사항입니다.";
		return false;
		
	}
	else{
		if(pattern.test(value)){
			if(value == password2_ele.value){
				message_ele.innerHTML = "";
				message_ele2.innerHTML = "";
				return true;
			}			
			else{
			
				message_ele.innerHTML = "비밀번호 불일치";
				return false;
			}
		}
		else{
			message_ele.innerHTML = "4~14자의 영문대소문자, 숫자만 사용가능합니다.";
			return false;
		}
	}	
}


function password2_check(){
	
	var ele = document.getElementById("password2");
	var password_ele = document.getElementById("password");
	var message_ele = document.getElementById("password2_message");
	var message_ele2 = document.getElementById("password_message");
	
	var value = ele.value;
	var pattern = /^[0-9a-zA-Z]{4}[0-9a-zA-Z]*$/;
	
	if(value.length == 0){
		message_ele.innerHTML = "필수입력사항입니다.";
		return false;
		
	}
	else{
		if(pattern.test(value)){
			if(value == password_ele.value){
				message_ele.innerHTML = "";
				message_ele2.innerHTML = "";
				return true;
			}			
			else{
			
				message_ele.innerHTML = "비밀번호 불일치";
				return false;
			}
		}
		else{
			message_ele.innerHTML = "4~14자의 영문대소문자, 숫자만 사용가능합니다.";
			return false;
		}
	}	
}

function name_check(){
	
	var value = document.getElementById("name").value;
	var message_ele = document.getElementById("name_message");
	var pattern = /^[0-9a-zA-Z가-힣]([_-]?[0-9a-zA-Z가-힣])*$/;
	
	if(value.length == 0){
		message_ele.innerHTML = "필수입력사항입니다.";
		return false;
		
	}
	else{
		if(pattern.test(value)){
			
			message_ele.innerHTML = "";
			return true;
		}
		else{
			message_ele.innerHTML = "1~20자 한글, 영문대소문자, 숫자, 특수문자( _ )(-)만 사용가능합니다.";	
			return false;
		}		
	}	
}

function email_std_check(){
	
	var value = document.getElementById("email").value;
	var message_ele = document.getElementById("email_message");
	var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	
	if (regEmail.test(value) === true) {
		message_ele.innerHTML = "";
		return true;
	}
	else{
		message_ele.innerHTML = "올바르지 못한 이메일 형식입니다.";
		return false;
	}	
}

function submitCheck(){
	
		return password_check() && password2_check() && name_check() && email_std_check();

}
</script>

</html>