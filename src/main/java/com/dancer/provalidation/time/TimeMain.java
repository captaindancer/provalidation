package com.dancer.provalidation.time;

import java.sql.Timestamp;
import java.util.Date;

public class TimeMain {

	public static void main(String[] args) {
		long currentTime=System.currentTimeMillis();
		System.out.println(currentTime);
		Timestamp timestamp=new Timestamp(currentTime);
		System.out.println(timestamp);
		System.out.println(timestamp.getTime());
		System.out.println(new Date(currentTime));
		java.sql.Date sqlDate=new java.sql.Date(currentTime);
		System.out.println(sqlDate);
		
		
		Date myDate=new Date(currentTime);
		System.out.println(myDate);
		Timestamp myTimestamp=new Timestamp(myDate.getTime());
		System.out.println(myTimestamp);
		
		System.out.println(myDate.getTime());
		System.out.println(myDate.getDate());
		
		System.out.println(new Timestamp(0));
		
		myDate=new Date(0);
		System.out.println(myDate);
		Date zeroDate=new Date(0);
		System.out.println(zeroDate);
		System.out.println(myDate.equals(zeroDate));
	}

}
