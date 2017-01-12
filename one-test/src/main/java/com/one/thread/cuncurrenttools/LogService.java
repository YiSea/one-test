package com.one.thread.cuncurrenttools;

import java.io.PrintWriter;
import java.io.Writer;
import java.lang.Character.UnicodeScript;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.http.annotation.GuardedBy;

/**
 * 
  * @Package com.one.thread.cuncurrenttools 
  * @ClassName LogWriter.java
  * @author jun.wu  
  * @date 2017年1月10日 上午10:52:27 
  * @Description: 向LoggerWriter添加可靠的取消操作《Java Concurency in Practice》demo.7_15
 */
public class LogService {
	/**
	 * 
	 * 为LogWriter提供可靠关系操作的方法是解决竞态条件问题，
	 * 因为要使日志消息的提交操作成为原子操作。然而，我们不希望
	 * 在消息加入队列时去持有一个锁，因为put方法本身就可以阻塞。
	 * 我们采用的方法是：通过原子方式来检查关闭请求，并有条件的
	 * 递增一个计数器来“保持”提交消息的权利，如LogService所示。
	 * 
	 * 
	 */
	
	private final BlockingQueue<String> queue;
	private final LoggerThread loggerThread;
	private final PrintWriter writer;
	@GuardedBy("this") private boolean isShutdown;
	@GuardedBy("this") private int reservations;

	public LogService(Writer writer){
		this.writer = (PrintWriter) writer;
		this.queue = new LinkedBlockingQueue<String>(1000);
		this.loggerThread = new LoggerThread();
	}
	
	public void start(){
		loggerThread.start();
		System.out.println("logthread is start");
	}
	
	public void stop(){
		synchronized(this){
			isShutdown = true;
		}
		loggerThread.interrupt();
		System.out.println("logthread stoped.  || reservations:" + reservations);
	}
	
	public void log(String msg) throws InterruptedException{
		synchronized(this){
			if(isShutdown){
				throw new IllegalStateException("log is shutdown");
			}
			++ reservations;// 入队列日志数+1
		}
		queue.put(msg);
		System.out.println("put log to queue No." + reservations);
	}
	
	// 异步日志线程
	private class LoggerThread extends Thread{
		public void run(){
			try {
				while(true){
					try {
						synchronized(LogService.this){
							if(isShutdown && reservations == 0){
								break;
							}
						}
						String msg = queue.take();
						synchronized(LogService.this){
							-- reservations;
						}
						writer.println("due log. No." + msg);
					} catch (InterruptedException e) {
						/*
						 * retry
						 */
					}
					try {
						this.sleep(100);
						System.out.println(".。zZZZ");
					} catch (InterruptedException e) {
						System.out.println("wow interrupted!!! who? fuck!!");
					}
				}
			} finally {
				writer.close();
				System.out.println("writer is closed");
			}
		}
	}
	
	
	/**********demo 7-16 使用ExecutorService的日志服务*************/
	private final ExecutorService exec = Executors.newSingleThreadExecutor();
	public void stopByExuc() throws InterruptedException {
		exec.shutdown();
		try {
			exec.awaitTermination(10, TimeUnit.MINUTES);
		} finally {
			writer.close();
		}
	}
	public void logByExuc(String msg){
		try {
			exec.execute(new WriteTask(msg));
		} catch (RejectedExecutionException ignored) {
			// ignored
		}
	}
	
	private class WriteTask extends Thread{
		public WriteTask(String msg) {
			// TODO ...
		}
		
		// TODO ...
		
	}
	/***********************/

	public static void main(String args[]){
		PrintWriter wirter = new PrintWriter(System.out);
		LogService logservice = new LogService(wirter);
		logservice.start();
		for(int i = 0; i < 1000; i++){
			try {
				logservice.log(String.valueOf(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logservice.stop();
		for(int i = 0; i < 10; i++){
			try {
				logservice.log(String.valueOf(i));
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}



