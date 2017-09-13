package com.dancer.provalidation.regexp;

import java.util.stream.Stream;

public class StringSplit {

	public static void main(String[] args) {
		String text="hello world  haha   yige	shuju";
		String[] arrs=text.split("\\s+");
		System.out.println(arrs.length);
		Stream.of(arrs).forEach(System.out::println);
		
		System.out.println(System.getProperty("java.io.tmpdir"));
		
		text="558339|305616|301714301|1|2017-07-01 23:47:06";
	    arrs=text.split("\\|");
	    System.out.println(arrs.length);
	    Stream.of(arrs).forEach(System.out::println);
	    
	    String id="id234343";
	    String owner="owner23343";
	    System.out.println(id.substring(2));
	    System.out.println(owner.substring(5));
	    
	    System.out.println(null+"|"+owner);
	    
	    text="525507|161359511";
	    arrs=text.split("\\|");
	    System.out.println(arrs.length);
	    Stream.of(arrs).forEach(System.out::println);
	    
	    text="124,,";
	    System.out.println(text.split(",",-1).length);		
	    text="abc,124";
	    System.out.println(text.split(",").length);
	}

}
