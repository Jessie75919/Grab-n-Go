package _06_listRestaurants.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;

/**
 * Servlet implementation class SaveLocationServlet
 */
@WebServlet("/getOneRest.do")
public class clickRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String restId = request.getParameter("id");
		int id = 0;
		if(restId!=null && restId.length()>=0){
			id = Integer.parseInt(restId);
		}
		System.out.println(restId);
		StoreBeanDAO dao = new StoreBeanDAO();
		StoreBean sb = dao.getStoreById(id);
		session.setAttribute("clickRest", sb);
		
		RequestDispatcher rd = request.getRequestDispatcher("_07_storePage/storePage.jsp");
		rd.forward(request, response);
		return;
		
//		RequestDispatcher rd = request.getRequestDispatcher("_07_storePage/storePage.jsp");
//		rd.forward(request, response);
//		return;
		
//		System.out.println(sb);
		
		/*ProductDAO dao = new ProductDAO(); 
		Product pro = dao.getOneProduct(id);
		System.out.println(pro);
		
		Gson gson = new Gson();
		String proTxt = gson.toJson(pro);
		out.write(proTxt);
		out.flush();*/
	}

}
