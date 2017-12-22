package com.thinkgem.jeesite.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		for(int i= 0;i<9;i++){
			getTime();
		}
	}
	
	private static void getTime(){
		Calendar Cld = Calendar.getInstance();
		int YY = Cld.get(Calendar.YEAR) ;
		int MM = Cld.get(Calendar.MONTH)+1;
		int DD = Cld.get(Calendar.DATE);
		int HH = Cld.get(Calendar.HOUR_OF_DAY);
		int mm = Cld.get(Calendar.MINUTE);
		int SS = Cld.get(Calendar.SECOND);
		int MI = Cld.get(Calendar.MILLISECOND);
		String curTime = ""+YY + MM + DD + HH + mm + SS + MI;
		System.out.println(curTime);
		Calendar cal = Calendar.getInstance(); 
		java.util.Date date = cal.getTime(); 

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS"); 

		String myTime = sdFormat.format(date);
		System.out.println(myTime);
	}
}
