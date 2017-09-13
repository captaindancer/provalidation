package com.dancer.provalidation.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CollectionOperation {

	public static int getIntersectionCount(String parentPath,String childPath) throws IOException{
		int count=0;
		Set<String> parentSet=new HashSet<String>();
		Set<String> childSet=new HashSet<String>();
		try(BufferedReader parentReader=new BufferedReader(new FileReader(parentPath));
			BufferedReader childReader=new BufferedReader(new FileReader(childPath))){
			String line=null;
			while((line=parentReader.readLine()) != null){
				parentSet.add(line.trim());
			}
			while((line=childReader.readLine()) != null){
				childSet.add(line.trim());
			}
		}
		for(String userid : childSet){
			if(parentSet.contains(userid)){
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		//一天数据：536712
		//一周数据：721807
		//两周数据：843995
		//一个月数据：1133333
		String parentPath="/Users/liufeng/yuewen/booklist/activeusers/day0911clientuserid.csv";
		String childPath="/Users/liufeng/yuewen/booklist/activeusers/subscribeuserids";
		int count=getIntersectionCount(parentPath, childPath);
		System.out.println(count);
	}

}
