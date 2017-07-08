package _06_listRestaurants.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;

@WebFilter({"/indexA.jsp","/SaveLocation.do"})
public class LoadingHomepage implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		double latitude = 0;
		double longitude = 0;
		String contextPath = servletRequest.getContextPath();
//		System.out.println(contextPath + " in LoadingHomepage");
		Cookie[] coks = servletRequest.getCookies();
		// System.out.println("here ??");
		for (Cookie cok : coks) {
			if (cok.getName().equals("lat")) {
				if(cok.getValue()!=null){
					latitude = Double.parseDouble(cok.getValue());
					System.out.println("latitude= " + latitude + " in LoadingHomepageFilter");
				}
			} else if (cok.getName().equals("lng")) {
				if(cok.getValue()!=null){
					longitude = Double.parseDouble(cok.getValue()); 
					System.out.println("latitude= " + longitude + " in LoadingHomepageFilter");
				}
			}
		}
		
		// 如果有再cookie取到經緯度的值
		if (latitude != 0 && longitude != 0) {
			StoreBeanDAO dao = new StoreBeanDAO();
			List<StoreBean> storeList = dao.getStoreFromUser(latitude, longitude);
			if(storeList == null){
				System.out.println("get nothing from dao.getStoreFromUser in LoadingHomepageFilter");
			}else{
				System.out.println("get Loc in in LoadingHomepageFilter");
				session.setAttribute("stList", storeList);
				
				chain.doFilter(servletRequest, servletResponse);
				
//				servletResponse.sendRedirect("/_Grab_Go/WebContent/indexA.jsp");
//				servletRequest.getRequestDispatcher(contextPath+"/indexA.jsp").forward(servletRequest, servletResponse);
//				return;
			}
		}else{  // 如果沒有cookie沒有經緯度的值
			
			System.out.println("give all rest in LoadingHomepageFilter");
			StoreBeanDAO dao = new StoreBeanDAO();
			List<StoreBean> storeList = dao.getAllStores();
			session.setAttribute("stList", storeList);
			chain.doFilter(request, response);
		}

/*		if (servletRequest.getParameter("latitude") != null && servletRequest.getParameter("longitude") != null) {
			try {
				latitude = Double.parseDouble(servletRequest.getParameter("latitude"));
				longitude = Double.parseDouble(servletRequest.getParameter("longitude"));
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
*/
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
