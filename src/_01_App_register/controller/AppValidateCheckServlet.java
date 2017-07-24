package _01_App_register.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _01_Store_register.model.StoreBeanDAO;

@SuppressWarnings("serial")
@WebServlet("/AppValidateCheckServlet")
public class AppValidateCheckServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType(CONTENT_TYPE);
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuffer jsonIn = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		int rest_id = Integer.parseInt(jsonObject.get("rest_id").getAsString());
		
		boolean rest_validate = false;
		StoreBeanDAO dao = new StoreBeanDAO();
		rest_validate = dao.checkRestValidate(rest_id);
		
		Map<String, String> map = new HashMap<>();
		PrintWriter out = response.getWriter();
		if (rest_validate) {
			map.put("ValidateCheckMessage", "ValidateOK");
		} else {
			map.put("ValidateCheckMessage", "ValidateNotYet");
		}
		out.println(gson.toJson(map));
		out.close();
	}

}
