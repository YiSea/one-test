package com.one.thread.cuncurrenttools;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
  * @Package com.one.thread.cuncurrenttools 
  * @ClassName InterruptedDemo.java
  * @author jun.wu  
  * @date 2017年1月9日 下午6:19:50 
  * @Description: 通过Future来实现取消（cuncurrency java program 程序清单7-10）
  * 
  * 
  * ！！！除非你知道线程的中断策略，否则，不要中断线程！！！
  * 
  * 
 */
public class InterruptedDemo7_10 {
	private static final ScheduledExecutorService cancelExcec = Executors.newScheduledThreadPool(1);
	private static final ExecutorService taskExec = Executors.newFixedThreadPool(1);
	
	public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException{
		Future<?> task = taskExec.submit(r);
		try {
			task.get(timeout, unit);
		} catch (TimeoutException e) {
			// 接下来任务将被取消
		} catch(ExecutionException e){
			// 如果在任务中抛出了异常，那么重新抛出该异常
			throw Preloader.launderThrowable(e.getCause());
		} finally{
			// 如果任务已经结束，那么执行取消操作也不会带来任何影响
			// 如果任务正在运行，那么将被中断
			task.cancel(true);
			/**
			 * 当Future抛出InterruptedException或TimeoutException时，
			 * 如果你知道不再需要结果，那么就可以调用Future.cancel来取消任务。
			 */
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
