package _24_App_storeOrder;

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

import _05_orderProcess.model.OrderBean;
import _05_orderProcess.model.OrderDAO;

@SuppressWarnings("serial")
@WebServlet("/AppStoreOrderHistoryServlet")
public class AppStoreOrderHistoryServlet extends HttpServlet {
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
		
		String param = jsonObject.get("param").getAsString();
		List<OrderBean> list = new ArrayList<>();
		if (param.equals("HistoryOrder")) {
			int rest_id = Integer.parseInt(jsonObject.get("rest_id").getAsString());
			String selectMonth = jsonObject.get("selectMonth").getAsString();
			String customer = jsonObject.get("customer").getAsString();			
			OrderDAO dao = new OrderDAO();
			dao.setRestId(rest_id);
			dao.setMonthSelect(selectMonth);
			dao.setMPickupName(customer);
			list = dao.getStoreOrdersByMonthForApp();
		}
		
		//將訂單資料送回App
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(list));
		System.out.println("list = " + list);
		out.close();
	}
}
