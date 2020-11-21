package epl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import epl.dao.ProductDao;

public class ManagerLoginAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String manager_id = request.getParameter("manager_id");
		String manager_pass = request.getParameter("manager_pass");
		ProductDao pdo = ProductDao.getInstance();
		int result  = pdo.loginChk(manager_id, manager_pass);
		if (result > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("manager_id", manager_id);
		}
		request.setAttribute("result", result);
		return "managerLogin";
	}
}
