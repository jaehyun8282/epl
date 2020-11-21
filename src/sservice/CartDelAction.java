package epl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.CartDao;

public class CartDelAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		  int cart_id = Integer.parseInt(request.getParameter("cart_id")); String
		  pageNum = request.getParameter("pageNum"); 
		  CartDao cdo = CartDao.getInstance(); 
		  int result = cdo.delete(cart_id);
		  
		  request.setAttribute("pageNum", pageNum); request.setAttribute("result",
		  result); return "cartDel";
	}
}
