<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 추가</title>
<link rel="stylesheet" type="text/css" href="/css/board_add.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>

<body>
		
	<section>
		<div id="content_div">
		
			<form action="board_add" method="post">
			<table border="0" id="board_add_table">
				<caption>자유게시판 글쓰기<span id="board_add_btn_span"><input type="submit" value="등록 " class="board_btn"></span></caption>
				<thead>
					<tr>
						<td class="board_head_line" width="1050px"></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="hidden" name="authorId" value="${conven_session_id}"></td>
					</tr>
					<tr>
						<td><input type="text" id="title" name="title" required="required" placeholder="제목을 입력해주세요(최대 40자)" maxlength="40"></td>
					</tr>
					<tr>
						<td><textarea rows="5" cols="30" id="contents" name="contents" required="required" placeholder="내용을 입력해주세요(최대 500자)" maxlength="500"></textarea></td>
				</tbody>
					
			</table>
			</form>
		</div>
	</section>
</body>
</html>