package com.dancer.provalidation.collection;

import java.util.HashSet;
import java.util.Set;

public class SetMain {

	public static void main(String[] args) {
		Set<String> cateSet=new HashSet<String>();
		cateSet.add("a");
		cateSet.add("b");
		cateSet.add("c");
		cateSet.add("b");
		cateSet.add("b");
		System.out.println(cateSet.size());
	}

}
