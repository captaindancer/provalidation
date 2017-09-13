package com.dancer.provalidation.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapMain {

	public static void main(String[] args) {
		TreeMap<Long, String> tree=new TreeMap<Long,String>();
		tree.put(100034L, "a");
		tree.put(23456L, "b");
		tree.put(10001L, "c");
		tree.put(999999L, "d");
		tree.put(80008L, "e");
		tree.put(6666L, "f");
		System.out.println(tree.firstEntry());
		System.out.println(tree.lastEntry());
		
		Map<String, Set<String>> validMap=new HashMap<String,Set<String>>();
		Set<String> set1=new HashSet<String>();
		set1.add("1");
		set1.add("2");
		set1.add("3");
		validMap.put("a", set1);
		Set<String> set2=new HashSet<String>();
		validMap.put("b", set2);
		for(Entry<String, Set<String>> entry : validMap.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue().size());
		}
	}

}
