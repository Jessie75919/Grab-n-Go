package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _05_orderProcess.model.OrderDAO;

@WebServlet("/updateOrderStatus.do")
public class UpdateOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hello! updateOrderStatus.do");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String ordId = request.getParameter("ordId");
		String ordStatus = request.getParameter("ordStatus");
		
		String status1 = "inprogress";
		String status2 = "unpaid";
		String status3 = "paid";

		System.out.println("商家選取的訂單編號：" + ordId);

		try {
			OrderDAO od = new OrderDAO();
			System.out.println("ready to update order status!");

			if (ordStatus.equalsIgnoreCase(status1)) {
				int n = od.updateOrderStatus(status2, Integer.parseInt(ordId.trim()));
				System.out.println(n);
				
			} else if (ordStatus.equalsIgnoreCase(status2)) {
				int n = od.updateOrderStatus(status3, Integer.parseInt(ordId.trim()));
				System.out.println(n);
				
				if (n == 1) {
					String result = new Gson().toJson("更新成功");
					out.write(result);
					out.flush();
				} else {
					System.out.println("error!!!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
