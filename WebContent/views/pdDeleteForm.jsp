<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">@import url("common.css");</style>
</head>
<body>
<form action="pdDelete.co">
	<input type="hidden" name="product_id" value="${product_id}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="submit" value="확인">
</form>
</body>
</html>