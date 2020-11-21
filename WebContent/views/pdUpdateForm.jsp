<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table { width : 50%; margin-left: auto; margin-right: auto; }
</style>
</head>
<body>
<form action="pdUpdate.co" method="post">
<input type="hidden" name="product_id" value="${pd.product_id}">
<table border="1"><caption><h2>상품 수정</h2></caption>
	<tr><th>상품 번호</th><td>${pd.product_id }</td></tr>
	<tr><th>카테고리</th><td>
		<select name="p_category">
			<option value="100">유니폼</option>
			<option value="200">축구화</option>
			<option value="300">축구공</option>
		</select></td></tr>
	<tr><th>상품명</th><td>
		<input type="text" size="50" maxlength="30"
		name="p_name" required="required"value="${pd.p_name}"></td></tr>
	<tr><th>상품 가격</th><td>
		<input type="text" size="10" maxlength="10" 
		name="p_price" required="required" value="${pd.p_price}">원</td></tr>
	<tr><th>상품 재고</th><td>
		<input type="text" size="10" maxlength="5"
		name="p_count" required="required" value="${pd.p_count}">개</td></tr>
	<tr><th>상품 이미지</th><td>
		<input type="file" name="p_image"
		required="required"value="${pd.p_image}"></td></tr>
	<tr><th>상품 설명</th><td>
		<pre><textarea rows="13" cols="40"
		name="p_content" required="required">${pd.p_content}</textarea></pre></td></tr>
	<tr><th>상품 할인율</th><td>
		<input type="text" size="5" maxlength="3"
		name="p_rate" required="required" value="${pd.p_rate}">%</td></tr>
	<tr><th colspan="2" align="center">
		<input type="submit" value="수정">
		<input type="submit" value="취소"></th></tr>
</table>
</form>
</body>
</html>