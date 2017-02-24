package com.one.jvm;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName OlderDayByDay.java
  * @author jun.wu  
  * @date 2017年2月23日 下午2:41:49 
  * @Description: 长期存活的对象进入老年代
  * 
  * VM参数： -verbose:gc -Xms20M  -Xmx20M  -Xmn10M   -XX:+PrintGCDetails  -XX:SurvivorRatio=8  -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution   
 */
public class OlderDayByDay {
	/**
	 -XX:MaxTenuringThreshold=1 : 默认15，设置长期进入老年代的年纪，没Minnor GC 一次就长大一岁，直到老去...
	 Heap
	 PSYoungGen      total 9216K, used 5501K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	  eden space 8192K, 67% used [0x00000000ff600000,0x00000000ffb5f478,0x00000000ffe00000)
	  from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
	  to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
	 ParOldGen       total 10240K, used 8192K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	  object space 10240K, 80% used [0x00000000fec00000,0x00000000ff400020,0x00000000ff600000)
	 Metaspace       used 2652K, capacity 4486K, committed 4864K, reserved 1056768K
	  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
	  
	  
	  -XX:MaxTenuringThreshold=15 ：？？ 咋一样捏~~
	  Heap
	 PSYoungGen      total 9216K, used 5501K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	  eden space 8192K, 67% used [0x00000000ff600000,0x00000000ffb5f478,0x00000000ffe00000)
	  from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
	  to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
	 ParOldGen       total 10240K, used 8192K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	  object space 10240K, 80% used [0x00000000fec00000,0x00000000ff400020,0x00000000ff600000)
	 Metaspace       used 2652K, capacity 4486K, committed 4864K, reserved 1056768K
	  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K

	 */
	
	private static final int _1MB = 1024 * 1024;
	
	public static void testTenuringThreshold(){
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];
		// 什么时候进入老年代取决于XX:MaxTenuringThreshold设置
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
		
		
	}
	
	public static void main(String[] args) {
		testTenuringThreshold();
	}

}
