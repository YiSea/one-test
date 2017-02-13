package com.one.xposed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketService {
	
	public static void main(String args[]){
		ServerSocket serverSocket = null;
		try {
			String req = "";
			String reponse = "over!";
			serverSocket = new ServerSocket(8888);
			
			while(true){
				Socket socket = serverSocket.accept();
				PrintWriter os = new PrintWriter(socket.getOutputStream());
				BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				req = is.readLine();
				// TODO
				os.println(req + "over");
				os.flush();
				
				try {
					Thread.sleep(100l);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				os.close();
				is.close();
			}
//			System.out.println("socket client:" + req);
//			System.out.println("socket service:" + reponse);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(serverSocket != null){
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
