<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equi="X-UA-Compatible" content="ie=edge">
<title>IntoTheEPL</title>
<style type="text/css">@import url("common.css");
</style>
</head><body>
<!-- 	<slider> 
	<slide><p>Slide 1</p></slide>
	<slide><p>Slide 2</p></slide>
	<slide><p>Slide 3</p></slide>
	<slide><p>Slide 4</p></slide>
	</slider> -->
	

<table>
<%	String menu = request.getParameter("menu");
	if (menu == null) 
		 menu="menu.jsp";
	// System.out.println("menu="+menu);
%>
	<tr>
		<td >
			<jsp:include page="header.jsp"></jsp:include></td></tr>
	<tr height="700">		
		<td width="2000" bgcolor="transparent"><jsp:include page='<%=menu%>'></jsp:include></td>
		</tr>
			<tr height="20"><td colspan="2">
		<jsp:include page="footer.jsp"></jsp:include></td></tr>
</table>
</body>
</html>