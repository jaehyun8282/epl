package epl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerLoginForm implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {

		return "managerLoginForm";
	}
}
