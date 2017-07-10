package _01_Store_register.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;

@WebServlet("/_01_StoreRegister/StoreRegister.do")
public class StoreRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
				StoreBean sb = new StoreBean(type, StoreName, branch, addr, tel, owner, email, memberID, password, url,
						langitude, latitude);

				int n = sbdao.insertShopData(sb);
				if (n == 1) {
					msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
					System.out.println(StoreName + " add successfully ,n= " +n );

					response.sendRedirect("_storeRegisterSuccessful.jsp");
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
	

}
