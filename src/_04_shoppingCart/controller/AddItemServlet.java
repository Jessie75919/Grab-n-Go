package _04_shoppingCart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _04_shoppingCart.model.ShoppingCart;

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
		
		List<String> paraList = new ArrayList<>();
		String prodId = request.getParameter("prod_id");
		paraList.add(prodId);
		String itemName = request.getParameter("itemName");
		paraList.add(itemName);
		String itemPrice = request.getParameter("itemPrice");
		paraList.add(itemPrice);
		String specialNeed = request.getParameter("specialNeed");
		paraList.add(specialNeed);
		String count = request.getParameter("count");
		paraList.add(count);
		String restId = request.getParameter("restId");
		paraList.add(restId);
		
		for(String st : paraList){
			if(st==null){  //specialNeed maybe contains ""
				System.out.println("讀取資料有問題");
				return;
			}else{
				System.out.println("restId="+restId);
				System.out.println("prodId="+prodId);
				System.out.println("itemName="+itemName);
				System.out.println("itemPrice="+itemPrice);
				System.out.println("specialNeed="+specialNeed);
				System.out.println("count="+count);
				session.setAttribute("OK", "OKKKKK");
				
				RequestDispatcher rd = request.getRequestDispatcher("storePage.jsp");
				rd.forward(request, response);
				return;
			}
		}
		
		/*validation*/
		
		
		/*create a OrderItemBean*/
		
		
		/*put OrderItemBean into shoppingCart*/

		
		/*put shoppingCart into SessionObject*/
		
		
		/*redirect to current page*/
		
		
	/*	RequestDispatcher rd = request.getRequestDispatcher("_07_storePage/storePage.jsp");
		rd.forward(request, response);
		return;*/
		
		
		
	}


}
