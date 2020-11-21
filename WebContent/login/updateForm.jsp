<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ include file="sessionChk.jsp" %>
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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	function chk() {
		if(frm.password.value!=frm.confirmPass.value){
			alert("암호가 다릅니다");
			frm.password.focus();
			frm.password.value="";
			return false;
		}
	}
	function openDaumPostcode() {
			new daum.Postcode({
				oncomplete : function(data) {
					document.getElementById('post').value = data.zonecode;
					document.getElementById('address').value = data.address;				
			}
		}).open();
	}
</script>
</head>
<body>
<form action="update.ao" method="post" name="frm" onsubmit="return chk()">
<input type="hidden" name="id" value="${member.id }">
<table><caption>회원 정보 수정</caption>
	<tr><th>아이디</th><td>${member.id }</td></tr>
	<tr><th>비밀번호</th><td><input type="password" name="password" required="required" autofocus="autofocus"></td></tr>
	<tr><th>비밀번호 확인</th><td><input type="password" name="confirmPass" required="required"></td></tr>
	<tr><th>이름</th><td><input type="text" name="name" required="required" value="${member.name}"></td></tr>
	<tr><th>성별</th><td>${member.gender }</td></tr>
	<tr><th>생년월일</th><td>${member.birthday}</td></tr>
	<tr><th>전화번호</th><td><input type="tel" name="tel" required="required" 
		placeholder="-없이 입력하시오" pattern="\d{10,11}" title="예) 01012345678" value="${member.tel}"></td></tr>
	<tr><th>이메일</th><td><input type=text size=20 id="email" name="email" value="${member.email}"></td></tr>
	<tr><th>우편번호</th><td><input type=text size=5 maxlength=5 id="post" name="post" value="${member.post}">
		<input type=button value="우편 검색" required="required" onClick="openDaumPostcode()"></td></tr>
	<tr><th>주소</th><td><input type="text" id="address" name="address" required="required" size="50" value="${member.address}"></td></tr>
</table>
<div id="btn_group" align="center"> 
   <button id="btn1" type="submit">확인</button>
   <button id="btn2" onclick="location.href='main.ao'">메인으로</button>
</div>
</form>
</body>
</html>