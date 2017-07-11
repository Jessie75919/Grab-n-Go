package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _05_orderProcess.model.OrderBean;
import _05_orderProcess.model.OrderDAO;

//商家帳務分析 - 月營業額統計
@WebServlet("/MonthlyRevenue.do")
public class MonthlyAnalysisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		System.out.println("Hello! MonthlyRevenue.do");
		PrintWriter out = response.getWriter();
		String restUsername = request.getParameter("restUsername");
		System.out.println("欲查詢的商家：" + restUsername);
		String month = request.getParameter("month");
		System.out.println("欲查詢的月份: " + month + "+1");
		
		try {
			OrderDAO od = new OrderDAO();
			od.setRestUsername(restUsername);
			od.setMonthSelect(month);
			Collection<OrderBean> list = od.getMonthlyStoreRevenue();
			
			int count = list.size();
			for(int i=0;i<(30-count);i++){
				OrderBean ob = new OrderBean();
				Timestamp ts = new Timestamp(System.currentTimeMillis()); 
				String tsStr = "2017-06-09 11:49:45";  
			        try {  
			            ts = Timestamp.valueOf(tsStr);  
			            System.out.println(ts);  
			        } catch (Exception e) {  
			            e.printStackTrace();  
			        }  
				
				ob.setOrd_pickuptime(ts);
				list.add(ob);
			}
			
			od.setRestUsername(restUsername);
			od.setMonth(Integer.parseInt(month.trim()) + 1);
			String monthlyRevenueList = new Gson().toJson(list);
			System.out.println(monthlyRevenueList);
			
			out.write(monthlyRevenueList);
			out.flush();
		} finally {
			out.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
