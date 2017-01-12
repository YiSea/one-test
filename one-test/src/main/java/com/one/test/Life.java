package com.one.test;

import java.util.Random;

public class Life {
	public static void main(String args[]){
		lineLife();
	}
	
	public static void lineLife(){
		StringBuilder sb = new StringBuilder();
		sb.append("").append("").append("").append("").append("/");
		
		/**
		 * 
 
 		   /\	   /\
 		  /	 \	  /	 \
 		 /	  \	 /	  \
 		/	   \/	   \.....
 -----------------------------------------------------------------------------------------------
		 * 
		 */
		
		String key2016Str = "相遇 北京  初心 认真 思念 担心   情不自禁 加班  勇气 自信  真心 担忧  矛盾  克服 欢喜   思考 出差 奔波 工作 开心  自由  真爱    躁动  问题  自卑 等待 怀疑   沉默 谨慎   细心  马虎  淡定 忙碌";
		String[] KEY_WORDS_2016 = key2016Str.split(" ");
		Random random = new Random();
		String key2016 = "";
		while("".equals(key2016)){
			int flag = random.nextInt(KEY_WORDS_2016.length -1);
			key2016 = KEY_WORDS_2016[flag];
			if("".equals(key2016)){
				continue;
			}
//			System.out.println(KEY_WORDS_2016.length -1  + "| "+flag);
			System.out.println("2016关键词：" + key2016);
		}
		
		String key2017Str = "沉淀 理想 坎坷 平淡 惊悚 欢喜 unbelievable amazing";
		String[] KEY_WORDS_2017 = key2017Str.split(" ");
		String key2017 = "";
		while("".equals(key2017)){
			int flag = random.nextInt(KEY_WORDS_2017.length -1);
			key2017 = KEY_WORDS_2017[flag];
			if("".equals(key2017)){
				continue;
			}
//			System.out.println(KEY_WORDS_2017.length -1  + "| "+flag);
			System.out.println("2017关键词：" + key2017);
		}
	}
}
