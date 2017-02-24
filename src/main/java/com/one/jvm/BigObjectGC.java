package com.one.jvm;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName BigObjectGC.java
  * @author jun.wu  
  * @date 2017年2月23日 下午12:29:01 
  * @Description: 大对象直接进入老年代
  * 
  * VM 参数 ： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 */
public class BigObjectGC {
	/**
	 Heap
	 PSYoungGen      total 9216K, used 5245K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	  eden space 8192K, 64% used [0x00000000ff600000,0x00000000ffb1f458,0x00000000ffe00000)
	  from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
	  to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
	 ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	  object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
	 Metaspace       used 2651K, capacity 4486K, committed 4864K, reserved 1056768K
	  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
	 */
	
	private static final int _1MB = 1024 * 1024;
	
	public static void testPretenureSizeThreshold(){
		byte[] allocation1;
		allocation1 = new byte[_1MB]; 
		
		byte[] allocation;
		allocation = new byte[8 * _1MB]; // 直接分配子老年代中
	}
	
	public static void main(String[] args) {
		testPretenureSizeThreshold();
	}

}
