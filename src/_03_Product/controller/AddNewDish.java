package _03_Product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@WebServlet("/addNewDish.do")
public class AddNewDish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Heello~");
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> msgOK = new HashMap<String, String>();
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息
		StoreBean sb = (StoreBean) session.getAttribute("StoreLoginOK");
		
		int count = Integer.parseInt(request.getParameter("countAA"));

		// check the row of count
		if (count == 0) {
			System.out.println("need to fill");
		} else {
			System.out.println("count = " + count);
		}

		for (int i = 1; i < count; i++) {
			String[] row = new String[4];
			row[0] = request.getParameter("dishName" + i);
			row[1] = request.getParameter("dishType" + i);
			row[2] = request.getParameter("dishDesc" + i);
			row[3] = request.getParameter("dishPrice" + i);

			System.out.println(row[0]);
			System.out.println(row[1]);
			System.out.println(row[2]);
			System.out.println(row[3]);

			Collection<Part> parts = request.getParts(); // 取出HTTP multipart
			// request內所有的parts
			GlobalService.exploreParts(parts, request);
			String fileName = "";
			long sizeInBytes = 0;
			InputStream is = null;
			int storeId = sb.getRest_id();

			if (parts != null) { // 如果這是一個上傳資料的表單
				
				for (Part p : parts) {
					if (p.getContentType() != null) {
						String fldName = p.getName();
						String value = request.getParameter(fldName);
						fileName = GlobalService.getFileName(p); // 此為圖片檔的檔名
						fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
						if (fileName != null && fileName.trim().length() > 0) {
//							sizeInBytes = p.getSize();
							is = p.getInputStream();
						} else {
							String path = "/images/productImageTest/1_人氣精選_01.jpg";
							String realPath = getServletContext().getRealPath(path);
							File file = new File(realPath);
							fileName = file.getName();
							is = new FileInputStream(file);
//							sizeInBytes = file.length();
						}
					}
				}

				Product prod = new Product(storeId, "主餐 Food", row[0], Integer.parseInt(row[3]), row[2], fileName);

				ProductDAO dao = new ProductDAO();
				dao.insertProduct(prod, is);
				System.out.println(row[0] + " = OK");
			}

		}

	}

}
