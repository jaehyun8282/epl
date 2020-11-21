package member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;
import javax.sql.DataSource;
public class MemberDao {
	private static MemberDao instance = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() {
		return instance;
	}
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
	public Member select(String id) {
		Connection conn = getConnection(); 	
		Member member = null;
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		String sql = "select * from s_member where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender"));
				member.setBirthday(rs.getString("birthday"));
				member.setTel(rs.getString("tel"));
				member.setEmail(rs.getString("email"));
				member.setPost(rs.getString("post"));
				member.setAddress(rs.getString("address"));
				member.setReg_date(rs.getDate("reg_date"));
				member.setDel(rs.getString("del"));
			}
		}catch(Exception e){System.out.println(e.getMessage());
		}finally {
			try{if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {		}
		}
		return member;
	}
	public int insert(Member member) {
		Connection conn = getConnection(); 	
		int result = 0;
		PreparedStatement pstmt = null;		
		String sql="insert into s_member(id,password,name,gender,birthday,tel,email,post,address,reg_date,del)"
				+ "values(?,?,?,?,?,?,?,?,?,sysdate,'n')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getBirthday());
			pstmt.setString(6, member.getTel());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getPost());
			pstmt.setString(9, member.getAddress());
			result = pstmt.executeUpdate();
		}catch(Exception e){System.out.println(e.getMessage());
		}finally {
			try{if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {		}
		}
		return result;
	}
	public int loginChk(String id, String password) {
		Connection conn = getConnection(); 	
		int result = 0;
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		String sql="select password,del from s_member where id=?";
		try{pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { 
				String dbPass=rs.getString("password"); 
				if (dbPass.equals(password)) result = 1; 
				else result = 0;
				if (rs.getString("del").equals("y"))
					result = -1;
			} else result = -1;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally {
			try{if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {		}
		}
		return result;
	}
	public int delete(String id) {
		Connection conn = getConnection(); 	
		int result = 0;
		PreparedStatement pstmt = null;		
		String sql="update s_member set del='y' where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e){System.out.println(e.getMessage());
		}finally {
			try{if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {		}
		}
		return result;
	}
	public int update(Member member) {
		Connection conn = getConnection(); 	
		int result = 0;
		PreparedStatement pstmt = null;	
		String sql="update s_member set password=?,name=?,tel=?,email=?,post=?,address=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getTel());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPost());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getId());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){System.out.println(e.getMessage());
		}finally {
			try{if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {		}
		}
		return result;
	}
	public List<Member> list(int startRow, int endRow) {
		Connection conn = getConnection(); 	
		List<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		String sql="select * from (select rowNum rn, a.* from ("
				+ "select * from s_member order by reg_date desc) a)"
				+ " where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender"));
				member.setBirthday(rs.getString("birthday"));
				member.setTel(rs.getString("tel"));
				member.setEmail(rs.getString("email"));
				member.setPost(rs.getString("post"));
				member.setAddress(rs.getString("address"));
				member.setReg_date(rs.getDate("reg_date"));
				member.setDel(rs.getString("del"));
				list.add(member);
			}
		}catch(Exception e){System.out.println(e.getMessage());
		}finally {
			try{if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {		}
		}
		return list;
	}
	public int total() {
		int total = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;       
		String sql = "select count(*) from s_member";
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
}