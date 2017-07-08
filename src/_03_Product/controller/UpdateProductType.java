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
import _03_Product.model.ProductType;
import _03_Product.model.ProductTypeDAO;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/_21_storeMenuSystem/updateDishType.do")
public class UpdateProductType extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("HIHI in UpdateProductType");

		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> msgOK = new HashMap<String, String>();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		request.setAttribute("MsgOK", msgOK); // 顯示正常訊息
		HttpSession session = request.getSession();
//		StoreBean sb = (StoreBean) session.getAttribute("StoreLoginOK");
		String updateList = request.getParameter("updateList");
		int count = Integer.parseInt(request.getParameter("countBB"));
		String[] updateLists = null;
		// check the updatelist
		if (updateList.equals("0")) {
			errorMsg.put("NeedOneUpdate", "至少需要修改一個欄位喔~");
			// RequestDispatcher rd =
			// request.getRequestDispatcher("_storeMenuEditDelete.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("_storeMenuInsertTypeA.jsp");
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
			String[] row = new String[2];
			row[0] = request.getParameter("typeNo" + i);
			System.out.println(row[0]);

			for (int j = 0; j < updateLists.length; j++) {
				if (updateLists[j].equals(row[0])) {
					System.out.println(" select = " + updateLists[j] + " = " + row[0]);
					row[1] = request.getParameter("dishType" + i);
					System.out.println("dishType = "+row[1]);
//					int storeId = sb.getRest_id();
					
					ProductTypeDAO dao = new ProductTypeDAO();
					ProductType pt = new ProductType(Integer.parseInt(row[0]),row[1]);
					
					int n = dao.updateAllProductType(pt,Integer.parseInt(row[0]));
					
					if(n==1){
						System.out.println("修改類型成功");
					}else{
						System.out.println("修改類型失敗");
					}
					

				}
				continue;
			}
		}
		System.out.println("全數更新成功~");
		msgOK.put("UpdateOK", "更新成功囉~");
		// RequestDispatcher rd =
		RequestDispatcher rd = request.getRequestDispatcher("_storeMenuInsertTypeA.jsp");
		rd.forward(request, response);
		return;

	}

}
