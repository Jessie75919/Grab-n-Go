package _04_ShoppingCart.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;
import _03_listBooks.model.OrderItemBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.model.OrderDAO;
import _04_ShoppingCart.model.OrderItemDAOBean;
import _04_ShoppingCart.model.ShoppingCart;
// OrderConfirm.jsp 呼叫本程式
@WebServlet("/_04_ShoppingCart/ProcessOrder.do")
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		request.setCharacterEncoding("UTF-8");
		
		String finalDecision = request.getParameter("finalDecision");		
		HttpSession session = request.getSession(false);
		if (session == null) {   // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (sc == null) {
			// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		if  (finalDecision.equals("CANCEL")){
			session.removeAttribute("ShoppingCart");
			response.sendRedirect(response.encodeRedirectURL (request.getContextPath()));
			return;  // 一定要記得 return 
		}
		String userId = mb.getMemberId();
		double totalAmount = Math.round(sc.getSubtotal() * 1.05); 
		String shippingAddress = request.getParameter("ShippingAddress");
		String bNO = request.getParameter("BNO");
		String invoiceTitle = request.getParameter("InvoiceTitle");
		Date today = (Date)session.getAttribute("today");
		List<OrderItemDAOBean> items = new ArrayList<OrderItemDAOBean>(); 
		Map<Integer, OrderItemBean> cart = sc.getContent();
		Set<Integer> set = cart.keySet();
		for (Integer k : set) {
			OrderItemBean oib = cart.get(k);
			String description = oib.getCompanyName().substring(0, 2) + " " +  
                         		 // 比較上下兩行的寫法
			                     oib.getAuthor().substring(0, Math.min(3, oib.getAuthor().length())) +  " " +  
			                     oib.getTitle() ;
			OrderItemDAOBean oiDAO = new OrderItemDAOBean(0, 0, oib.getBookID(), description, oib.getQty(), oib.getPrice(), oib.getDiscount());
			items.add(oiDAO);
		}
		// OrderBean:封裝一筆訂單資料的容器(包含訂單主檔與訂單明細檔的資料)
		OrderBean ob = new OrderBean(userId, totalAmount, shippingAddress, 
				bNO, invoiceTitle, today, null, items);  
		OrderDAO order = new OrderDAO();
		order.insertOrder(ob);
		session.removeAttribute("ShoppingCart");
		response.sendRedirect(response.encodeRedirectURL ("../ThanksForOrdering.jsp"));
		} catch(NamingException e) {
			throw new ServletException();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		}			
	}
}