package com.one.jvm;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName MinorGC.java
  * @author jun.wu  
  * @date 2017年2月23日 下午12:12:10 
  * @Description: 新生代MinorGC
 */
public class MinorGC {
	
	private static final int _1MB = 1024 * 1024;
	
	/***
	 *
	 * VM 参数 ： -verbose:gc -Xms20M  -Xmx20M  -Xmn10M   -XX:+PrintGCDetails  -XX:SurvivorRatio=8
	 * 
	 * -Xmn10M -> 新生代分得的内存大小（java堆总大小为20M，且不可扩展）
	 * XX:SurvivorRatio -> 决定新生代中Eden区与一个Survivor区的空间比例是8:1
	 * Heap
		 PSYoungGen      total 9216K, used 7293K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
		 [Eden]      eden space 8192K, 89% used [0x00000000ff600000,0x00000000ffd1f468,0x00000000ffe00000)
		 [surviver 1]from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
		 [surviver 1]to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
		 ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
		  object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000010,0x00000000ff600000)
		 Metaspace       used 2652K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K

	 */
	
	public static void testAllocation(){
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
	}
	
	public static void main(String[] args) {
		testAllocation();
	}

}
