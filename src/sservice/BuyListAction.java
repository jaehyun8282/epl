package epl.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import epl.dao.MemberDao;
import epl.dao.OrderDao;
import epl.dao.ProductDao;
import epl.model.Member;
import epl.model.Order;
import epl.model.Product;

public class BuyListAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int product_id = Integer.parseInt(request.getParameter("product_id"));

		ProductDao pdo = ProductDao.getInstance();
		Product pd = pdo.select(product_id);
		
		OrderDao odo = OrderDao.getInstance();
		Order od1 = odo.selectpd(product_id);
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id!=null) {
			MemberDao md = MemberDao.getInstance();
			Member member = md.select(id);
			request.setAttribute("member", member);
		}
		
		request.setAttribute("pd", pd);
		request.setAttribute("od1", od1);
		
//		String pageNum = request.getParameter("pagNum");
//		if (pageNum == null || pageNum.equals(""))
//				pageNum = "1";
//		int currentPage = Integer.parseInt(pageNum);
//		int rowPerPage = 50;
//		int pagePerBlk = 10;
//		int startRow = (currentPage -1) * rowPerPage +1;
//		int endRow = startRow + rowPerPage -1;
//		
//		OrderDao odo = OrderDao.getInstance();
//		List<Order> list = odo.selectList(startRow, endRow); 
//		
//		int total = odo.total();
//		int totalPage = (int)Math.ceil((double)total/rowPerPage);
//		int startPage = (currentPage-1)/ pagePerBlk * pagePerBlk +1;
//		int endPage = startPage + pagePerBlk -1;
//		if (endPage>totalPage)
//			endPage = totalPage;
//		
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("id");
//		if(id!=null) {
//			MemberDao md = MemberDao.getInstance();
//			Member member = md.select(id);
//			request.setAttribute("member", member);
//		}
//		
//		request.setAttribute("pagePerBlk", pagePerBlk);
//		request.setAttribute("totalPage", totalPage);
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("endPage", endPage);
//		
//		request.setAttribute("list", list);
		
		return "buyList";
	}
}

