package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/YearRevenue.do")
public class YearAnalysisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		System.out.println("Hello! YearRevenue.do");
		PrintWriter out = response.getWriter();
		String restUsername = request.getParameter("restUsername");
		System.out.println("欲查詢的商家：" + restUsername);
		String year = request.getParameter("yearVal");
		System.out.println("商家欲查詢的年度：" + year);
		
		try{
			OrderDAO od = new OrderDAO();
			od.setRestUsername(restUsername);
			od.setYearSelect(year);
			Collection<OrderBean> coll = od.getStoreYearRevenue();
			
//			//取得所選年的月份數  
			Calendar cal =   Calendar.getInstance();     
			cal.set(Calendar.YEAR,Integer.valueOf(year));			//年份 
			int maxMonth = cal.getActualMaximum(Calendar.MONTH) + 1;		//當月天數  
			System.out.println("取得月份數 = " + maxMonth);
			
			int count = coll.size();
			System.out.println("資料庫取得的筆數：" + count);
			for(int i= 0; i< maxMonth-count ; i++){
				OrderBean ob = new OrderBean();
				if(ob.getOrdPickuptime() == null){
				ob.getOrdPickuptime();	
				ob.setOrd_totalPrice(0);
				}
				
				coll.add(ob);
			}
			
			String yearRevenueList = new Gson().toJson(coll);
			System.out.println(yearRevenueList);
			
			out.write(yearRevenueList);
			out.flush();
			
		}finally{
			out.close();
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
