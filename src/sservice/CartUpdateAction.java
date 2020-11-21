package epl.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.CartDao;

public class CartUpdateAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
	    String cart_id = request.getParameter("cart_id");
	    String buy_count = request.getParameter("buy_count");
	    System.out.println("cart_id="+cart_id);
	    System.out.println("buy_count="+buy_count);
	 	CartDao cdo = CartDao.getInstance();
	    result = cdo.updateCount(Integer.parseInt(cart_id),Integer.parseInt(buy_count));
	   
	    request.setAttribute("result", result);
		return "cartUpdate";
	}
}
