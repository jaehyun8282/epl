<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="sessionChk.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#btn1 { border-top-left-radius: 5px; border-bottom-right-radius: 5px; border: 1px solid skyblue; 
			background-color: rgba(0,0,0,0); color: skyblue; padding: 5px; }
	#btn1:hover{ color:white; background-color: skyblue; }
</style>
<link rel="stylesheet" type="text/css" href="common2.css">
</head>
<body>
<table><caption>회원정보</caption>
	<tr><th>아이디</th><td>${member.id }</td></tr>
	<tr><th>이름</th><td>${member.name }</td></tr>
	<tr><th>성별</th><td>${member.gender }</td></tr>
	<tr><th>생년월일</th><td>${member.birthday }</td></tr>
	<tr><th>전화번호</th><td>${member.tel }</td></tr>
	<tr><th>이메일</th><td>${member.email }</td></tr>
	<tr><th>우편번호</th><td>${member.post }</td></tr>
	<tr><th>주소</th><td>${member.address }</td></tr>
	<tr><th>가입일</th><td>${member.reg_date }</td></tr>
</table>
<div id="btn" align="center"> 
   <button id="btn1" onclick="location.href='main.ao'">확인</button>
</div>
</body>
</html>