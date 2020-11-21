package epl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.OrderDao;
import epl.model.Order;


public class BuyDelForm implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int order_id = Integer.parseInt(request.getParameter("order_id"));
		OrderDao odo = OrderDao.getInstance();
		Order od = odo.select(order_id);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("order_id", order_id);
		request.setAttribute("od", od);
		
		return "buyDelForm";
	}
}