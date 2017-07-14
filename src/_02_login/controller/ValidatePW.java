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

import _00_init.GlobalService;
import _01_register.model.MemberBean;
import _02_login.model.LoginServiceDB;

@WebServlet("/ValidatePW.do")
public class ValidatePW extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(CheckEailExist.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		lg.info("hihihi");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json ; charset=utf-8");

		String userId = request.getParameter("userId");
		String pwInput = request.getParameter("pwInput");
		
		
		if(userId==null & pwInput==null){
			lg.error("getNoValue");
			request.getRequestDispatcher("../_06_member/updatePassword.jsp")
				.forward(request, response);
			return;
		}

		pwInput = GlobalService.getMD5Endocing(GlobalService.encryptString(pwInput));
		
		try {
			LoginServiceDB lsdb = new LoginServiceDB();
			lg.info("password=" + pwInput);
			MemberBean mb = lsdb.checkPassword(userId, pwInput);
			try(
					PrintWriter out = response.getWriter();	)
			{
				String jsonText = "";
				if(mb==null){
					jsonText =new Gson().toJson("0");
				}else{
					lg.info("密碼正確");
					jsonText =new Gson().toJson("1"); 
				}
				out.write(jsonText);
				out.flush();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
