package sservice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;

public class BuyForm implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Cart> cartLists = null;
		ArrayList<String> accountLists = null;
		Cart cartList = null;
		return "buyForm";
	}
}
