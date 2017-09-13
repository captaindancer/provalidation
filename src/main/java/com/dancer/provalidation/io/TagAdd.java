package com.dancer.provalidation.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class TagAdd {

	public static Map<Long,Set<Integer>> getTagMap(String filepath) throws IOException{
		Map<Long, Set<Integer>> tagMap=new HashMap<Long,Set<Integer>>();
		try(BufferedReader reader=new BufferedReader(new FileReader(filepath))){
			String line=null;
			while((line=reader.readLine()) != null){
				String[] splits=line.split(",");
				if(splits.length != 2){
					System.out.println("booklisttag没有两个元素:----------"+line);
					continue;
				}
				Long booklistid=Long.parseLong(splits[0].trim());
				if(splits[1].length() < 5){
					Integer tagid=Integer.parseInt(splits[1].trim());
					if(tagMap.containsKey(booklistid)){
						tagMap.get(booklistid).add(tagid);
					}else{
						Set<Integer> tagidSet=new HashSet<Integer>();
						tagidSet.add(tagid);
						tagMap.put(booklistid, tagidSet);
					}
				}
				
			}
		}
		return tagMap;
	}
	
	public static void main(String[] args) throws IOException {
		//args[0]:现在有的booklistid和tagid的对应关系
		//args[1]:分类标签要添加的booklistid和tagid对应关系
		//args[2]:流派标签要添加的booklistid和tagid对应关系
		//args[3]:添加的booklistid和tagid
		String currentBooklistTag=args[0];
		String cateAddBooklistTag=args[1];
		String genreAddBooklistTag=args[2];
		String addFilepath=args[3];
		Map<Long, Set<Integer>> addMap=new HashMap<Long,Set<Integer>>();
		Map<Long, Set<Integer>> currentBooklistTagMap=getTagMap(currentBooklistTag);
		Map<Long, Set<Integer>> cateAddBooklistTagMap=getTagMap(cateAddBooklistTag);
		Map<Long, Set<Integer>> genreAddBooklistTagMap=getTagMap(genreAddBooklistTag);
		for(Entry<Long, Set<Integer>> entry : cateAddBooklistTagMap.entrySet()){
			Long booklistid=entry.getKey();
			for(Integer tagid : entry.getValue()){
				if(currentBooklistTagMap.containsKey(booklistid)){
					if(currentBooklistTagMap.get(booklistid).contains(tagid)){
						
					}else{
						if(addMap.containsKey(booklistid)){
							addMap.get(booklistid).add(tagid);
						}else{
							Set<Integer> tagSet=new HashSet<Integer>();
							tagSet.add(tagid);
							addMap.put(booklistid, tagSet);
						}
					}
				}else{
					if(addMap.containsKey(booklistid)){
						addMap.get(booklistid).add(tagid);
					}else{
						Set<Integer> tagSet=new HashSet<Integer>();
						tagSet.add(tagid);
						addMap.put(booklistid, tagSet);
					}
				}
			}
		}
		
		for(Entry<Long, Set<Integer>> entry : genreAddBooklistTagMap.entrySet()){
			Long booklistid=entry.getKey();
			for(Integer tagid : entry.getValue()){
				if(currentBooklistTagMap.containsKey(booklistid)){
					if(currentBooklistTagMap.get(booklistid).contains(tagid)){
						
					}else{
						if(addMap.containsKey(booklistid)){
							addMap.get(booklistid).add(tagid);
						}else{
							Set<Integer> tagSet=new HashSet<Integer>();
							tagSet.add(tagid);
							addMap.put(booklistid, tagSet);
						}
					}
				}else{
					if(addMap.containsKey(booklistid)){
						addMap.get(booklistid).add(tagid);
					}else{
						Set<Integer> tagSet=new HashSet<Integer>();
						tagSet.add(tagid);
						addMap.put(booklistid, tagSet);
					}
				}
			}
		}
		//输出addMap
		try(BufferedWriter addWriter=new BufferedWriter(new FileWriter(addFilepath))){
			for(Entry<Long, Set<Integer>> entry : addMap.entrySet()){
				for(Integer tagAddInt : entry.getValue()){
					addWriter.write(entry.getKey()+","+tagAddInt);
					addWriter.newLine();
				}
			}
			
		}
	}

}
