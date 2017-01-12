package com.one.concurrent;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MasterTest {

	public static void main(String[] args) {
		File[] roots = new File[1];
		File file =  new File("E://test");
		roots[0] = file;
		startIndexing(roots);
	}
	
	
	private static void startIndexing(File[] roots) {
		BlockingQueue<File> queue = new LinkedBlockingQueue<File>();
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return true;
			}
		};
		
		for(File root : roots){
			new Thread(new FileCrawler(queue,filter,root)).start();;
		}
		
		for(int i = 0; i < 10; i++){
			new Thread(new Indexer(queue)).start();
		}
		
	}


	private static void test(){
		final BlockingQueue<File> fileQueue = new LinkedBlockingQueue<File>();
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return true;
			}
		};
		File root = new File("E://test");
		FileCrawler fireCrawler = new FileCrawler(fileQueue,filter,root);
		Indexer indexer = new Indexer(fileQueue);

		fireCrawler.run();
		fileQueue.add(new File("E://test/aaa.txt"));
		indexer.run();
		
		
		try {
			Thread.sleep(3000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		indexer.run();
//		fireCrawler.run();
	}

}
