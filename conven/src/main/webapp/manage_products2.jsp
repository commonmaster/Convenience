<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 리스트(매니저)</title>
<style>
	table{margin: 0px auto;
	border: 0;}
	th{background:#F8E0F1;}
	th,td{padding:5px;}
	td{height:30px;}
	input{
		width:380px;
		padding:5px;
	}
	
	button{
		padding:5px;
	}
	
	
</style>
</head>
<body>
<div align="center" id="logo_div"><img id="logo_img" src="/img/logo_ju2.jpg" style="vertical-align:middle;"><span>제품리스트(판매중)</span></div>
<table>
<thead><tr><th width="130px">바코드</th><th width="380px">제품명</th><th width="100px">종류</th><th width="80px">가격</th><th width="240px">제조사</th></tr></thead>
<tfoot>
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
						</tr>
					</c:if>


</tfoot>
<tbody>
	<tr><td>1234567891011</td><td>엄청나게 맛있는 과자인데 이름을 알려나몰라</td><td>아이스크림</td><td>100000</td><td>오리온은오리온인데무슨오리온</td></tr>
<tr><td>1234567891011</td><td>엄청나게 맛있는 과자인데 이름을 알려나몰라</td><td>아이스크림</td><td>100000</td><td>오리온은오리온인데무슨오리온</td></tr>
<tr><td>1234567891011</td><td>엄청나게 맛있는 과자인데 이름을 알려나몰라</td><td>아이스크림</td><td>100000</td><td>오리온은오리온인데무슨오리온</td></tr>
<tr><td>1234567891011</td><td>엄청나게 맛있는 과자인데 이름을 알려나몰라</td><td>아이스크림</td><td>100000</td><td>오리온은오리온인데무슨오리온</td></tr>
<tr><td>1234567891011</td><td>엄청나게 맛있는 과자인데 이름을 알려나몰라</td><td>아이스크림</td><td>100000</td><td>오리온은오리온인데무슨오리온</td></tr>
<tr><td>1234567891011</td><td>엄청나게 맛있는 과자인데 이름을 알려나몰라</td><td>아이스크림</td><td>100000</td><td>오리온은오리온인데무슨오리온</td></tr>
<tr><td>1234567891011</td><td>엄청나게 맛있는 과자인데 이름을 알려나몰라</td><td>아이스크림</td><td>100000</td><td>오리온은오리온인데무슨오리온</td></tr>
<tr><td>1234567891011</td><td>엄청나게 맛있는 과자인데 이름을 알려나몰라</td><td>아이스크림</td><td>100000</td><td>오리온은오리온인데무슨오리온</td></tr>
<tr><td>1234567891011</td><td>엄청나게 맛있는 과자인데 이름을 알려나몰라</td><td>아이스크림</td><td>100000</td><td>오리온은오리온인데무슨오리온</td></tr>
<tr><td>1234567891011</td><td>엄청나게 맛있는 과자인데 이름을 알려나몰라</td><td>아이스크림</td><td>100000</td><td>오리온은오리온인데무슨오리온</td></tr>
</tbody>



</table>
<div align="center"><input type="text">&nbsp;<button>검색</button></div>
</body>
</html>