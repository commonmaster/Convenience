<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	margin:20px auto;
}

caption {
	padding: 5px;
	font-size: 1.5em;
	font-weight: bold;
	font-style: italic;
}

td {
	padding: 3px 10px;
}

.message {
	color: red;
	font-size: 0.8em;
}

#submit_btn{
 		width:164px;
 		padding:5px;
 		background:purple;
 		color:white;
 		font-weight:bold;
 		border-radius:5px;
 		margin-top:10px;
 	}
 	
#submit_btn:hover{
	background:#B404AE;
} 	
</style>
</head>
<body>
	
	<form action="#" method="post" onsubmit="return checkSubmit()">
		<table>
			<caption>비밀번호 변경</caption>
			<tr>
				<td><span class="tit">기존 비밀번호</span><br> 
					<input type="password" name="origin_password" id="origin_password" maxlength="14" onblur="origin_password_check()" placeholder=""><br>
					<span id="origin_password_message" class="message"></span>
				</td>
			</tr>
			
			<tr>
				<td><span class="tit">변경할 비밀번호</span><br> 
					<input type="password" name="password" id="password" maxlength="14" onblur="password_check()" placeholder="최대  14자"><br>
					<span id="password_message" class="message"></span>
				</td>
			</tr>
			
			<tr>
				<td><span class="tit">비밀번호확인</span><br>
					<input type="password" id="password2" maxlength="14" onblur="password2_check()" placeholder="최대  14자"><br>
					<span id="password2_message" class="message"></span>
				</td>
			</tr>
			<tr>
			<td align="center"><input type="submit" id="submit_btn" value="비밀번호 변경"></td>
		<tr>
		</table>
	</form>
	

</body>
<script>

	function origin_password_check(){
		
		var ele = document.getElementById("origin_password");
		var value = ele.value;
		var message_ele = document.getElementById("origin_password_message");
		
		if(value.length == 0){
			message_ele.innerHTML = "필수입력사항입니다.";
			ele.focus();
			return false;
			
		}
		else{
			message_ele.innerHTML = "";
			return true;
		}
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
			ele.focus();
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
				ele.focus();
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
			ele.focus();
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
				ele.focus();
				return false;
			}
		}	
	}

	
	function checkSubmit(){
		
				
		return origin_password_check() && password_check() && password2_check();
	}
	
</script>
</html>