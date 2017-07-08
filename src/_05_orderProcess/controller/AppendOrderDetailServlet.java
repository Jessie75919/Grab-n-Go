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

/**
 * Servlet implementation class AppendText
 */
@WebServlet("/AppendOrderDetail.json")
public class AppendOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int i = 1;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html; charset=UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		String id = request.getParameter("id");
		System.out.println("被按到的訂單編號是: " + id);
		PrintWriter out = response.getWriter();
		
		try {
			OrderItemDAO oid = new OrderItemDAO();
			oid.setOrd_id(Integer.parseInt(id.trim()));
			Collection<OrderItemBean> list = oid.getOrderDetailsForStore();
			String orderDetails = new Gson().toJson(list);
			//out.print("<table border='1'><tr><th>餐點名稱</th><th>餐點編號</th><th>備註</th><th>數量</th><th>單價</th><th>Subtotal</th></tr></table>");
			out.write(orderDetails);
			out.flush();
		} finally {
			out.close();
		}
	}

}
