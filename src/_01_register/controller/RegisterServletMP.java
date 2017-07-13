package _01_register.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import _00_init.GlobalService;
import _01_register.model.MemberBean;
import _01_register.model.RegisterServiceDAO;
import _01_register.model.RegisterServiceDAO_JDBC;
import _08_mail.controller.JavaMailUtil;
/*
 * 本程式讀取使用者輸入資料，進行必要的資料轉換，檢查使用者輸入資料，
 * 進行Business Logic運算，依照Business Logic運算結果來挑選適當的畫面
 * 
 */
//
//啟動檔案上傳的功能：
//1. <form>標籤的 method屬性必須是"post", 而且
//    enctype屬性必須是"multipart/form-data"
//注意：enctype屬性的預設值為"application/x-www-form-urlencoded"
//2. 定義可以挑選上傳檔案的表單欄位：
//<input type='file' name='user-defined_name' />
//
//所謂 HTTP multipart request是指由Http客戶端(如瀏覽器)所建構的ㄧ種請求，
//用來上傳一般的表單資料(form data)與檔案。
//參考網頁：http://stackoverflow.com/questions/913626/what-should-a-multipart-http-request-with-multiple-files-look-like
//
//Servlet規格書一直到Servlet 3.0才提出標準API將檔案上傳的功能標準化。
//
//在Servlet 3.0中，若要能夠處理瀏覽器送來的HTTP multipart request, 
//我們撰寫的Servlet程式必須以註釋
//『javax.servlet.annotation.MultipartConfig』來加以說明。
//
//MultipartConfig的屬性說明:
//location: 上傳之表單資料與檔案暫時存放在Server端之路徑，此路徑必須存在。
//fileSizeThreshold: 檔案的大小臨界值，超過此臨界值，上傳檔案會用存放在硬碟，
//否則存放在主記憶體。
//maxFileSize: 上傳單一檔案之長度限制，如果超過此數值，Web Container會丟出例外
//maxRequestSize: 上傳所有檔案之總長度限制，如果超過此數值，Web Container會丟出例外
@MultipartConfig(location = "", 
fileSizeThreshold = 5*1024 * 1024, 
maxFileSize = 1024 * 1024 * 500, 
maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/_01_register/register.do")
public class RegisterServletMP extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	Logger lg = Logger.getLogger(RegisterServletMP.class);
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
    	request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
        // 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
        request.setAttribute("MsgMap", errorMsg);  //顯示錯誤訊息
        session.setAttribute("MsgOK", msgOK);      //顯示正常訊息

		String memberID = "";
		String password  = "" ;
		String password2  = "";
		String name  = "";
		String email  = "";
		String addr  = "";
		String tel  = "";
		String bday  = "";
		java.sql.Date date = null;
		String fileName = "";
		long sizeInBytes = 0;
		InputStream is = null;
		Collection<Part> parts = request.getParts(); // 取出HTTP multipart request內所有的parts
		GlobalService.exploreParts(parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) {   // 如果這是一個上傳資料的表單				
			for (Part p : parts) {   
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				
				// 1. 讀取使用者輸入資料
				if (p.getContentType() == null) {
					if (fldName.equals("userId")) {
						memberID = value;
					} else if (fldName.equals("pswd")) {
						password = value;
					} else if (fldName.equalsIgnoreCase("password2")) {
						password2 = value;
					} else if (fldName.equalsIgnoreCase("name")) {
						name = value;
					} else if (fldName.equalsIgnoreCase("eMail")) {
						email = value;
					} else if (fldName.equalsIgnoreCase("address")) {
						addr = value;  
					} else if (fldName.equalsIgnoreCase("tel")) {
						tel = value;
					} else if (fldName.equalsIgnoreCase("birthday")) {
						bday = value;  
					}
				} else {
					fileName = GlobalService.getFileName(p); // 此為圖片檔的檔名
					fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (fileName != null && fileName.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					} else {
						//errorMsg.put("errorPicture", "必須挑選圖片檔");
						String path = "/images/restImage/test_coverImg.JPG";
						String realPath = getServletContext().getRealPath(path);
						lg.info(realPath);
						File file = new File(realPath);
						fileName = file.getName();
						is = new FileInputStream(file);
						sizeInBytes = file.length();
					}
				
				}
			}
			// 2. 進行必要的資料轉換
			if (bday != null && bday.trim().length() > 0) {
				try {
					date = java.sql.Date.valueOf(bday);
				} catch (IllegalArgumentException e) {
					errorMsg.put("errorBirthday", "生日欄格式錯誤");
				}
			}else{
				errorMsg.put("errorBirthday", "生日欄必須輸入");
			}
		} else {
				errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}
			// 如果有錯誤
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("registerA.jsp");
				rd.forward(request, response);
				return;
			}
			try {
			// 4. 進行Business Logic運算
			// RegisterServiceFile類別的功能：
			// 1.檢查帳號是否已經存在
			// 2.儲存會員的資料 
			RegisterServiceDAO rs = new RegisterServiceDAO_JDBC();  
			if (rs.idExists(memberID)) {
				errorMsg.put("errorIDDup","此代號已存在，請換新代號");
			} else {
					MemberBean mem = new MemberBean(memberID, 
						password, name, tel, email, addr, date);
					// 將MemberBean mem立即寫入Database				
					System.out.println("filename:" + fileName);
					int n = rs.saveMember(mem, is, sizeInBytes, fileName);
					int x = sendMail(email,memberID);
					
					if ( n == 1 ) {
						if(x!=1){
							lg.error("驗證信失敗");
						}
						response.sendRedirect("register_success.jsp");
						return;
					} else {
						errorMsg.put("errorValid","驗證信失敗");
					}
			}
			// 5.依照 Business Logic 運算結果來挑選適當的畫面
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("registerA.jsp");
				rd.forward(request, response);
				return;
			}		
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIDDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("registerA.jsp");
			rd.forward(request, response);
		}		
     }
    
    
    public int  sendMail(String mailAddress,String memberID){
    	int n = -1;
    	String from = "grabngojava@gmail.com";
		List<String> to = Arrays.asList(new String[]{mailAddress});
		String subject = "歡迎加入Grab & Go會員";
		String text = " <table width='600' border='0' align='center' cellpadding='10' cellspacing='0'> "
		        +" <tr> "
		        +"     <td align='center' style='padding: 20px 0;'><img src='http://lovegreenfood.com/gg/logo.png' alt='Garb and Go' width='251' height='54' title='Garb and Go'></td> "
		        +" </tr> "
		        +" <tr> "
		        +"     <td align='center' bgcolor='#EB503C' style=\"font-family:Arial, '微軟正黑體', 'Microsoft YaHei', '新細明體'; color: #ffffff; font-size: 16px;\">Garb and Go 會員註冊確認信</td> "
		        +" </tr> "
		        +" <tr> "
		        +"     <td align='left' style=\"font-family:Arial, '微軟正黑體', 'Microsoft YaHei', '新細明體'; color: #000000; font-size: 16px;\"> "
		        +"         <p>親愛的 Garb and Go 會員您好：</p> "
		        +"         <p>感謝您的註冊，請點擊以下網址完成註冊認證，謝謝！</p> "
		        +"         <p><a href='http://localhost:8080/_Grab_Go/validate.do?mode=1&user=" + memberID+"'>點我認證</a></p> "
		        +"     </td> "
		        +" </tr> "
		        +" <tr> "
		        +"     <td align='left' bgcolor='#f5f5f5' style=\"font-family:Arial, '微軟正黑體', 'Microsoft YaHei', '新細明體'; color: #000000; font-size: 14px;\"> "
		        +"         <p>本e-mail系統通知由系統直接寄發，請勿直接回覆，若您對以上內容有任何問題，歡迎聯絡我們或洽 Garb and Go 客服中心</p> "
		        +"         <p>Copyright © Garb and Go All rights reserved.</p> "
		        +"     </td> "
		        +" </tr> "
		        +" </table>";
		
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