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
		System.out.println(1);
		String memberID = request.getParameter("mid");
		System.out.println(2);
		String password = request.getParameter("password");
		System.out.println(3);
		String StoreName = request.getParameter("StoreName");
		System.out.println(4);
		System.out.println("StoreName = " + StoreName);
		System.out.println(5);
		String type = request.getParameter("StoreType");
		System.out.println(6);
		System.out.println("type = " + type);
		System.out.println(7);
		String branch = request.getParameter("branch");
		System.out.println(8);
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
		System.out.println(9);
		Map<String, String> msgErr = new HashMap<>();
		System.out.println(10);
		Map<String, String> msgOK = new HashMap<>();
		

		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要 session物件來存放共用資料。
		System.out.println(11);
		request.setAttribute("msgErr", msgErr); // 顯示錯誤訊息
		System.out.println(12);
		request.setAttribute("msgOK", msgOK); // 顯示正常訊息

		// 2. 進行必要的資料轉換
		// All data are String

		// 3. 檢查使用者輸入資料
		// the validation of input are checked by Javascript

		try {
			System.out.println(13);
			StoreBeanDAO sbdao = new StoreBeanDAO();
			System.out.println(14);
			if (sbdao.isUserExists(memberID)) {
				System.out.println("15-a");
				msgErr.put("idRepeat", "此帳號已存在，請換新帳號");
			} else {
				System.out.println("15-a-1");
				StoreBean sb = new StoreBean(type, StoreName, branch, addr, tel, owner, email, memberID, password, url,
						langitude, latitude);

				System.out.println("15-a-2");
				int n = sbdao.insertShopData(sb);
				System.out.println("15-a-3");
				if (n == 1) {
					System.out.println("15-a-3-1");
					msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
					System.out.println("15-a-3-2");
					System.out.println(StoreName + " add successfully ,n= " +n );

					response.sendRedirect("_storeRegisterSuccessful.jsp");
				}
				 else{
					 System.out.println("15-b-1");
					 System.out.println("Insert doesn't work properly");
					 System.out.println("15-b-2");
					 msgErr.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
				 }
			}
			System.out.println(16);
			for (String key : msgErr.keySet()) {
				System.out.println(key + ", " + msgErr.get(key));
			}

			 if (!msgErr.isEmpty()) {
				 System.out.println(17);
			// // 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				 System.out.println(18);
			 RequestDispatcher rd =
			 request.getRequestDispatcher("_storeRegister.jsp");
			 System.out.println(19);
			 rd.forward(request, response);
			 System.out.println(20);
			 	return;
			 }

		} catch (Exception e) {
			System.out.println(21);
			e.printStackTrace();
			System.out.println(22);
			msgErr.put("errorIDDup", e.getMessage());
			System.out.println(23);
			RequestDispatcher rd = request.getRequestDispatcher("_storeRegister.jsp");
			System.out.println(24);
			rd.forward(request, response);
			return;
		}
	}
	

}
