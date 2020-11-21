package epl.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epl.dao.ProductDao;
import epl.model.Product;

public class PdUpdateAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		Product pd = new Product();
		pd.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
		pd.setP_category(request.getParameter("p_category"));
		pd.setP_name(request.getParameter("p_name"));
		pd.setP_price(Integer.parseInt(request.getParameter("p_price")));
		pd.setP_count(Integer.parseInt(request.getParameter("p_count")));
		pd.setP_image(request.getParameter("p_image"));
		pd.setP_content(request.getParameter("p_content"));
		pd.setP_rate(Integer.parseInt(request.getParameter("p_rate")));
		String pageNum = request.getParameter("pageNum");
		ProductDao pdo = ProductDao.getInstance();
		int result = pdo.update(pd);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		request.setAttribute("pd", pd);
		
		return "pdUpdate";
	}
}