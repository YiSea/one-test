package com.one.js;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64.Decoder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
  * @Package com.one.js 
  * @ClassName JavaExecScriptDemo.java
  * @author jun.wu  
  * @date 2016年12月6日 下午6:51:16 
  * @Description: 执行js
 */
public class ScriptEngineManagerDemo {
	  /**
	 * @param args
	 */
	public static void main(String[] args) {
		// 446-425  438-417  Content-Length:436 :params 进行encode编码后 post长度+1
//		String params = "H5RuRCA/BdmMNOVpedbfXWpvBvkNE4TRbKwoajMipm1sBR0BGct1NDDaGtji8ZdeRXX2NywWdJBiE3mQazE+i7LRwEDrHXrsnH457Pfo/kbphyIYHvIjK4j1cSfgtbh5g8cwQcMv1uSsZ0IYOjAwbg==";
//		String entry = "0fde7577afe0015d3db599b3a5595dfe7322bdf3512e62d8bb7733e3a55bfdbda346d4cdf1ac03551b001c6a58466e5780ef27bf4f2b8a1b9990cfb42a56e1bbea012f7a04c50c39f34c0ea7af7734875969f0478adbf6a1a64c26f6b6aa5b9aab613c2eded40c46b13591cc23898f4fcab5566aabe7b005c89ad7849b312763";
//		String afterDecodeparams = URLEncoder.encode(params);
//		String post = "params:" + params + "encSecKey:" +entry;
//		String afterDecodePost = "params:" + afterDecodeparams + "encSecKey:" +entry;
//		System.out.println(post);
//		System.out.println(post.length());
//		System.out.println("BZ:" + "params=jlqrXEr68QhZzvWtidRq9rcsvyC%2BjqUOs0KfX5AmLyOqC7SIvsO4C7omGW%2BNU3gaJQmlXOIsOxZQZyZ2tiH3wPG%2FyNnZ3Emcu1bJBnk9tZD5IFPaLLuwQDzOMdy3BNapyhSU2%2BE31x9I3pfiQZA7Pg%3D%3D&encSecKey=578df712f51def07b8b81b81d310caa4a369eaa94b044b3b773f3931e5faafe3af2662c54c80b96201285030968a4b21d1d76792e20a0416c8aa5200ffb20fe4ce5e9d99a905d705c15844082b29635087084140f1ff5ad3ee335dd2af074c0c4c0e7ec732bdde94683d8462a576217232dc0464450ec0bd300daa0ca53f2780".length());
//		System.out.println(afterDecodePost);
//		System.out.println(afterDecodePost.length());
		
		
		  ScriptEngineManager sem = new ScriptEngineManager();
		  ScriptEngine sengine = sem.getEngineByName("javascript");// nashorn  javascript
		  
		  FileReader test = null;
		  FileReader ntesid = null;
		  FileReader core = null;
		  FileReader jquery = null;
		  FileReader cryptoJs = null;
		try {
			test = new FileReader("E:\\workspace\\work1\\one-test\\js\\163music_secret.js");
			cryptoJs = new FileReader("E:\\workspace\\work1\\one-test\\js\\crypto-js.js");
			jquery = new FileReader("E:\\workspace\\work1\\one-test\\js\\jquery-1.4.2.js");
			ntesid = new FileReader("E:\\workspace\\work1\\one-test\\js\\ntes.id.js");
			core = new FileReader("E:\\workspace\\work1\\one-test\\js\\163core.js");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		  String js = "function a(a) {return \"Hi \" + a;}";
//		  sengine.put("core", core);
		  try {
			  sengine.eval(test);
//			  sengine.eval(jquery);
//			  sengine.eval(ntesid);
//			  sengine.eval(core);
			  Invocable invoke = (Invocable) sengine;  
			  Object c = invoke.invokeFunction("d",new Object[]{"{\"userId\":\"39002\",\"offset\":\"20\",\"total\":\"false\",\"limit\":\"20\",\"csrf_token\":\"\"}","010001","00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7","0CoJUm6Qyw8W8jud"});
			  
			  String[] cc = String.valueOf(c).split("，");
			  String myPost = "params=" + URLEncoder.encode(cc[0]) + "&encSecKey=" + cc[1];
			  System.out.println(myPost);
			  System.out.println(myPost.length());
		  } catch (Exception e) {
			e.printStackTrace();
		}
	  }
		

}

//"mgJmPcrJouFYpI9OZViUlypwy6dtzZFH8qMUk/PRDptT+6VApxOIqMgYNoI3j1jNTr57DC9od03cSLMsZr5mkWKccrGf0WDVHUoPtwKZfPVEjui43755xVmsqidsd4s+"
//"mgJmPcrJouFYpI9OZViUlypwy6dtzZFH8qMUk/PRDptT+6VApxOIqMgYNoI3j1jNTr57DC9od03cSLMsZr5mkWKccrGf0WDVHUoPtwKZfPVEjui43755xVmsqidsd4s+"




