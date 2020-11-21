<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 목록</title>
<style type="text/css">
	table { width : 50%; margin-left: auto; margin-right: auto; }
</style>
</head>
<body>
<table>
<tr align="center">
<td colspan="6" align="center">
<h2>장바구니</h2>
</td>
</tr>
<c:if test="${empty list }">
	<table border="1" style="width: 50%;">
		<tr><th colspan="5">장바구니에 담긴 물품이 없습니다.</th></tr>
	</table><br>
	<div align="center">
	<input type="button" value="쇼핑계속" onclick="location.href='shopMain.co'">
	</div>
</c:if>
</table>
<c:if test="${not empty list }">
<table border="1">
<tr align="center">
<th width="1%">번호</th><th width="7%">상품명</th>
<th width="2%">수량</th><th width="3%">금액</th>
<th width="1%">삭제</th></tr>
<c:set var="tot" value="0"></c:set>
<c:forEach var="cart" items="${list}" varStatus="status">
	<tr align="center">
	<td><c:out value="${status.count}" /><c:out value="${status.end}"/></td>		
		<td><img src="/epl/upload/${cart.p_image}" border="0" width="50" align="middle" alt="${cart.p_image}">
			<b>${cart.p_name}</b></td>
		<td align="center">
		<form name="frm" method="post" action="cartUpdate.co"
			onsubmit="return chk()">
			<input type="number" name="buy_count" value="${cart.buy_count}" size="1" style="width: 20%;">
			<input type="hidden" name="cart_id" value="${cart.cart_id}">
			<input type="submit" value="수정">
		</form></td>
		<td><fmt:formatNumber value="${cart.buy_price*cart.buy_count}"/>원</td>
		<td><input type="button" value="삭제" onclick="location.href='cartDel.co?cart_id=${cart.cart_id }'">
		</td></tr>
		<c:set var="pr" value="${cart.buy_price*cart.buy_count}"></c:set>
		<c:set var="tot" value="${pr+tot }"></c:set>
</c:forEach>
	<tr align="right">
		<td colspan="5"><b>총 금액 :<fmt:formatNumber value="${tot }"/>원</b>
		</td></tr>
	<tr align="center">
		<td colspan="5">
		<input type="button" value="구매하기" onclick="location.href='buyForm.co'">
		<input type="button" value="쇼핑계속" onclick="location.href='shopMain.co'">
	</td></tr>
</table>
</c:if>
</body>
</html>