package com.dancer.provalidation.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/***
 * 
 * @author liufeng
 *注意：一本书只有一个分类，但是会有多个流派
 */
public class TagAdjust {
	
	private static final Map<String, Integer> cateTags=new HashMap<String,Integer>(22);
	private static final Map<String, Integer> genreTags=new HashMap<String,Integer>(29);

	static{
		//初始化分类cateTags
		cateTags.put("玄幻", 2);
		cateTags.put("奇幻", 3);
		cateTags.put("武侠", 4);
		cateTags.put("仙侠", 5);
		cateTags.put("都市", 6);
		cateTags.put("现实", 7);
		cateTags.put("军事", 8);
		cateTags.put("历史", 9);
		cateTags.put("游戏", 10);
		cateTags.put("体育", 11);
		cateTags.put("科幻", 12);
		cateTags.put("灵异", 13);
		cateTags.put("二次元", 14);
		cateTags.put("短篇", 15);
		cateTags.put("古代言情", 17);
		cateTags.put("仙侠奇缘", 18);
		cateTags.put("现代言情", 19);
		cateTags.put("浪漫青春", 20);
		cateTags.put("玄幻言情", 21);
		cateTags.put("悬疑灵异", 22);
		cateTags.put("科幻空间", 23);
		cateTags.put("游戏竞技", 24);
		
		//初始化流派genreTags
		genreTags.put("豪门", 25);
		genreTags.put("孤儿", 26);
		genreTags.put("明星", 30);
		genreTags.put("学生", 34);
		genreTags.put("法师", 44);
		genreTags.put("感情", 46);
		genreTags.put("职场", 49);
		genreTags.put("魔兽", 55);
		genreTags.put("练功流", 60);
		genreTags.put("爆笑", 63);
		genreTags.put("轻松", 64);
		genreTags.put("冷酷", 65);
		genreTags.put("腹黑", 66);
		genreTags.put("机智", 69);
		genreTags.put("淡定", 72);
		genreTags.put("系统流", 80);
		genreTags.put("位面", 83);
		genreTags.put("铁血", 84);
		genreTags.put("坚毅", 86);
		genreTags.put("变身", 87);
		genreTags.put("赚钱", 89);
		genreTags.put("争霸流", 90);
		genreTags.put("种田文", 91);
		genreTags.put("宅男", 92);
		genreTags.put("无限流", 93);
		genreTags.put("技术流", 94);
		genreTags.put("热血", 96);
		genreTags.put("重生", 97);
		genreTags.put("穿越", 98);
	}
	
	public static Map<Long, String> getBookCatename(String filepath,int size) throws IOException{
		Map<Long, String> cateMap=new HashMap<Long,String>(size);
		try(BufferedReader reader=new BufferedReader(new FileReader(filepath))){
			String line=null;
			while((line=reader.readLine()) != null){
				String[] mappings=line.split(",");
				if(mappings.length != 3){
					System.out.println(line);
					continue;
				}
				cateMap.put(Long.parseLong(mappings[0].trim()), mappings[2].trim());
			}
		}
		return cateMap;
	}
	
	public static Map<Long, Set<String>> getBookGenrename(String filepath,int size) throws IOException{
		Map<Long, Set<String>> genreMap=new HashMap<Long,Set<String>>(size);
		try(BufferedReader reader=new BufferedReader(new FileReader(filepath))){
			String line=null;
			while((line=reader.readLine()) != null){
				String[] mappings=line.split(",");
				if(mappings.length != 2){
					System.out.println(line);
					continue;
				}
				Long bookid=Long.parseLong(mappings[0].trim());
				String genreName=mappings[1].trim();
				if(genreMap.containsKey(bookid)){
					genreMap.get(bookid).add(genreName);
				}else{
					Set<String> genreSet=new HashSet<String>();
					genreSet.add(genreName);
					genreMap.put(bookid, genreSet);
				}
			}
		}
		return genreMap;
	}
	
	public static void main(String[] args) throws IOException {
		if(args.length < 7){
			System.out.println("传入的参数不符合要求！");
			System.exit(1);
		}
		//args[0]:bookid和categoryname/genrename的对应关系
		//args[1]:bookid和categoryname/genrename对的行数
		//args[2]:booklistid和bookid的对应关系
		//args[3]:生成的新文件
		//args[4]:取值为0和1：其中0表示分类cateTags，1表示分派genreTags
		//args[5]:统计规则的下限
		//args[6]:统计规则的上限
		if(!"0".equals(args[4]) && !"1".equals(args[4])){
			System.out.println("第5个参数必须为0或者1！");
			System.exit(1);
		}
		
		Map<String, Integer> tagMap=null;
		Map<Long, String> cateMap=null;
		Map<Long, Set<String>> genreMap=null;
		
		if("0".equals(args[4])){
			//分类标签
			tagMap=cateTags;
			cateMap=getBookCatename(args[0], Integer.parseInt(args[1]));
		}
		if("1".equals(args[4])){
			//流派标签
			tagMap=genreTags;
			genreMap=getBookGenrename(args[0], Integer.parseInt(args[1]));
		}
		//booklistCateMap表示每个booklistid对应的tagid的数目
		Map<Long,Map<Integer,Integer>> booklistCateMap=new HashMap<Long,Map<Integer,Integer>>();
		String line=null;
		try(BufferedReader reader=new BufferedReader(new FileReader(args[2]));
			BufferedWriter writer=new BufferedWriter(new FileWriter(args[3]))){
			
			if("0".equals(args[4])){
				//分类标签的处理
				while((line=reader.readLine()) != null){
					String[] records=line.split(",");
					if(records.length != 2){
						System.out.println(line);
						continue;
					}
					Long booklistid=Long.parseLong(records[0].trim());
					Long bookid=Long.parseLong(records[1].trim());
					String categoryName=cateMap.get(bookid);
					if(categoryName != null){
						Integer tagid=tagMap.get(categoryName);
						if(tagid != null){
							if(booklistCateMap.containsKey(booklistid)){
								Map<Integer, Integer> getTagCount=booklistCateMap.get(booklistid);
								if(getTagCount.containsKey(tagid)){
									getTagCount.put(tagid, getTagCount.get(tagid)+1);
								}else{
									getTagCount.put(tagid, 1);
								}
							}else{
								Map<Integer, Integer> tagCount=new HashMap<Integer,Integer>();
								tagCount.put(tagid, 1);
								booklistCateMap.put(booklistid, tagCount);
							}
						}else{
							System.out.println(line+"|"+categoryName);
						}
					}else{
						System.out.println("书没有分类："+line);
					}
				}
			}
			
			if("1".equals(args[4])){
				//流派标签的处理
				while((line=reader.readLine()) != null){
					String[] records=line.split(",");
					if(records.length != 2){
						System.out.println(line);
						continue;
					}
					Long booklistid=Long.parseLong(records[0].trim());
					Long bookid=Long.parseLong(records[1].trim());
					Set<String> genreSets=genreMap.get(bookid);
					if(genreSets != null){
						for(String genreName : genreSets){
							Integer tagid=tagMap.get(genreName);
							if(tagid != null){
								if(booklistCateMap.containsKey(booklistid)){
									Map<Integer, Integer> getTagCount=booklistCateMap.get(booklistid);
									if(getTagCount.containsKey(tagid)){
										getTagCount.put(tagid, getTagCount.get(tagid)+1);
									}else{
										getTagCount.put(tagid, 1);
									}
								}else{
									Map<Integer, Integer> tagCount=new HashMap<Integer,Integer>();
									tagCount.put(tagid, 1);
									booklistCateMap.put(booklistid, tagCount);
								}
							}else{
								System.out.println(line+"|"+genreName);
							}
						}
					}else{
						System.out.println("书没有流派："+line);
					}
					
				}
			}
			
			int lower=Integer.parseInt(args[5]);
			int upper=Integer.parseInt(args[6]);
			for(Entry<Long, Map<Integer,Integer>> booklistEntry: booklistCateMap.entrySet()){
				for(Entry<Integer, Integer> tagEntry:booklistEntry.getValue().entrySet()){
					if(tagEntry.getValue()>=lower && tagEntry.getValue()<upper){
						writer.write(booklistEntry.getKey()+","+tagEntry.getKey());
						writer.newLine();	
					}
				}
			}
		}
	}

}
