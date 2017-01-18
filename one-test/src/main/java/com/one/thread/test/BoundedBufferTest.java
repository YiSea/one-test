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
					int unused = bb.take();
					fail();
				}catch (InterruptedException success){
					// 中断意味着达到目的，成功！
				}
			}
		};
		
		try {
			taker.start();
			Long LOCKUP_DETECT_TIMEOUT = 10l;// 寓意为何~
			Thread.sleep(LOCKUP_DETECT_TIMEOUT);
			taker.interrupt();
			taker.join(LOCKUP_DETECT_TIMEOUT);
			assertFalse(taker.isAlive());
		} catch (Exception unexpected) {
			// 意料之外的异常，失败!
			fail();
		}
		
	}
	
}
