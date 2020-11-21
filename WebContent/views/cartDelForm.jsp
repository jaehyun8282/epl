<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="cartDel.co">
	<input type="hidden" name="cart_id" value="${cart_id}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="submit" value="확인">
</form>
</body>
</html>