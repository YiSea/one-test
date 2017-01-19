package com.one.thread.test;

import junit.framework.TestCase;

/**
 * 
  * @Package com.one.thread.test 
  * @ClassName BoundedBufferTest.java
  * @author jun.wu  
  * @date 2017年1月18日 下午6:41:20 
  * @Description: BoundedBuffer的基本单元测试
 */
public class BoundedBufferTest extends TestCase {
	public void testIsEmptyWhenConstruted(){
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		assertTrue(bb.isEmpty());
	}
	
	public void testIsFullAfterPuts() throws InterruptedException{
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		for(int i = 0; i < 10; i++){
			bb.put(i);
		}
		assertTrue(bb.isFull());
	}
	
	public void testIsFullWhenConstruted(){
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		assertFalse(bb.isFull());
	}
	
	public void testIsEmptyAfterPuts() throws InterruptedException{
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		for(int i = 0; i < 10; i++){
			bb.put(i);
		}
		assertFalse(bb.isEmpty());
	}
	
	/**
	 * 测试阻塞行为以及对中断的响应
	 */
	public void testTakeBlocksWhenEmpty(){
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		Thread taker = new Thread(){
			public void run(){
				try{
					int unused = bb.take(); // 应该在这里发生阻塞中断
					fail(); 				// 到达这里，法儿说明没有阻塞中断，是不真长的，故失败！
				}catch (InterruptedException success){
					// 中断意味着达到目的，成功！
					System.out.println("..Interrupted.." + success.getMessage());
				}
			}
		};
		
		try {
			taker.start();
			Long LOCKUP_DETECT_TIMEOUT = 10000l;// 寓意为何~
			Thread.sleep(LOCKUP_DETECT_TIMEOUT);
			taker.interrupt(); 	// 使得availableItems.acquire();阻塞终止，抛出中断异常,线程结束。
			taker.join(LOCKUP_DETECT_TIMEOUT);// 意义？
			assertFalse(taker.isAlive());	// 取不到东西，线程已经阻塞中断，
		} catch (Exception unexpected) {
			// 意料之外的异常，失败!
			fail();
		}
		
	}
	
}
