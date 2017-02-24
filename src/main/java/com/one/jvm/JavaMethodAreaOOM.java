package com.one.jvm;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
  * @Package com.one.jvm 
  * @ClassName JavaMethodAreaOOM.java
  * @author jun.wu  
  * @date 2017年2月20日 下午6:22:12 
  * @Description: 借助CGLib使得方法区出现内存溢出异常
  * 
  * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class JavaMethodAreaOOM {
	// 没出现一场呢。。。。jun 
	
	public static void main(String[] args) {
		while(true){
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object obj, Method method, Object[] args,
						MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(obj, args);
				}
			});
			enhancer.create();
		}
		
	}
	
	static class OOMObject{
		
	}

}
