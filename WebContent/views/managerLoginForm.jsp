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
<form action="managerLogin.co">
<table>
<caption>관리자 로그인</caption>
	<tr><th>아이디</th><td><input type="text" name="manager_id"
		required="required" autofocus="autofocus"></td></tr>
	<tr><th>비밀번호</th><td><input type="password" name="manager_pass"
		required="required"></td></tr>
	<tr><th colspan="2"><input type="submit" value="로그인"></th></tr>
</table>
</form>
</body>
</html>