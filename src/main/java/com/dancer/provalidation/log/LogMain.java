package com.dancer.provalidation.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogMain.class);
	
	public static void main(String[] args) {
		LOGGER.info("info");
		System.out.println(LOGGER.getName());
	}

}
