package service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BoardDao;
import model.Board;
public class Content implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDao bd = BoardDao.getInstance();
		bd.updateReadCount(num);
		Board board = bd.select(num);
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		return "board/content";
	}
}