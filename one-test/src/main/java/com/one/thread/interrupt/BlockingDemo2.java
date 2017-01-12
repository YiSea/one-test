package com.one.thread.interrupt;

import com.one.concurrent.FileCrawler;

/**
 * 
  * @Package com.one.thread 
  * @ClassName BlockingDemo.java
  * @author jun.wu  
  * @date 2017年1月4日 下午3:31:52 
  * @Description: 5.4 阻塞方法与中断方法
 */
public class BlockingDemo2  extends Thread{
	/**
	 * 阻塞/暂停原因：
	 * 1、等待I/O操作结束
	 * 2、等待获得一个锁
	 * 3、等待从Thread.sleep方法中醒来
	 * 4、等待另一个线程的计算结果
	 * ....
	 * 
	 * 正常状态(可以被调度执行)：RUNNABLE
	 * 
	 * 阻塞状态：
	 * BLOCKING
	 * WAITING
	 * TIMED_WAITING
	 */
	
	public static void main(String args[]){
		BlockingDemo2 test = new BlockingDemo2();
		test.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// 恢复被中断的状态
			Thread.currentThread().interrupt();
		}
		boolean isInterrupted1 = test.isInterrupted();
		System.out.println(isInterrupted1);

		test.interrupt();
		
		boolean isInterrupted = test.isInterrupted();
		System.out.println(isInterrupted);
	}
	
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			Long time = System.currentTimeMillis();
			System.out.println("hi , i am thread tt~");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("error : interupted exception");
				System.out.println("e:" + this.isInterrupted());
				this.interrupt();
				System.out.println("a:" + this.isInterrupted());
			}
			
			while(System.currentTimeMillis()-time<1000){}
		}
		
		System.out.println("gg");
	}

}
	
	
	
