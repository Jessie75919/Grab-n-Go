package _05_orderProcess.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _05_orderProcess.model.OrderItemBean;
import _05_orderProcess.model.OrderItemDAO;

@WebServlet("/_24_storeOrder/SalesRankDaily.do")
public class SalesRankD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello, SalesRankDaily.do");
		request.setCharacterEncoding("UTF-8");
		
		//取日期的值
		String datepicker = request.getParameter("datepicker");
		System.out.println("商家欲查詢的日期為" + datepicker);
		String restUsername = request.getParameter("restUsername");
		System.out.println("商家：" + restUsername);
		
		try{
			OrderItemDAO od = new OrderItemDAO();
			System.out.println("ready to get daily sales rank data!");
			Collection<OrderItemBean> oib = od.getSalesRankD(restUsername, datepicker);
			od.setOrdPickuptime(datepicker);
			od.setRestUsername(restUsername);
			
			System.out.println(oib);
			
			RequestDispatcher rd = request.getRequestDispatcher("_storeSalesRankD.jsp");
			rd.forward(request, response);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
