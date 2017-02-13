package com.one.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupTest {
	public static void main(String args[]){
		
		
		String url = "https://github.com/";
		try {
			/**
			 * 1. 从一个URL加载一个Document
				存在问题
				你需要从一个网站获取和解析一个HTML文档，并查找其中的相关数据。你可以使用下面解决方法：
				
				解决方法
				使用 Jsoup.connect(String url)方法:
				
				Document doc = Jsoup.connect("http://example.com/").get();
				String title = doc.title();
				说明
				connect(String url) 方法创建一个新的 Connection, 和 get() 取得和解析一个HTML文件。如果从该URL获取HTML时发生错误，便会抛出 IOException，应适当处理。			
			 */
			Document doc = Jsoup.connect(url).get();
			
			/*******************************************/
			
			/**
			 * 2. Connection 接口还提供一个方法链来解决特殊请求，具体如下：
			 * 这个方法只支持Web URLs (http和https 协议); 
			 * 假如你需要从一个文件加载，可以使用 parse(File in, String charsetName) 代替。
			 */
			/**
			 * 报这个错误：·——·.......
			 * org.jsoup.HttpStatusException: HTTP error fetching URL. Status=404, URL=https://github.com/
				at org.jsoup.helper.HttpConnection$Response.execute(HttpConnection.java:590)
				at org.jsoup.helper.HttpConnection$Response.execute(HttpConnection.java:540)
				at org.jsoup.helper.HttpConnection.execute(HttpConnection.java:227)
				at org.jsoup.helper.HttpConnection.post(HttpConnection.java:222)
				at com.one.jsoup.JsoupTest.main(JsoupTest.java:42)
			 */
//			Document doc2 = Jsoup.connect("https://github.com/")
//					  .data("query", "Java")
//					  .userAgent("Mozilla")
//					  .cookie("auth", "token")
//					  .timeout(3000)
//					  .post();
			
			
			/*******************************************/
			/**
			 * 3. 在本机硬盘上有一个HTML文件，需要对它进行解析从中抽取数据或进行修改。
			 */
			File file = new File("source/conf/baidu.html");
			Document doc3 = Jsoup.parse(file, "UTF-8", "");
			
			/*******************************************/
			
			
			/*******************************************/
			
			String content = doc3.toString();
			System.out.println(content);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
