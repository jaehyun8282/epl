package epl.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import epl.model.Product;

public class ProductDao {
	// 싱글톤
	private static ProductDao instance;
	public static ProductDao getInstance() {
		if (instance==null) {
			instance = new ProductDao();
		}
		return instance;
	}
	// 데이터베이스 케넥션 pool연결(DB)
	private ProductDao() {}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)
					init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("연결에러 : "+e.getMessage());
		}
		return conn;
	}
	// 상품 등록
	public int insertProduct(Product pd) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String str = "insert into s_product values(s_product_seq.nextval,?,?,?,?,?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(str);
			// pstmt.setInt(1, pd.getProduct_id());
			pstmt.setString(1, pd.getP_name());
			pstmt.setInt(2, pd.getP_price());
			pstmt.setInt(3, pd.getP_count());
			pstmt.setString(4, pd.getP_category());
			pstmt.setString(5, pd.getP_image());
			pstmt.setString(6, pd.getP_content());
			pstmt.setInt(7, pd.getP_rate());
          //pstmt.setDate(8, pd.getP_date());
//			System.out.println(str);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
			}
		}
		return result;
	}
	// 분류별 또는 전체 등록된 책의 정보를 얻어내는 메소드
	public ArrayList<Product> getProducts(String p_category) {
        Connection conn = getConnection(); 
        PreparedStatement pstmt = null;
        ResultSet rs = null;        
        ArrayList<Product> pdList =null;  
        String sql1="select * from s_product order by p_date desc";
        String sql2 = "select * from s_product where " +
        		"p_category = ? order by p_date desc";
        try {         
            if(p_category.equals("all")){
            	 pstmt = conn.prepareStatement(sql1);
            }else{
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1, p_category);
            }
        	rs = pstmt.executeQuery();

            if (rs.next()) {
            	pdList = new ArrayList<Product>();
                do{
                    Product pd= new Product();
                    pd.setProduct_id(rs.getInt("product_id"));
 					pd.setP_category(rs.getString("p_category"));
 					pd.setP_name(rs.getString("p_name"));
 					pd.setP_price(rs.getInt("p_price"));
 					pd.setP_count(rs.getInt("p_count"));
 					pd.setP_image(rs.getString("p_image"));
 					pd.setP_content(rs.getString("p_content"));
 					pd.setP_rate(rs.getInt("p_rate"));
 					pd.setP_date(rs.getDate("p_date"));
                    
 					pdList.add(pd);
                     
			    }while(rs.next());
			}
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        } finally {
        	try{
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {	}
		}
		return pdList;
    }
	public int total() {
		int total = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;       
		String sql = "select count(*) from s_product";
		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			total = rs.getInt(1);			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try{
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			}catch (Exception e) {	}
		}
		return total;
	}
	public List<Product> selectList(int startRow,int  endRow) {
		List<Product> list = new ArrayList<Product>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from (select rowNum rn, a.* from ("
				+ "select * from s_product order by product_id) a)"
				+ " where rn between ? and ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Product pd = new Product();
					pd.setProduct_id(rs.getInt("product_id"));
					pd.setP_category(rs.getString("p_category"));
					pd.setP_name(rs.getString("p_name"));
					pd.setP_price(rs.getInt("p_price"));
					pd.setP_count(rs.getInt("p_count"));
					pd.setP_image(rs.getString("p_image"));
					pd.setP_content(rs.getString("p_content"));
					pd.setP_rate(rs.getInt("p_rate"));
					pd.setP_date(rs.getDate("p_date"));
					
					list.add(pd);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (rs    != null) rs.close();
					if (pstmt != null) pstmt.close();
					if (conn  != null) conn.close();
				} catch (Exception e) {}
			}
		return list;
	}
	public Product select(int product_id) {
		Product pd = new Product();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from s_product where product_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pd.setProduct_id(rs.getInt("product_id"));
				pd.setP_category(rs.getString("p_category"));
				pd.setP_name(rs.getString("p_name"));
				pd.setP_price(rs.getInt("p_price"));
				pd.setP_count(rs.getInt("p_count"));
				pd.setP_image(rs.getString("p_image"));
				pd.setP_content(rs.getString("p_content"));
				pd.setP_rate(rs.getInt("p_rate"));
				pd.setP_date(rs.getDate("p_date"));
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {}
		}
		return pd;
	}
	public int update(Product pd) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "update s_product set p_category=?,p_name=?,"
				+ "p_price=?,p_count=?,p_image=?,p_content=?,p_rate=? where product_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pd.getP_category());
			pstmt.setString(2, pd.getP_name());
			pstmt.setInt(3, pd.getP_price());
			pstmt.setInt(4, pd.getP_count());
			pstmt.setString(5, pd.getP_image());
			pstmt.setString(6, pd.getP_content());
			pstmt.setInt(7, pd.getP_rate());
			pstmt.setInt(8, pd.getProduct_id());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {}
		}
		return result;
	}
	public int delete(int product_id) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from s_product where product_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {}
		}
		return result;
	}
	// 관리자 인증 메소드
	public int loginChk(String manager_id, String manager_pass) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select manager_pass from s_manager where manager_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, manager_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPass = rs.getString("manager_pass");
				if (dbPass.equals(manager_pass)) result = 1; // 인증 성공
				else result = 0; // 비밀번호 불일치
			} else result = -1;  // 없는 아이디
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {}
		}
		return result;
	}
	public List<Product> list() {
		List<Product> list = new ArrayList<Product>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from s_product order by product_id";
			try {
				pstmt = conn.prepareStatement(sql);

				rs = pstmt.executeQuery();
				while(rs.next()) {
					Product pd = new Product();
					pd.setProduct_id(rs.getInt("product_id"));
					pd.setP_category(rs.getString("p_category"));
					pd.setP_name(rs.getString("p_name"));
					pd.setP_price(rs.getInt("p_price"));
					pd.setP_count(rs.getInt("p_count"));
					pd.setP_image(rs.getString("p_image"));
					pd.setP_content(rs.getString("p_content"));
					pd.setP_rate(rs.getInt("p_rate"));
					pd.setP_date(rs.getDate("p_date"));
					
					list.add(pd);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (rs    != null) rs.close();
					if (pstmt != null) pstmt.close();
					if (conn  != null) conn.close();
				} catch (Exception e) {}
			}
		return list;
	}
}