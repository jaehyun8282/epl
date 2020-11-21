package epl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.OrderDao;

public class BuyDelAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		 int order_id = Integer.parseInt(request.getParameter("order_id")); 
		 String pageNum = request.getParameter("pageNum"); 
		 OrderDao odo = OrderDao.getInstance(); 
		  int result = odo.delete(order_id);
		  
		  request.setAttribute("pageNum", pageNum); 
		  request.setAttribute("result",result); 
		  return "buyDel";
	}
}
