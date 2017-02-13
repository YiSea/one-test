package com.one.thread.cuncurrenttools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
  * @Package com.one.thread.cuncurrenttools 
  * @ClassName Preloader.java
  * @author jun.wu  
  * @date 2017年1月5日 下午12:06:17 
  * @Description: FutureTask
 */
public class Preloader {
	/**
	 * 川建一个FutureTask，其中包含从数据库加载产品信息的任务(callable)
	 * 以及一个执行运算的线程
	 * 
	 * callable：表示的任务可以抛出受检查的或未受检查的异常，
	 * 并且任何代码都可以抛出一个Error
	 */
	private final FutureTask<ProductInfo> future =
			new FutureTask<>(new Callable<ProductInfo>() {
				public ProductInfo call() throws Exception{// DataLoadException
					return loadProductInfo();
				}
			});
	
	private final Thread thread = new Thread(future);
	
	/**
	 * 在构造函数或者静态初始化方法中启动线程不是一种好方法
	 * 提供了一个start方法来启动线程
	 */
	public void start(){
		thread.start();
	}
	
	/**
	 * 加载产品信息
	 * @return
	 */
	protected ProductInfo loadProductInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public ProductInfo get() throws InterruptedException, ExecutionException{
		/**
		 * ExecutionException:
		 * 1、Callable抛出的受检查异常 RuntimeException,以及Error
		 * 2、
		 */
		return future.get();
		
	}
	
	/**
	 * 强制将未检查的Throwable转换为RuntimeException
	 * @param t
	 * @return
	 */
	public static RuntimeException launderThrowable(Throwable t){
		if(t instanceof RuntimeException){
			return (RuntimeException) t;
		}else if (t instanceof Error){
			throw (Error)t;
		}else
			throw new IllegalStateException("Not uncheked", t);
	}
	
}

/**
 * 
  * @Package com.one.thread.cuncurrenttools 
  * @ClassName ProductInfo.java
  * @author jun.wu  
  * @date 2017年1月5日 下午12:40:03 
  * @Description:  产品javabean
 */
class ProductInfo{
	
}
