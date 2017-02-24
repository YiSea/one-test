package com.one.jvm;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName JavaVMStackSOF.java
  * @author jun.wu  
  * @date 2017年2月20日 下午4:35:40 
  * @Description: 虚拟机栈和本地方法栈OOM测试
  * 
  * VM Args: -Xss128k 虚拟机栈容量参数设置
 */
public class JavaVMStackSOF {
	private int stackLength = 1;
	
	public void stackLeak() throws Exception{
		stackLength ++;
		stackLeak();
	}

	public static void main(String[] args) throws Exception {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Exception e) {
			System.out.println("stack length:" + oom.stackLength);
			throw e;
		}
		
	}

}
