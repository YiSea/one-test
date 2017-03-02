package com.one.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
  * @Package com.one.io 
  * @ClassName IOMain.java
  * @author jun.wu  
  * @date 2016年12月26日 上午10:42:07 
  * @Description: 各类io操作 demo
 */
public class IOMain {

	public static void main(String[] args) throws IOException {
		String path = "E:\\code\\yisea\\git\\one-test\\src\\main\\java\\com\\one\\io\\";
		
		systemWriter(path);
		
		/*copyFileA2FileB(path);*/
	}
	
	/**
	 * 记录控制台输入内容到文件
	 * @param path
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private static void systemWriter(String path) throws IOException {
		Scanner in = new Scanner(System.in);
		FileWriter out = new FileWriter(path + "systemIn.log");
		String s;
		while (!(s = in.nextLine()).equals("exit")){
		    out.write(s + "\n");
		}
		out.flush();
		out.close();
		in.close();		
	}

	/**
	 * 赋值文件A到文件B
	 * @param path
	 * @throws IOException
	 */
	public static void copyFileA2FileB(String path) throws IOException{
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(path + "IOMain.java");
			out = new FileOutputStream(path + "IOMain2.java");
			int b = 0;
			while((b = in.read()) != -1){
				out.write(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				if(in != null){
					in.close();
				}
				out.close();
			}
		}
			
	}

}
