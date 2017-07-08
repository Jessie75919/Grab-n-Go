package _02_App_profile.controller;

import java.io.BufferedReader;
import java.io.IOException;
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

import _00_init.GlobalService;
import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;
import _02_App_login.model.ImageUtil;
import _02_Store_login.model.StoreLoginServiceDB;

@SuppressWarnings("serial")
@WebServlet("/AppStoreProfileServlet")
public class AppStoreProfileServlet extends HttpServlet {
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
		String param = jsonObject.get("param").getAsString();
		String username = jsonObject.get("username").getAsString();
		String password = jsonObject.get("password").getAsString();
		
		// 將LoginServiceDB類別new為物件，存放物件參考的變數為 lsdb
		StoreLoginServiceDB slsdb;
		slsdb = new StoreLoginServiceDB();
		
		//將StoreBeanDAO類別new為物件，存放物件參考的變數為 dao
		StoreBeanDAO dao = new StoreBeanDAO();
		
		// 呼叫 ms物件的 checkIDPassword()，要記得傳入userid與password兩個參數
		String pwEncryped = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
					
		StoreBean sb = slsdb.checkPW(username, pwEncryped);
		Map<String, String> map = new HashMap<>();
		if (sb != null) {
			map.put("loginCheckMessage", "LoginInformationOK");
			System.out.println("帳號密碼驗證OK");
			if (param.equals("getProfile")) {
				System.out.println("開始getProfile");
				String rest_name = sb.getRest_name();
				map.put("rest_name", rest_name);
				String rest_type = sb.getRest_type();
				map.put("rest_type", rest_type);
				String rest_branch = sb.getRest_branch();
				map.put("rest_branch", rest_branch);
				String rest_owner = sb.getRest_owner();
				map.put("rest_owner", rest_owner);
				String rest_address = sb.getRest_address();
				map.put("rest_address", rest_address);
				String rest_phone = sb.getRest_phone();
				map.put("rest_phone", rest_phone);
				String rest_email = sb.getRest_email();
				map.put("rest_email", rest_email);
				String rest_url = sb.getRest_url();
				map.put("rest_url", rest_url);
				
				Blob rest_logo = sb.getRest_logo();
				byte[] logo_byte = ImageUtil.BlobToByteArrayAndAdjustSize(rest_logo, 100);
				String encodedImage = new String(Base64.encodeBase64(logo_byte), "UTF-8");
				System.out.println(encodedImage);
				map.put("rest_logo", encodedImage);
				
			} else if (param.equals("updateProfile")) {
				System.out.println("開始updateProfile");
				String newPassword = jsonObject.get("newPassword").getAsString();
				String newPasswordConfirm = jsonObject.get("newPasswordConfirm").getAsString();
				String address = jsonObject.get("address").getAsString();
				String phone = jsonObject.get("phone").getAsString();
				String email = jsonObject.get("email").getAsString();
				
				String website = jsonObject.get("website").getAsString();
				if(website == null || website.trim().length() == 0){
					website = null;
				}
				
				String latitude_s = jsonObject.get("latitude").getAsString();
				double latitude;
				if(latitude_s == null || latitude_s.trim().length() == 0){
					latitude = 0.0f;
				} else {
					latitude = Double.parseDouble(latitude_s);
				}

				String longitude_s = jsonObject.get("longitude").getAsString();
				double longitude;
				if(longitude_s == null || longitude_s.trim().length() == 0){
					longitude = 0.0f;
				} else {
					longitude = Double.parseDouble(longitude_s);
				}
				
				//紀錄-錯誤
				Map<String, String> msgErr = new HashMap<>();
				//紀錄-正確
				Map<String, String> msgOk = new HashMap<>();
				
				//檢查使用者輸入資料
				if ((newPassword == null || newPassword.trim().length() == 0)
						&& ( newPasswordConfirm == null || newPasswordConfirm.trim().length() == 0)) {
					// " 新密碼 " 及 " 確認新密碼 " 均無資料 -> 不改變密碼
					msgOk.put("newPassword", "OK");
					msgOk.put("newPasswordConfirm", "OK");
				} else if (newPassword.trim().equals(newPasswordConfirm.trim())) {
					// " 新密碼 " 或 " 確認新密碼 " 其中之一有資料，且兩者相符 -> 修改密碼 
					password = newPassword;
					msgOk.put("newPassword", "OK");
					msgOk.put("newPasswordConfirm", "OK");
				} else {
					// " 新密碼 " 或 " 確認新密碼 " 其中之一有資料，但兩者不相符 -> 錯誤
					msgErr.put("newPassword", "新密碼欄必須與確認欄一致");
					msgErr.put("newPasswordConfirm", "新密碼欄必須與確認欄一致");
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
				
				//將資料放進StoreBean
				sb = new StoreBean(address, phone, email, username, 
						password, website, longitude, latitude);
				
				if (!msgErr.isEmpty()) {
					map.put("UpdateProfileMessage", "UpdateProfileError");
					map.putAll(msgErr);
					map.putAll(msgOk);
					
				} else {
					int n = dao.updateShopDataForApp(sb);
					if (n == 1) {	//註冊資料存入資料庫成功
						map.put("UpdateProfileMessage", "UpdateProfileOk");
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
						
						String rest_id = String.valueOf(store.get(0).getRest_id());
						System.out.println(rest_id);
						map.put("rest_id", rest_id);
					} else {
						map.put("UpdateProfileMessage", "UpdateProfileError");
					}
				}
			}
		} else {
			map.put("loginCheckMessage", "LoginInformationError");
			map.put("passwordError", "密碼有誤，請重新輸入密碼");
		}
		
		System.out.println(map);
		
		//送回App
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(map));
		out.close();
		System.out.println(map);
		
		
	}

}
