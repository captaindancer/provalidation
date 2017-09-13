package com.dancer.provalidation.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.dancer.provalidation.db.TypeLabel;

public class BookListReader {

	public static void readBooklist(String filepath,String writePath) throws IOException{
		Map<Long, String> nicknameMap=readNickname("/Users/liufeng/yuewen/serverload/qduseridnickname0905.csv");
		try(BufferedReader reader=new BufferedReader(new FileReader(filepath));
			BufferedWriter writer=new BufferedWriter(new FileWriter(writePath))){
			String line=null;
			int count=0;
			while((line=reader.readLine())!=null){
				String[] fileds=line.split("\\|");
				if(fileds.length != 17){
					System.out.println(fileds.length);
					count++;
				}
//				writer.write(fileds[0]+","+fileds[2]);
				String nickname=nicknameMap.get(Long.parseLong(fileds[0].trim()));
				if(nickname == null){
					nickname="";
				}
				String label=null;
				if(StringUtils.isBlank(fileds[5])){
					label=TypeLabel.getDefaultLabelValue(Integer.parseInt(fileds[6].trim()));
				}else{
					label=fileds[5].trim();
				}
				writer.write(fileds[0]+"|"+nickname+"|"+fileds[1]+"|"+fileds[2]+"|"+fileds[3]+"|"+fileds[4]+"|"+label+"|"+fileds[6]+"|"+fileds[7]+"|"+fileds[8]+"|"+fileds[9]+"|"+fileds[10]+"|"+fileds[11]+"|"+fileds[12]+"|"+fileds[13]+"|"+fileds[14]+"|"+fileds[15]+"|"+fileds[16]);
				writer.newLine();
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
		String readPath="/Users/liufeng/yuewen/serverload/datafile";
		String resultPath="/Users/liufeng/yuewen/serverload/booklistresult";
//		String writePath="/Users/liufeng/yuewen/qidianuserid";
//		readBooklist(readPath,writePath);
//		Map<Long, String> nicknameMap=readNickname("/Users/liufeng/yuewen/qduseridnickname.csv");
//		System.out.println(nicknameMap.size());
		readBooklist(readPath, resultPath);
	}

}
