<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		location.replace("buyList.co?product_id=${od.product_id}");
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("구매하기 실패");
		history.go(-1);
	</script>
</c:if>
</body>
</html>