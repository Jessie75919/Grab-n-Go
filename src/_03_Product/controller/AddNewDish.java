package _03_Product.controller;

import java.io.IOException;
import java.util.ArrayList;
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

@MultipartConfig(location = "", 
fileSizeThreshold = 5*1024 * 1024, 
maxFileSize = 1024 * 1024 * 500, 
maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/addNewDish.do")
public class AddNewDish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Heello~");
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> msgOK = new HashMap<String, String>();
		HttpSession session = request.getSession();
        request.setAttribute("MsgMap", errorMsg);  //顯示錯誤訊息
        session.setAttribute("MsgOK", msgOK);      //顯示正常訊息
        
        
        int count = Integer.parseInt(request.getParameter("countAA")); // the count of rows
        
        
        List<String[]> allRow = new ArrayList<>();
        // check the row of count
        if(count ==0){
        	System.out.println("need to fill");
        }else{
        	System.out.println(count);
        }
        
        for(int i=1;i<count;i++){
        	String[] row = new String[4];  
        	row[0] = request.getParameter("dishName"+i);
        	row[1] = request.getParameter("dishType"+i);
        	row[2] = request.getParameter("dishDesc"+i);
        	row[3] = request.getParameter("dishPrice"+i);
        	System.out.println(row[0]);
        	System.out.println(row[1]);
        	System.out.println(row[2]);
        	System.out.println(row[3]);
        	allRow.add(row);
        }
        
        
        
        
        
        
        
        
        
        
	}

}
