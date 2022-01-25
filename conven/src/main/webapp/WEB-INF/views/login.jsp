<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>편의점 로그인</title>
 <style>
 	body {text-align:center;}
	
	#img{
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
				

 </style>
 </head>
 <body>
  <img id="img" src="img/logo_ju.jpg"><br>
  <form id="log" action="login" method="post" target="_parent">
	<table border="0">
		<tr>
			<td><input type="text" name="id" placeholder="아이디:user1 or user2" required="required"></td>
			<td rowspan="2" class="button_line"><input type="submit" value="로 그 인" id="button" style="font-size:20px"></td>
		</tr>
		<tr>
			<td><input type="password" name="password" placeholder="비밀번호:1234" required></td>
		</tr>
		<tr>
			<td></td>
			<td class="underline"><a href="/join">회원가입</a></td>
		</tr>
		
	</table>
  </form>
  
</body>
</html>