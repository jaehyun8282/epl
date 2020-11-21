<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table { width : 50%; margin-left: auto; margin-right: auto; }
	tr { text-align: center; }
</style>
</head>
<body>
<div align="center"><h2>상품 등록</h2></div>
<form action="pdRegister.co" method="post" enctype="multipart/form-data">
<table border="1">
	<!-- <tr><td align="right" colspan="2">
		<a href="../managerMain.jsp">관리자 메인으로</a></td></tr> -->
	<tr>
	<td>카테고리 선택</td><td>
		<select name="p_category">
			<option value="100">유니폼</option>
			<option value="200">축구화</option>
			<option value="300">축구공</option>
		</select></td></tr>
	<tr><td>상품명</td><td>
		<input type="text" size="50" maxlength="30"
			name="p_name"></td></tr>
	<tr><td>상품 가격</td><td>
		<input type="text" size="10" maxlength="10"
			name="p_price">원</td></tr>
	<tr><td>상품 재고</td><td>
		<input type="text" size="10" maxlength="5"
			name="p_count">개</td></tr>
	<tr><td>상품 이미지</td><td>
		<input type="file" name="p_image"></td></tr>
	<tr><td>상품 설명</td><td>
		<textarea name="p_content" rows="13" cols="40"></textarea></td></tr>
	<tr><td>상품 할인율</td><td>
		<input type="text" size="5" maxlength="3"
			name="p_rate" value="0">%</td></tr>
	<tr><td colspan="2" align="center">
		<input type="submit" value="상품 등록">
		<input type="reset" value="다시 작성"></td></tr>
</table>
</form>
</body>
</html>