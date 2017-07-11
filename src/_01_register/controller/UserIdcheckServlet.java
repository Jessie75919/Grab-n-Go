package _01_register.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _01_Store_register.model.StoreBeanDAO;
import _01_register.model.RegisterServiceDAO_JDBC;

@WebServlet("/_01_register/userIdcheck.do")
public class UserIdcheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am heard");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json ; charset=utf-8");
		PrintWriter out =  response.getWriter();
		String idQuery =  request.getParameter("id");
		
			RegisterServiceDAO_JDBC dao = new RegisterServiceDAO_JDBC();
			Boolean result = dao.idExists(idQuery);
			String resultTxt = "";
			if(!result){
				resultTxt = "OK";
			}else{
				resultTxt = "NO";
			}
			
			Gson gs = new Gson();
			out.print(gs.toJson(resultTxt));
			out.flush();
		
		
	}

}
