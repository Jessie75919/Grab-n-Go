package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _05_orderProcess.model.OrderBean;
import _05_orderProcess.model.OrderDAO;

@WebServlet("/StoreOrder.do")
public class StoreOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello! StoreOrder.do ");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String restUsername = request.getParameter("restUsername");
		System.out.println("欲取得訂單的商家:" + restUsername);
		String ordStatus = request.getParameter("ordInProgress");
		System.out.println("商家所在訂單狀態:" + ordStatus);
		
		String status1 = "inprogress";
		String status2 = "unpaid";
		String status3 = "paid";
		
		try {
			OrderDAO od = new OrderDAO();
			Collection<OrderBean> list = od.getStoreOrders(ordStatus);
			String storeOrdersJson = new Gson().toJson(list);
			System.out.println(storeOrdersJson);
			//out.println(monthlyOrdersJson);
			out.write(storeOrdersJson);
			out.flush();
		} finally {
			out.close();
		}
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
