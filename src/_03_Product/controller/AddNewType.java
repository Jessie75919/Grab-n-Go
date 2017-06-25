package _03_Product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Store_register.model.StoreBean;
import _03_Product.model.Product;
import _03_Product.model.ProductDAO;
import _03_Product.model.ProductType;
import _03_Product.model.ProductTypeDAO;

@WebServlet("/_21_storeMenuSystem/addNewDishType.do")
public class AddNewType extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("JJJJ~");
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼

		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> msgOK = new HashMap<String, String>();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		HttpSession session = request.getSession();
		request.setAttribute("MsgOK", msgOK); // 顯示正常訊息
		StoreBean sb = (StoreBean) session.getAttribute("StoreLoginOK");

		int count = Integer.parseInt(request.getParameter("countAA"));

		// check the row of count
		if (count == 0) {
			errorMsg.put("NeedOne", "至少需要填一個欄位喔~");
			RequestDispatcher rd = request.getRequestDispatcher("_storeMenuProdType.jsp");
			rd.forward(request, response);
			System.out.println("need to fill");
			return;
		} else {
			System.out.println("count = " + count);

		}
		String storeName = sb.getRest_name();

		for (int i = 1; i <= count; i++) {
			String type = request.getParameter("typeName" + i);

			System.out.println(type);
			
			ProductType pt = new ProductType(type,storeName);
			ProductTypeDAO ptDao = new ProductTypeDAO();
			
			ptDao.insertProductType(pt);
			System.out.println(pt + " = OK");

		}

		System.out.println("全數新增成功~");

		msgOK.put("OK", "新增成功囉~");
		RequestDispatcher rd = request.getRequestDispatcher("_storeMenuProdType.jsp");
		rd.forward(request, response);
		return;

	}

}
