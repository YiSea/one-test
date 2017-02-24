package com.one.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName RuntimeConstantPoolOOM.java
  * @author jun.wu  
  * @date 2017年2月20日 下午6:02:01 
  * @Description: 运行时常量池导致的内存溢出异常
  * 
  * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM {
	/**
		老实说，我怎么没遇到溢出。。OOM
	 */
	public static void main(String[] args) {
		// 使用List保持着常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		// 10MB的PermSize在integer范围内足够产生OOM了
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
			System.out.println(i);
		}
		
	}

}
