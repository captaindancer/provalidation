package com.dancer.provalidation.collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

public class SetRetain {
	
	public static Set<String> getSet4File(String filepath) throws IOException{
		Set<String> set=null;
		try(BufferedReader bufferedReader=Files.newBufferedReader(Paths.get(filepath))){
			set=bufferedReader.lines().filter(line->line.length()>0 && !line.equals("null")).map(line->line.trim().toUpperCase()).collect(Collectors.toSet());
		}
		return set;
	}

	public static void main(String[] args) throws IOException {
		Set<String> t1=getSet4File("/Users/liufeng/Downloads/t1.csv");
		Set<String> t2=getSet4File("/Users/liufeng/Downloads/t2.csv");
		System.out.println(t1.size());
		System.out.println(t2.size());
		System.out.println(t1.retainAll(t2));
		System.out.println(t1.size());
	}

}
