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

@WebServlet("/SalesRankM.json")
public class SalesRankM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hello, SalesRankDaily.do");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 取日期的值
		String month = request.getParameter("month");
		System.out.println("商家欲查詢的月份為" + month + "+1");
		String restId = request.getParameter("id");
		System.out.println("商家：" + restId);

		try {
			OrderItemDAO oid = new OrderItemDAO();
			System.out.println("ready to get monthly sales rank data!");
			oid.setRest_id(Integer.parseInt(restId.trim()));
			oid.setMonth(Integer.parseInt(month.trim()) + 1);
			Collection<OrderItemBean> list = oid.getSalesRankM();
			String monthlySales = new Gson().toJson(list);
			//System.out.println(monthlySales);
			out.write(monthlySales);
			out.flush();

		} finally {
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
