package epl.service;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import epl.dao.MemberDao;
import epl.dao.ProductDao;
import epl.model.Member;
import epl.model.Product;

public class ShopListAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String p_category = request.getParameter("p_category");
		ProductDao pdo = ProductDao.getInstance();
		ArrayList<Product> pdList = pdo.getProducts(p_category);
		if(p_category.equals("100")) {
			request.setAttribute("title", "유니폼");
		} else if (p_category.equals("200")) {
			request.setAttribute("title", "축구화");
		} else if (p_category.equals("300")) {
			request.setAttribute("title", "축구공");
		} else if (p_category.equals("all")) {
			request.setAttribute("title", "전체상품");
		}
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id!=null) {
			MemberDao md = MemberDao.getInstance();
			Member member = md.select(id);
			request.setAttribute("member", member);
		}
		request.setAttribute("pdList", pdList);
		return "shopList";
	}
}
