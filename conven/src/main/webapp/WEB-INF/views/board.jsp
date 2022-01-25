<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>자유게시판 상세</title>

<style>

#content_div{
	width:1160px;	
}

thead .board_head_line{
		border-bottom:1px solid purple;
	}

.board_detail_table, .board_detail_table2{
	margin:0px auto 20px auto;
	
}

.board_detail_table2 .detail_author_th {
	text-align:left;
	padding:0px 5px;
}

.board_detail_table2 .detail_regDate_td{
	text-align:left;
	padding:0px 5px;
	color:grey;
}

.board_detail_table2 .detail_title_th{
	width:1050px;
	height:40px;
	font-size:1.2em;
	padding:5px;
	margin-top:10px;
	text-align:left;
}

caption {
		padding:10px;
		font-size:2em;
		font-style:italic;
		text-align:left;
	}

.detail_content_td{	
	font-size:1.3em;
	padding:5px;
	margin-top:10px;
	resize:none;
}



#board_add_btn_span{
	float:right;
}

.board_btn{
	padding:7.5px 15px;
	border-radius:10px;
	margin-left:10px;
	background:skyblue;
	font-weight:bold;
	background:#F8E0F1;
	border:0px;
	
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	
	function deleteConfirm(){
		
		if(confirm("정말 삭제하시겠습니까?")){
			location.href="board_delete?no="+${board.no}
		}
	}
	
</script>

</head>
<body>	
	
	<section>
		<div id="content_div">		
			
			<table border="0" class="board_detail_table">
				<caption>
					<span id="board_add_btn_span"><c:set var="pageNo" value="${empty param.pageNo ? '1':param.pageNo}"/>
					<c:set var="searchType" value="${empty param.searchType ? '0':param.searchType}"/>
					<c:set var="searchContent" value="${empty param.searchContent ? null :param.searchContent}"/>
						<c:if test="${conven_session_id == board.authorId}">
						<button class="board_btn" onclick="location.href='board_modify?no=${board.no}&pageNo=${pageNo }'">수정</button><button class="board_btn" onclick="deleteConfirm();">삭제</button></c:if><button class="board_btn" onclick="location.href='boards?pageNo=${pageNo}&searchType=${searchType }&searchContent=${searchContent }'">목록</button>
					</span></caption>
				<thead>					
					<tr>
						<td class="board_head_line" width="1050px"></td>
					</tr>
				</thead>
				
			</table>
			
			<table border="0" class="board_detail_table2">
				
				<thead>
					<tr>
						<th class="detail_title_th">${board.title}</th>
					</tr>
					<tr>
						<th class="detail_author_th">${board.authorId }</th>
					</tr>
					<tr>
						<td class="detail_regDate_td">${board.regDate } &nbsp;&nbsp;조회 ${board.readCount}</td>
					</tr>
					<tr>
						<td class="board_head_line" width="1050px"></td>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td class="detail_content_td">${board.printContentText() }</td>
					</tr>
				</tbody>
				
			</table>
			
		</div>
	</section>
</body>
</html>