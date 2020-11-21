package epl.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import epl.dao.MemberDao;
import epl.dao.ProductDao;
import epl.model.Member;
import epl.model.Product;

public class ShopDetailAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		String pageNum = request.getParameter("pageNum");
		ProductDao pdo = ProductDao.getInstance();
		Product pd = pdo.select(product_id);
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id!=null) {
			MemberDao md = MemberDao.getInstance();
			Member member = md.select(id);
			request.setAttribute("member", member);
		}
		
		request.setAttribute("pd", pd);
		request.setAttribute("pageNum", pageNum);
		
		return "shopDetail";
	}
}
