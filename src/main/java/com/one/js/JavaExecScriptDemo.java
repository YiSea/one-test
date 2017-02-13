package com.one.js;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 
  * @Package com.one.js 
  * @ClassName JavaExecScriptDemo.java
  * @author jun.wu  
  * @date 2016年12月6日 下午6:51:16 
  * @Description: 执行js
 */
public class JavaExecScriptDemo {
	  public static void main(String[] args) {
		    System.out.println(jsObjFunc());
//		    System.out.println(Arrays.toString(getArray()));
		    System.out.println(jsCalculate("a=1+2+3+(2*2)"));
		    jsFunction();
		    jsVariables();
		  }
		 
		  /**
		   * 运行JS对象中的函数
		   * 
		   * @return
		   */
		  public static Object jsObjFunc() {
		    String script =
		        "var obj={run:function(){return 'run method : return:\"abc'+this.next('test')+'\"';},next:function(str){return ' 我来至next function '+str+')'}}";
		    ScriptEngineManager sem = new ScriptEngineManager();
		    ScriptEngine scriptEngine = sem.getEngineByName("js");
		    try {
		      scriptEngine.eval(script);
		      Object object = scriptEngine.get("obj");
		      Invocable inv2 = (Invocable) scriptEngine;
		      return (String) inv2.invokeMethod(object, "run");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return null;
		  }
		 
		  /**
		   * 获取js对象数字类型属性
		   * 
		   * @return
		   */
		  public static Object[] getArray() {
		    ScriptEngineManager sem = new ScriptEngineManager();
		    String script = "var obj={array:['test',true,1,1.0,2.11111]}";
		 
		    ScriptEngine scriptEngine = sem.getEngineByName("js");
		    try {
		      scriptEngine.eval(script);
		      Object object2 = scriptEngine.eval("obj.array");
		      Class<?> clazz = object2.getClass();
		      Field denseField = clazz.getDeclaredField("dense");
		      denseField.setAccessible(true);
		      return (Object[]) denseField.get(object2);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return null;
		  }
		 
		  /**
		   * JS计算
		   * 
		   * @param script
		   * @return
		   */
		  public static Object jsCalculate(String script) {
		    ScriptEngineManager manager = new ScriptEngineManager();
		    ScriptEngine engine = manager.getEngineByName("javascript");
		    try {
		      return (Object) engine.eval(script);
		    } catch (ScriptException ex) {
		      ex.printStackTrace();
		    }
		    return null;
		  }
		 
		  /**
		   * 运行JS基本函数
		   */
		  public static void jsFunction() {
		    ScriptEngineManager sem = new ScriptEngineManager();
		    ScriptEngine se = sem.getEngineByName("javascript");
		    try {
		      String script = "function say(name){ return 'hello,'+name; }";
		      se.eval(script);
		      Invocable inv2 = (Invocable) se;
		      String res = (String) inv2.invokeFunction("say", "test");
		      System.out.println(res);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }
		 
		  /**
		   * JS中变量使用
		   */
		  public static void jsVariables() {
		    ScriptEngineManager sem = new ScriptEngineManager();
		    ScriptEngine engine = sem.getEngineByName("javascript");
		    File file = new File("/data/js.txt");
		    engine.put("file", file);
		    try {
		      engine.eval("println('path:'+file.getAbsoluteFile())");
		    } catch (ScriptException e) {
		      e.printStackTrace();
		    }
		 
		  }

	

}

