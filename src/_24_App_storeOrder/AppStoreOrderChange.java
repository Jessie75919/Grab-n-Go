package _24_App_storeOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _05_orderProcess.model.OrderDAO;

@SuppressWarnings("serial")
@WebServlet("/AppStoreOrderChange")
public class AppStoreOrderChange extends HttpServlet {
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
		int ord_id = Integer.parseInt(jsonObject.get("ord_id").getAsString());
		OrderDAO dao = new OrderDAO();
		int n = -1;
		if (param.equals("Cancel")) {
			n = dao.deleteOrder(ord_id);
		} else if (param.equals("toComplete")) {
			String changeStatus = "unpaid";
			n = dao.updateOrderStatus(changeStatus, ord_id);
		}
		
		String changeResult = "";
		if (n == 1) {
			changeResult = "ok";
		} else if (n == -1) {
			changeResult = "fail";
		}
		
		//將變動結果送回App
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(changeResult));
		System.out.println("changeResult = " + changeResult);
		out.close();
	}

}
