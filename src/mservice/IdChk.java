package mservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.*;
public class IdChk implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		MemberDao md = MemberDao.getInstance();
		Member member  = md.select(id); 
		request.setAttribute("member", member);
		request.setAttribute("id", id);
		return "login/idChk";
	}
}