package _02_login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class SaveLocationServlet
 */
@WebServlet("/SaveLocation.do")
public class SaveLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello!!");
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json; charset=utf-8");
	    PrintWriter out = response.getWriter();
		String latitude = request.getParameter("latitude");
	    String longitude = request.getParameter("longitude");
	    System.out.println(latitude);
	    System.out.println(longitude);
	    if(latitude==null || longitude==null || 
	    		latitude.length()==0 || longitude.length()==0 ){
	    	System.out.println("latitude & longitude not found ! ");
	    	Gson gson = new Gson();
	    	String msgJson = gson.toJson("找不到您的座標，這樣無法替您搜尋附近的餐廳喔~");
	    	out.write(msgJson);
	    	out.flush();
	    }else{
	    	Cookie lat = new Cookie("lat", latitude);
	    	lat.setMaxAge(30*60*60);
	    	lat.setPath(request.getContextPath());
	    	Cookie lng = new Cookie("lng", longitude);
	    	lng.setMaxAge(30*60*60);
	    	lng.setPath(request.getContextPath());
	    	response.addCookie(lat);
	    	response.addCookie(lng);
	    }
		
		
		
		
		
		
	}


}
