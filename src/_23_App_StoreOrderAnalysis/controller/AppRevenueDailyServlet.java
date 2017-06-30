package _23_App_StoreOrderAnalysis.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _01_Store_register.model.RestaurantTypeDAO;
import _05_orderProcess.model.OrderBean;

@SuppressWarnings("serial")
@WebServlet("/AppRevenueDailyServlet")
public class AppRevenueDailyServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		//判斷送來的請求為要求revernueDate或是orderInformation
		String param = jsonObject.get("param").getAsString();
		System.out.println("param = " + param);
		Collection<OrderBean> coll = new ArrayList<>();
		if (param.equals("revernueDate")) {
			String rest_name = jsonObject.get("rest_name").getAsString();
//			coll = getOrdersDate(rest_name,,);
			System.out.println("coll = " + coll);
			
			//將訂單日期送回App
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(coll));
			out.close();
		} else if (param.equals("orderInformation")){
			
		}
		
	}

}
