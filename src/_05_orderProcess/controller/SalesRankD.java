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

import _05_orderProcess.model.OrderItemBean;
import _05_orderProcess.model.OrderItemDAO;

@WebServlet("/SalesRankD.json")
public class SalesRankD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hello, SalesRankDaily.do");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 取日期的值
		String datepicker = request.getParameter("date");
		System.out.println("商家欲查詢的日期為" + datepicker);
		String restId = request.getParameter("id");
		System.out.println("商家：" + restId);

		try {
			OrderItemDAO oid = new OrderItemDAO();
			System.out.println("ready to get daily sales rank data!");
			oid.setRest_id(Integer.parseInt(restId.trim()));
			oid.setOrdPickuptime(datepicker);
			Collection<OrderItemBean> list = oid.getSalesRankD();
			String dailySales = new Gson().toJson(list);
			System.out.println(dailySales);
			out.write(dailySales);
			out.flush();

		} finally {
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
