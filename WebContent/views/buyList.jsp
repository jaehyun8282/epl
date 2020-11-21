<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
	table { width : 50%; margin-left: auto; margin-right: auto; }
</style>
</head>
<body>
<div align="center"><h2>구매 목록</h2></div>
<%-- <c:if test="${od1 != null }"> --%>
	<table border="1">
		<tr align="center">
		<th width="1%">번호</th><th width="7%">상품명</th>
		<th width="2%">수량</th><th width="3%">금액</th>
	<!--  <th width="1%">삭제</th></tr> -->
<c:set var="tot" value="0"></c:set>
<%-- <c:forEach var="order" items="${od1}"> --%>
	<tr align="center"><td>${order.order_id}</td>		
		<td><img src="/epl/upload/${pd.p_image}" border="0" width="50" align="middle" alt="${pd.p_image}">
			<b>${pd.p_name}</b></td>
		<td align="center">
			${order.buy_count}
		</td>
		<td><fmt:formatNumber value="${order.buy_price*order.buy_count}"/>원</td>
		<%-- <td><input type="button" value="삭제" onclick="location.href='buyDel.co?order_id=${order.order_id }'">  --%>
		<c:set var="pr" value="${order.buy_price*order.buy_count}"></c:set>
		<c:set var="tot" value="${pr+tot }"></c:set>
<%-- </c:forEach> --%>
	<tr align="right">
		<td colspan="5"><b>총 금액 :<fmt:formatNumber value="${tot }"/>원</b>
		</td></tr>
</table>
<br>
<form action="" method="post">
 <table border="1" style="width: 50%;">
      <tr> 
       	<td colspan="2" align="center"><font size="+1" >
       	<b>주문자 정보</b></font></td>
     </tr>
     <tr> 
       <td width="200">이름</td>
       <td width="400">${member.name}</td>
     </tr>
     <tr><td>전화번호</td><td>${member.tel}</td>
     </tr>
     <tr><td>주소</td><td>${member.address}</td>
     </tr>
     <tr><td>결제계좌</td>
       <td>
         <select name="account">
			<option value="">계좌를 선택해주세요</option>
			<option value="1">1111-국민-김건우</option>
			<option value="2">2222-신한-박시은</option>
			<option value="3">3333-우리-박재현</option>
		</select>

       </td>
    </tr>
  </table>
  <br>
     <table border="1" style="width: 50%;">
      <tr> 
       <td colspan="2" align="center"><font size="+1" ><b>배송지 정보</b></font></td>
     </tr>
     <tr> 
       <td width="200">이름</td>
       <td width="400">
          <input type="text" name="delivery_name" value="${order.delivery_name}">
       </td>
     </tr>
     <tr> 
       <td>전화번호</td><td>
         <input type="text" name="delivery_tel" value="${order.delivery_tel}">
       </td>
     </tr>
     <tr><td>주소</td><td>
         <input type="text" name="delivery_addr" value="${order.delivery_addr}">
       </td>
     </tr>
     <tr> 
       <td colspan="2" align="center">
         <input type="submit" value="확인" >
         <input type="button" value="취소" onclick="location.href='shopMain.co'">      
       </td>
    </tr>
  </table>
  </form>
<%-- </c:if> --%>
</body>
</html>