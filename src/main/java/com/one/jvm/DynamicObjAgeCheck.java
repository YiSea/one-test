package com.one.jvm;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName DynamicObjAgeCheck.java
  * @author jun.wu  
  * @date 2017年2月23日 下午3:02:41 
  * @Description: 动态对象年龄判定
  * 
  * VM 参数： -verbose:gc  -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=9 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution   
  * 
 */
public class DynamicObjAgeCheck {
	
	/**
	 * 
	 * 这结果咋这样啊。。。不对啊
	 * Heap
	 PSYoungGen      total 9728K, used 1292K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	  eden space 9216K, 14% used [0x00000000ff600000,0x00000000ff743038,0x00000000fff00000)
	  from space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
	  to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
	 ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	  object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
	 Metaspace       used 2651K, capacity 4486K, committed 4864K, reserved 1056768K
	  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
	 */
	
	private static final int _1MB = 1024 * 1024;
	
	@SuppressWarnings("unused")
	public static void testTenuringThreshold(){
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[_1MB / 4];
		// allocation1 + allocation2 大于survivor空间的一半
		allocation2 = new byte[_1MB / 4];
		allocation3 = new byte[4 * _1MB];
		allocation4 = new byte[4 * _1MB];
		allocation4 = null;
		allocation4 = new byte[4 * _1MB];
	}

	public static void main(String args[]){
		
	}
	
}
