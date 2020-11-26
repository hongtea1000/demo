package com.example.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static void main(String args[]) {
		
		try {
		
			String dateValue = "Wed, 30 Sep 2020 10:41:49";
			
			
			SimpleDateFormat sf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.KOREA);
			sf.parse(dateValue);
			
			//System.out.println(dateFormat.format(new Date()));
		
//			// Calendar형식에서 날짜를 가져온다. 특이점은 Calendar가 singleton 형식이다.
//			// Date 값을 가져와서 String으로 변환한다.
//			String datestr = format.format(Calendar.getInstance().getTime());
//			System.out.println(datestr);
//			// Date 객체를 선언해서 String으로 변환한다.
//			datestr = format.format(new Date());
//			System.out.println(datestr);
//			// String 형식을 Date형식으로 변환한다.
//			Date date = format.parse(datestr);
//			System.out.println(date);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		 
	}
}
