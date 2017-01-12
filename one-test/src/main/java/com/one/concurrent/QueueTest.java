package com.one.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueTest {

	public static void main(String[] args) {
		/*BlockingQueue<String> bqueue = null;
//		bqueue = new LinkedBlockingQueue<>();
		bqueue = new ArrayBlockingQueue<>(10);//FIFO
		bqueue = new PriorityBlockingQueue<>();// 按优先级排序  comparable/comparator
		bqueue = new SynchronousQueue<>();// 生产者->直接交付->消费者   不会为队列中元素维护存储空间
		try {
			bqueue.put("1");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		
		ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
		System.out.println(map.putIfAbsent("a", "1"));;
		System.out.println(map.putIfAbsent("b", "2"));;
		System.out.println(map.putIfAbsent("c", "3"));;
		System.out.println(map.putIfAbsent("a", "1"));

	}

}
