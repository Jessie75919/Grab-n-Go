package _02_login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;

/**
 * Servlet implementation class SaveLocationServlet
 */
@WebServlet("/SaveLocation.do")
public class SaveLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hello!!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		double latitude = 0;
		double longitude = 0;

		try {
			latitude = Double.parseDouble(request.getParameter("latitude"));
			longitude = Double.parseDouble(request.getParameter("longitude"));
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		System.out.println("latitude = " + latitude + ", longitude = " + longitude);

		if (latitude == 0 || longitude == 0) {
			/* check the value of Cookies */
			Cookie[] coks = request.getCookies();
			System.out.println("here ??");
			try {
				for (Cookie cok : coks) {
					if (cok.getName().equals("lat")) {
						latitude = Double.parseDouble(cok.getValue());
						System.out.println("latitude= " + latitude);
					} else if (cok.getName().equals("lng")) {
						longitude = Double.parseDouble(cok.getValue());
					}
					
				}
				if (latitude == 0 && longitude == 0) {
					// get nothing from Cookies ....
					// 隨機塞幾家餐廳回去
					System.out.println("隨機塞幾家餐廳回去");
					StoreBeanDAO dao = new StoreBeanDAO();
					List<StoreBean> storeList = dao.getAllStores();
					Gson gson = new Gson();
					String storeListJson = gson.toJson(storeList);
					out.write(storeListJson);
					out.flush();
				}else{
					getRestWithLocation(out);

				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		} else { /* get the value of location from broswer */
			/* store into Cookie */
			Cookie lat = new Cookie("lat", String.valueOf(latitude));
			lat.setMaxAge(30 * 60 * 60);
			lat.setPath(request.getContextPath());
			Cookie lng = new Cookie("lng", String.valueOf(longitude));
			lng.setMaxAge(30 * 60 * 60);
			lng.setPath(request.getContextPath());
			response.addCookie(lat);
			response.addCookie(lng);
			
			getRestWithLocation(out);

		}

	}
	private void getRestWithLocation(PrintWriter out){
		/* get restaurant from user's Location */
		StoreBeanDAO dao = new StoreBeanDAO();
		// CALL get_Rest(25.0483199,121.5344137);
		List<StoreBean> storeList = dao.getStoreFromUser(25.0483199, 121.5344137);
		// List<StoreBean> storeList = dao.getStoreFromUser(latitude,
		// longitude);
		Gson gson = new Gson();
		String storeListJson = gson.toJson(storeList);
		System.out.println(storeListJson);
		out.write(storeListJson);
		out.flush();
	}
	

}
