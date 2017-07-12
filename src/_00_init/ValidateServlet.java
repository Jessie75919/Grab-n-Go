package _00_init;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import _01_Store_register.model.StoreBeanDAO;
import _01_register.model.RegisterServiceDAO_JDBC;

@WebServlet("/validate.do")
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger lg = Logger.getLogger(ValidateServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		String username = request.getParameter("user");
		String mode = request.getParameter("mode");
		HttpSession session = request.getSession();
		Map<String, String> msg = new HashMap<>();
		
		String ctxPath = getServletContext().getContextPath() + "/";
		session.setAttribute("MSG", msg);
		if (username == null) {
			msg.put("getNameFail", "抓不到值");
			lg.error("抓不到值");
			request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
			return;
		}
		if (mode == null) {
			msg.put("getNameFail", "抓不到值");
			lg.error("抓不到值");
			request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
			return;
		}

		lg.info("username : " + username);
		lg.info("mode : " + mode);
		int modeNum = Integer.parseInt(mode);
		int n = 0;
		RegisterServiceDAO_JDBC dao = new RegisterServiceDAO_JDBC();

		
		if(modeNum==1){
			if (!dao.idExists(username)) {
				msg.put("noUser", "查無使用者");
				lg.error("查無使用者");
				request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
				return;
			}
			
			
		}else if(modeNum==2){
			StoreBeanDAO sbdao = new StoreBeanDAO();
			if(!sbdao.isUserExists(username)){
				lg.error("查無使用者");
				request.getRequestDispatcher(ctxPath + "_01_StoreRegister/_storeRegister.jsp").forward(request, response);
				return;
			}
		}
		
			if (dao.isValidated(username, modeNum)==1) {
				msg.put("alreadyValid", "已經驗證過囉");
				lg.error("已經驗證過囉");
				request.getRequestDispatcher(ctxPath + "indexA.jsp").forward(request, response);
				return;
			}
			n = dao.validate(username, modeNum);
			
			
		if (n == 1) {
			if(modeNum==1){
				response.sendRedirect(response.encodeRedirectURL(ctxPath + "_01_register/valid_success.jsp"));
				return;
			}else if(modeNum==2){
				response.sendRedirect(response.encodeRedirectURL(ctxPath + "_01_register/valid_success.jsp"));
				return;
			}
		} else {
			msg.put("validateFail", "驗證失敗請重新驗證");
			lg.error("驗證失敗請重新驗證");
			response.sendRedirect(response.encodeRedirectURL(ctxPath + "indexA.jsp"));
			return;
		}

	}

}
