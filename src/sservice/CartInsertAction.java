package epl.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.CartDao;
import epl.model.Cart;

public class CartInsertAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		 Cart ct = new Cart();
		 // ct.setCart_id(Integer.parseInt(request.getParameter("cart_id")));
		 // ct.setMember_id(request.getParameter("member_id"));
		 ct.setP_name(request.getParameter("p_name"));
		 ct.setBuy_price(Integer.parseInt(request.getParameter("p_price")));
		 ct.setBuy_count(Integer.parseInt(request.getParameter("buy_count")));
		 ct.setP_image(request.getParameter("p_image"));
		 // ct.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
	
		 CartDao cdo = CartDao.getInstance(); 
		 int result = cdo.insert(ct);
		 request.setAttribute("result", result);
		 
		return "cartInsert";
	}
}
