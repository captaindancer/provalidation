package com.dancer.provalidation.collection;

import java.util.HashMap;
import java.util.Map;

public class MapMain {

	public static void main(String[] args) {
		Map<String, String> map=new HashMap<String, String>(10);
		map.put("a", "avalue");
		map.put("b", "bvalue");
		map.put("c", "cvalue");
		map.put("d", "dvalue");
		map.put("e", "evalue");
		map.put("f", "fvalue");
		map.put("g", "gvalue");
		map.put("h", "hvalue");
		System.out.println(map.get("a"));
		System.out.println(map.get("1"));
		
		
		Map<Integer, String> intMap=new HashMap<Integer,String>(10);
		intMap.put(12, "a");
		intMap.put(10, "b");
		intMap.put(8, "c");
		
		System.out.println(intMap.get(12));
		System.out.println(intMap.get(9));
		System.out.println(intMap.get(10));
		
		Map<Long,Map<Integer,Integer>> booklistCateMap=new HashMap<Long,Map<Integer,Integer>>();
		booklistCateMap.put(20L, new HashMap<Integer,Integer>());
		System.out.println(booklistCateMap);
	}

}
