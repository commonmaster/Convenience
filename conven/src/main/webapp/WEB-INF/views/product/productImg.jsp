<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>	
<%
	// 응답(response)객체에 이미지 전송하도록 설정
	byte[] 물품이미지 = (byte[]) request.getAttribute("productImg");
	response.setContentType("image/png");
	ServletOutputStream 배송자 = response.getOutputStream();
	배송자.write(물품이미지);
%>