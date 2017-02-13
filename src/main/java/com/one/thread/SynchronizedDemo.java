package com.one.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedDemo implements Runnable{
	private static final String syn = "wow";

	public static void main(String[] args) {
		
		int N_CPUS = Runtime.getRuntime().availableProcessors();
		System.out.println("CPU_N:" + N_CPUS + "\n\n");
		
		SynchronizedDemo synchro = new SynchronizedDemo();
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i<2; i++){
			Thread t = new Thread(synchro);
			exec.execute(t);
		}
		System.out.println(Thread.currentThread().getName() + ":i see one lock");
		synchronized (syn) {
			System.out.println(Thread.currentThread().getName() + ":i get the lock");
			System.out.println(Thread.currentThread().getName() + ":" + syn);
		}
		
		try {
			exec.shutdown();
			exec.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ":i see one lock");
		synchronized(syn){
			System.out.println(Thread.currentThread().getName() + ":i get the lock");

			System.out.println(Thread.currentThread().getName() + ":hei hei");
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
