package _03_Product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
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

import _00_init.GlobalService;
import _01_Store_register.model.StoreBean;
import _03_Product.model.Product;
import _03_Product.model.ProductDAO;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/_21_storeMenuSystem/updateDish.do")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> msgOK = new HashMap<String, String>();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		request.setAttribute("MsgOK", msgOK); // 顯示正常訊息
		HttpSession session = request.getSession();
		StoreBean sb = (StoreBean) session.getAttribute("StoreLoginOK");
		String updateList = request.getParameter("updateList");
		int count = Integer.parseInt(request.getParameter("countAA"));
		String[] updateLists = null;
		// check the updatelist
		if (updateList.equals("0")) {
			errorMsg.put("NeedOne", "至少需要修改一個欄位喔~");
			//RequestDispatcher rd = request.getRequestDispatcher("_storeMenuEditDelete.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("_storeMenuEditDeleteA.jsp");
			rd.forward(request, response);
			System.out.println("need to fill");
			return;
		} else {
			updateLists = updateList.split(",");
			for (String x : updateLists) {
				System.out.println(x);
			}
			System.out.println("count = " + count);
		}

		for (int i = 1; i <= count; i++) {
			String[] row = new String[5];
			row[0] = request.getParameter("dishName" + i);
			System.out.println(row[0]);

			for (int j = 0; j < updateLists.length; j++) {
				if (updateLists[j].equals(row[0])) {
//					System.out.println(" select = " + updateLists[j] + " = " + row[0]);
					row[1] = request.getParameter("dishType" + i);
					row[2] = request.getParameter("dishDesc" + i);
					row[3] = request.getParameter("dishPrice" + i);
					row[4] = request.getParameter("dishId" + i);
					Part filePart = request.getPart("file" + i);

					System.out.println("dishName = " + row[0]);
					System.out.println("dishType = "+row[1]);
					System.out.println("dishDesc = " +row[2]);
					System.out.println("dishPrice = " +row[3]);
					System.out.println("dishId = " +row[4]);
					System.out.println("filePart = " + filePart);

					InputStream is = null;
					int storeId = sb.getRest_id();

					if (filePart != null) { // 如果這是一個上傳資料的表單
						String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
						System.out.println("fileName = " + fileName);
						if (fileName != null && fileName.trim().length() > 0) {
							fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
							is = filePart.getInputStream();
							Product prod = new Product(Integer.parseInt(row[4]),storeId, row[1], row[0], Integer.parseInt(row[3]), row[2], fileName);
							ProductDAO dao = new ProductDAO();
							dao.updateProduct(prod, is , 1);
							System.out.println("有修改照片");
							
						} else {
							Product prod = new Product(Integer.parseInt(row[4]),storeId, row[1], row[0], Integer.parseInt(row[3]), row[2], fileName);
							ProductDAO dao = new ProductDAO();
							dao.updateProduct(prod, is , 0);
							System.out.println("沒有修改照片");
						}

//						System.out.println(row[0] + " = OK");
					}
				}
			}
			continue;
		}
		System.out.println("全數新增成功~");
		msgOK.put("OK", "更新成功囉~");
		//RequestDispatcher rd = request.getRequestDispatcher("_storeMenuEditDelete.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("_storeMenuEditDeleteA.jsp");
		rd.forward(request, response);
		return;

	}

}
