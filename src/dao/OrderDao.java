package epl.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import epl.model.Cart;
import epl.model.Order;

public class OrderDao {
	private static OrderDao instance = new OrderDao();
	public static OrderDao getInstance() {
		return instance;
	}
	private OrderDao() {}
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
	// 구매하기로 연결
	public int insert(Order od) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into s_order values(s_order_seq.nextval,?,?,?,?,?,?,sysdate,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			// pstmt.setInt(1, pd.getOrder_id());
			pstmt.setString(1, od.getId());
			pstmt.setInt(2, od.getProduct_id());
			pstmt.setString(3, od.getP_name());
			pstmt.setInt(4, od.getBuy_price());
			pstmt.setInt(5, od.getBuy_count());
			pstmt.setString(6, od.getP_image());
			// pstmt.setDate(7, od.getOrder_date());
			pstmt.setString(7, od.getAccount());
			pstmt.setString(8, od.getDelivery_name());
			pstmt.setString(9, od.getDelivery_addr());
			pstmt.setString(10, od.getDelivery_tel());
			pstmt.setString(11, od.getOrder_state());
			
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
	public List<Order> selectList(int startRow,int  endRow) {
		List<Order> lists = new ArrayList<Order>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select * from (select rowNum rn, a.* from ("
				+ "select * from s_order order by order_id) a)"
				+ " where rn between ? and ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Order od = new Order();
					od.setOrder_id(rs.getInt("order_id"));
					od.setId(rs.getString("id"));
					od.setProduct_id(rs.getInt("product_id"));
					od.setP_name(rs.getString("p_name"));
					od.setBuy_price(rs.getInt("buy_price"));
					od.setBuy_count(rs.getInt("buy_count"));
					od.setP_image(rs.getString("p_image"));
					od.setOrder_date(rs.getDate("order_date"));
					od.setAccount(rs.getString("account"));
					od.setDelivery_name(rs.getString("delivery_name"));
					od.setDelivery_addr(rs.getString("delivery_addr"));
					od.setDelivery_tel(rs.getString("delivery_tel"));
					od.setOrder_state(rs.getString("order_state"));
					
					lists.add(od);
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
		return lists;
	}

	  public int total() { 
		  int total = 0; 
		  Connection conn = getConnection();
		  PreparedStatement pstmt = null; 
		  ResultSet rs = null; 
		  String sql = "select count(*) from s_order"; 
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
	public Order select(int order_id) {
		Order od = new Order();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from s_order where int order_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				od.setOrder_id(rs.getInt("order_id"));
				od.setId(rs.getString("id"));
				od.setProduct_id(rs.getInt("product_id"));
				od.setP_name(rs.getString("p_name"));
				od.setBuy_price(rs.getInt("buy_price"));
				od.setBuy_count(rs.getInt("buy_count"));
				od.setP_image(rs.getString("p_image"));
				od.setOrder_date(rs.getDate("order_date"));
				od.setAccount(rs.getString("account"));
				od.setDelivery_name(rs.getString("delivery_name"));
				od.setDelivery_addr(rs.getString("delivery_addr"));
				od.setDelivery_tel(rs.getString("delivery_tel"));
				od.setOrder_state(rs.getString("order_state"));
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
		return od;
	}
	public int delete(int order_id) {
		int result = 0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from s_order where order_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_id);
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
	public Order selectpd(int product_id) {
		Order od1 = new Order();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from s_order where int product_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				od1.setOrder_id(rs.getInt("order_id"));
				od1.setId(rs.getString("id"));
				od1.setProduct_id(rs.getInt("product_id"));
				od1.setP_name(rs.getString("p_name"));
				od1.setBuy_price(rs.getInt("buy_price"));
				od1.setBuy_count(rs.getInt("buy_count"));
				od1.setP_image(rs.getString("p_image"));
				od1.setOrder_date(rs.getDate("order_date"));
				od1.setAccount(rs.getString("account"));
				od1.setDelivery_name(rs.getString("delivery_name"));
				od1.setDelivery_addr(rs.getString("delivery_addr"));
				od1.setDelivery_tel(rs.getString("delivery_tel"));
				od1.setOrder_state(rs.getString("order_state"));
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
		return od1;
	}
	//id에 해당하는 buy테이블의 레코드수를 얻어내는 메소드
	public int getListCount(String id) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
        ResultSet rs = null;     
        int count = 0;
        try { 
            String sql = "select count(*) from s_order where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
               count = rs.getInt(1);
			}
        } catch(Exception e) { 
        	System.out.println(e.getMessage());
        } finally {
        	try {
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {}
		}
		return count ;
    }
	//id에 해당하는 buy테이블의 구매목록을 얻어내는 메소드
	public ArrayList<Order> getBuyList(String id) {
		Connection conn = getConnection();  
		PreparedStatement pstmt = null;
        ResultSet rs = null;     
        Order order = null;
        String sql = "";         
        ArrayList<Order> lists = null;        
        try {      	 
       	 sql = "select * from s_order where id = ? order by order_id";
            pstmt = conn.prepareStatement(sql);            
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();            
            lists = new ArrayList<Order>(); 
            while (rs.next()) {
            	order = new Order();
            	order.setOrder_id(rs.getInt("order_id"));
            	order.setId(rs.getString("id"));
            	order.setProduct_id(rs.getInt("product_id"));
            	order.setP_name(rs.getString("p_name"));
            	order.setBuy_price(rs.getInt("buy_price"));
            	order.setBuy_count(rs.getInt("buy_count"));
            	order.setP_image(rs.getString("p_image"));
            	order.setOrder_date(rs.getDate("order_date"));
            	order.setAccount(rs.getString("account"));
            	order.setDelivery_name(rs.getString("delivery_name"));
            	order.setDelivery_addr(rs.getString("delivery_addr"));
            	order.setDelivery_tel(rs.getString("delivery_tel"));
            	order.setOrder_state(rs.getString("order_state"));
           	 
           	     lists.add(order);
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
	//구매 테이블인 buy에 구매목록 등록
	public void insertBuy(ArrayList<Cart> cartLists, String id, String account, String delivery_name,
			String delivery_tel, String delivery_addr) {
		Connection conn = getConnection();  
		PreparedStatement pstmt = null;
        ResultSet rs = null;     
        Timestamp reg_date = null;
        String sql = "";        
        String todayDate = "";
        String compareDate = ""; 
        int order_id = 0;       
        int nowCount ;
        try {
            reg_date = new Timestamp(System.currentTimeMillis());
            todayDate = reg_date.toString();
            compareDate = todayDate.substring(0, 4) + todayDate.substring(5, 7) + todayDate.substring(8, 10);
            sql = "select nvl(max(order_id),0) from s_order";
            pstmt = conn.prepareStatement(sql);            
            rs = pstmt.executeQuery();
            if (rs.next()) order_id =  rs.getInt(1) + 1;
            conn.setAutoCommit(false);
            // 여러 테이블을 입력/수정/삭제하기 때문에 모든 정리가 성공한 후에 commit
            for(int i=0; i<cartLists.size();i++){
            	//해당 아이디에 대한 cart테이블 레코드를을 가져온후 buy테이블에 추가
            	Cart cart = (Cart)cartLists.get(i);
            	
            	sql = "insert into s_order (order_id,id,product_id,p_name,buy_price,buy_count,";
                sql += "p_image,order_date,account,delivery_name,delivery_tel,delivery_addr)";
                sql += " values (?,?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, order_id);
                pstmt.setString(2, id);
                pstmt.setInt(3, cart.getProduct_id());
                pstmt.setString(4, cart.getP_name());
                pstmt.setInt(5, cart.getBuy_price());
                pstmt.setInt(6, cart.getBuy_count());
                pstmt.setString(7, cart.getP_image());
                pstmt.setTimestamp(8, reg_date);
                pstmt.setString(9, account);
                pstmt.setString(10, delivery_name);
                pstmt.setString(11, delivery_tel);
                pstmt.setString(12, delivery_addr);

                pstmt.executeUpdate();
                
                //상품이 구매되었으므로 book테이블의 상품수량을 재조정함
                pstmt.close();
                // 책 재고수량을 읽어서
                pstmt = conn.prepareStatement("select book_count from s_product where product_id=?");
                pstmt.setInt(1, cart.getProduct_id());
                rs = pstmt.executeQuery();
                rs.next();
                // 재고 - 판매수량 한 후에 책의 재고 수정
                nowCount = rs.getInt(1) - cart.getBuy_count();
                
                sql = "update s_product set p_count=? where product_id=?";
                pstmt = conn.prepareStatement(sql);
            
                pstmt.setInt(1, nowCount);
    			pstmt.setInt(2, cart.getProduct_id());
                
                pstmt.executeUpdate(); 
                order_id++;
            }
            // 장바구니 비우기
            sql = "delete from s_cart where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
          
            pstmt.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
         }catch(Exception e) {
        	 System.out.println(e.getMessage());
        } finally {
        	try {
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {}
		}
    }
}