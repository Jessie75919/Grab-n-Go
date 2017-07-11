package _03_Product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import _03_Product.model.Product;
import _03_Product.model.ProductDAO;
import _03_Product.model.ProductTypeDAO;

@WebServlet("/_21_storeMenuSystem/delProduct.do")
public class deleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Logger lg = Logger.getLogger(deleteProduct.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("delProduct.do!!!!");
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String dishIdStr = request.getParameter("dishId").trim();
		if(dishIdStr == null) {
			lg.error("get Nothing");
			return;
		}
		int dishId = Integer.parseInt(dishIdStr);
		System.out.println(dishId);
//		
		try {
			ProductDAO dao = new ProductDAO();
			int n = dao.deleteProduct(dishId);
			
			if(n==1){
				String result = new Gson().toJson("delOK"); 
				out.write(result);
				out.flush();
			}else{
				System.out.println("Something gets wrong");
			}
		} finally {
			out.close();
		}

	}

}
