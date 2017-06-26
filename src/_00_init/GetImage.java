package _00_init;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;




/**
 * Servlet implementation class GetImage
 */
@WebServlet("/GetImage") 

            
public class GetImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			StoreBean sb = null;
			String no = request.getParameter("no");
			String type = request.getParameter("type").trim();
			int ino = -1;
			if (no != null) {
				try {
					ino = Integer.parseInt(no);
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
					ino = -1;
				}
				StoreBeanDAO dao = new StoreBeanDAO();
				sb = dao.getStoreById(ino);
			}
			
			String mimeType = null;
			// 放預設圖檔
			if (sb == null) {
				is = getServletContext().getResourceAsStream(
						"/images/NoImage.gif");
				mimeType = "image/gif";
			} else {
				Blob blob = null;
				if(type.equals("coverImage")){
					 blob = sb.getRest_coverimage();
				}else if(type.equals("logo")){
					 blob = sb.getRest_logo();
				}else if(type.equals("mainBanner")){
					 blob = sb.getRest_mainbanner();
				}
				
				// 放預設圖檔
				if (blob == null){
					is = getServletContext().getResourceAsStream(
							"/images/NoImage.gif");
					mimeType = "image/gif";
				} else {
					is = blob.getBinaryStream();
					mimeType = "image/jpg";
				}
			}
			response.setContentType(mimeType);
			os = response.getOutputStream();
			int len = 0 ; 
			byte[] b = new byte[8192];
			while ( ( len = is.read(b) )!= -1){
				os.write(b, 0, len);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} finally {
			if (is != null){
				is.close();
			}
			if (os != null){
				os.close();
			}
		}
	}

}
