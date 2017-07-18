package _05_orderProcess.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import _05_orderProcess.model.OrderDAO;

@WebServlet("/ReadOrderServlet")
public class ReadOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ReadOrderServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
		String id = request.getParameter("ordId");
		
		OrderDAO od = new OrderDAO();
		int n = od.readOrder(id);
		logger.info(id + "號訂單已讀: " + n);
//		out.write("hi~" + id + n);
//		out.flush();
//		out.close();
	}

}
