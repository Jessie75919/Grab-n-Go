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

import _01_register.controller.SendValidMail;
import _01_register.model.MemberBean;
import _02_login.model.LoginServiceDB;



@WebServlet("/ForgetPassword.do")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(ForgetPassword.class);

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		lg.info("hihi");
		String email = request.getParameter("eMail");
		if(email!=null){
			try {
				LoginServiceDB dsdb = new LoginServiceDB();
				MemberBean mb = dsdb.getMemberByEmail(email);
				SendValidMail sm = new SendValidMail();
				String newPw = sm.randomPassword();
//				int n = sm.sendMail(email, mb.getMemberId(), 1);
//				if(n==1){
//					lg.info("寄信成功");
//				}
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
		}else{
			lg.error("get NOthing ");
		}
	}

}
