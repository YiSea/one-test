package com.one.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.TestCase;

/**
 * 
  * @Package com.one.thread.test 
  * @ClassName TestingThreadFactory.java
  * @author jun.wu  
  * @date 2017年1月19日 下午5:00:08 
  * @Description: 测试ThreadPoolFactory的线程工厂类
 */
public class TestingThreadFactory  extends  TestCase implements ThreadFactory {
	public final AtomicInteger numCreated = new AtomicInteger();
	private final ThreadFactory factory = Executors.defaultThreadFactory();

	@Override
	public Thread newThread(Runnable r) {
		numCreated.incrementAndGet();
		return factory.newThread(r);
	}
	
	public void testPoolExpansion() throws InterruptedException{
		int MAX_SIZE = 10;
		ExecutorService exec = Executors.newFixedThreadPool(MAX_SIZE);
		
		for(int i = 0; i < 10 * MAX_SIZE; i++){
			exec.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(Long.MAX_VALUE);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			});
		}
		
		for(int i = 0; i < 20 && this.numCreated.get() < MAX_SIZE; i++){
			Thread.sleep(100);
		}
		
		assertNotSame(this.numCreated.get(), MAX_SIZE);
//		assertEquals(this.numCreated.get(), MAX_SIZE);
		exec.shutdownNow();
	}

}
