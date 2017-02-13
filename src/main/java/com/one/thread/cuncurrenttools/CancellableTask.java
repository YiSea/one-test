package com.one.thread.cuncurrenttools;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * 
  * @Package com.one.thread.cuncurrenttools 
  * @ClassName CancellableTask.java
  * @author jun.wu  
  * @date 2017年1月10日 上午9:51:40 
  * @Description:《java conccurrent program》 demo 7_12
 */
public interface CancellableTask<T> extends Callable<T> {
	void cancel();
	RunnableFuture<T> newTask();
}
