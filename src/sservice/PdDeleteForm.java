package epl.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.ProductDao;
import epl.model.Product;

public class PdDeleteForm implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		ProductDao pdo = ProductDao.getInstance();
		Product pd = pdo.select(product_id);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("product_id", product_id);
		request.setAttribute("pd", pd);
		return "pdDeleteForm";
	}
}
