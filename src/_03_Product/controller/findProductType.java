package _03_Product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _03_Product.model.ProductTypeDAO;

@WebServlet("/_21_storeMenuSystem/findProductType.do")
public class findProductType extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("KKKK~");
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String restName = request.getParameter("id");
		System.out.println(restName);
		
		try {
			ProductTypeDAO dao = new ProductTypeDAO();
			List<String> list = dao.queryAllProductType(restName);
			String typeJson = new Gson().toJson(list); 
            out.write(typeJson);
            out.flush();
		} finally {
			out.close();
		}

	

	}

}
