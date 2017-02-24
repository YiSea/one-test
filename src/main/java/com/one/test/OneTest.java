package com.one.test;

import java.util.Scanner;

public class OneTest {
	
	public static void main(String args[]){
		System.out.print("指定最大位数N:");
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        input.close();
        for (int i = 3; i <= N; i++) {
            int a[] = new int[i];
            int num = (int) Math.pow(10, i - 1) + 1;
            System.out.print(i + "位的水仙花数有：\t");
            while (num <= Math.pow(10, i)) {
                int sum = 0;
                for (int j = 0; j < i; j++)
                    a[j] = (int) (num / Math.pow(10, j) % 10);
                for (int j = 0; j < i; j++) 
                    sum = sum + (int) Math.pow(a[j], i);
                if (num == sum)
                    System.out.print(num + "\t");
                num++;
            }
            System.out.print("\n");
        }
		
		
	/*	String[] regexAttr = new String[]{"[0-9]+"};
		for(String regex : regexAttr){
			Pattern patter = Pattern.compile(regex);
			Matcher matcher = patter.matcher("22人已购买| 月销1232”、 把“已抢光”去掉，保留空，不要置为.  ");
			while(matcher.find()){
				// 特殊处理规格 700ml*6 
				String gg = matcher.group(0);
				System.out.println(gg);
			}
		}
		*/
		/*String eva = "010101010101010101";
		char[] evas = eva.toCharArray();
		for(char ev : evas){
			System.out.println(String.valueOf(ev).equals("1"));
		}*/
		
		System.out.println( "over!");
	}

}
