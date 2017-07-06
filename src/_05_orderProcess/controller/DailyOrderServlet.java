package _05_orderProcess.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _05_orderProcess.model.OrderItemBean;
import _05_orderProcess.model.OrderItemDAO;

//商家本日訂單營業額
public class DailyOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Hey, daily order analysis!");
		
		String datepicker = "";
		String restUsername= "";
		OrderItemDAO oid = new OrderItemDAO();
		
		//取日期的值
		datepicker = request.getParameter("datepicker");
		System.out.println("商家欲查詢的日期為" + datepicker);
		restUsername = request.getParameter("restUsername");
		System.out.println("商家：" + restUsername);
		if(datepicker.trim().length() != 0){
			oid.setOrdPickuptime(datepicker);
		}
		if(restUsername.trim().length() != 0){
			oid.setRestUsername(restUsername);
		}
		System.out.println("Heyy!");
		Collection<OrderItemBean> coll = oid.getOrderItemsByDate();
		System.out.println("Ready to get sorted order items");
		request.setAttribute("datepicker", coll);
		
		RequestDispatcher rd = request.getRequestDispatcher("/_23_storeOrderAnalysis/_storeOrderAnalysisDay.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
