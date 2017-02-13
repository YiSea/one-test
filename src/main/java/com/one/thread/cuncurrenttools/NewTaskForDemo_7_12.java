package com.one.thread.cuncurrenttools;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;

/**
 * 
  * @Package com.one.thread.cuncurrenttools 
  * @ClassName NewTaskForDemo_7_12.java
  * @author jun.wu  
  * @date 2017年1月10日 上午9:45:49 
  * @Description: 通过newTaskFor将非标准的取消操作封装在一个任务中
 */
public class NewTaskForDemo_7_12 {

	/**
	 * CancellableTask中定义了一个CancellableTask接口，该接口扩展了Callable,
	 * 并增加了一个cancel方法和一个newTask工厂方法来构造RunnableFuture。
	 * CancellingExecutor扩展了ThreadPoolExecutor,并通过改写newTaskFor使得
	 * CancellableTask可以创建自己的Future。
	 * 
	 */
	public static void main(String[] args) {

	}

}

@ThreadSafe
class CancellingExecutor extends ThreadPoolExecutor{
	public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	protected<T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if(callable instanceof CancellableTask){
			return ((CancellableTask<T>) callable).newTask();
		}else{
			return super.newTaskFor(callable);
		}
	}
}

abstract class SocketUsingTask<T> implements CancellableTask<T>{
	@GuardedBy("this") private Socket socket;
	
	protected synchronized void setSocket(Socket s){
		socket = s;
	}
	
	public synchronized void cancel(){
		try {
			if(socket != null){
				socket.close();
			}
		} catch (IOException ignored) {
			// ignored
		}
	}
	
	public RunnableFuture<T> newTask(){
		return new FutureTask<T>(this){
			public boolean cancel(boolean mayInterruptIfRunning){
				try {
					SocketUsingTask.this.call();
				} finally {
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}
}



