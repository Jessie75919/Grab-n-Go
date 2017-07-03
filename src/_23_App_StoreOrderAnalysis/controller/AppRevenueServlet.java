package _23_App_StoreOrderAnalysis.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _05_orderProcess.model.OrderBean;
import _05_orderProcess.model.OrderItemBean;
import _05_orderProcess.model.OrderItemDAO;

@SuppressWarnings("serial")
@WebServlet("/AppRevenueServlet")
public class AppRevenueServlet extends HttpServlet {
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
		Collection<OrderItemBean> coll_daily = new ArrayList<>();
		Collection<OrderItemBean> coll_monthly = new ArrayList<>();
		Collection<OrderItemBean> coll_yearly = new ArrayList<>();
		if (param.equals("getOrderData")) {
			String rest_name = jsonObject.get("rest_name").getAsString();
			OrderItemDAO dao = new OrderItemDAO();
			coll_daily = dao.getOrdersItemDataForApp(rest_name, "daily");
			System.out.println("coll_daily = " + gson.toJson(coll_daily));
			coll_monthly = dao.getOrdersItemDataForApp(rest_name, "monthly");
			System.out.println("coll_monthly = " + gson.toJson(coll_monthly));
			coll_yearly = dao.getOrdersItemDataForApp(rest_name, "yearly");
			System.out.println("coll_yearly = " + gson.toJson(coll_yearly));
		}
		String[] sa = {gson.toJson(coll_daily), gson.toJson(coll_monthly), gson.toJson(coll_yearly)};
		//將訂單資料送回App
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(sa));
		System.out.println("gson.toJson(sa) = " + gson.toJson(sa));
		out.close();
	}

}
