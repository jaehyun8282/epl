package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import model.Board;

public class InsertForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int num=0, ref=0, re_level=0, re_step=0; 
		// 답변글은 no에 값이 있음
		String no = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");
		if (no != null) {
			num = Integer.parseInt(no);
			BoardDao bd = BoardDao.getInstance();
			Board board = bd.select(num);
			ref = board.getRef();
			re_step = board.getRe_step();
			re_level = board.getRe_level();
		}
		// 답변글 끝
		request.setAttribute("num", num);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		request.setAttribute("pageNum", pageNum);
		return "board/insertForm";
	}

}
