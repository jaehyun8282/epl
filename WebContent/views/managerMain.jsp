<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div align="center"><h2>관리자 페이지</h2></div>
<table border="1">
	<tr align="center"><td><button onclick="location.href='pdRegisterForm.co'">상품 등록</button></td></tr>
	<tr align="center"><td><button onclick="location.href='pdList.co'">상품 수정/삭제</button></td></tr>
	<tr align="center"><td><button>게시글 등록</button></td></tr>
	<tr align="center"><td><button>게시글 수정/삭제</button></td></tr>
	<tr align="center"><td><button onclick="location.href='managerLogout.co'">로그아웃</button></td></tr>
</table>
</body>
</html>