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

@WebServlet("/addItem.do")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("hi low~");
		request.setCharacterEncoding("UTF-8");
		/*HttpSession session = request.getSession(false);
		if (session == null) {      // 使用逾時
			request.setAttribute("Errors", "使用逾時，請重新登入(BuyBookServlet:SessionTimeOut)");
			RequestDispatcher rd = request.getRequestDispatcher("/_02_login/login.jsp");
			rd.forward(request, response);
			return;
		}
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart cart = (ShoppingCart)session.getAttribute("ShoppingCart");*/
//		String prodId = request.getParameter("proId");
//		System.out.println(prodId);
		
	/*	RequestDispatcher rd = request.getRequestDispatcher("_07_storePage/storePage.jsp");
		rd.forward(request, response);
		return;*/
		
		
		
	}


}
