package epl.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.ProductDao;
public class PdDeleteAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		String pageNum = request.getParameter("pageNum");
		ProductDao pdo = ProductDao.getInstance();
		int result = pdo.delete(product_id);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		return "pdDelete";
	}
}
