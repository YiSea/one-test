package com.one.thread.test;

import java.util.concurrent.Semaphore;

import org.apache.http.annotation.GuardedBy;

/**
 * 
  * @Package com.one.thread.test 
  * @ClassName BoundedBuffer.java
  * @author jun.wu  
  * @date 2017年1月18日 下午6:43:08 
  * @Description: 基于信号量的有界缓存
 */

//@ThreadSafe // 啥玩意~
public class BoundedBuffer<E> {
	private final Semaphore availableItems, availableSpaces;		// 
	@GuardedBy("this") private final E[] items;
	@GuardedBy("this") private int putPosition = 0, takePosition = 0;
	
	@SuppressWarnings("unchecked")
	public BoundedBuffer(int capacity){
		availableItems = new Semaphore(0);
		availableSpaces = new Semaphore(capacity);
		items = (E[]) new Object[capacity];
	}
	
	public boolean isEmpty(){
//		System.out.println("isEmpty");
		return availableItems.availablePermits() == 0;
	}
	
	public boolean isFull(){
//		System.out.println("isFull");
		return availableSpaces.availablePermits() == 0;
	}
	
	public void put(E x) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + "puting...");
		availableSpaces.acquire();
		doInsert(x);
		availableItems.release();
		System.out.println(Thread.currentThread().getName() + "put over!");
	}
	
	public E take() throws InterruptedException{
		System.out.println(Thread.currentThread().getName() + ":taking...");
		availableItems.acquire();
		E item = doExtract();
		availableSpaces.release();
		System.out.println(Thread.currentThread().getName() + "take over!");
		return item;
	}


	private synchronized void doInsert(E x) {
		int i = putPosition;
		items[i] = x;
		putPosition = (++i == items.length?0:i);
	}
	
	private synchronized E doExtract() {
		int i = takePosition;
		E item = items[i];
		items[i] = null;
		takePosition = (++i == items.length?0:i);
		return item;
	}
}








