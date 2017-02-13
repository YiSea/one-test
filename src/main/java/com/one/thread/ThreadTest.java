package com.one.thread;

import java.util.concurrent.ThreadFactory;

public class ThreadTest {

	public static void main(String[] args) {
		ThreadFactory tf = new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		

	}

}
