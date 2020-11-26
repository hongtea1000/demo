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
		
//			// Calendar���Ŀ��� ��¥�� �����´�. Ư������ Calendar�� singleton �����̴�.
//			// Date ���� �����ͼ� String���� ��ȯ�Ѵ�.
//			String datestr = format.format(Calendar.getInstance().getTime());
//			System.out.println(datestr);
//			// Date ��ü�� �����ؼ� String���� ��ȯ�Ѵ�.
//			datestr = format.format(new Date());
//			System.out.println(datestr);
//			// String ������ Date�������� ��ȯ�Ѵ�.
//			Date date = format.parse(datestr);
//			System.out.println(date);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		 
	}
}
