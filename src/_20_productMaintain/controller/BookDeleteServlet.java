package _20_productMaintain.controller;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import _03_Product.model.*;
@WebServlet("/_20_productMaintain/BookDelete.do")
public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	HttpSession session = request.getSession();
        	String bID = request.getParameter("bookID");
        	String bNo = request.getParameter("bookNo");
        	int bookID = Integer.parseInt(bID);
			BookAccessBean  bab = new BookAccessBean();
			int n = bab.deleteBook(bookID);
			if (n == 1) {
				session.setAttribute("BookDeleteMsg", "書籍(" + bNo + ")刪除成功");
			} else {
				session.setAttribute("BookDeleteMsg", "書籍(" + bNo + ")刪除失敗");
			}
			response.sendRedirect("DisplayPageProducts");
			return;
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
        
	}

}
