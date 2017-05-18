package com.one.xposed;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	
	public static void main(String args[]){
		for(int i = 0;i<1000;i++){
			String ss = hookMafengwo("0，https://mapi.mafengwo.cn/travelguide/poi/pois/mdds/143878，app_version_code=320, oauth_nonce=9fc583e0-ed1d-4d5e-863e-02b036deab12, oauth_consumer_key=5, screen_scale=2.0, device_type=android, hardware_model=YQ603, time_offset=480, o_lng=112.554918, x_auth_mode=client_auth, oauth_signature_method=HMAC-SHA1, oauth_token=0_0969044fd4edf59957f4a39bce9200c6, length=15, has_room=0, mddId=143878, app_code=com.mfw.roadbook, o_lat=29.521503, oauth_version=1.0, mfwsdk_ver=20140507, screen_width=1080, device_id=64:00:6A:0A:17:D0, sys_ver=4.4.2, channel_id=MFW, type_id=1, sort_type=comment, open_udid=64:00:6A:0A:17:D0, app_ver=7.8.7, oauth_timestamp=1492759275, screen_height=1920，b51c30e9ec2b3beb549cbb2f6e766abd");
			System.out.println(i+":"+ss);
		}
/**
 * yNRYyGASIy9G5VJSJvQ/6V4jDAg=
 * yNRYyGASIy9G5VJSJvQ/6V4jDAg=

 */
		
		/*String time = String.valueOf(System.currentTimeMillis());

		String sign = hookZuiYouSign(time, "58ccde57277f286ef87a267c-2");
		System.out.println(time);
		System.out.println(sign);*/
	}
	
	public static String hookMafengwo(String req){
		Socket socket = null;
		String reponse = null;
		try {
			socket = new Socket("127.0.0.1", 12580);
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
			bis.toString().getBytes();
			
			os.println(req);// 请求信息
			os.flush();
			
			byte[] buffer = new byte[1024];
			int i = 0;
			StringBuilder sb2 = new StringBuilder();
			while((i = bis.read(buffer)) != -1){
				String str = new String(buffer, "UTF-8");
				sb2.append(str);
				break;
			}
			reponse = sb2.toString();
			if(reponse != null && !"".equals(reponse)){
				reponse = reponse.replace("\n", "");
			}
			
			reponse = "".equals(reponse)?reponse:reponse.trim();
			
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
		return reponse;
	}
	
	/**
	 * 
	 * @param time
	 * @param token
	 * @return
	 */
	public static String hookZuiYouSign(String time, String token){
		Socket socket = null;
		String reponse = null;
		try {
			
			String req = time+",YQ603,19,up,rec,0,3.1.2,18745793,864394010640011_64:00:6A,me," + token + ",0,0,1,all";
//				
			socket = new Socket("127.0.0.1", 12580);// 8889  12580
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
			bis.toString().getBytes();
			
			os.println(req);// 请求信息
			os.flush();
			
			byte[] buffer = new byte[1024];
			int i = 0;
			StringBuilder sb2 = new StringBuilder();
			while((i = bis.read(buffer)) != -1){
				String str = new String(buffer, "UTF-8");
				sb2.append(str);
				break;
			}
			reponse = sb2.toString();
			if(reponse != null && !"".equals(reponse)){
				reponse = reponse.replace("\n", "");
			}
			
			reponse = "".equals(reponse)?reponse:reponse.trim();
//			System.out.println(req);
//			System.out.println(reponse);
//			System.out.println("?sign=0b532f594490e9b99f9c45e0b7f5de83".equals(reponse));
			
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
		return reponse;
	}
}
