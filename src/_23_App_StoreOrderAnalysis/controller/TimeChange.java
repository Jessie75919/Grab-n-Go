package _23_App_StoreOrderAnalysis.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeChange{
	
	
	public Date GetTimeBeforeAndOnlyDate(Calendar cal, int i){
		cal.add(Calendar.MONTH, i);
		Date date = cal.getTime();
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String date_s = sdf.format(date);
		try {
			date = sdf.parse(date_s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("date = " + date);
		
		return date;
	}
}
