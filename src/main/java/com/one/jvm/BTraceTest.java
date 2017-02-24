package com.one.jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName BTraceTest.java
  * @author jun.wu  
  * @date 2017年2月24日 下午6:18:27 
  * @Description: BTrace 跟踪演示
 */
public class BTraceTest {
	

	public int add(int a, int b){
		return a + b;
	}
	
	public static void main(String[] args) throws IOException {
		BTraceTest test = new BTraceTest();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 10; i++){
			br.readLine();
			int a = (int) Math.round(Math.random() * 1000);
			int b = (int) Math.round(Math.random() * 1000);
			System.out.println(test.add(a, b));
		}
	}
	
	
	/**
	 
	 BTraceTest-脚本代码 VisualVM - BTrace 插件
	 
		import com.sun.btrace.annotations.*;
		import static com.sun.btrace.BTraceUtils.*;
		
		@BTrace
		public class TracingScript {
		    @OnMethod(
		        clazz="com.one.jvm.BTraceTest",
		        method="add",
		        location=@Location(Kind.RETURN)
		    )
		    
		    public static void func(@Self com.one.jvm.BTraceTest instance, int a, int b, @Return int result){
		        println("调用堆栈");    
		        jstack();
		        println(strcat("方法参数A：",str(a)));
		        println(strcat("方法参数B：",str(b)));
		        println(strcat("方法结果：",str(result)));
		    }
		    
		}
	 
	 */

}
