<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Boolean canUse = (Boolean) request.getAttribute("canUse");
	String id = (String) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	marin: 20px;
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
</style>
</head>
<body>

	<form action="#" method="post" onsubmit="return checkSubmit()">
		<table>
			<caption>비밀번호 변경</caption>
			<tr>
				<td><span class="tit">기존 비밀번호</span><br> 
					<input type="password" name="origin_password" id="origin_password" maxlength="14" onblur="password_check(this)" placeholder="최대  14자"><br>
					<span id="password_message" class="message"></span>
				</td>
			</tr>
			
			<tr>
				<td><span class="tit">변경할 비밀번호</span><br> 
					<input type="password" name="password" id="password" maxlength="14" onblur="password_check(this)" placeholder="최대  14자"><br>
					<span id="password_message" class="message"></span>
				</td>
			</tr>
			
			<tr>
				<td><span class="tit">비밀번호확인</span><br>
					<input type="password" id="password2" maxlength="14" onblur="password2_check(this)" placeholder="최대  14자"><br>
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

	var correctInputPW = false;
	var correctInputPW2 = false;

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
			correctInputPW2 = false;
			
		}
		else{
			if(value.indexOf(" ") != -1){
				message_ele.innerHTML = "공백은 입력할 수 없습니다.";
				correctInputPW2 = false;
			}
			else if(value != value_origin){
				message_ele.innerHTML = "패스워드 불일치";
				correctInputPW2 = false;
			}
			
			else{
				message_ele.innerHTML = "";
				correctInputPW2 = true;
			}		
		}	
	}

	
	function checkSubmit(){
		
		var ele = document.getElementById("id");
		var value = ele.value.trim();
		ele.value = value;
		
		return correctInputID;
	}
	
</script>
</html>