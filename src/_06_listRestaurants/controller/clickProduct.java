package _06_listRestaurants.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _03_Product.model.Product;
import _03_Product.model.ProductDAO;

/**
 * Servlet implementation class SaveLocationServlet
 */
@WebServlet("/getOneProduct.do")
public class clickProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Wow  ~~!!");
//		HttpSession session = request.getSession();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String proId = request.getParameter("proId");
		int id = 0;
		if(proId!=null && proId.length()>=0){
			id = Integer.parseInt(proId);
		}
		System.out.println(proId);
		ProductDAO dao = new ProductDAO(); 
		Product pro = dao.getOneProduct(id);
		System.out.println(pro);
//		session.setAttribute("clickProd", pro);
		
		Gson gson = new Gson();
		String proTxt = gson.toJson(pro);
		out.write(proTxt);
		out.flush();
	}

}
