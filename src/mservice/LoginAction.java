package mservice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.MemberDao;
public class LoginAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberDao md = MemberDao.getInstance();
		int result  = md.loginChk(id, password);
		System.out.println("result = "+result);
		if (result > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
		}
		request.setAttribute("result", result);
		return "login/login";
	}
}