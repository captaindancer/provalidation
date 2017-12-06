package com.dancer.provalidation.threadpool;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	
	private String name;
	private BlockingQueue<String> blockingQueue;
	
	public Producer(String name, BlockingQueue<String> blockingQueue) {
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
		System.out.println(name+" begin to produce");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			blockingQueue.put(name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println(name+" : "+blockingQueue.peek());
		System.out.println(name+" produces successfully");
	}

}
