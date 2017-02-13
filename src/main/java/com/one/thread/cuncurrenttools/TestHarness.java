package com.one.thread.cuncurrenttools;

import java.util.concurrent.CountDownLatch;

import javax.xml.soap.Node;

/**
 * 
  * @Package com.one.thread.countdownlatch 
  * @ClassName TestHarness.java
  * @author jun.wu  
  * @date 2017年1月5日 上午10:58:04 
  * @Description: 同步工具类：闭锁
 */
public class TestHarness implements Runnable{

	public long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		
		for(int i = 0; i < nThreads; i++){
			Thread t = new Thread(){
				public void run(){
					try {
						startGate.await();// 我ready了，其他线程兄弟快点，等着呢
						try {
							task.run();	// go go go
						} finally {
							endGate.countDown(); // 结束门-1（我结束了哈，你们记得打卡~）
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			};
			t.start();
		}
		
		System.out.println("are you ready?");
		Thread.sleep(2000);
		System.out.println("-------Go!----------");
		Thread.sleep(1000);
		long start = System.nanoTime();
		startGate.countDown();// 启动门-1 开启
		endGate.await(); // 结束门，主线程等待所有线程都结束，方可开门让你走
		long end = System.nanoTime();
		return end - start; // 并发时间差
			
	}

	@Override
	public void run() {
	System.out.println(Thread.currentThread().getName() + " Run Run Run！！" + System.currentTimeMillis());
		
	}
	
	public static void main(String[] args) {
		
		TestHarness mainTask = new TestHarness();
		try {
			Long fu = mainTask.timeTasks(100, mainTask);
			System.out.println(fu);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
