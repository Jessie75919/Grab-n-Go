﻿package _02_Store_login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import _00_init.GlobalService;
import _00_init.ValidateServlet;
import _01_Store_register.model.StoreBean;
import _01_register.model.RegisterServiceDAO_JDBC;
import _02_Store_login.model.StoreLoginServiceDB;

@WebServlet("/_02_storeLogin/Storelogin.do")
public class StoreLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(StoreLoginServlet.class);
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		System.out.println("I am here");
		
//		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsgMap = new HashMap<String, String>();
//		// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
		request.setAttribute("ErrorMsgKey", errorMsgMap);
//		// 1. 讀取使用者輸入資料
		String userId = request.getParameter("username");
		String password = request.getParameter("password");
//		
		System.out.println("password = "+password);
		String rm = request.getParameter("rememberMe");
		String requestURI = (String) session.getAttribute("requestURI");
//		// 2. 進行必要的資料轉換
//		// 無
//		// 3. 檢查使用者輸入資料
//		// 如果 userId 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
//		if (userId == null || userId.trim().length() == 0) {
//			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
//		}
//		// 如果 password 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
//		if (password == null || password.trim().length() == 0) {
//			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
//		}
//		
//		//  **********Remember Me**************************** 
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
//		
		if (rm != null) {   // rm存放瀏覽器送來之RememberMe的選項
			cookieUser = new Cookie("StoreUser", userId);
			cookieUser.setMaxAge(30*60*60);
			cookieUser.setPath(request.getContextPath());
			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("StorePassword", encodePassword);
			cookiePassword.setMaxAge(30*60*60);
			cookiePassword.setPath(request.getContextPath());
			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(30*60*60);
			cookieRememberMe.setPath(request.getContextPath());
		} else {
			cookieUser = new Cookie("StoreUser", userId);
			cookieUser.setMaxAge(0);   // MaxAge==0 表示要請瀏覽器刪除此Cookie
			cookieUser.setPath(request.getContextPath());
			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("StorePassword", encodePassword);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());
			cookieRememberMe = new Cookie("rm", "false");
			cookieRememberMe.setMaxAge(30*60*60);
			cookieRememberMe.setPath(request.getContextPath());
		}
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);
//		//********************************************
//		// 如果 errorMsgMap 不是空的，表示有錯誤，交棒給login.jsp
//		if (!errorMsgMap.isEmpty()) {
//			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//			rd.forward(request, response);
//			return;
//		}
//		// 4. 進行 Business Logic 運算
//		// 將LoginServiceDB類別new為物件，存放物件參考的變數為 lsdb
		StoreLoginServiceDB slsdb;
		
			slsdb = new StoreLoginServiceDB();
//			// 呼叫 ms物件的 checkIDPassword()，要記得傳入userid與password兩個參數
			password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
			System.out.println("password=" + password);
			
			StoreBean sb = slsdb.checkPW(userId,password);
			
			RegisterServiceDAO_JDBC rsdao = new RegisterServiceDAO_JDBC();
			
			
			if (sb != null) {
//				// OK, 將mb物件放入Session範圍內，識別字串為"LoginOK"
				session.setAttribute("StoreLoginOK", sb);
			} else {
				// NG, userid與密碼的組合錯誤，放一個錯誤訊息到 errorMsgMap 之內
				errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
				RequestDispatcher rd = request.getRequestDispatcher("StoreLogin.jsp");
				rd.forward(request, response);
				return;
			}
			
			if(rsdao.isValidated(userId, 2)==0){
				errorMsgMap.put("validError", "此帳號尚未驗證");
				lg.error("此帳號尚未驗證");
				RequestDispatcher rd = request.getRequestDispatcher("StoreLogin.jsp");
				rd.forward(request, response);
				return;
				
			}
			
//
//		// 5.依照 Business Logic 運算結果來挑選適當的畫面
//		// 如果 errorMsgMap 是空的，表示沒有任何錯誤，交棒給下一棒
		if (errorMsgMap.isEmpty()) {
			
//			if (requestURI != null) {
//				requestURI = (requestURI.length() == 0 ? request
//						.getContextPath() : requestURI);
//				response.sendRedirect(response.encodeRedirectURL(requestURI));
//				return;
//			} else {
//				response.sendRedirect(response.encodeRedirectURL(request
//						.getContextPath()));
//				return;
//			}
//			
			//response.sendRedirect("_storeIndexAfterLogin.jsp");
			response.sendRedirect("_storeIndex.jsp");
				return;
		} else {
			// 如果errorMsgMap不是空的，表示有錯誤，交棒給login.jsp
			RequestDispatcher rd = request.getRequestDispatcher("StoreLogin.jsp");
			rd.forward(request, response);
			return;
		}
	}
}
