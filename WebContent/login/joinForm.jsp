<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function idchk(){
	if(!frm.id.value){
		alert("아이디 입력 후에 체크하세요");
		frm.id.focus();
		return false;
	}
	window.open('idChk.ao?id='+frm.id.value,"","width=300 height=300");
}
function chk(){
	if(frm.password.value != frm.confirmPass.value){
		alert("암호를 확인하세요");
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
<form action="join.ao" method="post" name="frm" onsubmit="return chk()">
<table><caption>회원 가입</caption>
	<tr><th>아이디</th><td><input type="text" name="id" required="required" autofocus="autofocus">
						<input type="button" value="id 중복체크" onclick="idchk()"></td></tr>
	<tr><th>비밀번호</th><td><input type="password" name="password" required="required"></td></tr>
	<tr><th>비밀번호 확인</th><td><input type="password" name="confirmPass" required="required"></td></tr>
	<tr><th>이름</th><td><input type="text" name="name" required="required"></td></tr>
	<tr><th>성별</th><td><input type="radio" name="gender" value="남성">남성
		<input type="radio" name="gender" value="여성">여성</td></tr>
	<tr><th>생년월일</th><td><input type="text" id="birthday" name="birthday" required="required" placeholder="예) 930813" pattern="\d{6}" title="예) 930813"></td></tr>
	<tr><th>전화번호</th><td><input type="tel" name="tel" required="required" 
		placeholder="-없이 입력하시오" pattern="\d{10,11}" title="예) 01012345678"></td></tr>
	<tr><th>이메일</th><td><input type=text size=20 id="email" name="email"></td></tr>
	<tr><th>우편번호</th><td><input type=text size=5 maxlength=5 id="post" name="post">
		<input type=button value="우편 검색" required="required" onClick="openDaumPostcode()"></td></tr>
	<tr><th>주소</th><td><input type="text" id="address" name="address" required="required" size="50"></td></tr> 
</table>
<!-- <div class="button" align="center">
   <input type="submit" value="확인">
</div> -->
<div id="btn" align="center"> 
   <button id="btn1" type="submit">확인</button>
</div>
</form>
</body>
</html>