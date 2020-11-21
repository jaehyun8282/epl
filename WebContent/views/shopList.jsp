<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	ul#a1 { 
		list-style-type: none;
		margin: 0;
		padding: 0;
		width: 200px;
		background-color: #5CD1E5;
		border-radius: 0.4em;
		display: inline-block;

		}
	ul#a1 li a { 
		display: block;
		font-family: "돋움";
		font-weight: 600;
		font-size: 1em;
		padding: 10px 20px;
		color: #e7e5e5;
		margin: 0;
		text-align :center;
		border-radius: 1.4em;
		text-decoration: none;
		text-shadow: 0 1px 1px rgba(0,0,0,.3);
		}
	ul#a1 li a:hover {
		/* 메뉴 항목 위로 마우스 올렸을 때 스타일 */
		background: #ebebeb; /* 배경색 */
		color: #444; /* 글자색 */
		text-shadow: 0 1px 1px rgba(255,255,255,1); /* 글자 그림자 */
		border-radius: 1.4em; /* 모서리 둥글게 */
		-webkit-box-shadow: 0 1px 1px rgba(0,0,0,0.2); /* 메뉴 항목에 그림자 */
		-moz-box-shadow: 0 1px 1px rgba(0,0,0,0.2); /* 메뉴 항목에 그림자 */
		}
	a { text-decoration: none; }
</style>
</head>
<body>
<body>
<c:set var="id" value="${sessionScope.id }"></c:set>
<c:if test="${empty id }">
	<div style="text-align: right">
		<input type="button" value="로그인" onclick="location.href='loginForm.ao'">
		</div>
	</c:if>
<c:if test="${not empty id }">
	<div style="text-align: right">
	<a href="main.ao">${member.name }님 환영합니다</a>
	<input type="button" value="장바구니" onclick="location.href='cartList.co'">
	<input type="button" value="구매목록" onclick="location.href='buyFormS.co'">
	<input type="button" value="로그아웃" onclick="location.href='logout.ao'">
	</div>
</c:if>
<div align="center">
	<a href="shopMain.co"><img src="/epl/upload/title.jpg" width="450"></a>
</div>
<br>
<ul id="a1">
<li><a href="shopList.co?p_category=all">전체상품</a></li>
<li><a href="shopList.co?p_category=100">유니폼</a></li>
<li><a href="shopList.co?p_category=200">축구화</a></li>
<li><a href="shopList.co?p_category=300">축구공</a></li>
</ul><br>
<c:if test="${empty pdList }">
	<table style=" width: 60%; height:60%; margin:auto; text-align: center;">
		<tr><td colspan="2">등록된 상품이 없습니다.</td></tr>
	</table>	
</c:if>
<c:if test="${not empty pdList }">
<div align="center">
<h3>${title}</h3>
</div>
<table style=" width: 60%; height:60%; margin:auto; text-align: center;">
	<c:forEach var="pd" items="${pdList}" varStatus="a">
		<c:if test="${a.index %4 ==0 }"><tr></c:if>	
		<td align="center" width="20%">
		<a href="shopDetail.co?product_id=${pd.product_id}&p_category=${pd.p_category}">
		<img alt="" src="/epl/upload/${pd.p_image}" width="200" border="0"></a><br>
		<font size="+1"><b>
		<a href="shopDetail.co?product_id=${pd.product_id}&p_category=${pd.p_category}">
		${pd.p_name}</a></b></font><br>
		<fmt:formatNumber value="${pd.p_price}" />원</td>
	<c:if test="${a.index %4 == 3 }"></tr></c:if>
	</c:forEach>
</table>
</c:if>
</body>
</html>