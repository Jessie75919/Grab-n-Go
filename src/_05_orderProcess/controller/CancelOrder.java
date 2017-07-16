package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import _05_orderProcess.model.OrderDAO;
@WebServlet("/_02_storeLogin/CancelOrder.do")
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello! CancelOrder.do");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String ordId = request.getParameter("ordId");
		System.out.println("欲取消的訂單：" + ordId);
		
		try{
			OrderDAO od = new OrderDAO();
			System.out.println("ready to cancel order!");
//			od.setOrd_id();
//			Integer orderCancel = od.deleteOrder(Integer.parseInt(ordId.trim()));
//			String ordCancel = new Gson().toJson(orderCancel);
			int n = od.deleteOrder(Integer.parseInt(ordId.trim()));
			if( n == 1 ){
				String result = new Gson().toJson("刪除成功"); 
				
				out.write(result);
				out.flush();
			}else{
				System.out.println("error!!!");
			}
		} finally {
			out.close();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
