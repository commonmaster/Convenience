<%@page import="com.stone.springmvc.board.common.자유게시판페이지구성정보"%>
<%@page import="com.stone.springmvc.board.common.자유게시글"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int searchType = (int) request.getAttribute("searchType");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>자유게시판</title>
<style>
@charset "UTF-8";

section {	
	padding:20px;
	width: 1180px;
	height:730px;
	margin:0px auto;	
}

#content_div {
	width: 1160px;	
}

#board_table {
	margin-left: auto;
	margin-right: auto;
}

#no_column {
	width: 100px;
}

#title_column {
	width: 540px;
}

#author_column {
	width: 170px;
}

#regDate_column {
	width: 140px;
}

#readCount_column {
	width: 100px;
}

caption {
	padding: 10px;
	font-size: 2em;
	font-style: italic;
	text-align: left;
}

thead th {
	height: 30px;
}

thead .board_head_line {
	border-bottom: 1px solid purple;
}

tfoot th {
	height: 50px;
}

#board_table tbody td {
	padding: 2px 5px;
	height: 40px;
}

#board_table tbody #hasNoRecord {
	height: 150px;
	color: blue;
}

tfoot #write_btn_td {
	text-align: right;
	padding: 20px;
}

tfoot #write_btn {
	padding: 5px;
	border-radius: 10px;
	background: white;
	font-weight: bold;
	font-size: 1em;
	line-height: 22px;
}

tfoot th a {
	text-decoration: none;
}

.board_title_td, .board_author_td {
	text-align: left;
}

.board_no_td, .board_regDate_td, .board_readCount_td {
	text-align: center;
}

.board_title_td a, .page_th a {
	text-decoration: none;
	color: black;
}

.page_th a {
	
}

.page_th .page_num_a {
	width: 20px;
	height: 20px;
	display: inline-block;
}

.page_str_a {
	width: 40px;
}

.text_left {
	text-align: left;
}

#write_img {
	width: 15px;
	height: 15px;
}

#write_btn_text_span {
	text-decoration: none;
	color: black;
}

#search_div {
	text-align: center;
}

#search_select {
	height: 30px;
	padding: 5px;
	margin-left: 15px;
	border-radius: 5px;
}

#search_input {
	width: 400px;
	height: 24px;
	margin-left: 10px;
	border-radius: 5px;
}

#search_btn {
	width: 52px;
	height: 32px;
	border: 0px;
	background: purple;
	color: white;
	font-weight: bold;
}

#currentPage_a {
	border: 1px solid purple;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
	<nav>
		<%@include file="../include/nav_contents.jsp"%>
	</nav>
	<section>
		<div id="content_div">
			<table id="board_table" border="0">
				<caption>자유게시판</caption>
				<thead>
					<tr>
						<td colspan="5" class="board_head_line"></td>
					</tr>
					<tr>
					<tr>
						<th id="no_column"></th>
						<th id="title_column">제목</th>
						<th id="author_column" class="text_left">작성자</th>
						<th id="regDate_column">작성일</th>
						<th id="readCount_column">조회</th>
					</tr>
				<thead>
				<tfoot>
					<tr>
						<td id="write_btn_td" colspan="5">
							<button id='write_btn'>
								<img src="img/board_write.png" id="write_img">&nbsp;<span
									id="write_btn_text_span">글쓰기</span>
							</button>
						</td>
					</tr>

					<c:if test="${pageInfo.hasRecords()}">
						<tr>
							<th colspan="5" class="page_th">
								<c:if test="${pageInfo.startPage > pageInfo.SHOW_PAGE_COUNT}">
									<a href="boards?pageNo=${pageInfo.startPage - 1}&searchType=${searchType}&searchContent=${searchContent}" class="page_str_a">[이전]</a>
								</c:if> 
								<c:forEach var="pNo" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
									<c:if test="${pNo == pageInfo.currentPage}">
										<a href="boards?pageNo=${pNo}&searchType=${searchType}&searchContent=${searchContent}" id="currentPage_a" class="page_num_a">${pNo}</a>
									</c:if>
									<c:if test="${pNo != pageInfo.currentPage}">
										<a href="boards?pageNo=${pNo}&searchType=${searchType}&searchContent=${searchContent}"	class="page_num_a">${pNo}</a>
									</c:if>
								</c:forEach> <c:if test="${pageInfo.endPage < pageInfo.totalPageCount}">
									<a href="boards?pageNo=${pageInfo.endPage + 1}&searchType=${searchType}&searchContent=${searchContent}" class="page_str_a">[다음]</a>
								</c:if>
							</th>	
						</tr>
					</c:if>

				</tfoot>

				<tbody>
					<c:if test="${pageInfo.hasNoRecords()}">
						
						<tr><th colspan="5" id="hasNoRecord">게시글이 없습니다</th></tr>
					</c:if>
					<c:forEach var="board_record" items="${pageInfo.contents}">
						<tr>
							<td class="board_no_td">${board_record.no}</td>
							<td class="board_title_td">
								<a href="board?no=${board_record.no}&pageNo=${pageInfo.currentPage}&searchType=${searchType}&searchContent=${searchContent}">
								<c:out value="${board_record.title}" /></a>
							</td>
							<td class="board_author_td">${board_record.name}</td>
							<td class="board_regDate_td">${board_record.printDate()}</td>
							<td class="board_readCount_td">${board_record.readCount}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="search_div">
				<form action="/boards">
					<select id="search_select" name="searchType">
						<option value="1">제목</option>
						<option value="2">내용</option>
					</select>
					<input type="text" id="search_input" name="searchContent" required="required" value=${searchContent}> 
					<input type="submit" value="검색" id="search_btn">
				</form>
			</div>
		</div>
	</section>
	
	<footer>
		<%@include file="../include/footer_contents.jsp"%>
	</footer>
</body>

<script>
	$().ready(
			function() {
				$(".board_title_td a, .page_th a").on("mouseover", function(e) {
					var ele = $(e.target);
					ele.css({
						"text-decoration" : "underline"
					});
				});

				$(".board_title_td a, .page_th a").on("mouseout", function(e) {
					var ele = $(e.target);
					ele.css({
						"text-decoration" : "none"
					});
				});

				$("#write_btn").click(function() {
					window.open("/board_add", "_self");
				});

				$("#write_btn").on("mouseover", function(e) {
					var ele = $(e.target);
					ele.css({
						"text-decoration" : "underline"
					});
				});

				$("#write_btn").on("mouseout", function(e) {
					var ele = $(e.target);
					ele.css({
						"text-decoration" : "none"
					});
				});

				if (<%=searchType == 0%>) {
					$("#search_select").find('option:eq(${searchType})').prop(
							"selected", true);
				} else {
					$("#search_select").find('option:eq(${searchType-1})')
							.prop("selected", true);
				}
			});
</script>
</html>