package epl.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.OrderDao;
import epl.model.Order;

public class BuyInsertAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		 Order od = new Order();
		 od.setId(request.getParameter("id"));
		 od.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
		 od.setP_name(request.getParameter("p_name"));
		 od.setBuy_price(Integer.parseInt(request.getParameter("p_price")));
		 od.setBuy_count(Integer.parseInt(request.getParameter("buy_count")));
		 od.setP_image(request.getParameter("p_image"));
		 od.setAccount(request.getParameter("account"));
		 od.setDelivery_name(request.getParameter("delivery_name"));
		 od.setDelivery_addr(request.getParameter("delivery_addr"));
		 od.setDelivery_tel(request.getParameter("delivery_addr"));
		 od.setOrder_state(request.getParameter("order_state"));
	
	
		 OrderDao odo  = OrderDao.getInstance(); 
		 int result = odo.insert(od);
		 request.setAttribute("result", result);
		 request.setAttribute("od", od);
		 
		return "buyInsert";
	}
}