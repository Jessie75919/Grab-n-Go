package _03_Product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _03_Product.model.ProductTypeDAO;

@WebServlet("/_21_storeMenuSystem/delProductType.do")
public class deletProductType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello ~~ delProductType.do!!!!");
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		response.setContentType("application/json; charset=utf-8");
		
		String typeName = request.getParameter("name");
		System.out.println("typeName = "+typeName);
		
		if(typeName==null){
			System.out.println("get Data fail in deletProductType" );
			return;
		}
		
		ProductTypeDAO dao = new ProductTypeDAO();
		int n = dao.deleteProductType(typeName);
		System.out.println("n = " + n);
		if(n==1){
			try(PrintWriter out = response.getWriter();)
			{
				
				String msg = new Gson().toJson("deleteSuccess");
				out.write(msg);
				out.flush();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
