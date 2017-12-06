package com.dancer.provalidation.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMain {

	private static long initTime;
	private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	static{
		initTime=System.currentTimeMillis();
		System.out.println("加载开始时间："+dateFormat.format(new Date(initTime)));
		String path=LogMain.class.getProtectionDomain().getCodeSource().getLocation().getFile()+"logs";
		System.out.println(path);
		//设置系统属性
		System.setProperty("datasync.booklist", path);
		System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogMain.class);
	
	
	public static void main(String[] args) throws InterruptedException {
		long startTime=System.currentTimeMillis();
		System.out.println("main方法开始时间："+dateFormat.format(new Date(startTime)));
		System.out.println(startTime-initTime);
		CountDownLatch countDownLatch=new CountDownLatch(10);
		for(int count=0;count<10;count++){
			new Thread(()->{
				for(int index=0;index<100000;index++){
					LOGGER.info("sys property default value");
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				countDownLatch.countDown();
			}).start();
		}
		System.out.println(LOGGER.getName());
		countDownLatch.await();
		System.out.println("当前使用目录："+System.getProperty("user.dir"));
		System.out.println("使用时间："+(System.currentTimeMillis()-startTime));
		for(int index=0;index<10000;index++){
			LOGGER.info("sys property default value");
		}
		System.out.println("最终时间："+(System.currentTimeMillis()-initTime));
		//实验方法是：先在最开始的static块中计算出jar包所在的路径，然后使用System.setProperty设置为系统属性
		//在log4j2.xml文件中读取系统属性
	}

}
