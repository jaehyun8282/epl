package epl.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import epl.dao.CartDao;
import epl.dao.MemberDao;
import epl.dao.OrderDao;
import epl.model.Cart;
import epl.model.Member;
import epl.model.Order;


public class BuyFormAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
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
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id!=null) {
			MemberDao md = MemberDao.getInstance();
			Member member = md.select(id);
			request.setAttribute("member", member);
		}
		/*
		 * ArrayList<String> accountLists = null; OrderDao odo = OrderDao.getInstance();
		 * accountLists = odo.getAccount();
		 */
			
		request.setAttribute("pagePerBlk", pagePerBlk);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("list", list);
		return "buyForm";
	}
  }
