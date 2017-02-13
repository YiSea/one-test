package com.one.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OneTest {
	
	public static void main(String args[]){
		
		String[] regexAttr = new String[]{"[0-9]+"};
		for(String regex : regexAttr){
			Pattern patter = Pattern.compile(regex);
			Matcher matcher = patter.matcher("22人已购买| 月销1232”、 把“已抢光”去掉，保留空，不要置为.  ");
			while(matcher.find()){
				// 特殊处理规格 700ml*6 
				String gg = matcher.group(0);
				System.out.println(gg);
			}
		}
		
		/*String eva = "010101010101010101";
		char[] evas = eva.toCharArray();
		for(char ev : evas){
			System.out.println(String.valueOf(ev).equals("1"));
		}*/
		
		System.out.println( "over!");
	}

}
