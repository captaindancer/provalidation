package com.dancer.provalidation.loop;

import java.util.ArrayList;
import java.util.List;

public class ForeachMain {

	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		for(String element : list){
			if(element.equals("b")){
				continue;
			}
			System.out.println("haha");
		}
	}

}
