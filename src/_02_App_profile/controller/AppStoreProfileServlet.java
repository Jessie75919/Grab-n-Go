package _02_App_profile.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.HashMap;
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
		if (param.equals("getProfile")) {
			// 將LoginServiceDB類別new為物件，存放物件參考的變數為 lsdb
			StoreLoginServiceDB slsdb;
			slsdb = new StoreLoginServiceDB();
//			// 呼叫 ms物件的 checkIDPassword()，要記得傳入userid與password兩個參數
			password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
			
			StoreBean sb = slsdb.checkPW(username, password);
			Map<String, String> map = new HashMap<>();
			if (sb != null) {
				map.put("loginCheckMessage", "LoginInformationOK");
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
			} else {
				map.put("loginCheckMessage", "LoginInformationError");
			}
			//送回App
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(map));
			out.close();
			System.out.println(map);
		}
		
		
	}

}
