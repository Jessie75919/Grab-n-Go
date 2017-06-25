package _01_App_register.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _01_Store_register.model.RestaurantTypeDAO;
import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;
import _02_App_login.model.ImageUtil;

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
			if(branch == null || branch.trim().length() == 0){
				branch = null;
			}
			
			String address = jsonObject.get("address").getAsString();
			String phone = jsonObject.get("phone").getAsString();
			String email = jsonObject.get("email").getAsString();
			String owner = jsonObject.get("owner").getAsString();
			
			String website = jsonObject.get("website").getAsString();
			if(website == null || website.trim().length() == 0){
				website = null;
			}
			
			String latitude_s = jsonObject.get("latitude").getAsString();
			float latitude;
			if(latitude_s == null || latitude_s.trim().length() == 0){
				latitude = 0.0f;
			} else {
				latitude = Float.parseFloat(latitude_s);
			}
			
			String longitude_s = jsonObject.get("longitude").getAsString();
			float longitude;
			if(longitude_s == null || longitude_s.trim().length() == 0){
				longitude = 0.0f;
			} else {
				longitude = Float.parseFloat(longitude_s);
			}
			
			
			//紀錄-錯誤
			Map<String, String> msgErr = new HashMap<>();
			//紀錄-正確
			Map<String, String> msgOk = new HashMap<>();
			
			
			//放置預設的圖片
			InputStream[] is = new InputStream[3]; 
					
				long size[] = new long[3];	
				is[0] =	new FileInputStream("C:\\_JSP\\GitHub_Root\\GrabAndGo\\WebContent\\images\\restImage\\test_mBanner.jpg");
				size[0] = is[0].available();
				is[1] =	new FileInputStream("C:\\_JSP\\GitHub_Root\\GrabAndGo\\WebContent\\images\\restImage\\test_logo.JPG");
				size[1] = is[1].available();
				is[2] =	new FileInputStream("C:\\_JSP\\GitHub_Root\\GrabAndGo\\WebContent\\images\\restImage\\test_coverImg.JPG");
				size[2] = is[2].available();
				
			
			//將資料放進StoreBean
			StoreBean sb = new StoreBean(restType, storeName, branch, address, 
					phone, owner, email, username, password, website, longitude,
					latitude);
			
			//將StoreBeanDAO類別new為物件，存放物件參考的變數為 dao
			StoreBeanDAO dao = new StoreBeanDAO();
			
			
			//檢查使用者輸入資料
			if (username == null || username.trim().length() == 0) {
				msgErr.put("username", "帳號欄必須輸入");
			} else if (dao.isUserExists(username)){
				msgErr.put("username", "帳號已使用");
			} else {
				msgOk.put("username", "OK");
			}
			if (password == null || password.trim().length() == 0) {
				msgErr.put("password", "密碼欄必須輸入");
			} else {
				msgOk.put("password", "OK");
			}
			if (passwordConfirm == null || passwordConfirm.trim().length() == 0) {
				msgErr.put("passwordConfirm", "密碼確認欄必須輸入");
			} else if (!password.trim().equals(passwordConfirm.trim())) {
				msgErr.put("passwordConfirm", "密碼欄必須與確認欄一致");
			} else {
				msgOk.put("passwordConfirm", "OK");
			}
			if (storeName == null || storeName.trim().length() == 0) {
				msgErr.put("storeName", "店家名稱欄必須輸入");
			} else {
				msgOk.put("storeName", "OK");
			}
			if (address == null || address.trim().length() == 0) {
				msgErr.put("address", "地址欄必須輸入");
			} else {
				msgOk.put("address", "OK");
			}
			if (phone == null || phone.trim().length() == 0) {
				msgErr.put("phone", "電話號碼欄必須輸入");
			} else {
				msgOk.put("phone", "OK");
			}
			if (email == null || email.trim().length() == 0) {
				msgErr.put("email", "電子郵件欄必須輸入");
			} else {
				msgOk.put("email", "OK");
			}
			if (owner == null || owner.trim().length() == 0) {
				msgErr.put("owner", "商家負責人欄必須輸入");
			} else {
				msgOk.put("owner", "OK");
			}
			
			//設定回傳資料
			Map<String, String> map = new HashMap<>();
			if (!msgErr.isEmpty()) {
				map.put("RegisterMessage", "RegisterError");
				map.putAll(msgErr);
				map.putAll(msgOk);
				
			} else {
				int n = dao.insertShopData(sb,is[0],is[1],is[2],size[0],size[1],size[2]);
				if (n == 1) {	//註冊資料存入資料庫成功
					map.put("RegisterMessage", "RegisterOk");
					List<StoreBean> store = dao.getNameBranchLogoValidate(username);
					String rest_name = store.get(0).getRest_name();
					map.put("rest_name", rest_name);
					String rest_branch = store.get(0).getRest_branch();
					map.put("rest_branch", rest_branch);
					System.out.println(rest_branch);
					
					Blob rest_logo = store.get(0).getRest_logo();
					byte[] logo_byte = ImageUtil.BlobToByteArrayAndAdjustSize(rest_logo, 100);
					String encodedImage = new String(Base64.encodeBase64(logo_byte), "UTF-8");
					System.out.println(encodedImage);
					map.put("rest_logo", encodedImage);
					
					String rest_validate = String.valueOf(store.get(0).isRest_validate());
					System.out.println(rest_validate);
					map.put("rest_validate", rest_validate);
				}
			}
			System.out.println(map);
			System.out.println(msgErr);
			System.out.println(msgOk);
			
			//送回App
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(map));
			out.close();
			
		}

	}

}
