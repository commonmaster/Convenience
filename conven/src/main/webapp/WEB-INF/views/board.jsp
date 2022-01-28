<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 상세</title>
<link rel="stylesheet" type="text/css" href="/css/board.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	
	function deleteConfirm(){
		
		if(confirm("정말 삭제하시겠습니까?")){
			location.href="/board_delete?no="+${board.no}
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
						<button class="board_btn" onclick="location.href='/board_modify?no=${board.no}&pageNo=${pageNo }'">수정</button><button class="board_btn" onclick="deleteConfirm();">삭제</button></c:if><button class="board_btn" onclick="location.href='boards?pageNo=${pageNo}&searchType=${searchType }&searchContent=${searchContent }'">목록</button>
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
						<th class="detail_author_th">${board.name }</th>
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