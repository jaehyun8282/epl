package epl.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import epl.model.Cart;
import epl.model.Product;

public class CartDao {
	private static CartDao instance;
	public static CartDao getInstance() {
		if (instance == null) {
			instance = new CartDao();
		}
		return instance;
	}
	private CartDao() {}
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
	// 장바구니 담기로 연결
	public int insert(Cart ct) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into s_cart values(s_cart_seq.nextval,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ct.GetId());
			pstmt.setString(2, ct.getP_name());
			pstmt.setInt(3, ct.getBuy_price());
			pstmt.setInt(4, ct.getBuy_count());
			pstmt.setString(5, ct.getP_image());
			pstmt.setInt(6, ct.getProduct_id());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return result;
	}
	public List<Cart> selectList(int startRow,int  endRow) {
		List<Cart> list = new ArrayList<Cart>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from (select rowNum rn, a.* from ("
				+ "select * from s_cart order by cart_id) a)"
				+ " where rn between ? and ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Cart ct = new Cart();
					ct.setCart_id(rs.getInt("cart_id"));
					ct.setId(rs.getString("id"));
					ct.setProduct_id(rs.getInt("product_id"));
					ct.setP_name(rs.getString("p_name"));
					ct.setBuy_price(rs.getInt("buy_price"));
					ct.setBuy_count(rs.getInt("buy_count"));
					ct.setP_image(rs.getString("p_image"));
					
					list.add(ct);
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

	
	  public int total() { 
		  int total = 0; 
		  Connection conn = getConnection();
	  PreparedStatement pstmt = null; 
	  ResultSet rs = null; String sql = "select count(*) from s_cart"; 
	  try{ 
		  pstmt = conn.prepareStatement(sql); 
		  rs = pstmt.executeQuery(); 
		  rs.next(); total = rs.getInt(1); 
		  }catch (Exception e) {
			  System.out.println(e.getMessage()); 
		  }finally { 
			  try{
				  if (rs != null) rs.close(); 
				  if (pstmt != null) pstmt.close(); 
				  if (conn != null) conn.close();
	  }catch (Exception e) { } 
			  } 
	  return total; 
  }
	public Cart select(int cart_id) {
		Cart ct = new Cart();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from s_cart where cart_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ct.setCart_id(rs.getInt("cart_id"));
				ct.setId(rs.getString("id"));
				ct.setProduct_id(rs.getInt("product_id"));
				ct.setP_name(rs.getString("p_name"));
				ct.setBuy_price(rs.getInt("buy_price"));
				ct.setBuy_count(rs.getInt("buy_count"));
				ct.setP_image(rs.getString("p_image"));
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
		return ct;
	}
	public int delete(int cart_id) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from s_cart where cart_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {
			}
		}
		return result;
	}
	//장바구니에서 수량을 수정시 실행되는 메소드
	public int updateCount(int cart_id, int count) {
		Connection conn = getConnection();        
		int result = 0;
        PreparedStatement pstmt = null;  
        String sql = "update s_cart set buy_count=? where cart_id=?";
        try {      
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, count);
            pstmt.setInt(2, cart_id);                        
            result = pstmt.executeUpdate();
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }finally {
        	try {
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {
			}
        }
		return result;
	}
	//id에 해당하는 레코드의 목록을 얻어내는 메소드
	public ArrayList<Cart> getCart(String id) {
		Connection conn = getConnection();  
		PreparedStatement pstmt = null;
        ResultSet rs = null;    
        Cart cart=null;
        String sql = "select * from s_cart where cart_id = ?";
        ArrayList<Cart> lists = null;         
        try {	  
            pstmt = conn.prepareStatement(sql);             
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();             
            lists = new ArrayList<Cart>();
            while (rs.next()) {
           	cart = new Cart();            	 
           	cart.setCart_id(rs.getInt("cart_id"));
           	cart.setId(rs.getString("id"));
           	cart.setProduct_id(rs.getInt("product_id"));
           	cart.setP_name(rs.getString("p_name"));
           	cart.setBuy_price(rs.getInt("buy_price"));
           	cart.setBuy_count(rs.getInt("buy_count"));
           	cart.setP_image(rs.getString("p_image"));
           	 
           	 lists.add(cart);
			 }
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }finally {
        	try {
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {}
		}
		 return lists;
	}
}