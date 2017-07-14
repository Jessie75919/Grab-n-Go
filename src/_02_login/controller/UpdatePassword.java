package _02_login.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import _00_init.GlobalService;
import _01_register.model.RegisterServiceDAO_JDBC;

@WebServlet("/_06_member/userUpdatePassword.do")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(CheckEailExist.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String newPW = request.getParameter("newPW");

		if (userId == null & newPW == null) {
			lg.error("getNoValue");
			request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
			return;
		}
		
		lg.info("userId=" + userId);
		lg.info("password=" + newPW);
		
		
		newPW = GlobalService.getMD5Endocing(GlobalService.encryptString(newPW));

			RegisterServiceDAO_JDBC jdbc = new RegisterServiceDAO_JDBC();
			lg.info("password=" + newPW);
			int n = jdbc.updatePassword(userId, newPW);
			if(n==1){
				lg.info("密碼更新成功");
				request.setAttribute("msg", "密碼更新成功");
				request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
				return;
			}else{
				lg.info("密碼更新失敗");
				request.setAttribute("msg", "密碼更新失敗");
				request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
				return;
			}

	}

}
