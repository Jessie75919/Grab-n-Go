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


@WebServlet("/SaveLocation.do")
public class SaveLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	double latitude = 0;
	double longitude = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hello!! SaveLocationServlet ");
		request.setCharacterEncoding("UTF-8");
//		HttpSession session = request.getSession();

		try {
			latitude = Double.parseDouble(request.getParameter("latitude"));
			longitude = Double.parseDouble(request.getParameter("longitude"));
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Hello ? in SaveLocationServlet");
		response.sendRedirect("index.jsp");
//
	}

}
