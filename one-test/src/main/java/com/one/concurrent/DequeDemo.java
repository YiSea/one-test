package com.one.concurrent;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
  * @Package com.one.concurrent 
  * @ClassName DequeDemo.java
  * @author jun.wu  
  * @date 2017年1月4日 下午3:24:59 
  * @Description: 双端队列与工作密取
 */
public class DequeDemo {
	public static void main(String args[]){
		/**
		 * 非常适用于：即是消费者也是生产者问题-当执行某个工作时可能导致出现更多的工作。eg:爬虫。
		 */
		Deque<String> deque = new ArrayDeque<>();// new LinkedBlockingDeque<>();
		deque.push("1");
		deque.push("2");
		deque.push("3");
		System.out.println(deque.pop());
	}
}
