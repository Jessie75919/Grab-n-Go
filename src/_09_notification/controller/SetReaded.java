package _09_notification.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import _09_notification.model.NotificationDAO;

@WebServlet("/setReaded.do")
public class SetReaded extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Logger lg = Logger.getLogger(SetReaded.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		lg.info("hihi");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String username = request.getParameter("name");
		lg.info("username = "+username);
		
		if(username==null){
			lg.error("get data error");
			return;
		}
		
		NotificationDAO dao = new NotificationDAO();
		int n = dao.setNoticationReaded(username);
		if(n>0){
			try(
				PrintWriter out = response.getWriter();
			){
				String msgData = new Gson().toJson("Readed");
				out.write(msgData);
				out.flush();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		
	}

}
