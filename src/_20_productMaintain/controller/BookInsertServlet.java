package _20_productMaintain.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
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
import _03_Product.model.BookAccessBean;
import _03_Product.model.BookBean;

@WebServlet("/_20_productMaintain/BookInsert.do")
//  name1=%BC%5E%BD&name2=value2&....
//啟動檔案上傳的功能：
//1. <form>標籤的 method屬性必須是"post", 而且
//enctype屬性必須是"multipart/form-data"
//注意：enctype屬性的預設值為"application/x-www-form-urlencoded"
//2. 定義可以挑選上傳檔案的表單欄位：
//<input type='file' name='user-defined_name' />
//註: HTTP multipart request: 由Http客戶端(如瀏覽器)所建構的ㄧ種請求，用來
//   同時上傳表單資料與檔案。
//
// what-should-a-multipart-http-request-with-multiple-files-look-like?
// http://stackoverflow.com/questions/913626/what-should-a-multipart-http-request-with-multiple-files-look-like

// 在Servlet 3.0中，若要能夠處理瀏覽器送來的multipart request, Servlet必須
// 以註釋『javax.servlet.annotation.MultipartConfig』來宣告。
// 
// MultipartConfig的屬性說明:
// location: 上傳之表單資料與檔案暫時存放在Server端之路徑。此路徑必須存在。
// fileSizeThreshold: 檔案的大小臨界值，超過此臨界值，上傳檔案會用存放在硬碟，
// 否則存放在主記憶體。
// maxFileSize: 上傳單一檔案之長度限制，如果超過此數值，Container會丟出例外
// maxRequestSize: 上傳所有檔案的總長度限制，如果超過此數值，Container會丟出例外
@MultipartConfig(location = "c:\\temp", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
public class BookInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new HashMap<String, String>();
		Map<String, String> successMsgs = new HashMap<String, String>();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
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
			// javax.servlet.http.Part: 代表上傳到Server的資料，可以是正常的表單資料(form data)，
			// 也可以上傳的檔案。
			// Part介面可以: 
			// 1. 傳回欄位的名稱(<input>的name屬性)、大小、ContentType
			// 2. 每個Part的Header
			// 3. 刪除Part
			// 4. 將Part寫入硬碟
			Collection<Part> parts = request.getParts();
			//GlobalService.exploreParts(parts, request);
			if (parts != null) {   // 如果這是一個上傳資料的表單				
				for (Part p : parts) {
					String fldName = p.getName();
					System.out.println("fldName=" + fldName);
					String value = request.getParameter(fldName);
					if (p.getContentType() == null) {
						if (fldName.equals("title")) {
							title = value;
							if (value == null || title.trim().length() == 0) {
								errorMsgs.put("errTitle", "必須輸入書名");
							} else {
								request.setAttribute("title", title);
							}
						} else if (fldName.equals("author")) {
							author = value;
							if (author == null || author.trim().length() == 0) {
								errorMsgs.put("errAuthor", "必須輸入作者");
							} else {
								request.setAttribute("author", author);
							}
						} else if (fldName.equals("price")) {
							priceStr = value;
							priceStr = priceStr.trim();
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
							;
							if (bookNo == null || bookNo.trim().length() == 0) {
								errorMsgs.put("errBookNo", "必須輸入書號");
							} else {
								request.setAttribute("bookNo", bookNo);
							}
						} else if (fldName.equals("companyID")) {
							companyID = value;
							;
							if (companyID == null
									|| companyID.trim().length() == 0) {
								errorMsgs.put("errCompanyID", "必須輸入出版社");
							}
							request.setAttribute("cID", companyID);
						}

					} else {
						fileName = GlobalService.getFileName(p); // 此為圖片檔的檔名
						fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
						if (fileName != null && fileName.trim().length() > 0) {
							sizeInBytes = p.getSize();
							is = p.getInputStream();
						} else {
							errorMsgs.put("errPicture", "必須挑選圖片檔");
						}
					}
				}
			} else {
				errorMsgs.put("errTitle", "此表單不是上傳檔案的表單");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request
						.getRequestDispatcher("BookInsert.jsp");
				rd.forward(request, response);
				return;
			}
			int cID = Integer.parseInt(companyID);
			BookBean bb = new BookBean(0, title, author, price, 1.0, cID,
					fileName, bookNo);
			BookAccessBean bab = new BookAccessBean();
			System.out.println(bb.getFileName());
			bab.insertBook(bb, is, sizeInBytes);
			successMsgs.put("success", "資料新增成功");
			
			response.sendRedirect(response.encodeRedirectURL("BookInsert.jsp"));
			return;
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.put("Exception", e.getMessage());
			RequestDispatcher rd = request
					.getRequestDispatcher("BookInsert.jsp");
			rd.forward(request, response);
		}
	}

	public String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
	public void exploreParts(Collection<Part> parts, HttpServletRequest req){
		try {
			for (Part part: parts){
				String name = part.getName();
				String contentType = part.getContentType();
				String value = "";
				long size = part.getSize(); // 上傳資料的大小，即上傳資料的位元組數
				InputStream is =part.getInputStream();
				if (contentType != null) {	// 表示該part為檔案
				   // 取出上傳檔案的檔名
				   String filename = getFileName(part);
				// 將上傳的檔案寫入到location屬性所指定的資料夾
				   part.write(filename);		
				} else {	// 表示該part為一般的欄位
				   // 將上傳的欄位資料寫入到location屬性所指定的資料夾，檔名為"part_"+ name
				   part.write("part_"+ name);	
				   value = req.getParameter(name);    
				}
				System.out.printf("%-15s %-15s %8d  %-20s \n", name, contentType, size, value);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}