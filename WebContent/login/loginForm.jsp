<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#btn1 { border-top-left-radius: 5px; border-bottom-right-radius: 5px; }
	#btn2 { border-top-left-radius: 5px; border-bottom-right-radius: 5px; }
	#btn_group button{ border: 1px solid skyblue; background-color: rgba(0,0,0,0); color: skyblue; padding: 5px; }
	#btn_group button:hover{ color:white; background-color: skyblue; }
</style>
<link rel="stylesheet" type="text/css" href="common2.css">
</head>
<body>
<form action="login.ao">
<table><caption>로그인</caption>
	<tr><th>아이디</th><td><input type="text" name="id" required="required" autofocus="autofocus"></td></tr>
	<tr><th>비밀번호</th><td><input type="password" name="password" required="required"></td></tr>
</table>
<!-- <div class="button" align="center">
    <button type="submit" class="btn btn-primary">확인</button> -->
   <!--  <input type="button" value="회원가입" onclick="location.href='joinForm.do'"> -->
   <div id="btn_group" align="center"> 
   <button id="btn1" type="submit">확인</button>
   <button id="btn2" onclick="location.href='joinForm.ao'">회원가입</button> 
   </div>
<!-- </div> -->
</form>
</body>
</html>