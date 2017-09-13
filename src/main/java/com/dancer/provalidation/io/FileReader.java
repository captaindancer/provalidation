package com.dancer.provalidation.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;


public class FileReader {

	public static void main(String[] args) throws IOException {
//		String filepath="/Users/liufeng/yuewen/standdata";
		String filepath="/Users/liufeng/yuewen/blistbresult.csv";
		BufferedReader reader=new BufferedReader(new java.io.FileReader(filepath));
		String line=null;
		int count=0;
		Set<Long> numberSet=new TreeSet<Long>();
		while((line=reader.readLine()) != null){
			if(line.split(",").length == 2){
				if(Long.parseLong(line.split(",")[1])>0){
					System.out.println(line.split(",")[1]);
					numberSet.add(Long.parseLong(line.split(",")[1]));
					count++;
				}
			}
		}
		System.out.println("--"+count+"--");
		
		System.out.println(numberSet.size());
		System.out.println(numberSet);
		reader.close();
	}

}
