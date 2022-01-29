<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%Integer result = (Integer)request.getAttribute("result"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>편의점 로그인</title>
 <style>
 	body {text-align:center;}
	
	#img{
		margin-top:130px;
		width:250px;
		height:148px;
		}

	#log {display:inline-block;}
	
	
	#button { background:purple;
		color:white;
		font-size:bold;
		border-radius:3px;
		}
	
	.button_line {padding:0px 9px;}

	.underline a{font-size:14px;
				color:gray;
				text-decoration:none;
				}
				
	#message{font-size:0.8em}
 </style>
 </head>
 <body>
  <img id="img" src="img/logo_ju.jpg"><br>
  <form id="log" action="/login" method="post" onsubmit="return submitCheck()" target="_parent">
	<table border="0">
		<tr>
			<td><input type="text" id="id" name="id" placeholder="아이디"></td>
			<td rowspan="2" class="button_line"><input type="submit" value="로 그 인" id="button" style="font-size:20px"></td>
		</tr>
		<tr>
			<td><input type="password" id="password" name="password" placeholder="비밀번호"></td>
		</tr>
		<tr>
			<td></td>
			<td class="underline"><a href="/join">회원가입</a></td>
		</tr>
		<tr><td><span id="message">
		<% if(result != null && result == -1){%>
			아이디가 없습니다
		<%}else if(result != null && result == 0){%>
			비밀번호 오류
		<%} %>	
		</span></td></tr>
	</table>
  </form>
  
</body>
<script>
function idCheck(){
	
	var ele = document.getElementById("id");
	var message_ele = document.getElementById("message");
	
	if(ele.value.length == 0){
		message_ele.innerHTML = "아이디를 입력해주세요.";
		return false;
		
	}
	else{
		if(ele.value.indexOf(" ") != -1){
			message_ele.innerHTML = "공백은 입력할 수 없습니다.";
			return false;
		}
		else{
			message_ele.innerHTML = "";
			return true;
		}		
	}
}

function pwdCheck(){
	
	var ele = document.getElementById("password");
	var message_ele = document.getElementById("message");
	
	if(ele.value.length == 0){
		message_ele.innerHTML = "비밀번호를 입력해주세요.";
		return false;
		
	}
	else{
		if(ele.value.indexOf(" ") != -1){
			message_ele.innerHTML = "공백은 입력할 수 없습니다.";
			return false;
		}
		else{
			message_ele.innerHTML = "";
			return true;
		}		
	}
}

function submitCheck(){
	
	return idCheck() && pwdCheck();
}

</script>
</html>