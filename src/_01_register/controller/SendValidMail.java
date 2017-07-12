package _01_register.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import _01_register.model.MemberBean;
import _01_register.model.RegisterServiceDAO_JDBC;
import _02_login.model.LoginServiceDB;
import _08_mail.controller.JavaMailUtil;

@WebServlet("/sendValidMail.do")
public class SendValidMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(SendValidMail.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		lg.info("hihi");
		
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		String username = request.getParameter("user");
		String ctxPath = getServletContext().getContextPath() + "/";
		if (username == null) {
			lg.error("抓不到值");
			request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
			return;
		}

		lg.info("username : " + username);

		RegisterServiceDAO_JDBC dao = new RegisterServiceDAO_JDBC();
		if (!dao.idExists(username)) {
			lg.error("查無使用者");
			request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
			return;
		}

		if (dao.isValidated(username, 1) == 1) {
			lg.error("已經驗證過囉");
			request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
			return;
		}
		
		MemberBean mb;
		try {
			LoginServiceDB lsdb = new LoginServiceDB();
			mb = lsdb.getMemberFromId(username);
			lg.info("mail = "+mb.getEmail());
			int x = sendMail(mb.getEmail(),username);
			
			if(x!=1){
				lg.error("驗證信失敗");
				response.sendRedirect(response.encodeRedirectURL(ctxPath + "indexA.jsp"));
				return;
			}else{
				response.sendRedirect("_01_register/register_success.jsp");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public int sendMail(String mailAddress, String memberID) {
		int n = -1;
		String from = "grabngojava@gmail.com";
		List<String> to = Arrays.asList(new String[] { mailAddress });
		String subject = "歡迎加入會員";
		String text = "<h1>謝謝您加入會員</h1>" + "<h2>您可以按下列連結感受最新的體驗</h2>"
				+ "<a href='http://localhost:8080/_Grab_Go/validate.do?user=" + memberID + "'>認證信</a><br>"
				+ "<a href='http://www.google.com'>google</a><br>"
				+ "<br><br><font color='blue'> 再次感謝, </font><br>工作小組敬上";

		JavaMailUtil util = new JavaMailUtil(from, to, null, null, subject, text, null);
		if (util.send()) {
			n = 1;
			System.out.println("發信成功");
		} else {
			System.out.println("發信失敗");
		}
		return n;
	}
}