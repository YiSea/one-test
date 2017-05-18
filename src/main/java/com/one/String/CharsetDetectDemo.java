package com.one.String;

import org.mozilla.universalchardet.UniversalDetector;

/**
 * 
  * @Package com.one.String 
  * @ClassName CharsetDetectUtil.java
  * @author jun.wu  
  * @date 2017年2月10日 上午11:12:27 
  * @Description: 字符编码识别-demo
 */
public class CharsetDetectDemo {
	public static String detect(byte[] content) {
		/**
		 * 官方建议重复利用UniversalDetector对象，可以设置为类属性，
		 * 不过这时你就要调用detector.reset()方法重置UniversalDetector了
		 * （还有就是如果设置为类属性，编码识别这个方法就不是线程安全的了）。
		 */
//		String sss = new String(content);
        UniversalDetector detector = new UniversalDetector(null);
            //开始给一部分数据，让学习一下啊，官方建议是1000个byte左右（当然这1000个byte你得包含中文之类的）
        detector.handleData(content, 0, content.length);
            //识别结束必须调用这个方法
        detector.dataEnd();
        	//神奇的时刻就在这个方法了，返回字符集编码。
        return detector.getDetectedCharset();
    }
	
	public static void main(String args[]){
//		File file = new File("C:\\Users\\admin\\Desktop\\detail-1.SCD");
		try {
//			InputStream is = new FileInputStream(file);
			byte[] bytes = new byte[10000000];
			bytes = "GENBJ&kwds=大病医疗保险&dgid=9827".getBytes();
			String charset = detect(bytes);
			System.out.println(charset);

//			int i = 0;
//			while((i = is.read(bytes)) != -1){
//				if(bytes != null){
//					String charset = detect(bytes);
//					if(charset == null || "null".equals(charset)){
//						System.out.println(new String(bytes));
//					}
//					System.out.println(charset);
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
