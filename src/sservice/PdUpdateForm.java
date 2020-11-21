package epl.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.ProductDao;
import epl.model.Product;

public class PdUpdateForm implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		String pageNum = request.getParameter("pageNum");
		ProductDao pdo = ProductDao.getInstance();
		Product pd = pdo.select(product_id);
		
		request.setAttribute("product_id", product_id);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pd", pd);
		
		return "pdUpdateForm";
	}
}
