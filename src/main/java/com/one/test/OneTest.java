package com.one.test;

import java.util.Scanner;

public class OneTest {
	protected void lala(){
		System.out.println("lala");
	}
	
	public static void main(String args[]){
		
		
		
		System.out.println(123/1 %10);
		System.out.println(123/10 %10);
		System.out.println(123/100 %10);
		
//		System.out.println(0 % 10);
//		
//		
//		System.out.println(Math.pow(3,2));
//		
//		flower(); // 花儿

		
	}
	
	public static void flower(){
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
	}
	
}
