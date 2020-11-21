package service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import model.Board;
public class InsertAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		Board board = new Board();
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setWriter(request.getParameter("writer"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		board.setEmail(request.getParameter("email"));
		board.setPassword(request.getParameter("password"));
		board.setRef(Integer.parseInt(request.getParameter("ref")));
		board.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		board.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		String pageNum = request.getParameter("pageNum");
		board.setIp(request.getRemoteAddr());
		
		BoardDao bd = BoardDao.getInstance();
		int result = bd.insert(board);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result",result);
		return "board/insert";
	}
}
