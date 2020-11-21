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
<div align="center"><h2>구매가 완료되었습니다</h2></div>
<table border="1">
	<tr align="center">
		<th width="1%">번호</th><th width="6%">상품명</th>
		<th width="2%">수량</th><th width="3%">금액</th>
	</tr>
<c:set var="tot" value="0"></c:set>
<c:forEach var="order" items="${lists}" varStatus="status">
	<tr align="center">
	<td><c:out value="${status.count}" /><c:out value="${status.end}"/></td>
		<td><img src="/epl/upload/${order.p_image}" border="0" width="50" align="middle" alt="${cart.p_image}">
			<b>${order.p_name}</b></td>
		<td align="center">
		${order.buy_count}
		<td><fmt:formatNumber value="${order.buy_price*order.buy_count}"/>원</td>
		<c:set var="pr" value="${order.buy_price*order.buy_count}"></c:set>
		<c:set var="tot" value="${pr+tot }"></c:set>
</c:forEach>
	<tr align="right">
		<td colspan="5"><b>총 금액 :<fmt:formatNumber value="${tot }"/>원</b>
		</td></tr>
</table>
<br>
<div align="center">
<input type="button" value="메인으로" onclick="location.href='shopMain.co'">
</div>
</body>
</html>