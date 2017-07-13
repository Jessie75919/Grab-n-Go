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
import _01_register.controller.SendValidMail;
import _01_register.model.MemberBean;
import _01_register.model.RegisterServiceDAO_JDBC;
import _02_login.model.LoginServiceDB;



@WebServlet("/_02_login/ForgetPassword.do")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(ForgetPassword.class);

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		lg.info("hihi");
		String ctxPath = getServletContext().getContextPath() + "/";
		lg.info("ctxPath = " +ctxPath);
		String email = request.getParameter("eMail");
		if(email!=null){
			try {
				LoginServiceDB dsdb = new LoginServiceDB();
				MemberBean mb = dsdb.getMemberByEmail(email);
				SendValidMail sm = new SendValidMail();
				String newPw = sm.getRandomPassword();
				
				RegisterServiceDAO_JDBC jdbc = new RegisterServiceDAO_JDBC();
				 
				String encrypPW = GlobalService.getMD5Endocing(GlobalService.encryptString(newPw));
				int x = jdbc.updatePassword(mb.getMemberId(), encrypPW);
				if(x==1){
					int n = sm.sendForgetPWMail(email, mb.getMemberId(), 1, newPw);
					if(n==1){
						lg.info("寄信成功");
						request.getRequestDispatcher("ForgetPwSent.jsp").forward(request, response);
						return;
					}else{
						lg.info("寄信失敗");
						request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
						return;
					}
				}else{
					lg.error("更新密碼失敗");
				}
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
