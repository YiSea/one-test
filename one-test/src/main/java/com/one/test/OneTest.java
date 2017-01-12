package com.one.test;

import java.util.ArrayList;
import java.util.List;

public class OneTest {
	
	public static void main(String args[]){
		List<String> list = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		
		
//		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		
		list2.add("1");
		list2.add("2");
		
		list.removeAll(list2);
		
		System.out.println(list.toString());
		System.out.println( "over!");
	}

}
