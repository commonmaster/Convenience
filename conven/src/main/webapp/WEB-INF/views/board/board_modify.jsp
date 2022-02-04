<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<style>
#content_div{
	
}

table{
	margin: 0px auto;	
}

#logo_img{
		border-radius: 15px;
	}

caption {
		padding:10px;
		font-size:2em;
		font-style:italic;
		text-align:left;
	}

thead .board_head_line{
		border-bottom:1px solid purple;
	}


#title{
	width:1050px;
	height:40px;
	font-size:1.2em;
	padding:5px;
	margin-top:10px;
}

#contents{
	width:1050px;
	height:420px;
	font-size:1.3em;
	padding:5px;
	margin-top:10px;
	resize:none;
}

#board_btn_span{
	float:right;
}

.board_btn{
	padding:7.5px 15px;
	border-radius:10px;
	margin-left:10px;
	background:#F8E0F1;
	font-weight:bold;
	border:0px;
	font-size:0.5em;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
		
	<section>
		<div id="content_div">
			<div align="center">
			<img id="logo_img" src="/img/logo_ju2.jpg">
			</div>
			<form action="/board_modify" method="post">
			<input type="hidden" name="no" value="${param.no}">
			<table border="0" id="board_add_table">
				<caption>자유게시글 수정<span id="board_btn_span"><input type="submit" value="수정" class="board_btn"><a class="board_btn" onclick="history.back()">취소</a></span></caption>
				<thead>
					<tr>
						<td class="board_head_line" width="1050px"></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" id="title" name="title" required="required" placeholder="제목을 입력해주세요(최대 40자)" maxlength="40" value="${board.title }"></td>
					</tr>
					<tr>
						<td><textarea rows="5" cols="30" id="contents" name="contents" required="required" placeholder="내용을 입력해주세요(최대 500자)" maxlength="500">${board.contents }</textarea></td>
				</tbody>
					
			</table>
			</form>
		</div>
	</section>
</body>
</html>