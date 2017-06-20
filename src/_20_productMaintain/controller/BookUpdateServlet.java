package _20_productMaintain.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import _00_init.GlobalService;
import _03_Product.model.*;
@WebServlet("/_20_productMaintain/BookUpdate.do")
@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
public class BookUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookBean bb ;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (!session.isNew())  {
			bb = (BookBean) session.getAttribute("bean");
		} else {
			bb = new BookBean();
		}
		String pageNo = "1";
		Map<String, String> errorMsgs = new HashMap<String, String>();
		Map<String, String> successMsgs = new HashMap<String, String>();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		request.setAttribute("ErrMsg", errorMsgs);
		session.setAttribute("successMsg", successMsgs);
		try {
			String title = "";
			String author = "";
			String priceStr = "";
			double price = 0;
			String bookNo = "";
			String companyID = "";
			String fileName = "";
			long sizeInBytes = 0;
			InputStream is = null;
			// request.getParts()方法傳回一個由javax.servlet.http.Part物件所組成的Collection
			// javax.servlet.http.Part: 代表上傳到Server的，可以是正常的表單資料(form data)，
			// 也可以上傳的檔案。
			// Part介面可以: 
			// 1. 傳回欄位的名稱(<input>的name屬性)、大小、ContentType
			// 2. 每個Part的Header
			// 3. 刪除Part
			// 4. 將Part寫入硬碟
			Collection<Part> parts = request.getParts();
			GlobalService.exploreParts(parts, request);
			if (parts != null) {   // 如果這是一個上傳資料的表單				
				for (Part p : parts) {
					String fldName = p.getName();
					String value = request.getParameter(fldName);
					if (p.getContentType() == null) {
						if (fldName.equals("title")) {
							title = value;
							bb.setTitle(title);
							if (value == null || title.trim().length() == 0) {
								errorMsgs.put("errTitle", "必須輸入書名");
							} else {
								request.setAttribute("title", title);
							}
						} else if (fldName.equals("author")) {
							author = value;
							bb.setAuthor(author);
							if (author == null || author.trim().length() == 0) {
								errorMsgs.put("errAuthor", "必須輸入作者");
							} else {
								request.setAttribute("author", author);
							}
						} else if (fldName.equals("price")) {
							priceStr = value;
							priceStr = priceStr.trim();
							bb.setPriceStr(priceStr);
							if (priceStr == null
									|| priceStr.trim().length() == 0) {
								errorMsgs.put("errPrice", "必須輸入價格");
							} else {
								try {
									price = Double.parseDouble(priceStr);
								} catch (NumberFormatException e) {
									errorMsgs.put("errPrice", "價格必須是數值");
								}
							}
							request.setAttribute("price", priceStr);
						} else if (fldName.equals("bookNo")) {
							bookNo = value;
							bb.setBookNo(bookNo);
							if (bookNo == null || bookNo.trim().length() == 0) {
								errorMsgs.put("errBookNo", "必須輸入書號");
							} else {
								request.setAttribute("bookNo", bookNo);
							}
						} else if (fldName.equals("companyID")) {
							companyID = value;
							int tempID  = Integer.parseInt(companyID);
							bb.setCompanyId(tempID);
							if (companyID == null
									|| companyID.trim().length() == 0) {
								errorMsgs.put("errCompanyID", "必須輸入出版社");
							}
							request.setAttribute("cID", companyID);
						} else if (fldName.equals("pageNo")){
							pageNo = value;
						} 

					} else {
						fileName = getFileName(p); // 此為圖片檔的檔名
						if (fileName != null && fileName.trim().length() > 0) {
							sizeInBytes = p.getSize();
							is = p.getInputStream();
						} else {
							sizeInBytes = -1;
						}
					} 
				}
			} else {
				errorMsgs.put("errTitle", "此表單不是上傳檔案的表單");
			}
			if (!errorMsgs.isEmpty()) {
			   RequestDispatcher rd = request.getRequestDispatcher("BookUpdate.jsp");
			   rd.forward(request, response);
			   return;
			} 
			int cID = Integer.parseInt(companyID);
			BookBean newBean = new BookBean(bb.getBookId(), title, author, price, 1.0, cID, fileName, bookNo);
			BookAccessBean  bab = new BookAccessBean();
			bab.updateBook(newBean, is, sizeInBytes); 
			//successMsgs.put("success" , "資料修改成功");
			RequestDispatcher rd = request.getRequestDispatcher("DisplayPageProducts?pageNo=" + pageNo);
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.put("errDBMessage", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("BookError.jsp");
			rd.forward(request, response);
		}
	}
	private String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
}
