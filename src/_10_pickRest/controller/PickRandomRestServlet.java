package _10_pickRest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;
import _06_listRestaurants.filter.LoadingHomepage;

@WebServlet("/PickRandomRest.do")
public class PickRandomRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Logger lg = Logger.getLogger(LoadingHomepage.class);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");

		String latitudeStr = request.getParameter("lat");
		String longitudeStr = request.getParameter("lng");
		double latitude = 0;
		double longitude = 0;

		if (latitudeStr != null && longitudeStr != null) {
			latitude = Double.parseDouble(latitudeStr);
			longitude = Double.parseDouble(longitudeStr);
			lg.error("latitude = " + latitude);
			lg.error("longitude = " + longitude);

		} else {
			lg.error("getNothing");
			return;
		}

		if (latitude != 0 && longitude != 0) {
			StoreBeanSmSize sbsm = new StoreBeanSmSize();
			StoreBeanDAO dao = new StoreBeanDAO();
			List<StoreBeanSmSize> storeList = dao.getStoreFromUserLocForWheel(latitude, longitude);
			List<Integer> pickOneRound = new ArrayList<>();
			List<StoreBeanSmSize> chooseStore = new ArrayList<>();
			if (storeList.size() == 0) {
				System.out.println("get nothing from dao.getStoreFromUser in LoadingHomepageFilter");
			} else {
				int storeCount = storeList.size();
				Random rd = new Random();
				int lastNum = 0;
				// lg.info("lastNum = " + lastNum);
				for (int i = 0; i < 6;) {
					int rdNum = rd.nextInt(storeCount - 1);
					// lg.info("rdNum = " + rdNum);
					if (isExist(rdNum, pickOneRound) == 1) {
						continue;
					} else {
						lastNum = rdNum;
						pickOneRound.add(rdNum);
						StoreBeanSmSize sb = storeList.get(rdNum);
						chooseStore.add(sb);
						i++;
					}
				}
			}

			lg.info(Arrays.toString(pickOneRound.toArray()));
			for (StoreBeanSmSize sb : chooseStore) {
				System.out.println(sb);
			}
			
			try (PrintWriter out = response.getWriter();) {
				String stChoose = new Gson().toJson(chooseStore);
				out.write(stChoose);
				out.flush();
			}


		}

	}

	private int isExist(int inputNum, List<Integer> box) {
		int rs = 0;
		for (Integer num : box) {
			if (num == inputNum) {
				rs = 1;
				break;
			}
		}
		return rs;
	}

}
