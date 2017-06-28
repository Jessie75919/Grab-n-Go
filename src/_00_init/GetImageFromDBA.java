package _00_init;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/_00_init/getImage")
public class GetImageFromDBA extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = null;
		OutputStream os = null;
		InputStream is = null;

		String id = request.getParameter("id");

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);

			conn = ds.getConnection();
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("SELECT prod_img FROM product WHERE prod_id = ?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				 is = rs.getBinaryStream(1);
				 if(is.available() == 0){
				is = getServletContext().getResourceAsStream("/images/NoImage.jpg");
				 }
				String mimeType = "image/jpg";
				response.setContentType(mimeType);
				os = response.getOutputStream();

				int count = 0;
				byte[] bytes = new byte[8192];
				while ((count = is.read(bytes)) != -1) {
					os.write(bytes, 0, count);
				}
			}

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				os.close();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
