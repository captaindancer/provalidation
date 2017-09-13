package com.dancer.provalidation.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TagResult {

	private static final int MALETAGID=1;
	private static final int FEMALETAGID=16;
	private static final Map<Integer, Integer> cateTagidMap;
	
	static{
		cateTagidMap=new HashMap<Integer,Integer>(25);
		cateTagidMap.put(101, 2);
		cateTagidMap.put(102, 3);
		cateTagidMap.put(103, 4);
		cateTagidMap.put(104, 5);
		cateTagidMap.put(105, 6);
		cateTagidMap.put(106, 7);
		cateTagidMap.put(107, 8);
		cateTagidMap.put(108, 9);
		cateTagidMap.put(109, 10);
		cateTagidMap.put(110, 11);
		cateTagidMap.put(111, 12);
		cateTagidMap.put(112, 13);
		cateTagidMap.put(113, 14);
		cateTagidMap.put(114, 15);
		cateTagidMap.put(115, 17);
		cateTagidMap.put(116, 18);
		cateTagidMap.put(117, 19);
		cateTagidMap.put(118, 20);
		cateTagidMap.put(119, 21);
		cateTagidMap.put(120, 22);
		cateTagidMap.put(121, 23);
		cateTagidMap.put(122, 24);
	}
	
	public static Map<Long, Integer> getBooklistCateTagMap(String filepath) throws IOException{
		Map<Long, Integer> booklistCateTagMap=new HashMap<Long,Integer>(3000);
		try(BufferedReader reader=new BufferedReader(new FileReader(filepath))){
			String line=null;
			while((line=reader.readLine()) != null){
				String[] splits=line.split(",");
				if(splits.length != 2){
					System.out.println("catebooklist不是2个元素:------"+line);
					continue;
				}
				Long booklistid=Long.parseLong(splits[0].trim());
				Integer tagid=cateTagidMap.get(Integer.parseInt(splits[1].trim()));
				if(tagid != null){
					booklistCateTagMap.put(booklistid, tagid);
				}else{
					System.out.println("catebooklist找不到对应的tagid:------"+line);
				}
			}
		}
		return booklistCateTagMap;
	}
	
	public static Map<Long, Set<Integer>> getDeletedBooklistTagMap(String filepath) throws IOException{
		Map<Long, Set<Integer>> deletedBooklistTagMap=new HashMap<Long,Set<Integer>>();
		try(BufferedReader reader=new BufferedReader(new FileReader(filepath))){
			String line=null;
			while((line=reader.readLine()) != null){
				String[] splits=line.split(",");
				if(splits.length != 2){
					System.out.println(line);
					continue;
				}
				Long booklistid=Long.parseLong(splits[0].trim());
				Integer tagid=Integer.parseInt(splits[1].trim());
				if(deletedBooklistTagMap.containsKey(booklistid)){
					deletedBooklistTagMap.get(booklistid).add(tagid);
				}else{
					Set<Integer> tagidSet=new HashSet<Integer>();
					tagidSet.add(tagid);
					deletedBooklistTagMap.put(booklistid, tagidSet);
				}
			}
		}
		return deletedBooklistTagMap;
	}
	
	public static Map<Long,Set<Integer>> getCurrentBooklistTagMap(String filepath) throws IOException{
		Map<Long, Set<Integer>> currentBooklistTagMap=new HashMap<Long,Set<Integer>>();
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
					if(currentBooklistTagMap.containsKey(booklistid)){
						currentBooklistTagMap.get(booklistid).add(tagid);
					}else{
						Set<Integer> tagidSet=new HashSet<Integer>();
						tagidSet.add(tagid);
						currentBooklistTagMap.put(booklistid, tagidSet);
					}
				}
				
			}
		}
		return currentBooklistTagMap;
	} 
	
	public static Map<Long, Integer> getBooklistMaleFemale(String booklistbooks,String bookfreetype) throws IOException{
		Map<Long, Integer> booklistMaleFemaleMap=new HashMap<Long,Integer>();
		Map<Long, Integer> bookfreetypeMap=new HashMap<Long,Integer>();
		Map<Long, List<Integer>> booklistFreetypeList=new HashMap<Long,List<Integer>>();
		try(BufferedReader reader=new BufferedReader(new FileReader(bookfreetype))){
			String line=null;
			while((line=reader.readLine()) != null){
				String[] mappings=line.split(",");
				if(mappings.length != 3){
					System.out.println("bookcate没有三个元素:------------"+line);
					continue;
				}
				bookfreetypeMap.put(Long.parseLong(mappings[0].trim()), Integer.parseInt(mappings[1].trim()));
			}
		}
		try(BufferedReader reader=new BufferedReader(new FileReader(booklistbooks))){
			String line=null;
			while((line=reader.readLine()) != null){
				String[] mappings=line.split(",");
				if(mappings.length != 2){
					System.out.println("booklistbooks没有两个元素:-----------"+line);
					continue;
				}
				Long booklistid=Long.parseLong(mappings[0].trim());
				Long bookid=Long.parseLong(mappings[1].trim());
				Integer freetype=bookfreetypeMap.get(bookid);
				if(freetype != null){
					if(booklistFreetypeList.containsKey(booklistid)){
						booklistFreetypeList.get(booklistid).add(freetype);
					}else{
						List<Integer> freetypeList=new ArrayList<Integer>();
						freetypeList.add(freetype);
						booklistFreetypeList.put(booklistid, freetypeList);
					}
				}else{
					System.out.println("booklistbooks没有对应的freetype:---------"+line);
				}
			}
		}
		for(Entry<Long, List<Integer>> entry:booklistFreetypeList.entrySet()){
			int judgeSize=entry.getValue().size()/2;
			int maleCount=0;
			int femaleCount=0;
			for(Integer freeInt : entry.getValue()){
				if(freeInt == 0){
					femaleCount++;
				}
				if(freeInt == 1){
					maleCount++;
				}
			}
			if(maleCount > judgeSize){
				booklistMaleFemaleMap.put(entry.getKey(), MALETAGID);
			}
			if(femaleCount > judgeSize){
				booklistMaleFemaleMap.put(entry.getKey(), FEMALETAGID);
			}
		}
		return booklistMaleFemaleMap;
	}
	
	public static void main(String[] args) throws IOException {
		//args[0]:分类书单的booklistid和type
		//args[1]:book_list_id和book_id的对应关系
		//args[2]:book_id跟categoryname以及freetype的对应关系
		//args[3]:现有book_list_id和tag_id的关系
		//args[4]:分类标签要删除的booklistid和tagid对应关系
		//args[5]:流派标签要删除的booklistid和tagid对应关系
		//args[6]:删除的booklistid和tagid
		//args[7]:添加的booklistid和tagid
		Map<Long, Set<Integer>> deleteMap=new HashMap<Long,Set<Integer>>();
		Map<Long, Set<Integer>> addMap=new HashMap<Long,Set<Integer>>();
		if(args.length < 8){
			System.out.println("传入的参数不符合要求！");
			System.exit(1);
		}
		Map<Long, Integer> boolistCateTagMap=getBooklistCateTagMap(args[0]);
		Map<Long,Integer> booklistMaleFemaleMap=getBooklistMaleFemale(args[1], args[2]);
		Map<Long, Set<Integer>> currentBooklistTag=getCurrentBooklistTagMap(args[3]);
		Map<Long, Set<Integer>> deletedCateTagMap=getDeletedBooklistTagMap(args[4]);
		Map<Long,Set<Integer>> deletedGenreTagMap=getDeletedBooklistTagMap(args[5]);
		for(Entry<Long, Set<Integer>> entry: deletedCateTagMap.entrySet()){
			Long booklistid=entry.getKey();
			if(currentBooklistTag.containsKey(booklistid)){
				for(Integer tagInteger:entry.getValue()){
					if(currentBooklistTag.get(booklistid).contains(tagInteger)){
						if(deleteMap.containsKey(booklistid)){
							deleteMap.get(booklistid).add(tagInteger);
						}else{
							Set<Integer> tagidSet=new HashSet<Integer>();
							tagidSet.add(tagInteger);
							deleteMap.put(booklistid, tagidSet);
						}
					}
				}
			}
		}
		
		for(Entry<Long, Set<Integer>> entry: deletedGenreTagMap.entrySet()){
			Long booklistid=entry.getKey();
			if(currentBooklistTag.containsKey(booklistid)){
				for(Integer tagInteger:entry.getValue()){
					if(currentBooklistTag.get(booklistid).contains(tagInteger)){
						if(deleteMap.containsKey(booklistid)){
							deleteMap.get(booklistid).add(tagInteger);
						}else{
							Set<Integer> tagidSet=new HashSet<Integer>();
							tagidSet.add(tagInteger);
							deleteMap.put(booklistid, tagidSet);
						}
					}
				}
			}
		}
		
		
		for(Entry<Long, Integer> entry : boolistCateTagMap.entrySet()){
			Long booklistid=entry.getKey();
			Integer cateTagid=entry.getValue();
			if(currentBooklistTag.containsKey(booklistid)){
				if(currentBooklistTag.get(booklistid).contains(cateTagid)){
					if(deleteMap.containsKey(booklistid)){
						if(deleteMap.get(booklistid).contains(cateTagid)){
							deleteMap.get(booklistid).remove(cateTagid);
						}
					}
				}else{
					if(addMap.containsKey(booklistid)){
						addMap.get(booklistid).add(cateTagid);
					}else{
						Set<Integer> tagidSet=new HashSet<Integer>();
						tagidSet.add(cateTagid);
						addMap.put(booklistid, tagidSet);
					}
				}
			}else{
				if(addMap.containsKey(booklistid)){
					addMap.get(booklistid).add(cateTagid);
				}else{
					Set<Integer> tagidSet=new HashSet<Integer>();
					tagidSet.add(cateTagid);
					addMap.put(booklistid, tagidSet);
				}
			}
		}
		
		for(Entry<Long, Integer> entry : booklistMaleFemaleMap.entrySet()){
			Long booklistid=entry.getKey();
			Integer cateTagid=entry.getValue();
			if(currentBooklistTag.containsKey(booklistid)){
				if(currentBooklistTag.get(booklistid).contains(cateTagid)){
					if(deleteMap.containsKey(booklistid)){
						if(deleteMap.get(booklistid).contains(cateTagid)){
							deleteMap.get(booklistid).remove(cateTagid);
						}
					}
				}else{
					if(addMap.containsKey(booklistid)){
						addMap.get(booklistid).add(cateTagid);
					}else{
						Set<Integer> tagidSet=new HashSet<Integer>();
						tagidSet.add(cateTagid);
						addMap.put(booklistid, tagidSet);
					}
				}
			}else{
				if(addMap.containsKey(booklistid)){
					addMap.get(booklistid).add(cateTagid);
				}else{
					Set<Integer> tagidSet=new HashSet<Integer>();
					tagidSet.add(cateTagid);
					addMap.put(booklistid, tagidSet);
				}
			}
		}
		System.out.println("deleteMap的大小："+deleteMap.size());
		System.out.println("addMap的大小："+addMap.size());
		for(Entry<Long, Set<Integer>> entry : deleteMap.entrySet()){
			System.out.println("delete->"+entry);
		}
		for(Entry<Long, Set<Integer>> entry : addMap.entrySet()){
			System.out.println("add->"+entry);
		}
		//增加逻辑：数据库中可能由于一些历史遗留问题存在一些问题数据，比如：一个书单没有一个标签所对应的书，却有标签
		//！！！这段代码有问题：因为这段代码删除了分类大于20的，流派大于10的
		/*for(Entry<Long, Set<Integer>> entry : currentBooklistTag.entrySet()){
			Long booklistid=entry.getKey();
			for(Integer tagid : entry.getValue()){
				//分类标签
				if(tagid != 1 && tagid != 16){
				if(deletedCateTagMap.containsKey(booklistid)){
					if(deletedCateTagMap.get(booklistid).contains(tagid)){
						
					}else{
						if(deleteMap.containsKey(booklistid)){
							deleteMap.get(booklistid).add(tagid);
						}else{
							Set<Integer> tagidSet=new HashSet<Integer>();
							tagidSet.add(tagid);
							deleteMap.put(booklistid, tagidSet);
						}
					}
				}else{
					if(deleteMap.containsKey(booklistid)){
						deleteMap.get(booklistid).add(tagid);
					}else{
						Set<Integer> tagidSet=new HashSet<Integer>();
						tagidSet.add(tagid);
						deleteMap.put(booklistid, tagidSet);
					}
				}
				//流派标签
				if(deletedGenreTagMap.containsKey(booklistid)){
					if(deletedGenreTagMap.get(booklistid).contains(tagid)){
						
					}else{
						if(deleteMap.containsKey(booklistid)){
							deleteMap.get(booklistid).add(tagid);
						}else{
							Set<Integer> tagidSet=new HashSet<Integer>();
							tagidSet.add(tagid);
							deleteMap.put(booklistid, tagidSet);
						}
					}
				}else{
					if(deleteMap.containsKey(booklistid)){
						deleteMap.get(booklistid).add(tagid);
					}else{
						Set<Integer> tagidSet=new HashSet<Integer>();
						tagidSet.add(tagid);
						deleteMap.put(booklistid, tagidSet);
					}
				}
				}
			}			
		}*/
		//输出deleteMap
		try(BufferedWriter deleteWriter=new BufferedWriter(new FileWriter(args[6]))){
			for(Entry<Long, Set<Integer>> entry : deleteMap.entrySet()){
				for(Integer tagWriteInt : entry.getValue()){
					deleteWriter.write(entry.getKey()+","+tagWriteInt);
					deleteWriter.newLine();
				}
			}
			
		}
		//输出addMap
		try(BufferedWriter addWriter=new BufferedWriter(new FileWriter(args[7]))){
			for(Entry<Long, Set<Integer>> entry : addMap.entrySet()){
				for(Integer tagAddInt : entry.getValue()){
					addWriter.write(entry.getKey()+","+tagAddInt);
					addWriter.newLine();
				}
			}
			
		}
	}

}
