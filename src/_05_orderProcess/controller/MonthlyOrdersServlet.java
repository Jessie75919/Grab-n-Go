package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _05_orderProcess.model.OrderBean;
import _05_orderProcess.model.OrderDAO;


@WebServlet("/MonthlyOrders.json")
public class MonthlyOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//int i = 1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
//		System.out.println("進來了>///<..." + i);
//		try (
//			PrintWriter out = response.getWriter();
//		) {
//
//			out.print("這是doGet()方法送出的訊息. (由Servlet送出回應)");
//			System.out.println("出去了>///<..." + i);
//			i++;
//			return;                                   
//		} catch (UnsupportedEncodingException e) {
//			throw new ServletException(e);
//		}
		request.setCharacterEncoding("UTF-8");
		//response.setContentType("application/json; charset=UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String month = request.getParameter("month");
		System.out.println(id);
		System.out.println(month);
		
		try {
			OrderDAO od = new OrderDAO();
			od.setRestId(Integer.parseInt(id.trim()));
			od.setMonth(Integer.parseInt(month.trim()) + 1);
			Collection<OrderBean> list = od.getStoreOrdersByMonth();
			String monthlyOrdersJson = new Gson().toJson(list);
			System.out.println(monthlyOrdersJson);
			out.println(monthlyOrdersJson);
//			out.write(monthlyOrdersJson);
			out.flush();
		} finally {
			out.close();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
