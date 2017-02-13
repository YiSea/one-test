package com.one.thread;

import java.awt.Event;
import java.util.EventListener;

/**
 * 
  * @Package com.one.thread 
  * @ClassName ThisEscape.java
  * @author jun.wu  
  * @date 2016年10月31日 上午10:26:40 
  * @Description: 隐式地使this引用逸出（don't do it like this)
 */
public class ThisEscape {
	public ThisEscape(EventSource source){
		source.registerListener(new EventListener(){
			public void onEvent(Event e){
				doSomething(e);
			}
		});
	}

	protected void doSomething(Event e) {
		System.out.println("even,lala~");
	}
	
	public static void main(String argsp[]){
		ThisEscape thisEscape = new ThisEscape(new EventSource());
	}
}
