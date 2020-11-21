package epl.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.CartDao;
import epl.model.Cart;

public class CartDelForm implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int cart_id = Integer.parseInt(request.getParameter("cart_id"));
		CartDao cdo = CartDao.getInstance();
		Cart ct = cdo.select(cart_id);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cart_id", cart_id);
		request.setAttribute("ct", ct);
		
		return "cartDelForm";
	}
}
