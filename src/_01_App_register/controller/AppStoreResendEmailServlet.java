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
import _01_register.controller.SendValidMail;
import _02_Store_login.model.StoreLoginServiceDB;

@SuppressWarnings("serial")
@WebServlet("/AppStoreResendEmailServlet")
public class AppStoreResendEmailServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 解開從App取得的Gson
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuffer jsonIn = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("jsonIn = " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String param = jsonObject.get("param").getAsString();
		System.out.println("param = " + param);
		String username = jsonObject.get("username").getAsString();
		String password = jsonObject.get("password").getAsString();

		// 將LoginServiceDB類別new為物件，存放物件參考的變數為 lsdb
		StoreLoginServiceDB slsdb;
		slsdb = new StoreLoginServiceDB();
		
		//設定回傳資料
		Map<String, String> map = new HashMap<>();
		
		//checkPW
		String pwEncryped = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
		StoreBean sb = slsdb.checkPW(username, pwEncryped);
		if (sb != null) {
			map.put("loginCheckMessage", "LoginInformationOK");
			System.out.println("帳號密碼驗證OK");
			
			String rest_email = sb.getRest_email();
			map.put("rest_email", rest_email);
			
			//寄送驗證信
			SendValidMail sendValidMail = new SendValidMail();
			int n = sendValidMail.sendMail(rest_email, username, 2);
			map.put("resendEmail", String.valueOf(n));
		}
		
		// 將餐廳類別送回App
		 PrintWriter out = response.getWriter();
		 out.println(gson.toJson(map));
		 out.close();
	}

}
