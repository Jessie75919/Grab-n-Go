package _23_App_StoreOrderAnalysis.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _05_orderProcess.model.OrderItemBean;
import _05_orderProcess.model.OrderItemDAO;

@SuppressWarnings("serial")
@WebServlet("/AppSalesChartsServlet")
public class AppSalesChartsServlet extends HttpServlet {
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
		String interval = jsonObject.get("interval").getAsString();
		String param = jsonObject.get("param").getAsString();
		List<OrderItemBean> list = new ArrayList<>();
		if (param.equals("getSalesChartsData")) {
			String rest_name = jsonObject.get("rest_name").getAsString();
//			String limitCount_s = jsonObject.get("limitCount").getAsString();
//			int limitCount = Integer.parseInt(limitCount_s);
			OrderItemDAO dao = new OrderItemDAO();
			list = dao.getSalesChartsForApp(rest_name, interval);
		}
	
		//將訂單資料送回App
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(list));
		System.out.println("list(" + interval + ") = " + list);
		out.close();
	}

}
