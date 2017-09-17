package com.dancer.provalidation.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMain {

	private static long initTime;
	
	static{
		initTime=System.currentTimeMillis();
		System.out.println(initTime);
		String path=LogMain.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		System.out.println(path);
		//设置系统属性
//		System.setProperty("datasync.booklist", "/Users/liufeng/logs/datasync/booklist");
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogMain.class);
	
	
	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		System.out.println(startTime);
		System.out.println(startTime-initTime);
		LOGGER.info("sys property default value");
		System.out.println(LOGGER.getName());
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.currentTimeMillis()-startTime);
		
		//实验方法是：先在最开始的static块中计算出jar包所在的路径，然后使用System.setProperty设置为系统属性
		//在log4j2.xml文件中读取系统属性
	}

}
