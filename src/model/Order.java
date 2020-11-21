package epl.model;
import java.sql.Date;

public class Order {
	private int order_id;
	private String id; 				// 구매자
	private int product_id; 		// 상품번호
	private String p_name; 			// 구매 상품
	private int buy_price; 			// 판매가
	private int buy_count;    		// 판매수량
	private String p_image; 		// 상품이미지
	private Date order_date; 		// 구매 일자
	private String account; 		// 계좌
	private String delivery_name;	// 배송받는 사람
	private String delivery_addr; 	// 배송지
	private String delivery_tel; 	// 배송지 전화번호
	private String order_state;		// 배송상황
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}
	public int getBuy_count() {
		return buy_count;
	}
	public void setBuy_count(int buy_count) {
		this.buy_count = buy_count;
	}
	public String getP_image() {
		return p_image;
	}
	public void setP_image(String p_image) {
		this.p_image = p_image;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDelivery_name() {
		return delivery_name;
	}
	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}
	public String getDelivery_addr() {
		return delivery_addr;
	}
	public void setDelivery_addr(String delivery_addr) {
		this.delivery_addr = delivery_addr;
	}
	public String getDelivery_tel() {
		return delivery_tel;
	}
	public void setDelivery_tel(String delivery_tel) {
		this.delivery_tel = delivery_tel;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
}
