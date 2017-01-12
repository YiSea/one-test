package com.one.xposed;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	
	public static void main(String args[]){
		Socket socket = null;
		try {
			String req = "454532";
			String reponse = null;
			socket = new Socket("127.0.0.1", 12580);
			PrintWriter os = new PrintWriter(socket.getOutputStream());
//			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
			
			os.println(req);// 请求信息
			os.flush();
//			reponse = is.readLine(); // 反馈信息
			
			byte[] buffer = new byte[1024];
			int i = 0;
			StringBuilder sb = new StringBuilder();
			while((i = bis.read(buffer)) != -1){ 
				String str = new String(buffer, "UTF-8");
				sb.append(str);
				break;
			}
			reponse = sb.toString();
			if(reponse != null && !"".equals(reponse)){
				reponse = reponse.replace("\n", "");
			}
			
			
			System.out.println("socket client:" + req);
			System.out.println("socket service:" + reponse + "&support_pwd=1");
			
			os.close();
			bis.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		} finally{
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
