package _04_shoppingCart.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _04_shoppingCart.model.ShoppingCart;
import _05_orderProcess.model.OrderItemBean;

@WebServlet("/_07_storePage/addItem.do")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String prodId = "";
	String itemName = "";
	String itemPrice = "";
	String specialNeed = "";
	String count = "";
	String restId = "";
	String itemType = "";
	ShoppingCart cart = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if (session == null) { // 使用逾時
			request.setAttribute("Errors", "使用逾時，請重新登入(BuyBookServlet:SessionTimeOut)");
			RequestDispatcher rd = request.getRequestDispatcher("/_02_login/login.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		
		request.setAttribute("msg", "");
		// 取出存放在session物件內的ShoppingCart物件
		cart = (ShoppingCart) session.getAttribute("cart");
		// check state of cart
		if (cart == null) {
			/* put shoppingCart into SessionObject */
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}else{
			if(cart.getItemNumber()==0){
				session.removeAttribute("orderRest");
			}
		}

		try {
			prodId = request.getParameter("prod_id");
			itemName = request.getParameter("itemName");
			itemPrice = request.getParameter("itemPrice");
			specialNeed = request.getParameter("specialNeed");
			count = request.getParameter("count");
			restId = request.getParameter("rest_id");
			itemType = request.getParameter("itemType");
			System.out.println("itemType = "+itemType + "  in AddItemServlet" );
			session.setAttribute("anchor", itemType+"|"+restId);
//
			 System.out.println("restId="+restId);
			 System.out.println("prodId="+prodId);
			 System.out.println("itemName="+itemName);
			 System.out.println("itemPrice="+itemPrice);
			 System.out.println("specialNeed="+specialNeed);
			 System.out.println("count="+count);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* validation */
		String orderRest = (String) session.getAttribute("orderRest");
		if (orderRest == null) {
			session.setAttribute("orderRest", restId);
			addToCart();
		} else {
			// if the order comes from different Store
			if (!orderRest.equals(restId)) {
				System.out.println("========================");
				System.out.println("restId = " + restId);
				System.out.println("orderRest = " + orderRest);
				request.setAttribute("msg", "無法一次同時訂購兩間餐廳喔~");
				RequestDispatcher rd = request.getRequestDispatcher("storePage.jsp");
				rd.forward(request, response);
				return;
			} else {

				addToCart();
			}
		}
		/* redirct to storePage */
		RequestDispatcher rd = request.getRequestDispatcher("storePage.jsp");
		rd.forward(request, response);
		return;

	}

	public void addToCart() {
		/* create a OrderItemBean */
		int prod_id = Integer.parseInt(prodId);
		int item_Price = Integer.parseInt(itemPrice);
		int item_Count = Integer.parseInt(count);
		// int prod_id, String item_name, int item_price, int
		// item_amount, String item_note)
		OrderItemBean oid = new OrderItemBean(prod_id, itemName, item_Price, item_Count, specialNeed);
//		System.out.println(oid);
		/* put OrderItemBean into shoppingCart */
		cart.addToCart(prod_id, oid);
		
		cart.getListAllMapToPrint(cart);
		
		System.out.println(cart.getItemNumber());
		
		/*List<OrderItemBean> list = cart.getListAllMap();
		for(OrderItemBean oib : list){
			System.out.println(oib);
		}*/
		
		cart.getSubtotalToPrint(cart);
	}

}
