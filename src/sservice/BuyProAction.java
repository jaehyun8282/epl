package epl.service;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.CartDao;
import epl.dao.OrderDao;
import epl.model.Cart;

public class BuyProAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	    String account = request.getParameter("account");
		/*
		 * String deliveryName = request.getParameter("deliveryName"); String
		 * deliveryTel = request.getParameter("deliveryTel"); String deliveryAddess =
		 * request.getParameter("deliveryAddess");
		 * 
		 * CartDao cdo = CartDao.getInstance(); ArrayList<Cart> list = cdo.getCart(id);
		 * 
		 * OrderDao odo = OrderDao.getInstance();
		 * odo.insertBuy(list,p_name,account,deliveryName,deliveryTel,deliveryAddess);
		 */
		return "buyPro";
	}
}
