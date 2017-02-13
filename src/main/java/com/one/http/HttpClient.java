package com.one.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 
  * @Package com.one.http 
  * @ClassName HttpClient.java
  * @author jun.wu  
  * @date 2017年2月6日 下午5:13:51 
  * @Description: yisea's httpclient
 */
public class HttpClient {
	public static void main(String args[]){
		HttpClient httpClient = new HttpClient();
		CloseableHttpResponse resp = null;
		String url = "https://www.zhihu.com/";
		// 1.http get test
		System.err.println("request:" + url);
		try {
			resp = httpClient.httpGet(url);
			InputStream is = resp.getEntity().getContent();
			byte[] bs = new byte[1024];
			int i;
			String content = "";
			while((i = is.read(bs)) != -1){
				content += new String(bs);
			}
			
			System.out.println("---------------------------------------");
			System.out.println(content);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(resp != null){
					resp.close();
					System.out.println("closed");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse httpGet(String url) throws ClientProtocolException, IOException{
		CloseableHttpResponse resp = null;
//		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// deal warning: 警告: Invalid cookie header: "Set-Cookie: q_c1=3ac879efa82d40d .... 
		DefaultHttpClient httpClient = new DefaultHttpClient();   
		
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			System.out.println("error,check your url:" + url);
			e.printStackTrace();
		}
		
		// deal warning: 警告: Invalid cookie header: "Set-Cookie: q_c1=3ac879efa82d40d .... 
		HttpClientParams.setCookiePolicy(httpClient.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);  
		
		HttpGet httpGet = new HttpGet(uri);
		resp = httpClient.execute(httpGet);
		// close -> java.net.SocketException: Socket is closed
//		httpClient.close();
		return resp;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse httpPost(String url, String content, Map<String, String> attrMap) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		CloseableHttpResponse resp;
		try {
			resp = null;
			URI uri = null;
			try {
				uri = new URI(url);
			} catch (URISyntaxException e) {
				System.out.println("error,check your url:" + url);
				e.printStackTrace();
			}
			
			HttpPost httpPost = new HttpPost(uri);
			try {
				resp = httpClient.execute(httpPost);
			} finally{
				resp.close();
			}
		} finally{
			httpClient.close();
		}

		return resp;
	}
}
