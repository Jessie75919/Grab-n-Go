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
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("hi low~");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if (session == null) {      // 使用逾時
			request.setAttribute("Errors", "使用逾時，請重新登入(BuyBookServlet:SessionTimeOut)");
			RequestDispatcher rd = request.getRequestDispatcher("/_02_login/login.jsp");
			rd.forward(request, response);
			return;
		}
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart cart = (ShoppingCart)session.getAttribute("ShoppingCart");
		if(cart==null){
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		
		String prodId ="";
		String itemName ="";
		String itemPrice ="";
		String specialNeed ="";
		String count ="";
		String restId ="";
		try {
			 prodId = request.getParameter("prod_id");
			 itemName = request.getParameter("itemName");
			 itemPrice = request.getParameter("itemPrice");
			 specialNeed = request.getParameter("specialNeed");
			 count = request.getParameter("count");
			 restId = request.getParameter("rest_id");
			
			System.out.println("restId="+restId);
			System.out.println("prodId="+prodId);
			System.out.println("itemName="+itemName);
			System.out.println("itemPrice="+itemPrice);
			System.out.println("specialNeed="+specialNeed);
			System.out.println("count="+count);
			session.setAttribute("msg", "Data OK~");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*validation*/
		String orderRest = (String) session.getAttribute("orderRest");
		if(orderRest == null){
			session.setAttribute("orderRest", restId);
		}else{
			// if the order comes from different Store
			if(!orderRest.equals(restId)){
				session.setAttribute("msg", "無法一次同時訂購兩間餐廳喔~");
				RequestDispatcher rd = request.getRequestDispatcher("storePage.jsp");
				rd.forward(request, response);
				return;
			}else{
//				int serial_no, int ord_id, int prod_id, String item_name, int item_price, int item_amount,
//				String item_note)
				OrderItemBean oid = new OrderItemBean();
				
			}
		}
		
		
		/*create a OrderItemBean*/
		
		
		/*put OrderItemBean into shoppingCart*/

		
		/*put shoppingCart into SessionObject*/
		
		
		/*redirect to current page*/
		
		
	/*	RequestDispatcher rd = request.getRequestDispatcher("_07_storePage/storePage.jsp");
		rd.forward(request, response);
		return;*/
		
		
		
	}


}
