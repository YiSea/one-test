package com.one.test;

public class OneTest {
	
	public static void main(String args[]){
		String eva = "010101010101010101";
		char[] evas = eva.toCharArray();
		for(char ev : evas){
			System.out.println(String.valueOf(ev).equals("1"));
		}
		
		System.out.println( "over!");
	}

}
