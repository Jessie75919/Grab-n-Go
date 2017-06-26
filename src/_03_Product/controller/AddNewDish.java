package _03_Product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import _00_init.GlobalService;
import _01_Store_register.model.StoreBean;
import _03_Product.model.Product;
import _03_Product.model.ProductDAO;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/_21_storeMenuSystem/addNewDish.do")
public class AddNewDish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Heello~");
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
			RequestDispatcher rd = request.getRequestDispatcher("_storeMenuInsert.jsp");
			rd.forward(request, response);
			System.out.println("need to fill");
			return;
		} else {
			System.out.println("count = " + count);
			
		}

		for (int i = 1; i <= count; i++) {
			String[] row = new String[4];
			row[0] = request.getParameter("dishName" + i);
			row[1] = request.getParameter("dishType" + i);
			row[2] = request.getParameter("dishDesc" + i);
			row[3] = request.getParameter("dishPrice" + i);
			Part filePart = request.getPart("file" + i);

			System.out.println(row[0]);
			System.out.println(row[1]);
			System.out.println(row[2]);
			System.out.println(row[3]);
			System.out.println("filePart = " + filePart);

			InputStream is = null;
			//
			int storeId = sb.getRest_id();

			if (filePart != null) { // 如果這是一個上傳資料的表單

				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				System.out.println("fileName = " + fileName);
				if (fileName != null && fileName.trim().length() > 0) {
					fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
					is = filePart.getInputStream();
				} else {
					String path = "/images/productImageTest/1_人氣精選_01.jpg";
					String realPath = getServletContext().getRealPath(path);
					System.out.println(realPath);
					File file = new File(realPath);
					fileName = file.getName();
					is = new FileInputStream(file);
				}

				Product prod = new Product(storeId, row[1], row[0], Integer.parseInt(row[3]), row[2], fileName);
				ProductDAO dao = new ProductDAO();
				dao.insertProduct(prod, is);
				System.out.println(row[0] + " = OK");
			}
		}

		System.out.println("全數新增成功~");
		
		msgOK.put("OK", "新增成功囉~");
		RequestDispatcher rd = request.getRequestDispatcher("_storeMenuInsert.jsp");
		rd.forward(request, response);
		return;

	}

}
