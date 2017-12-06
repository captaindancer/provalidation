package com.dancer.provalidation.threadpool;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultThreadPool {

	private static int size=20;
	
	public static void testFixedThreadPool() throws InterruptedException{
		ExecutorService executorService=Executors.newFixedThreadPool(10);
		CountDownLatch countDownLatch=new CountDownLatch(size);
		Random random=new Random();
		for(int index=0;index<size;index++){
			executorService.execute(()->{
				String threadName=Thread.currentThread().getName();
				System.out.println(threadName+" beign");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(random.nextInt(6) == 5){
					throw new NullPointerException();
				}
				System.out.println(threadName+" end");
				countDownLatch.countDown();
				
			});
		}
		countDownLatch.await();
		executorService.shutdown();
	}
	
	public static void testCachedThreadPool() throws InterruptedException{
		ExecutorService executorService=Executors.newCachedThreadPool();
		CountDownLatch countDownLatch=new CountDownLatch(30);
		for(int index=0;index<15;index++){
			executorService.execute(()->{
				String threadName=Thread.currentThread().getName();
				System.out.println(threadName+" beign");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(threadName+" end");
				countDownLatch.countDown();
			});
		}
		
		Thread.sleep(3000);
		
		for(int index=0;index<15;index++){
			executorService.execute(()->{
				String threadName=Thread.currentThread().getName();
				System.out.println(threadName+" beign");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(threadName+" end");
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
	}
	
	public static void testSingleThreadPool() throws InterruptedException{
		ExecutorService executorService=Executors.newSingleThreadExecutor();
		CountDownLatch countDownLatch=new CountDownLatch(10);
		for(int index=0;index<10;index++){
			executorService.execute(()->{
				String threadName=Thread.currentThread().getName();
				System.out.println(threadName+" beign");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(threadName+" end");
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		//比较jdk提供的几个线程池的现象
		//newFixedThreadPool 应该只有固定可以执行的数目 注意线程中用sleep
		//newSingleThreadExecutor 是不是就一个
		//newCachedThreadPool 是不是可以执行无限多个
		//让线程自己挂掉，看会不会生成新线程
		System.out.println(0<<29);
		int running=-1<<29;
		System.out.println(running);
		System.out.println(Integer.toBinaryString(running));
		System.out.println(Integer.toBinaryString(running | 0));
		int capacity=(1<<29)-1;
		System.out.println(capacity);
		System.out.println(Integer.toBinaryString(capacity));
		System.out.println(Integer.toBinaryString(-1<<29));
		System.out.println(Integer.toBinaryString(0<<29));
		System.out.println(Integer.toBinaryString(1<<29));
		System.out.println(Integer.toBinaryString(2<<29));
		System.out.println(Integer.toBinaryString(3<<29));
		testFixedThreadPool();
//		testCachedThreadPool();
//		testSingleThreadPool();
		System.out.println("main end");
		
	}

}
