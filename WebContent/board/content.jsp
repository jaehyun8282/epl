<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/epl/common.css">
<style type="text/css">
table { width: 1800px; background-color: transparent; 
		 margin: 10px; table-layout: fixed;  }
th, td { padding: 8px; border: 1px solid transparent;}
</style>
<script type="text/javascript" src="/epl/js/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// $('#disp').load('/epl/list.do?pageNum=${pageNum}');
});

</script>
</head><body>
<div align="center">
<table><caption>게시글 상세 보기</caption>
	<tr><th>번호</th><td>${board.num}</td></tr>
	<tr><th>제목</th><td>${board.subject}</td></tr>
	<tr><th>작성자</th><td>${board.writer}</td></tr>
	<!-- pre 줄바꿈, 간격 등 입력한 형태를 보존하여 출력 -->
	<tr><th>내용</th><td><pre>${board.content}</pre></td></tr>
	<tr><th>이메일</th><td>${board.email}</td></tr>
	<tr><th>IP</th><td>${board.ip}</td></tr>
	<tr><th>조회수</th><td>${board.readcount}</td></tr>
	<tr><th>작성일</th><td>${board.reg_date}</td></tr>
</table>
<button onclick="location.href='updateForm.do?num=${board.num}&pageNum=${pageNum}'">수정</button>
<button onclick="location.href='deleteForm.do?num=${board.num}&pageNum=${pageNum}'">삭제</button>
<!-- num은 변수(속성)명이 있으므로 현재 조회한 num번호를 가지고 insertForm.jsp를 실행 -->
<button onclick="location.href='insertForm.do?num=${board.num}&pageNum=${pageNum}'">답변</button>
<button onclick="location.href='list.do?pageNum=${pageNum}'">목록</button>
<div id="disp"></div>
</div>
</body>
</html>

