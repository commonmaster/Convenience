<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>회원 가입</title>
 <style>
 	.join_table{
 		margin:0px auto;
 		border:0;
 		margin-bottom:20px;
 	
 	} 	
 	caption{
 		text-align:left;
 		font-size:1.5em;
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
 	
 	#submit_btn{
 		width:390px;
 		height:30px;
 		background:purple;
 		color:white;
 		font-weight:bold;
 		border-radius:5px;
 	}
				

 </style>
 </head>
 <body>
  
  <form>
	<table class="join_table">
		<caption>회원가입</caption>
		<tr><td id="caption_line" width="500px"></td></tr>
	</table>	
	
	<table class="join_table" id="join_table_content">	
		
		
		<tr><td><span class="tit">아이디</span><br>
				<input type="text" name="id" id="id" maxlength="14" readonly="readonly">&nbsp;<input type="button" value="중복검사"><br>
				<span id="id_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">비밀번호</span><br>
				<input type="password" name="password" id="password" maxlength="14" onblur="password_check(this)"><br>
				<span id="password_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">비밀번호확인</span><br>
				<input type="password" name="password2" id="password2" maxlength="14" onblur="password2_check(this)"><br>
				<span id="password2_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">이름</span><br>
				<input type="text" name="name" id="name" maxlength="14" onblur="name_check(this)"><br>
				<span id="name_message" class="message"></span>
			</td>
		</tr>
		<tr><td><span class="tit">이메일</span><br>
				<input type="email" id="email" name="email" onblur="email_std_check(this)"><br>
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

var correctInputID = false;
var correctInputPW = false;
var correctInputPW2 = false;
var correctInputName = false;
var correctInputEmail = false;

function id_check(ele){
	
	var value = ele.value;
	var message_ele = document.getElementById("id_message");
	
	if(value.trim() == ""){
		message_ele.value = "필수 입력 사항입니다.";
	}
	else{
		message_ele.value = "";
	}	
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



function password2_check(ele){
	
	var value_origin = document.getElementById("password").value;
	var value = ele.value;
	var message_ele = document.getElementById("password2_message");
	
	if(value.length == 0){
		message_ele.innerHTML = "필수입력사항입니다.";
		correctInputPW = false;
		
	}
	else{
		if(value.indexOf(" ") != -1){
			message_ele.innerHTML = "공백은 입력할 수 없습니다.";
			correctInputPW = false;
		}
		else if(value != value_origin){
			message_ele.innerHTML = "패스워드 불일치";
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


</script>
</script>
</html>