package com.dancer.provalidation.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataImport {
	
	public static void readFile(String filepath,String nicknamePath) throws IOException{
//		Map<Long, String> nicknameMap=readNickname("/Users/liufeng/yuewen/qduseridnickname.csv");
		int count=0;
		try(BufferedReader reader=new BufferedReader(new FileReader(filepath));
			BufferedWriter writer=new BufferedWriter(new FileWriter(nicknamePath))){
			String line=null;
			while((line=reader.readLine()) != null){
				String[] fileds=line.split("\\|");
				if(fileds.length != 17){
					System.out.println(fileds.length);
					continue;
				}
				writer.write(fileds[0]+","+fileds[2]);
				writer.newLine();
				/*if(nicknameMap.containsKey(ownerid)){
					
				}else{
					count++;
				}*/
			}
			System.out.println(count);
		}
	}

	public static Map<Long,String> readNickname(String filepath) throws IOException{
		Map<Long, String> nicknameMap=new HashMap<Long,String>();
		try(BufferedReader reader=new BufferedReader(new FileReader(filepath))){
			String line=null;
			while((line = reader.readLine()) != null){
				String[] splits=line.split(",");
				if(splits.length != 2){
					System.out.println(line);
				}
				nicknameMap.put(Long.parseLong(splits[0]), splits[1]);
			}
		}
		return nicknameMap;
	}
	
	public static void main(String[] args) throws IOException {
		String filepath="/Users/liufeng/yuewen/serverload/datafile";
		String nicknamePath="/Users/liufeng/yuewen/serverload/qidianuserid";
		readFile(filepath,nicknamePath);
		System.out.println("------finish------");
	}

}
