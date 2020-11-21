package mservice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.*;
public class UpdateAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String post = request.getParameter("post");
		String address = request.getParameter("address");
		Member member = new Member();
		
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setGender(gender);
		member.setBirthday(birthday);
		member.setTel(tel);
		member.setEmail(email);
		member.setPost(post);
		member.setAddress(address);
		
		MemberDao md = MemberDao.getInstance();
		int result = md.update(member);
		request.setAttribute("result", result);
		return "login/update";
	}
}