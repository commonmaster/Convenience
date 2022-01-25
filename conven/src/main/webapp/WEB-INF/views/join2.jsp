<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>회원 가입</title>
 <style>
 	#join_table{
 		margin:0px auto;
 	}
				

 </style>
 </head>
 <body>
  
  <form action="login" method="post" target="_parent">
	<table id="join_table">
		<tr><td></td></tr>
		<tr><td><span>아이디</span><br>
				<input type="text" name="id"><br>
				<span id="id_message"></span>
			</td>
		</tr>
		<tr><td><span>비밀번호</span><br>
				<input type="text" name="password"><br>
				<span id="passowrd_message"></span>
			</td>
		</tr>
		<tr><td><span>비밀번호확인</span><br>
				<input type="text" name="password2"><br>
				<span id="password2_message"></span>
			</td>
		</tr>
		<tr><td><span>이메일</span><br>
				<input type="text" id="email_id_part" value="test">
					@ <input type="hidden" id="email_direct" value="naver.com">
					<select id="email_select" onchange="emailSelect(this)">
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="google.com">google.com</option>
						<option value="direct">직접 입력</option>
				</select> <br>
				<span id="email_message"></span><br>
				<input type="hidden" id="email" name="email">
			</td>
		</tr>			
		
	</table>
  </form>
  
</body>
<script>
function send() {

	var id_part = document.getElementById("email_id_part");
	var address_part = document.getElementById("email_direct");
	var sendEmail = document.getElementById("email");

	sendEmail.value = id_part.value + "@" + address_part.value;
	alert(sendEmail.value );
	
	return true;

}

function emailSelect(ele) {

	var e = document.getElementById("email_direct");

	if (ele.value == "direct") {
		e.value = "";
		e.type = "text";

	} else {
		e.type = "hidden";
		e.value = ele.value;
	}
}

</script>
</script>
</html>