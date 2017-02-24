package com.one.jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName ThreadWaitTest.java
  * @author jun.wu  
  * @date 2017年2月24日 下午3:04:30 
  * @Description: 线程等待演示代码
  * 
 */
public class ThreadWaitTest {
	
	/**
	 * 线程死循环演示 
	 */
	public static void createBusyThread(){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true);
			}
		}, "testBusyThread");
		
		thread.start();
	}
	
	/**
	 * 线程锁等待演示
	 */
	public static void createLockThread(final Object lock){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "testLockThread");
		
		thread.start();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br.readLine();
//		createBusyThread();
		br.readLine();
		Object obj = new Object();
		createLockThread(obj);
		
	}

}
