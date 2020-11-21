package epl.service;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import epl.dao.ProductDao;
import epl.model.Product;

public class PdRegisterAction implements CommandProcess{
	public List<Product> list() {
		ProductDao pdo = ProductDao.getInstance();
		List<Product> list = pdo.list();
		return list;
	}
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int max = 1024 * 1024 * 10; // 10MB 입력 가능
		String real = request.getSession()
				.getServletContext().getRealPath("/upload");
		// MultipartRequest 그림이나 음악 파일등 byte데이터 처리
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(request,
					real, max, "utf-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {		}
		// 입력된 데이터 값 가져오기
		String p_category = mr.getParameter("p_category");
		String p_name = mr.getParameter("p_name");
		int p_price = Integer.parseInt(mr.getParameter("p_price"));
		int p_count = Integer.parseInt(mr.getParameter("p_count"));
		String p_image = mr.getFilesystemName("p_image");
		String p_content = mr.getParameter("p_content");
		int p_rate = Integer.parseInt(mr.getParameter("p_rate"));
		// Data에 연결
		Product pd = new Product();
		pd.setP_category(p_category);
		pd.setP_name(p_name);
		pd.setP_price(p_price);
		pd.setP_count(p_count);
		pd.setP_image(p_image);
		pd.setP_content(p_content);
		pd.setP_rate(p_rate);
		ProductDao pdo = ProductDao.getInstance();
		int result = pdo.insertProduct(pd);
		request.setAttribute("result", result);
		request.setAttribute("p_category",p_category);
		return "pdRegister";
	}
}
