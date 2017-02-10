package com.one.thread.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 没有说服力、
 */
public class YieldTest extends Thread {  
	private static CountDownLatch cdl = new CountDownLatch(1);
  
    public YieldTest(String name) {  
        super(name);  
    }  
  
    @Override  
    public void run() {  
//    	synchronized (YieldTest.class) {
    		try {
    			cdl.await();
	  		} catch (InterruptedException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
	        for (int i = 1; i <= 50; i++) {  
	            System.out.println("" + this.getName() + "-----" + i);  
	            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）  
	            if (i == 30) {  
	                this.yield();  
	            }  
	        }  
//    	}
    }  
  
    public static void main(String[] args) {
    	ExecutorService exec = Executors.newCachedThreadPool();
        YieldTest yt1 = new YieldTest("张三");  
        YieldTest yt2 = new YieldTest("李四");  
      
        exec.execute(yt1);
        exec.execute(yt2);
        cdl.countDown();
        
        exec.shutdown();
        
    }  
}  