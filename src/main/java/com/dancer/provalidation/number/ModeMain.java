package com.dancer.provalidation.number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModeMain {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ModeMain.class);

	public static void main(String[] args) {
		int test=989;
		System.out.println(test%1000/100);
		test=9066;
		System.out.println(test%1000/100);
		
		System.out.println(7/2);
		for(int index=0;index<10;index++){
			LOGGER.warn("lala");
			LOGGER.info("haha");
		}
	}

}
