<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Document</title>

<style>

#content_div{
	width:1160px;	
}

caption {
		padding:10px;
		font-size:2em;
		font-style:italic;
	}

thead .board_head_line{
		border-bottom:1px solid purple;
	}

#board_add_table{
	margin:0px auto;
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
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	
	
</script>

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