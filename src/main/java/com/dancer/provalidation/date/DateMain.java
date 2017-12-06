package com.dancer.provalidation.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateMain {

	public static void main(String[] args) {
		String pattern="yyyyMMdd";
		SimpleDateFormat dateFormat=new SimpleDateFormat(pattern, Locale.getDefault());
		Date date=new Date();
		System.out.println(date);
		System.out.println(dateFormat.format(date));
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -10);
		System.out.println(calendar.getTime());
		System.out.println(dateFormat.format(calendar.getTime()));
	}

}
