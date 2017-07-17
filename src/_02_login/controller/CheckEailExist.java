package _02_login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import _02_login.model.LoginServiceDB;



@WebServlet("/_02_login/checkEailExist.do")
public class CheckEailExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(CheckEailExist.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		lg.info("hihi");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		
		String email = request.getParameter("email");
		
		if(email!=null){
			try {
				lg.info(email);
				LoginServiceDB dsdb = new LoginServiceDB();
				int n = dsdb.checkEmail(email);
				try(
					PrintWriter out = response.getWriter();
						){
					String jsonText = new Gson().toJson(n);
					out.write(jsonText);
					out.flush();
				}
				
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			lg.error("get Nothing");
		}
		
	}

}
