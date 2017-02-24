package com.one.jvm;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName JavaVMStackOOM.java
  * @author jun.wu  
  * @date 2017年2月20日 下午5:09:42 
  * @Description: 创建线程导致内存溢出异常
  * VM Args: -Xss2M
 */
public class JavaVMStackOOM {
	/**
	 * !!!!!!!!!
	 * !!!!! warning !!!!!!!
	 * 特别提示：
	 * 		在windows平台的虚拟机汇总，java的线程是映射到操作系统的内核线程上的，
	 * 执行该代码有较大风险，导致操作系统假死~~强迫关机~（记得保存资料）
	 * 
	 * !!!!!!!!!
	 */
	private void dontStop(){
		while(true){
			System.out.println("woooow");
		}
	}

	public void stackLeakByThread(){
		while(true){
			Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {
					dontStop();
				}
				
			});
			thread.start();
		}
	}
	
	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
		
	}

}
