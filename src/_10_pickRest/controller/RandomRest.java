package _10_pickRest.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Test;

import _01_Store_register.model.StoreBean;
import _01_Store_register.model.StoreBeanDAO;

public class RandomRest {
	Logger lg = Logger.getLogger(RandomRest.class);
	
	double latitude = 25.044019;
	double longitude = 121.5332270000001;
	

	@Test
	public void test() {
		if (latitude != 0 && longitude != 0) {
			StoreBeanDAO dao = new StoreBeanDAO();
			List<StoreBean> storeList = dao.getStoreFromUser(latitude, longitude);
			List<Integer> pickOneRound = new ArrayList<>();
			
			if (storeList.size() == 0) {
				System.out.println("get nothing from dao.getStoreFromUser in LoadingHomepageFilter");
			} else {
				int storeCount = storeList.size();
				Random rd = new Random();
				int lastNum = 0;
				for(int i=0;i<storeCount;){
					lg.info("lastNum = " + lastNum);
					int rdNum = rd.nextInt(storeCount-1);
					lg.info("rdNum = " + rdNum);
					if(lastNum==rdNum){
						continue;
					}else{
						i++;
						lastNum = rdNum;
						pickOneRound.add(rdNum);
					}
				}
				
				System.out.println(Arrays.toString(pickOneRound.toArray()));
				

			}

		}
	}

}
