package _02_login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import _01_register.model.RegisterServiceDAO_JDBC;

@WebServlet("/checkValidate.do")
public class CheckValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(CheckValidateServlet.class);
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		lg.info("hi");
		
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		response.setContentType("application/json; charset=utf-8");
		String username = request.getParameter("user");
		
		if (username == null) {
			lg.error("抓不到值");
			return;
		}
		lg.info("username : " + username);

		RegisterServiceDAO_JDBC dao = new RegisterServiceDAO_JDBC();

		try(PrintWriter out = response.getWriter();){
			if (dao.isValidated(username, 1)) {
				String msg = new Gson().toJson("NotValid");
				out.write(msg);
				out.flush();
			}
			
		}


	}

}
