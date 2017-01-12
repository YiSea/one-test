package com.one.thread.cuncurrenttools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
  * @Package com.one.thread.cuncurrenttools 
  * @ClassName InterruptedDemo.java
  * @author jun.wu  
  * @date 2017年1月9日 下午6:19:50 
  * @Description: （cuncurrency java program 程序清单7-9）在专门的线程中中断任务：
  * 	不足：依赖于一个限时的join,因此存在着join的不足：无法知道执行控制是因为线程
  * 	正常退出而返回还是因为join超时而返回。
  * 	【这是Thrad API的一个缺陷】
 */
public class InterruptedDemo7_9 {
	private static final ScheduledExecutorService cancelExcec = Executors.newScheduledThreadPool(1);
	
	public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException{
		class RethrowableTask implements Runnable {
			private volatile Throwable t;
			public void run(){
				try {
					r.run();
				} catch (Throwable e) {
					this.t = t;
				}
			}
			
			void rethrow(){
				if(t != null){
					// 自定义的异常区分方法
					throw Preloader.launderThrowable(t);
				}
			}
		}
		
		RethrowableTask task = new RethrowableTask();
		final Thread taskThread = new Thread(task);
		taskThread.start();
		cancelExcec.schedule(new Runnable() {
			
			@Override
			public void run() {
				taskThread.interrupt();
			}
		}, timeout, unit);
		taskThread.join(unit.toMicros(timeout));
		task.rethrow();
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
