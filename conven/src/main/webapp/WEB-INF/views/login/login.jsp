<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Integer result = (Integer)request.getAttribute("result"); 
	String prev_url = (String)request.getAttribute("prev_url");
	if(prev_url == null){
		prev_url = "/main";
	}
%>

   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>편의점 로그인</title>
 <style>
 	body {text-align:center;}
	
	#img{		
		width:400px;
		
		}
		
	input[type=text], input[type=password]{
		width:300px;
		height:35px;
		font-size:1.2em;
		padding:0px 5px;
	}	
	
	

	#log {display:inline-block;}
	
	
	#button { background:purple;
		color:white;
		font-weight:bold;
		border-radius:3px;
		font-size:1.5em;
		padding:10px 5px;
		
		}
	
	.button_line {padding:0px 9px;}

	.underline a{font-size:1.2em;
				color:gray;
				text-decoration:none;
				}
				
	#message{font-size:0.8em;
			color:red;}
 </style>
 </head>
 <body>
  <img id="img" src="img/logo_ju.jpg"><br>
  <form id="log" action="/login" method="post" onsubmit="return submitCheck()" target="_parent">
	<table border="0">
		<tr>
			<td><input type="text" id="id" name="id" placeholder="아이디" maxlength="14"></td>
			<td rowspan="2" class="button_line"><input type="submit" value="로 그 인" id="button"></td>
		</tr>
		<tr>
			<td><input type="password" id="password" name="password" placeholder="비밀번호" maxlength="14"></td>
		</tr>
		<tr>
			<td></td>
			<td class="underline"><a href="/join" target="_parent">회원가입</a></td>
		</tr>
		<tr><td><span id="message">
		<% if(result != null){%>
			아이디 또는 비밀번호 오류
		<%} %>	
		</span></td></tr>
	</table>
  </form>
  
</body>
<script>
function idCheck(){
	
	var ele = document.getElementById("id");
	var message_ele = document.getElementById("message");
	var value = ele.value.trim();
	ele.value = value;
	
	if(ele.value.length == 0){
		message_ele.innerHTML = "아이디를 입력해주세요.";
		ele.focus();
		return false;
		
	}
	else{		
		message_ele.innerHTML = "";
		return true;
				
	}
}

function pwdCheck(){
	
	var ele = document.getElementById("password");
	var message_ele = document.getElementById("message");
	var value = ele.value.trim();
	ele.value = value;
	
	if(ele.value.length == 0){
		message_ele.innerHTML = "비밀번호를 입력해주세요.";
		ele.focus();
		return false;
		
	}
	else{
		message_ele.innerHTML = "";
		return true;	
	}
}

function submitCheck(){
	
	return idCheck() && pwdCheck();
}

</script>
</html>