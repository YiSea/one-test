package com.one.concurrent;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Indexer implements Runnable{
	private final BlockingQueue<File> queue;
	
	public Indexer(BlockingQueue<File> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			while(true){
				indexFile(queue.take());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void indexFile(File take) {
		System.out.println(take.getPath() + ":" +take.getName());
	}

}
