package com.one.concurrent;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

public class FileCrawler implements Runnable{
	private final BlockingQueue<File> fileQueue;
	
	private final FileFilter fileFilter;
	
	private final File root;// = new File("E://test");
	
	public FileCrawler(BlockingQueue<File> fileQueue,FileFilter fileFilter, File root) {
		this.fileQueue = fileQueue;
		this.fileFilter = fileFilter;
		this.root = root;
	} 

	@Override
	public void run() {
		try {
			crawler(root);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void crawler(File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);
		if(entries != null){
			for(File entry : entries){
				if(entry.isDirectory()){
					crawler(entry);
				}else if(!fileQueue.contains(entry)){
					fileQueue.put(entry);
				}
			}
		}
	}

}

