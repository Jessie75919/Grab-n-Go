package _06_listRestaurants.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;

/**
 * Servlet Filter implementation class LoadingHomepage
 */
@WebFilter("/indexA.jsp")
public class LoadingHomepage implements Filter {

    /**
     * Default constructor. 
     */
    public LoadingHomepage() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse servletResponse = (HttpServletResponse)response;
		HttpSession session = servletRequest.getSession();
		double latitude = 0;
		double longitude = 0;
		if(servletRequest.getParameter("latitude")!=null && 
				servletRequest.getParameter("longitude")!=null	
				){
			try {
				latitude = Double.parseDouble(servletRequest.getParameter("latitude"));
				longitude = Double.parseDouble(servletRequest.getParameter("longitude"));
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		StoreBeanDAO dao = new StoreBeanDAO();
		List<StoreBean> storeList = dao.getAllStores();
		session.setAttribute("stList", storeList);
		
//		RequestDispatcher rd = 
//				request.getRequestDispatcher("indexA.jsp");
//		rd.forward(request, response);
		
		
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
