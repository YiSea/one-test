package com.one.thread.cuncurrenttools;

/**
 * 
  * @Package com.one.thread.cuncurrenttools 
  * @ClassName Computable.java
  * @author jun.wu  
  * @date 2017年1月6日 下午2:33:28 
  * @Description: compute 接口
 */
public interface Computable<A, V> {
	V compute(A arg) throws InterruptedException;
}
