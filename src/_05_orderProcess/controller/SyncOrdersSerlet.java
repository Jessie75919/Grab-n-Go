package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import _05_orderProcess.model.OrderBean;
import _05_orderProcess.model.OrderDAO;

@WebServlet("/_02_storeLogin/SyncOrders.json")
public class SyncOrdersSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger logger = Logger.getLogger(SyncOrdersSerlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String restId = request.getParameter("id");
		//System.out.println(restId);
		String ordIds = request.getParameter("arr");
		//System.out.println(ordIds);
		logger.info(ordIds);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			OrderDAO od = new OrderDAO();
			od.setRestId(Integer.parseInt(restId.trim()));
			od.setOrdIds(ordIds);
			
			Collection<OrderBean> list = od.getNewOrdersInProgress();
			String newOrdersJson = new Gson().toJson(list);
			//System.out.println(newOrdersJson);
			//out.println(monthlyOrdersJson);
			out.write(newOrdersJson);
			out.flush();
		} finally {
			out.close();
		}
	}

}
