package com.one.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName OOMObject.java
  * @author jun.wu  
  * @date 2017年2月24日 下午12:11:10 
  * @Description: JConsole监视代码
  * 
  * VM 参数： -Xms100m -Xmx100m -XX:+UseSerialGC
  * 
 */
public class JConsoleTest {

	/**
	  *	内存占位符对象，一个OOMObject大约占64KB
	 */
	static class OOMObject {
		public byte[] placeholder = new byte[64 * 1024];
	}
	
	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<OOMObject>();
		for(int i = 0; i < num; i++){
			//
			Thread.sleep(100);
			list.add(new OOMObject());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
		System.gc();
		System.out.println("over!");
	}

}
