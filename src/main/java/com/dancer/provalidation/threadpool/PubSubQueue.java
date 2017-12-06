package com.dancer.provalidation.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class PubSubQueue {
	
	public static void pubsub(){
		BlockingQueue<String> blockingQueue=new SynchronousQueue<>();
		System.out.println(blockingQueue.peek());
		Producer producer1=new Producer("p1", blockingQueue);
		Producer producer2=new Producer("p2", blockingQueue);
		Consumer consumer1=new Consumer("c1", blockingQueue);
		Consumer consumer2=new Consumer("c2", blockingQueue);
		new Thread(producer1, producer1.getName()).start();
		new Thread(producer2, producer2.getName()).start();
		new Thread(consumer1, consumer1.getName()).start();
		new Thread(consumer2, consumer2.getName()).start();
	}

	public static void testSemaphore() throws InterruptedException{
		Semaphore semaphore=new Semaphore(0);
//		semaphore.acquire();
		semaphore.release();
	}
	
	public static void main(String[] args) throws InterruptedException {
		testSemaphore();
		BlockingQueue<String> blockingQueue=new SynchronousQueue<>();
		for(int index=0;index<10;index++){
			System.out.println("poll状态："+blockingQueue.offer("lala"));
		}
//		blockingQueue.put("111");
		System.out.println(blockingQueue.take());
//		pubsub();
	}

}
