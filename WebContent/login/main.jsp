<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="sessionChk.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#btn1 { border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; width: 40%}
	#btn2 { border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; width: 40%}
	#btn3 { border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; width: 40%}
	#btn4 { border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; width: 40%}
	#btn_group button{ border: 1px solid skyblue; background-color: rgba(0,0,0,0); color: skyblue; padding: 5px; }
	#btn_group button:hover{ color:white; background-color: skyblue; }
</style>
<link rel="stylesheet" type="text/css" href="common2.css">
</head>
<body>
<h2 align="center"><img src="image/ea.png" width="35" height="35" align="center"> ${member.name }님 환영합니다</h2>
<div id="btn_group" align="center"> 
	<button id="btn1" onclick="location.href='view.ao'">회원정보 보기</button><p>
	<button id="btn2" onclick="location.href='updateForm.ao'">회원정보 수정</button><p>
	<button id="btn3" onclick="location.href='delete.ao'">회원탈퇴</button><p>
	<button id="btn4" onclick="location.href='logout.ao'">로그아웃</button>
</div>
</body>
</html>