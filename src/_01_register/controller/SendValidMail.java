package _01_register.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;
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
		String modeStr = request.getParameter("mode");
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		String username = request.getParameter("user");
		String ctxPath = getServletContext().getContextPath() + "/";
		if (username == null) {
			lg.error("抓不到值");
			request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
			return;
		}

		lg.info("username : " + username);
		lg.info("mode : " + modeStr);
		int mode = Integer.parseInt(modeStr);
		int n = 0;
		RegisterServiceDAO_JDBC dao = new RegisterServiceDAO_JDBC();

		if (mode == 1) {
			if (!dao.idExists(username)) {
				lg.error("查無使用者");
				request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
				return;
			}
		} else if (mode == 2) {
			StoreBeanDAO sbdao = new StoreBeanDAO();
			if (!sbdao.isUserExists(username)) {
				lg.error("查無使用者");
				request.getRequestDispatcher(ctxPath + "_02_storeLogin/StoreLogin.jsp").forward(request, response);
				return;
			}
		}

		if (dao.isValidated(username, mode) == 1) {
			lg.error("已經驗證過囉");
			request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
			return;
		}
		
		int x = 0;
		String mail="";
		try {
			if (mode == 1) {
				MemberBean mb;
				LoginServiceDB lsdb = new LoginServiceDB();
				mb = lsdb.getMemberFromId(username);
				lg.info("mail = " + mb.getEmail());
				mail = mb.getEmail();
				
			}else{
				StoreBeanDAO sdao = new StoreBeanDAO();
				StoreBean sb = sdao.getStoreByStoreUsername(username);
				lg.info("mail = " + sb.getRest_email());
				mail = sb.getRest_email();
			}

			x = sendMail(mail, username, mode);
			if (x != 1) {
				lg.error("驗證信失敗");
				response.sendRedirect(response.encodeRedirectURL(ctxPath + "indexA.jsp"));
				return;
			} else {
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

	public int sendMail(String mailAddress, String memberID, int mode) {
		int n = -1;
		String from = "grabngojava@gmail.com";
		List<String> to = Arrays.asList(new String[] { mailAddress });
		String subject = "歡迎加入Grab & Go會員";
		String text = " <table width='600' border='0' align='center' cellpadding='10' cellspacing='0'>"
				+ " <tr><td align='center' style='padding: 20px 0;'><img src='http://lovegreenfood.com/gg/logo.png' "
				+ "alt='Garb and Go' width='251' height='54' title='Garb and Go'></td></tr><tr> "
				+ " <td align='center' bgcolor='#EB503C' style='font-family:Arial, '微軟正黑體', 'Microsoft YaHei', '新細明體'; "
				+ "color: #ffffff; font-size: 16px;'>Garb and Go 會員註冊確認信</td></tr><tr> "
				+ " <td align='left' style='font-family:Arial, '微軟正黑體', 'Microsoft YaHei', '新細明體'; color: #000000; "
				+ "font-size: 16px;'>" + "    <p>親愛的 Garb and Go 會員您好：</p> " + "     <p>感謝您的註冊，請點擊以下網址完成註冊認證，謝謝！</p> "
				+ "    <p><a href='http://localhost:8080/_Grab_Go/validate.do?mode=" + mode + "&user=" + memberID
				+ "''>點我驗證</a></p> " + "</td> " + " </tr> " + " <tr> "
				+ "  <td align='left' bgcolor='#f5f5f5' style='font-family:Arial, '微軟正黑體', "
				+ "'Microsoft YaHei', '新細明體'; color: #000000; font-size: 14px;'> "
				+ "       <p>本e-mail系統通知由系統直接寄發，請勿直接回覆，若您對以上內容有任何問題，" + "歡迎聯絡我們或洽 Garb and Go 客服中心</p> "
				+ "      <p>Copyright © Garb and Go All rights reserved.</p> " + "   </td> " + "  </tr></table>";

		// String text = "<h1>謝謝您加入會員</h1>" + "<h2>您可以按下列連結感受最新的體驗</h2>"
		// + "<a href='http://localhost:8080/_Grab_Go/validate.do?user=" +
		// memberID + "'>認證信</a><br>"
		// + "<a href='http://www.google.com'>google</a><br>"
		// + "<br><br><font color='blue'> 再次感謝, </font><br>工作小組敬上";

		JavaMailUtil util = new JavaMailUtil(from, to, null, null, subject, text, null);
		if (util.send()) {
			n = 1;
			System.out.println("發信成功");
		} else {
			System.out.println("發信失敗");
		}
		return n;
	}
	
	
	public String randomPassword(){
		StringBuilder sb = new StringBuilder();
		String [] lowerCase = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] number = {"0","1","2","3","4","5","6","7","8","9"};
		Random rd = new Random();
		for(int i=0;i<10;i++){
			int chooseMode = rd.nextInt(2);
			if(chooseMode==1){
				lg.info("chooseMode = "+chooseMode);
				int chooseCase = rd.nextInt(2);
				lg.info("chooseCase = " + chooseCase);
				if(chooseCase==1){
					int chooseChar = rd.nextInt(25);
					sb.append(lowerCase[chooseChar].toUpperCase());
				}
			}else if(chooseMode==2){
				lg.info("chooseMode = " +chooseMode);
				int chooseNum = rd.nextInt(9);
				sb.append(lowerCase[chooseNum]);
			}
		}
		lg.info(sb.toString());
		
		return sb.toString();
	}
	
}