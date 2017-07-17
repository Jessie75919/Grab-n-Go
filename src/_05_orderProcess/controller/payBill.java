package _05_orderProcess.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import _01_register.model.MemberBean;
import _04_shoppingCart.model.ShoppingCart;
import _05_orderProcess.model.OrderBean;
import _05_orderProcess.model.OrderDAO;
import _05_orderProcess.model.OrderItemBean;
import _24_App_storeOrder.cotroller.AppStoreWebSocketServer;

@WebServlet("/_04_ShoppingCart/PayBill.do")
public class payBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(payBill.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		lg.info("hihi");
		
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
				items,tel,eMail,0);

		OrderDAO dao = new OrderDAO();
		int n = dao.insertOrder(ob);
		if (n == 1) {
			System.out.println("訂單新增成功");
			session.removeAttribute("orderRest");
			session.removeAttribute("cart");
			
			//送webSocketMessage至App
//			AppStoreWebSocketServer socketServer = new AppStoreWebSocketServer();
			String messageTitle = "您有一筆新訂單";
			String messageBody = "取餐時間為: " + time;
			Map<String, String> map = new HashMap<>();
			map.put("messageTitle", messageTitle);
			map.put("messageBody", messageBody);
			Gson gson = new Gson();
//			String json = gson.toJson(map);
			String message = gson.toJson(map);
			String restId = String.valueOf(orderRest);
//			socketServer.setMessage(json, restId);
			
//			WebSocketContainer container = 
//					ContainerProvider.getWebSocketContainer();
//			try {
//				Session sess = container.connectToServer(payBill.class, 
//						new URI("ws://10.0.2.2:8080/_Grab_Go/"
//								+ "AppStoreWebSocketServer/" + restId));
//				sess.getAsyncRemote().sendText(message);
//			} catch (DeploymentException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
			
			try {
				WebsocketClientEndpoint clientEndpoint = new WebsocketClientEndpoint(
						new URI("ws://10.0.2.2:8080/_Grab_Go/AppStoreWebSocketServer/" + restId));
				clientEndpoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
					
					@Override
					public void handleMessage(String message) {
						
					}
				});
				clientEndpoint.sendMessage(message);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
			
			
			
			response.sendRedirect(response.encodeRedirectURL("cart_success.jsp"));
		}

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

}
