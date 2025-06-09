package com.comcast.crm.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber(){
		
		Random ran=new Random();
		int r=ran.nextInt(5000);
		return r;
	}
	
	public String getSystemDateYYYYDDMM() {
	Date dateObj=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateObj);
		return date;
	}
	
	public String getRequireDateYYYYDDMM(int days) {
		
         Date dateObj=new Date();
		
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String date=sim.format(dateObj);
		
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String requireDate=sim.format(cal.getTime());
		return requireDate;
		
	}
	public String getCurrentTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return sdf.format(new Date());
	}

}
