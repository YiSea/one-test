package com.one.thread.cuncurrenttools;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 
  * @Package com.one.thread.cuncurrenttools 
  * @ClassName Memoizer.java
  * @author jun.wu  
  * @date 2017年1月6日 下午2:45:18 
  * @Description: 自定义缓存
 */
public class Memoizer<A, V> implements Computable<A, V> {

	private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	
	private final Computable<A, V> c;
	
	public Memoizer(Computable<A, V> c) {
		this.c = c;
	}
	
	@Override
	public V compute(final A arg) throws InterruptedException{
		while(true){
			Future<V> f = cache.get(arg);
			if(f == null){
				Callable<V> eval = new Callable<V>(){
					public V call() throws InterruptedException{
						return c.compute(arg);
					}
				};
				FutureTask<V> ft = new FutureTask<>(eval);
				f = cache.putIfAbsent(arg, ft);
				if(f == null){
					f = ft;
					ft.run();
				}
			}
			try {
				return f.get();
			} catch (CancellationException e) {
				cache.remove(arg, f);
			}catch (ExecutionException e) {
				// 自己区分
				throw Preloader.launderThrowable(e.getCause());
			}
		}
	}
	

}
