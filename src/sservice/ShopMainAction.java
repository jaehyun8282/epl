package epl.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import epl.dao.MemberDao;
import epl.dao.ProductDao;
import epl.model.Member;
import epl.model.Product;

public class ShopMainAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		ProductDao pdo = ProductDao.getInstance();	
		List<Product> list = pdo.list();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id!=null) {
			MemberDao md = MemberDao.getInstance();
			Member member = md.select(id);
			request.setAttribute("member", member);
		}
		request.setAttribute("list", list);
		return "shopMain";
	}
}
