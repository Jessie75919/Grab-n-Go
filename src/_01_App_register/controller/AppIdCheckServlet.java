package _01_App_register.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _00_init.GlobalService;
import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;
import _02_Store_login.model.StoreLoginServiceDB;

@SuppressWarnings("serial")
@WebServlet("/AppIdCheckServlet")
public class AppIdCheckServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuffer jsonIn = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		String username = jsonObject.get("username").getAsString();
		String password = jsonObject.get("password").getAsString();
		System.out.println("username = " + username + ", password = " + password);
		
		// 將LoginServiceDB類別new為物件，存放物件參考的變數為 lsdb
		StoreLoginServiceDB slsdb;
		slsdb = new StoreLoginServiceDB();
		
		//將StoreBeanDAO類別new為物件，存放物件參考的變數為 dao
		StoreBeanDAO dao = new StoreBeanDAO();
		
		// 呼叫 ms物件的 checkIDPassword()，要記得傳入userid與password兩個參數
		String pwEncryped = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
					
		StoreBean sb = slsdb.checkPW(username, pwEncryped);
		Map<String, String> map = new HashMap<>();
		PrintWriter out = response.getWriter();
		if (sb != null) {
			map.put("loginCheckMessage", "LoginInformationOK");
			System.out.println("帳號密碼驗證OK");
		} else {
			map.put("loginCheckMessage", "LoginInformationError");
			System.out.println("帳號密碼驗證失敗");
		}
		out.println(gson.toJson(map));
		out.close();
	}
}
