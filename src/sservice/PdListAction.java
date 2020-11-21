package epl.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.ProductDao;
import epl.model.Product;

public class PdListAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pagNum");
		if (pageNum == null || pageNum.equals(""))
				pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int rowPerPage = 50;
		int pagePerBlk = 10;
		int startRow = (currentPage -1) * rowPerPage +1;
		int endRow = startRow + rowPerPage -1;
		
		ProductDao pdo = ProductDao.getInstance();
		
		List<Product> list = pdo.selectList(startRow, endRow);
		
		int total = pdo.total();
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
		return "pdList";
	}
}
