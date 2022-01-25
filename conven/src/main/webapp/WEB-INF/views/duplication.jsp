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

#id {
	width: 200px;
	height: 20px;
}

td {
	padding: 10px;
}

#duple_btn {
	padding: 5px 8px;
	color: white;
	border-radius: 5px;
	font-weight: bold;
	background: purple;
	border: 0;
}

#use_btn {
	width: 250px;
	padding: 5px 8px;
	color: white;
	border-radius: 5px;
	font-weight: bold;
	background: purple;
	border: 0;
}

.message {
	color: red;
	font-size: 0.8em;
}
</style>
</head>
<body>

	<form action="/duplication" method="post" onsubmit="return checkSubmit()">
		<table>
			<caption>ID 중복검사</caption>
			<tr>
				<td><input type="text" maxlength="14" placeholder="최대 14자" id="id" name="id" onblur="id_check(this)" value="${id}">&nbsp;
				<input type="submit" value="중복검사" id="duple_btn"><br>
				<span id="id_message" class="message"></span></td>
			</tr>
			<%if(canUse != null && canUse == true){ %>
			<tr>
				<td align="center"><button onclick="idSend()" id="use_btn">사용 하기</button></td>
			</tr>
			<%}%>
			
			<%if(canUse != null && canUse == false){ %>
			<tr>
				<td align="center">이미 사용 중인 아이디입니다.</td>
			</tr>
			<%} %>
		</table>
	</form>


</body>
<script>
	var correctInputID = false;

	function id_check(ele) {

		var value = ele.value;
		var message_ele = document.getElementById("id_message");

		if (value.length == 0) {
			message_ele.innerHTML = "필수입력사항입니다.";
			correctInputID = false;

		} else {
			if (value.indexOf(" ") != -1) {
				message_ele.innerHTML = "공백은 입력할 수 없습니다.";
				correctInputID = false;
			} else {
				message_ele.innerHTML = "";
				correctInputID = true;
			}
		}
	}

	function idSend() {
		var ele = document.getElementById("id");
		opener.idGet(ele.value);
		window.close();
	}
	
	function checkSubmit(){
		
		var ele = document.getElementById("id");
		var value = ele.value.trim();
		ele.value = value;
		
		return correctInputID;
	}
	
</script>
</html>