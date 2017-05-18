package com.one.thread;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 
  * @Package com.one.thread 
  * @ClassName ThreadPoolTest.java
  * @author jun.wu  
  * @date 2017年3月27日 下午5:57:50 
  * @Description: 此处添加类描述……
 */
public class ThreadPoolTest {
  public static void main(String args[]) {
	  /********* test jun*********/
	  Executors.newCachedThreadPool();
	  Semaphore sema = new Semaphore(1);
	  try {
		  sema.acquire();
	  } catch (InterruptedException e) {
		
	  }finally{
	  	   sema.release();
	  }
	 /*********sema test jun*********/
	 
	 
	 
    Random random = new Random();
    // 建立一个容量为3的固定尺寸的线程池
    ExecutorService executor = Executors.newFixedThreadPool(2);
    // 判断可是线程池可以结束
    int waitTime = 50;
    for (int i = 0; i < 6; i++) {
      String name = "线程 " + i;
      int time = random.nextInt(1000);
      waitTime += time;
      Runnable runner = new ExecutorThread(name, time);
      System.out.println("增加: " + name + " / " + time);
      executor.execute(runner);
    }
    try {
      Thread.sleep(waitTime);
      executor.shutdown();
      executor.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
    } catch (InterruptedException ignored) {
    }
  }
}
class ExecutorThread implements Runnable {
  private final String name;
  private final int delay;
  public ExecutorThread(String name, int delay) {
    this.name = name;
    this.delay = delay;
  }
  public void run() {
    System.out.println("启动: " + name);
    try {
      Thread.sleep(delay);
    } catch (InterruptedException ignored) {
    }
    System.out.println("完成: " + name);
  }
}
