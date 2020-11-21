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
	table { width : 50%; margin-left: auto; margin-right: auto; }
</style>
<script type="text/javascript">
	function cart() {
		frm.action="cartList.co";
		frm.submit();
	}
</script> 
</head>
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
<br>
<div id="top">
<ul id="a1">
<li><a href="shopList.co?p_category=all">전체상품</a></li>
<li><a href="shopList.co?p_category=100">유니폼</a></li>
<li><a href="shopList.co?p_category=200">축구화</a></li>
<li><a href="shopList.co?p_category=300">축구공</a></li>
</ul>
</div><br>
<form method="post" name="frm">
	<input type="hidden" name="p_name" value="${pd.p_name}">
	<input type="hidden" name="product_id" value="${pd.product_id}">
	<input type="hidden" name="p_image" value="${pd.p_image}">
<table>
	<tr>
	<td align="left">
		<img src="/epl/upload/${pd.p_image}" width="300" height="300" alt="${pd.p_image}"></td>
	<td width="500" align="center">
		<font size="+1"><b>${pd.p_name}</b></font><br>
	<b><input type="hidden" name="p_price" value="${pd.p_price }">
	<fmt:formatNumber value="${pd.p_price}"/>원</b><br>
	 수량 : <input type="number" name="buy_count" value="1" size="1" style="width: 10%;">개<br>
	<br>
	<input type="submit" value="구매하기" onclick="javascript: form.action='buyInsert.co';"/> 
	<input type="submit" value="장바구니" onclick="javascript: form.action='cartInsert.co';"/> 
	</td></tr>
</table>
</form>
</body>
</html>