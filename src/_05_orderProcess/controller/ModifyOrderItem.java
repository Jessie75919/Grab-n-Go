package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import _03_Product.model.ProductDAO;
import _04_shoppingCart.model.ShoppingCart;

@WebServlet("/_04_ShoppingCart/ModifyOrderItem.do")
public class ModifyOrderItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("ModifyOrderItem.do!!!!");
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼

		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if (session == null) { // 使用逾時
			request.setAttribute("Errors", "使用逾時，請重新登入(BuyBookServlet:SessionTimeOut)");
			RequestDispatcher rd = request.getRequestDispatcher("/_02_login/login.jsp");
			rd.forward(request, response);
			return;
		}
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (shoppingCart == null) {
			// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行 ，導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/indexA.jsp");
			return;
		}
		String cmd = request.getParameter("cmd").trim();
		String proId = request.getParameter("proId").trim();
		String itemNote = request.getParameter("itemNote");
		System.out.println("cmd= " + cmd);
		System.out.println("proId= " + proId);
		System.out.println("itemNote= " + itemNote);
		if (itemNote == null) {
			itemNote = "";
		}

		if (cmd.equalsIgnoreCase("del")) {
			try {
				int n = shoppingCart.deleteItem(Integer.parseInt(proId), itemNote);
				if (n == 1) {
					System.out.println("刪除成功");
					String result = new Gson().toJson("刪除成功");
					out.write(result);
					out.flush();
				} else {
					System.out.println("Something gets wrong");
				}
			} finally {
				out.close();
			}
		} else if (cmd.equalsIgnoreCase("modAcount")) {
			String count = request.getParameter("count");

			int n = shoppingCart.modifyAmount(Integer.parseInt(proId), itemNote, Integer.parseInt(count));
			if (n == 1) {
				System.out.println("修改成功");
			} else {
				System.out.println("Something gets wrong");
			}
		} else if (cmd.equalsIgnoreCase("modNote")) {
			String note = request.getParameter("newNote");

			int n = shoppingCart.modifyNote(Integer.parseInt(proId), itemNote, note);
			if (n == 1) {
				System.out.println("修改成功");
			} else {
				System.out.println("Something gets wrong");
			}
		} else if(cmd.equalsIgnoreCase("delOrderRest")){
			session.removeAttribute("orderRest");
			session.removeAttribute("ShoppingCart");
			
		}

		//
	}

}
