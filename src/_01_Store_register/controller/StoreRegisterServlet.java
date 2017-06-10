package _01_Store_register.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import _00_init.GlobalService;

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
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/_01_Store_register/StoreRegister.do")
public class StoreRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("I heard ! ");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("I heard !!!" );
		Gson gson = new Gson();
		String lan = request.getParameter("langitude");
		System.out.println("lan = "+ lan);
		
		// 準備存放錯誤訊息的Map物件
		// 準備存放註冊成功之訊息的Map物件
		Map<String,String> msgErr = new HashMap<>();
		Map<String,String> msgOK = new HashMap<>();
		
		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要 session物件來存放共用資料。
		HttpSession session =  request.getSession(); 
		request.setAttribute("msgErr", msgErr); // 顯示錯誤訊息
		request.setAttribute("msgOK", msgOK); // 顯示正常訊息
		
		String memberID = "";
		String password = "";
		String StoreName = "";
		String branch ="";
		String addr = "";
		String tel = "";
		String email = "";
		String owner = "";
		String url = "";
		float langitude = 0;
		float latitude = 0;
		// 上傳圖片
		/*// mainBanner ------------------
		String mb_fileName = "";
		long mb_sizeInBytes = 0;
		InputStream mb_is = null;
		// logo ------------------		
		String lg_fileName = "";
		long lg_sizeInBytes = 0;
		InputStream lg_is = null;
		// coverImage ------------------
		String cv_fileName = "";
		long cv_sizeInBytes = 0;
		InputStream cv_is = null;*/
		
		Collection<Part> parts = request.getParts(); // 取出HTTP multipart
//														// request內所有的parts
		GlobalService.exploreParts(parts, request);
		
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				out.println(fldName );
				out.println(value );
				out.println("------------------");
				// 1. 讀取使用者輸入資料
				if (p.getContentType() == null) {
					if (fldName.equals("mid"))
						memberID = value;
					else if (fldName.equals("password"))
						password = value;
					else if (fldName.equalsIgnoreCase("StoreName"))
						StoreName = value;
					else if (fldName.equalsIgnoreCase("branch"))
						branch = value;
					else if (fldName.equalsIgnoreCase("StoreName"))
						StoreName = value;
					else if (fldName.equalsIgnoreCase("address"))
						addr = value;
					else if (fldName.equalsIgnoreCase("tel"))
						tel = value;
					else if (fldName.equalsIgnoreCase("eMail"))
						email = value;
					else if (fldName.equalsIgnoreCase("owner"))
						owner = value;
					else if (fldName.equalsIgnoreCase("url"))
						url = value;
					else if (fldName.equalsIgnoreCase("langitude"))
						langitude = Float.parseFloat(value);
					else if (fldName.equalsIgnoreCase("latitude"))
						latitude = Float.parseFloat(value);
				}
				/* jc : maybe don't ask Image of restaurant in registration.
				 * 
				  else {
					 mb_fileName = GlobalService.getFileName(p); // 此為圖片檔的檔名
					 mb_fileName = GlobalService.adjustFileName(mb_fileName,
					 GlobalService.IMAGE_FILENAME_LENGTH);
					 if (mb_fileName != null && mb_fileName.trim().length() > 0) {
						 mb_sizeInBytes = p.getSize();
						 mb_is = p.getInputStream();
					 } 
				}*/
			}
		}
			// 2. 進行必要的資料轉換
			// All data are String 
	
	
			// 3. 檢查使用者輸入資料
			// the validation of input are checked by Javascript			
	
	
//		try {
//			// 4. 進行Business Logic運算
//			// RegisterServiceFile類別的功能：
//			// 1.檢查帳號是否已經存在
//			// 2.儲存會員的資料
		
			
		
//			RegisterServiceDAO rs = new RegisterServiceDAO_JDBC();
//			if (rs.idExists(memberID)) {
//				errorMsg.put("errorIDDup", "此代號已存在，請換新代號");
//			} else {
//				MemberBean mem = new MemberBean(memberID, name, password, addr, email, tel, experience);
//				// 將MemberBean mem立即寫入Database
//				System.out.println("filename:" + fileName);
//				int n = rs.saveMember(mem, is, sizeInBytes, fileName);
//				if (n == 1) {
//					msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
//					response.sendRedirect("../index.jsp");
//					return;
//				} else {
//					errorMsg.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
//				}
//			}
//			// 5.依照 Business Logic 運算結果來挑選適當的畫面
//			if (!errorMsg.isEmpty()) {
//				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
//				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
//				rd.forward(request, response);
//				return;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			errorMsg.put("errorIDDup", e.getMessage());
//			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
//			rd.forward(request, response);
//		}
	}
}