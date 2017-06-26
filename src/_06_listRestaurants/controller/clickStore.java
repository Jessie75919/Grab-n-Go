package _06_listRestaurants.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;

/**
 * Servlet implementation class SaveLocationServlet
 */
@WebServlet("/getStoreBean.do")
public class clickStore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("getStoreBean!!");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		int rest_id = Integer.parseInt(request.getParameter("id"));
		System.out.println("rest_Id = "+rest_id);
		
		StoreBeanDAO dao = new StoreBeanDAO();
		StoreBean sb = dao.getStoreById(rest_id);
		System.out.println(sb);
		
		if(sb!=null){
			session.setAttribute("storeBean", sb);
			response.sendRedirect("_07_storePage/storePage.jsp");
			return;
		}else{
			response.sendRedirect("indexA.jsp");
		}
	}

}
