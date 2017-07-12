package _01_Store_register.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;
import _08_mail.controller.JavaMailUtil;

@WebServlet("/_01_StoreRegister/StoreRegister.do")
public class StoreRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(StoreRegisterServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		
		
		HttpSession session = request.getSession();
		String requestURI = (String) session.getAttribute("requestURI");
		System.out.println("requestURI = " + requestURI );
		String memberID = request.getParameter("mid");
		String password = request.getParameter("password");
		String StoreName = request.getParameter("StoreName");
		System.out.println("StoreName = " + StoreName);
		String type = request.getParameter("StoreType");
		System.out.println("type = " + type);
		String branch = request.getParameter("branch");
		String addr = request.getParameter("address");
		
		String tel = request.getParameter("tel");
		String email = request.getParameter("eMail");
		String owner = request.getParameter("owner");
		System.out.println("owner = " + owner);
		String url = request.getParameter("url");
		float langitude = 0;
		float latitude = 0;
		try {
			langitude = Float.parseFloat(request.getParameter("langitude"));
			latitude = Float.parseFloat(request.getParameter("latitude"));
			lg.info("langitude :" +langitude);
			lg.info("latitude :" +latitude);
		} catch (Exception e) {
			System.out.println("langitude & latitude got wrong");
			e.printStackTrace();
		}

		// 準備存放錯誤訊息的Map物件
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgErr = new HashMap<>();
		Map<String, String> msgOK = new HashMap<>();
		

		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要 session物件來存放共用資料。
		request.setAttribute("msgErr", msgErr); // 顯示錯誤訊息
		request.setAttribute("msgOK", msgOK); // 顯示正常訊息

		// 2. 進行必要的資料轉換
		// All data are String

		// 3. 檢查使用者輸入資料
		// the validation of input are checked by Javascript

		try {
			StoreBeanDAO sbdao = new StoreBeanDAO();
			if (sbdao.isUserExists(memberID)) {
				msgErr.put("idRepeat", "此帳號已存在，請換新帳號");
			} else {
				
				
				File file = new File("./");
				lg.info(file.getAbsolutePath());
				lg.info(request.getRequestURI());
				lg.info(request.getContextPath());
				InputStream cover = getServletContext().getResourceAsStream("/images/restImage/test_coverImg.JPG");
				InputStream logo = getServletContext().getResourceAsStream("/images/restImage/test_logo.JPG");
				InputStream banner = getServletContext().getResourceAsStream("/images/restImage/test_mBanner.jpg");
				
				
				StoreBean sb = new StoreBean(type, StoreName, branch, addr, tel, owner, email, memberID, password, url,
						langitude, latitude);
				sb.setCover(cover);
				sb.setLogo(logo);
				sb.setMainBanner(banner);

				int n = sbdao.insertShopData(sb);
				
				int x = sendMail(email,memberID);
				
				
				
				if (n == 1) {
					msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
					System.out.println(StoreName + " add successfully ,n= " +n );
					if(x!=1){
						lg.error("驗證信失敗");
						msgErr.put("errorValid","驗證信失敗");
					}else {
						response.sendRedirect("_storeRegisterSuccessful.jsp");
					} 

				}
				 else{
					 System.out.println("Insert doesn't work properly");
					 msgErr.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
				 }
			}
			for (String key : msgErr.keySet()) {
				System.out.println(key + ", " + msgErr.get(key));
			}

			 if (!msgErr.isEmpty()) {
			// // 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			 RequestDispatcher rd =
			 request.getRequestDispatcher("_storeRegister.jsp");
			 rd.forward(request, response);
			 	return;
			 }

		} catch (Exception e) {
			e.printStackTrace();
			msgErr.put("errorIDDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("_storeRegister.jsp");
			rd.forward(request, response);
			return;
		}
	}
	
	

    public int  sendMail(String mailAddress,String memberID){
    	int n = -1;
    	String from = "grabngojava@gmail.com";
		List<String> to = Arrays.asList(new String[]{mailAddress});
		String subject = "歡迎加入Grab & Go會員";
		String text = " <table width='600' border='0' align='center' cellpadding='10' cellspacing='0'>" 
	              + " <tr><td align='center' style='padding: 20px 0;'><img src='http://lovegreenfood.com/gg/logo.png' "
	              + "alt='Garb and Go' width='251' height='54' title='Garb and Go'></td></tr><tr> "
	           + " <td align='center' bgcolor='#EB503C' style='font-family:Arial, '微軟正黑體', 'Microsoft YaHei', '新細明體'; "
	           + "color: #ffffff; font-size: 16px;'>Garb and Go 會員註冊確認信</td></tr><tr> "
	           + " <td align='left' style='font-family:Arial, '微軟正黑體', 'Microsoft YaHei', '新細明體'; color: #000000; "
	           + "font-size: 16px;'>"
	           + "    <p>親愛的 Garb and Go 會員您好：</p> "
	           + "     <p>感謝您的註冊，請點擊以下網址完成註冊認證，謝謝！</p> "
	            + "    <p><a href='http://localhost:8080/_Grab_Go/validate.do?mode=2&user=" + memberID + "''>點我驗證</a></p> "
	            + "</td> "
	       + " </tr> "
	       + " <tr> "
	          + "  <td align='left' bgcolor='#f5f5f5' style='font-family:Arial, '微軟正黑體', "
	          + "'Microsoft YaHei', '新細明體'; color: #000000; font-size: 14px;'> "
	          + "       <p>本e-mail系統通知由系統直接寄發，請勿直接回覆，若您對以上內容有任何問題，"
	          + "歡迎聯絡我們或洽 Garb and Go 客服中心</p> "
	          + "      <p>Copyright © Garb and Go All rights reserved.</p> "
	         + "   </td> "
	      + "  </tr></table>" ;
		
		JavaMailUtil  util = new JavaMailUtil(from, to, null, null, subject, text ,null);
		if (util.send()){
		   n=1;
		   System.out.println("發信成功");
		} else {
		   System.out.println("發信失敗");
		}
    	return n;
    }
	

}
