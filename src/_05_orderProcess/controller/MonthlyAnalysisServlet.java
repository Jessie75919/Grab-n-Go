package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;
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
		System.out.println("欲查詢的月份+年的字串: " + month);
		String year = request.getParameter("year");
		System.out.println("欲查詢的年份：" + year);
		
		try {
			OrderDAO od = new OrderDAO();
			od.setRestUsername(restUsername);
			od.setMonthSelect(month);
			//od.setMonth(Integer.parseInt(month.trim()) + 1);
			//od.setMonthSelect(month);
			Collection<OrderBean> list = od.getMonthlyStoreRevenue();
			
			//取得所選月份天數  
			Calendar cal =   Calendar.getInstance();     
			cal.set(Calendar.YEAR,Integer.valueOf(year));			//年份 
			cal.set(Calendar.MONTH,Integer.valueOf(month)); 			//設定月  
			int maxDate = cal.getActualMaximum(Calendar.DATE);		//當月天數  
			System.out.println(maxDate);
			int count = list.size();
			
			for(int i=0; i<maxDate-count; i++){
				OrderBean ob = new OrderBean();
				if( ob.getOrd_totalPrice() == 0 ){
					ob.getOrd_pickuptime();
				}
				ob.getOrd_pickuptime();
				list.add(ob);
				
			}

//			for(int i=0;i<(maxDate-count);i++){
//				OrderBean ob = new OrderBean();
////				Timestamp ts = new Timestamp(System.currentTimeMillis()); 
////				//String tsStr = "";  
////				//ob.setOrd_totalPrice(0);
////			        try {  
////			            ts = Timestamp.valueOf(String.valueOf(maxDate));  
////			            System.out.println(ts);  
////			        } catch (Exception e) {  
////			            e.printStackTrace();  
////			        }  
//				
//				ob.getOrd_pickuptime();
//				list.add(ob);
//			}
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
