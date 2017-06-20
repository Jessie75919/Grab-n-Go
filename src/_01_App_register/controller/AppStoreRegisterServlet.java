package _01_App_register.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _01_App_register.model.RestaurantTypeDAO;
import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;

@SuppressWarnings("serial")
@WebServlet("/AppStoreRegisterServlet")
public class AppStoreRegisterServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	private List<String> list;
	
	@Override
	public void init() throws ServletException {
		super.init();
		RestaurantTypeDAO dao = new RestaurantTypeDAO();
		list = dao.getRestaurantType();
		System.out.println("init : list = " + list);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType(CONTENT_TYPE);

		//解開從App取得的Gson
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuffer jsonIn = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("jsonIn = " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		
		//判斷送來的請求為要求category或是signUp
		String param = jsonObject.get("param").getAsString();
		System.out.println("param = " + param);
		if (param.equals("category")) {
			//將餐廳類別送回App
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(list));
			out.close();
		} else if (param.equals("signUp")){
			String username = jsonObject.get("username").getAsString();
			String password = jsonObject.get("password").getAsString();
			String passwordConfirm = jsonObject.get("passwordConfirm").getAsString();
			String storeName = jsonObject.get("storeName").getAsString();
			String restType = jsonObject.get("restType").getAsString();
			String branch = jsonObject.get("branch").getAsString();
			String address = jsonObject.get("address").getAsString();
			String phone = jsonObject.get("phone").getAsString();
			String email = jsonObject.get("email").getAsString();
			String owner = jsonObject.get("owner").getAsString();
			String website = jsonObject.get("website").getAsString();
			float latitude = Float.parseFloat(jsonObject.get("latitude").getAsString());
			float longitude = Float.parseFloat(jsonObject.get("longitude").getAsString());

			System.out.println("latitude: " + latitude);
			System.out.println("longitude: " + longitude);
			System.out.println("latitude: " + latitude);
			System.out.println("longitude: " + longitude);
			
			
			//紀錄-錯誤
			Map<String, String> msgErr = new HashMap<>();
			
			//將資料放進StoreBean
			StoreBean sb = new StoreBean(restType, storeName, branch, address, 
					phone, owner, email, username, password, website, longitude, latitude);
			
			
			//將StoreBeanDAO類別new為物件，存放物件參考的變數為 dao
			StoreBeanDAO dao = new StoreBeanDAO();
			
			
			//檢查使用者輸入資料
			if (username == null || username.trim().length() == 0) {
				msgErr.put("username", "帳號欄必須輸入");
			} else if (dao.isUserExists(username)){
				msgErr.put("username", "帳號已使用");
			}
			if (password == null || password.trim().length() == 0) {
				msgErr.put("password", "密碼欄必須輸入");
			}
			if (passwordConfirm == null || passwordConfirm.trim().length() == 0) {
				msgErr.put("passwordConfirm", "密碼確認欄必須輸入");
			} else if (!password.trim().equals(passwordConfirm.trim())) {
				msgErr.put("passwordConfirm", "密碼欄必須與確認欄一致");
			}
			if (storeName == null || storeName.trim().length() == 0) {
				msgErr.put("storeName", "店家名稱欄必須輸入");
			}
			if (address == null || address.trim().length() == 0) {
				msgErr.put("address", "地址欄必須輸入");
			}
			if (phone == null || phone.trim().length() == 0) {
				msgErr.put("phone", "電話號碼欄必須輸入");
			}
			if (email == null || email.trim().length() == 0) {
				msgErr.put("email", "電子郵件欄必須輸入");
			}
			if (owner == null || owner.trim().length() == 0) {
				msgErr.put("owner", "商家負責人欄必須輸入");
			}
			
			//設定回傳資料
			Map<String, String> map = new HashMap<>();
			if (!msgErr.isEmpty()) {
				map = msgErr;
			} else {
				int n = dao.insertShopData(sb);
				if (n == 1) {
					map.put("RegisterMessage", "RegisterOk");
				}
			}
			
			//送回App
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(map));
			out.close();
			
		}

	}

}
