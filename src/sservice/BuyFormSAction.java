package epl.service;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.CartDao;
import epl.dao.OrderDao;
import epl.model.Cart;
import epl.model.Order;

public class BuyFormSAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String account = request.getParameter("account");
	    String delivery_name = request.getParameter("delivery_name");
	    String delivery_tel = request.getParameter("delivery_tel");
	    String delivery_addr = request.getParameter("delivery_addr"); 
	    String id = request.getParameter("id");
	    CartDao cdo = CartDao.getInstance();
	    ArrayList<Cart> cartLists = cdo.getCart(id);
	      
	    OrderDao odo = OrderDao.getInstance();   
	    odo.insertBuy(cartLists,id,account, delivery_name, delivery_tel, delivery_addr);
		
		/* String id = request.getParameter("id"); */
		ArrayList<Order> orderLists = null;
		Order order = null;
		int count = 0;
		int number = 0;
		int total = 0;
		long compareId = 0;
		long preId = 0;

		count = odo.getListCount(id);
		orderLists = odo.getBuyList(id);
		return "buyFormS";
	}
}