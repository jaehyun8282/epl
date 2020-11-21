<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table { width : 50%; margin-left: auto; margin-right: auto; }
</style>
</head>
<body>
<div align="center"><h2>상품 등록 리스트</h2></div>
<div align="center">
<button onclick="location.href='pdRegisterForm.co'">상품 등록</button>
<button onclick="location.href='managerMain.co'">관리자 메인</button>
</div>
<br>
<table border="1">
	<tr align="center">
	<th width="5%">상품 번호</th><th width="5%">카테고리</th>
	<th width="40%">상품명</th><th>상품가격</th><th>재고</th>
	<th>이미지</th><th>할인율</th><th>상품등록일</th><th>수정</th><th>삭제</th></tr>
<c:forEach var="pd" items="${list}">
	<tr align="center">
		<td><b>${pd.product_id }</b></td>
		<td>${pd.p_category }</td>
		<td>${pd.p_name }</td>
		<td><fmt:formatNumber value="${pd.p_price}"/>원</td>
		<td>${pd.p_count }</td>
		<td>
		<img src="upload/${pd.p_image }" width="50"></td>
		<%-- <td>${pd.p_content }</td> --%>
		<td>${pd.p_rate }</td>
		<td>${pd.p_date }</td>
		<td align="center">
		<input type="button" value="수정" onclick="location.href='pdUpdateForm.co?product_id=${pd.product_id }'">
		<td align="center">
		<input type="button" value="삭제" onclick="location.href='pdDeleteForm.co?product_id=${pd.product_id }'">
	</tr>
</c:forEach>
</table>
</body>
</html>
