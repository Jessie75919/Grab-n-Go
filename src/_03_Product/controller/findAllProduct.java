package _03_Product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import _03_Product.model.Product;
import _03_Product.model.ProductDAO;
import _03_Product.model.ProductTypeDAO;

@WebServlet("/_21_storeMenuSystem/findAllProduct.do")
public class findAllProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		String typeFood = "";
		int restId = Integer.parseInt(request.getParameter("id"));
		String use = request.getParameter("use");
		
		
		System.out.println("restId = " + restId);
		try {
			typeFood = request.getParameter("type");
			System.out.println(typeFood);
		} catch (Exception e) {
			System.out.println("沒有type");
		}
		
		if(typeFood==null){
			try {
				ProductDAO dao = new ProductDAO();
				List<Product> list = dao.queryProducts(restId, "no");
				String typeJson = new Gson().toJson(list); 
	            out.write(typeJson);
	            out.flush();
			} finally {
				out.close();
			}
			return;
			
		}else{
			try {
				ProductDAO dao = new ProductDAO();
				List<Product> list = dao.queryProducts(restId, typeFood);
				
				if(use.equals("ajax")){
					String typeJson = new Gson().toJson(list); 
					System.out.println(typeJson);
		            out.write(typeJson);
		            out.flush();
				}else if(use.equals("jsp")){
					HttpSession session = request.getSession();
					session.setAttribute("prodByType", list);
					RequestDispatcher rd = request.getRequestDispatcher("../_07_storePage/storePage.jsp");
					rd.forward(request, response);
					return;
				}
				
				
				
			} finally {
				out.close();
			}
			return;
		}
			
	}

}