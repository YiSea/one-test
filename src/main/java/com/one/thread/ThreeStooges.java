package com.one.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * 
  * @Package com.one.thread 
  * @ClassName ThreeStooges.java
  * @author jun.wu  
  * @date 2016年10月31日 下午5:58:14 
  * @Description: 在可变对象基础上构建的不可变类(ThreeStooges不可变)
 */
public final class ThreeStooges {

	// set是可变的
	private final Set<String> stooges = new HashSet<String>();
	
	public ThreeStooges(){
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
	}
	
	public boolean isStooge(String name){
		return stooges.contains(name);
	}
	
	class testThread extends Thread{
		ThreeStooges ts1 = new ThreeStooges();
//		ts1.stooges.remove("Moe");
	}
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 3; i++){
			new Thread().start();
		}
		
		ThreeStooges ts = new ThreeStooges();
		System.out.println(ts.isStooge("Moe"));
		ts.stooges.remove("Moe");
		System.out.println(ts.isStooge("Moe"));
		ts.stooges.add("111");
		System.out.println(ts.isStooge("111"));
		
//		ts.stooges = new HashSet<String>(); // 不行哦，final修身，不可变的对象引用，但是对象是可以变的，即：stooges：add,remove。。。。
	}

}
