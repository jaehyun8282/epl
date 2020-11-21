<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" >
<style type="text/css">
	body {	background-image: url("/epl/image/aa.jpg");
			background-size: cover; 
			background-repeat: no-repeat;	}
</style>
</head><body>

<table width="1500" border="1"><caption><h1 align="center">게시글 목록</h1></caption>
	<tr><th>번호</th><th>제목</th><th>작성자</th><th>조회수</th>
		<th>작성일</th></tr>
<c:if test="${empty list }">
	<tr><th colspan="5">게시글 없습니다</th></tr> 
</c:if>
<c:if test="${not empty list }"> 
	<c:forEach var="board" items="${list}">
		<tr><th>${tot}<c:set var="tot" value="${tot - 1}"/></th>
		<c:if test="${board.del=='n' }">
			<td title="${board.content}">
			<c:if test="${board.re_level > 0 }">		
				<img alt="" src="/epl/image/level.gif" height="10" width="${board.re_level *10 }">
				<img alt="" src="/epl/image/re.gif">
			</c:if>
				<a href="content.do?num=${board.num}&pageNum=${currentPage}">
						${board.subject}</a> 
			<c:if test="${board.readcount > 30 }">
				<img alt="" src="/epl/image/hot.gif">
			</c:if>
			</td>
			<th>${board.writer}</th>
			<th>${board.readcount}</th>
			<th>${board.reg_date}</th>
		</c:if>
		<c:if test="${board.del=='y'}">
			<th colspan="4">삭제된 글입니다</th>
		</c:if>
		</tr>
	</c:forEach>
</c:if>
</table>
<div align="center">
<button onclick="location.href='insertForm.do?pageNum=1'">게시글 작성</button>
	<c:if test="${startPage > pagePerBlock }">
		<button onclick="location.href='list.do?pageNum=${startPage-1}'">[이전]</button>
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<c:if test="${i==currentPage }">
				<button onclick="location.href='list.do?pageNum=${i}'"
					disabled="disabled">
				[${i}]</button>
		</c:if>	
		<c:if test="${i!=currentPage }">		
			<button onclick="location.href='list.do?pageNum=${i}'">
			[${i}]</button>
		</c:if>
	</c:forEach>
	<c:if test="${endPage < totalPage }">
		<button onclick="location.href='list.do?pageNum=${endPage+1}'">[다음]</button>
	</c:if>
	
</div>

<!-- num은 변수(속성)명이 없으므로 null을 가지고 insertForm.jsp를 실행 -->

</body>
</html>



