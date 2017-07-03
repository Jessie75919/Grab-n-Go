package _02_login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am here"); 
		request.getSession().removeAttribute("LoginOK");
		request.getSession().invalidate();
		HttpSession session = request.getSession();
		session.removeAttribute("orderRest");
		session.removeAttribute("cart");
		response.sendRedirect("indexA.jsp"); 
		
	}

}
