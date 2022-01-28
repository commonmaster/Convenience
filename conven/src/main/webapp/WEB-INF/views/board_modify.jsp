<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" type="text/css" href="/css/board_modify.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
		
	<section>
		<div id="content_div">
			
			<form action="board_modify?no=${board.no}&pageNo=${param.pageNo}" method="post">
			<table border="0" id="board_add_table">
				<caption>자유게시글 수정<span id="board_btn_span"><input type="submit" value="수정" class="board_btn"></span></caption>
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