package com.dancer.provalidation.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestBooklistMain {

	public static Map<Long, String> getGenrebookMap(String filepath) throws IOException{
		Map<Long, String> cateMap=new HashMap<Long,String>(3838800);
		try(BufferedReader reader=new BufferedReader(new FileReader(filepath))){
			String line=null;
			while((line=reader.readLine()) != null){
				String[] mappings=line.split(",");
				if(mappings.length != 2){
					System.out.println(line);
					continue;
				}
				cateMap.put(Long.parseLong(mappings[0].trim()), mappings[1].trim());
			}
		}
		return cateMap;
	}
	
	public static void main(String[] args) throws IOException {
		String filepath="/Users/liufeng/yuewen/booklist/qaenv/genrebooks";
		Map<Long, String> cateMap=getGenrebookMap(filepath);
		System.out.println(cateMap.size());
	}

}
