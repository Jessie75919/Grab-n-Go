package _05_orderProcess.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _05_orderProcess.model.OrderBean;
import _05_orderProcess.model.OrderDAO;

//商家本日訂單搜尋
@WebServlet("/_02_storeLogin/SearchOrder.do")
public class SearchOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Hello! Search Order~~");
		// 取餐顧客
		String mPickupName = "";
		String restUsername = "";
		OrderDAO od = new OrderDAO();

		// 取得查詢字串的值
		mPickupName = request.getParameter("mPickupName");
		System.out.println("商家輸入顧客姓名：" + mPickupName);
		restUsername = request.getParameter("restUsername");
		System.out.println(restUsername);

		if (mPickupName.trim().length() != 0) {
			od.setMPickupName(mPickupName);
		}
		if(restUsername.trim().length() != 0){
			od.setRestUsername(restUsername);
		}

		System.out.println("Hello!");
		// 取得商家訂單
		Collection<OrderBean> coll = od.getStoreOrdersByMpickupName();
		System.out.println("ready to get order search result!");
		request.setAttribute("mPickupName", coll);
		
		RequestDispatcher rd = request.getRequestDispatcher("/_24_storeOrder/_storeOrderSearchResult.jsp?");
		rd.forward(request, response);

		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
