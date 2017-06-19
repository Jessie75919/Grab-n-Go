package _01_Store_register.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/_01_StoreRegister/loadFoodType.do")
public class loadFoodType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		System.out.println("I am heard");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json ; charset=utf-8");
		PrintWriter out =  response.getWriter();
		
		String idQuery =  request.getParameter("id");
	
	}



}
