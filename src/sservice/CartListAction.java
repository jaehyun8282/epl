package epl.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import epl.dao.CartDao;
import epl.dao.MemberDao;
import epl.model.Cart;
import epl.model.Member;

public class CartListAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String p_name = request.getParameter("p_name");
		String product_id = request.getParameter("product_id");
		String p_image = request.getParameter("p_image");
		String p_price = request.getParameter("p_price");
		String buy_count = request.getParameter("buy_count");
		/* System.out.println("p_name = "+p_name); */
		String pageNum = request.getParameter("pagNum");
		if (pageNum == null || pageNum.equals(""))
				pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int rowPerPage = 50;
		int pagePerBlk = 10;
		int startRow = (currentPage -1) * rowPerPage +1;
		int endRow = startRow + rowPerPage -1;
		
		CartDao cdo = CartDao.getInstance();
		List<Cart> list = cdo.selectList(startRow, endRow); 
		int total = cdo.total();
		int totalPage = (int)Math.ceil((double)total/rowPerPage);
		int startPage = (currentPage-1)/ pagePerBlk * pagePerBlk +1;
		int endPage = startPage + pagePerBlk -1;
		if (endPage>totalPage)
			endPage = totalPage;
		
		request.setAttribute("pagePerBlk", pagePerBlk);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("list", list);
		return "cartList";
	}
}
