package com.one.jvm;

import java.lang.reflect.Field;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName DiretMemoryOOM.java
  * @author jun.wu  
  * @date 2017年2月20日 下午7:29:17 
  * @Description: 使用unsafe分配本机内存
  *  
  * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
  *  (-XX:MaxDirectMemorySize如不指定，默认和java堆的最大值-Xmx一样)
 */
public class DiretMemoryOOM {
	
	private static final int  _1MB = 1025 * 1024;

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		// Unsafe 啥？ jun
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		while(true){
			unsafe.allocateMemory(_1MB);
		}
	}

}
