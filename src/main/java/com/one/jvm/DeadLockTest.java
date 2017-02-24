package com.one.jvm;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName DeadLockTest.java
  * @author jun.wu  
  * @date 2017年2月24日 下午3:31:09 
  * @Description: 死锁代码样例
  * 
 */
public class DeadLockTest {
	
	static class SynAddRunable implements Runnable{
		static int a,b;
		
		public SynAddRunable(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public void run() {
			/**
			 * 死锁原因：
			 * 	   Integer.valueOf()方法基于减少对象创建次数和节省内存的考虑，
			 * [-128 ~ 127]之间的数字会被缓存，当valueOf()方法传入参数在这个范围之内，
			 * 将直接返回缓存中的对象，也就是说，代码中调用了上百次Integer.valueOf()方法，
			 * 一共就返回了两个不同的对象。
			 */
			synchronized (Integer.valueOf(a)) {  
				synchronized (Integer.valueOf(b)) {
					System.out.println(a + b);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 100; i++){
			new Thread(new SynAddRunable(1, 2)).start();
			new Thread(new SynAddRunable(2, 1)).start();
		}
		
	}

}
