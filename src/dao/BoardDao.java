package dao;
// 글은 최신글을 먼저 보여 주지만 답변은 글 쓴 순서대로 정열
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Board;
import javax.naming.*;
import javax.sql.DataSource;

public class BoardDao {
	// 싱글톤
	private static BoardDao instance = new BoardDao();
	private BoardDao() {}
	public static BoardDao getInstance() {
		return instance;
	}
	// 데이터베이스 케넥션 pool연결(DB)
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch (Exception e) {
			System.out.println("연결에러 : "+e.getMessage());
		}
		return conn;
	}
	public List<Board> list(int startRow, int endRow) {
		List<Board> list = new ArrayList<Board>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;        // 가장 최근에 입력한 글이 먼저 보여라 
//		String sql = "select * from board1 order by num desc";
// Mysql String sql = 
//	 "select * from board1 order by num desc limit startRow, 10";
		// topN
//		String sql="select * from (select rowNum rn, a.* from ("
//			+ "select * from board1 order by num desc) a)"
//			+ " where rn between ? and ?";
		String sql="select * from (select rowNum rn, a.* from ("
			+ "select * from board1 order by ref desc,re_step) a)"
			+ " where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setReadcount(rs.getInt("readcount"));
				board.setPassword(rs.getString("password"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setDel(rs.getString("del"));
				
				list.add(board);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {	}
		}
		return list;
	}
	public int insert(Board board) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;  
		// 글 마지막에 추가할 것인지 중간에 끼워 넣을 것인지chk
//		select min(STEP) from BOARD
//		where REF = 부모REF
//		and STEP > 부모STEP
//		and LVL <= 부모LVL
		String sqlChk = "select min(re_step) from board1 "
				+ "where ref=? and re_step > ? and re_level <= ?";
		             // num값은 가장 큰수에다가 1씩 증가
		String sqlNum="select nvl(max(num),0) + 1 from board1";
		String sql = "insert into board1 "
			+ "values(?,?,?,?,?,0,?,?,?,?,?,sysdate,'n')"; 
	// 글을 읽고 ref가 같고 읽은글보다 re_step가 큰값이 있으면 그 글의 re_step + 1
//		String sqlUp = "update board1 set re_step=re_step+1"
//				+ " where ref=? and re_step > ?";
//		update BOARD set STEP = STEP + 1
//				where REF = 부모REF and STEP >= step
		String sqlUp = "update board1 set re_step=re_step+1"
				+ " where ref=? and re_step >= ?";
		// 맨 밑으로 가는 형태
//		select max(STEP) + 1 from BOARD
//		where REF = 부모REF 
		String sqlStep =
			"select max(re_step)+1 from board1 where ref=?";
		try {
			pstmt = conn.prepareStatement(sqlNum);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1); // 새로운 num값 nvl(max(num),0)+1
			rs.close(); pstmt.close();
			// 답변글 시작
			if (board.getNum() > 0) {
				// sqlChk실행
				pstmt = conn.prepareStatement(sqlChk);
				pstmt.setInt(1, board.getRef());
				pstmt.setInt(2, board.getRe_step());
				pstmt.setInt(3, board.getRe_level());
				rs = pstmt.executeQuery();
				rs.next();
				String chk = rs.getString(1);
				rs.close();  pstmt.close();
				if (chk != null) {
					int tempStep = Integer.parseInt(chk);
					pstmt = conn.prepareStatement(sqlUp);
					pstmt.setInt(1, board.getRef());
					pstmt.setInt(2, tempStep);
					pstmt.executeUpdate();
					board.setRe_step(tempStep);
				}else {
					pstmt = conn.prepareStatement(sqlStep);
					pstmt.setInt(1, board.getRef());
					rs = pstmt.executeQuery();
					rs.next();
					board.setRe_step(rs.getInt(1));
				}
				board.setRe_level(board.getRe_level()+1);
				pstmt.close();
			} else board.setRef(number);
			// 답변글 끝
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getEmail());
			pstmt.setString(6, board.getPassword());
			pstmt.setInt(7, board.getRef());
			pstmt.setInt(8, board.getRe_step());
			pstmt.setInt(9, board.getRe_level());
			pstmt.setString(10, board.getIp());

			result = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {	}
		}
		return result;
	}
	public void updateReadCount(int num) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = // 조회수 1증가
			"update board1 set readcount=readcount+1 where num=?"; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {	}
		}
	}
	public Board select(int num) {
		Board board = new Board();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;       
		String sql = "select * from board1 where num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {				
				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setReadcount(rs.getInt("readcount"));
				board.setPassword(rs.getString("password"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setDel(rs.getString("del"));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {	}
		}
		return board;
	}
	public int total() {
		int total = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;       
		String sql = "select count(*) from board1";
		try{pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			total = rs.getInt(1);			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try{if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {	}
		}
		return total;
	}
	public int update(Board board) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;     
		String sql = "update board1 set writer=?,subject=?,"
			+ "content=?,email=? where num=?"; 
		try{pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getEmail());
			pstmt.setInt(5, board.getNum());
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try{if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {	}
		}
		return result;
	}
	public int delete(int num) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;     
		String sql = "update board1 set del='y' where num=?"; 
		try{pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try{if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {	}
		}
		return result;
	}
}





