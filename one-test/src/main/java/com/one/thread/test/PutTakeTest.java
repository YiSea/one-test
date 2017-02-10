package com.one.thread.test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.TestCase;

/**
 * 
  * @Package com.one.thread.test 
  * @ClassName PutTakeTest.java
  * @author jun.wu  
  * @date 2017年1月19日 上午11:16:40 
  * @Description: 测试BoundedBuffer的生产者-消费者程序
 */
public class PutTakeTest extends TestCase{
	private static final ExecutorService pool = Executors.newCachedThreadPool();
	private final AtomicInteger putSum = new AtomicInteger(0);
	private final AtomicInteger takeSum = new AtomicInteger(0);
	private final CyclicBarrier barrier;	// 栅栏
	private final BoundedBuffer<Integer> bb;
	private final int nPairs;	// put  number
	private final int nTrials;	// take number
	
	public static void main(String args[]){
//		System.out.println(1<<1);
		
		new PutTakeTest(10, 1, 1).test(); // 示例参数
		pool.shutdown();
	}
	
	PutTakeTest(int capacity, int npairs, int ntrials) {
		this.bb = new BoundedBuffer<Integer>(capacity);
		this.nTrials = ntrials;
		this.nPairs = npairs;
		this.barrier = new CyclicBarrier(npairs * 2);// 工作者线程数量 再加1
	}
	
	/**
	 * 适合在测试中使用的随机数生成器
	 * @param seed
	 * @return
	 */
	public static int xorShift(int y) {
		y ^= (y << 6);
		y ^= (y >>> 21);
		y ^= (y << 7);
		return y;
	}

	private void test() {
		try {
			for(int i = 0; i < nPairs; i++){
				pool.execute(new Producer());
				pool.execute(new Consumer());
			}
			System.out.println("--------reader-------");
			barrier.await();	// 等待所有的线程就绪
			System.out.println("--------go-------");
			barrier.await();	// 等待所有的线程执行完成
			System.out.println("--------over-------");
			assertEquals(putSum.get(), takeSum.get());
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
	
	/**
	 * 
	  * @Package com.one.thread.test 
	  * @ClassName Producer.java
	  * @author jun.wu  
	  * @date 2017年1月19日 上午11:48:23 
	  * @Description: 内部类 生产者
	 */
	class Producer implements Runnable{

		@Override
		public void run() {
			try {
				int seed = (this.hashCode() ^ (int)System.nanoTime());
				int sum = 0;
				barrier.await();
				for(int i = nPairs; i > 0; --i){
					bb.put(seed);
					sum += seed;
					seed = xorShift(seed);
//					System.out.println(seed);
				}
				putSum.getAndAdd(sum);
				System.out.println(sum);
//				barrier.wait();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
		}

	}
	
	/**
	 * 
	  * @Package com.one.thread.test 
	  * @ClassName Consumer.java
	  * @author jun.wu  
	  * @date 2017年1月19日 上午11:48:40 
	  * @Description: 内部类-消费者
	 */
	class Consumer implements Runnable{

		@Override
		public void run() {
			try {
				barrier.await();
				int sum = 0;
				for(int i = nTrials; i > 0; --i){
					sum += bb.take();
//					System.out.println(sum);
				}
				takeSum.getAndAdd(sum);
				System.out.println(sum);
//				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
		}
		
	}
	
}
