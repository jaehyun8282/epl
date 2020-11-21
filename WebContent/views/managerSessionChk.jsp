<%
	String manager_id = (String)session.getAttribute("manager_id");
	if (manager_id==null || manager_id.equals("")) {
		response.sendRedirect("managerLoginForm.co");
		return;
	}
%>