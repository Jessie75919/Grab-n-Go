package _22_searchRest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _22_searchRest.model.RestBean;
import _22_searchRest.model.RestDAO;


@WebServlet("/_22_SearchRest/SearchRestServlet")
public class SearchRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("SearchRestServlet");
		
		String foodKind = "";
		String storeName = "";
		String foodName = "";
		int count = 0;
		
		RestDAO rd = new RestDAO();
		//取得查詢字串的值
		foodKind = request.getParameter("foodKind");
		storeName = request.getParameter("storeName");
		foodName = request.getParameter("foodName");
		
		System.out.println("foodKind = " + foodKind);
		System.out.println("storeName = " + storeName);
		System.out.println("foodName = " + foodName);
		
		//依欄位是否為空值做判斷，會得到不同的count值
		//若非空值時才會丟給RestDAO
		if(foodKind.trim().length() != 0){
			rd.setFoodKind(foodKind);
			count += 1;
		}
		
		if(storeName.trim().length() != 0){
			rd.setStoreName(storeName);
			count += 2;
		}
		
		if(foodName.trim().length() != 0){
			rd.setFoodName(foodName);
			count += 4;
		}
		//把count丟給RestDAO
		rd.setCount(count);
		Collection<RestBean> coll = rd.searchRest();
		if(coll != null){
			System.out.println("搜尋結果筆數= " + coll.size());
			request.setAttribute("resultSize", coll.size());
			request.setAttribute("Restaurants", coll);
			request.getRequestDispatcher("/_10_search/search_result.jsp").forward(request, response);
			return;
		} 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
