package com.dancer.provalidation.threadpool;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private String name;
	private BlockingQueue<String> blockingQueue;
	
	public Consumer(String name, BlockingQueue<String> blockingQueue) {
		this.name = name;
		this.blockingQueue = blockingQueue;
	}

	public String getName() {
		return name;
	}

	public BlockingQueue<String> getBlockingQueue() {
		return blockingQueue;
	}


	@Override
	public void run() {
		String name=Thread.currentThread().getName();
		System.out.println(name+" begin to consume");
		try {
			System.out.println("the value is : "+blockingQueue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
