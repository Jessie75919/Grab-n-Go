package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;
import _04_shoppingCart.model.ShoppingCart;
import _05_orderProcess.model.OrderBean;
import _05_orderProcess.model.OrderDAO;
import _05_orderProcess.model.OrderItemBean;

@WebServlet("/_04_ShoppingCart/PayBill.do")
public class payBill extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("ModifyOrderItem.do!!!!");
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼

		HttpSession session = request.getSession(false);

		// validate session
		if (session == null) { // 使用逾時
			request.setAttribute("Errors", "使用逾時，請重新登入(BuyBookServlet:SessionTimeOut)");
			RequestDispatcher rd = request.getRequestDispatcher("/_02_login/login.jsp");
			rd.forward(request, response);
			return;
		}

		// validate loggin
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/_02_login/login.jsp");
			rd.forward(request, response);
			return;
		}

		// validate shoppingCart
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		if (shoppingCart == null) {
			// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行 ，導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/indexA.jsp");
			return;
		}
		int total = shoppingCart.getSubtotal();
//		System.out.println("total = " + total);

		// get the OrderRestaurant
		int orderRest = Integer.parseInt((String) session.getAttribute("orderRest"));
//		System.out.println("orderRest = " + orderRest);

		// get the info of buyer
		String pickName = request.getParameter("name").trim();
		String tel = request.getParameter("tel").trim();
		String eMail = request.getParameter("eMail").trim();
		String time = request.getParameter("time").trim() + ":00";
//		System.out.println("name= " + pickName);
//		System.out.println("tel= " + tel);
//		System.out.println("eMail= " + eMail);
//		System.out.println("time= " + time);

		List<OrderItemBean> items = shoppingCart.getListAllMap();

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd ");

		Date d = new Date();
//		System.out.println(d);
		Timestamp orderTime = Timestamp.valueOf(sdf1.format(d));
//		System.out.println("orderTime= " + orderTime);
//		System.out.println("sdf2.format(d) = " + sdf2.format(d));

		Timestamp pickTime = Timestamp.valueOf(sdf2.format(d).concat(time));
//		System.out.println("pickTime= " + pickTime);

		String status = "inprogress";
		OrderBean ob = new OrderBean(mb.getMemberId(), pickName, orderTime, pickTime, orderRest, total, status,
				items,tel,eMail);

		OrderDAO dao = new OrderDAO();
		int n = dao.insertOrder(ob);
		if (n == 1) {
			System.out.println("訂單新增成功");
			session.removeAttribute("orderRest");
			session.removeAttribute("cart");
			
			response.sendRedirect(response.encodeRedirectURL("cart_success.jsp"));
		}

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

}
