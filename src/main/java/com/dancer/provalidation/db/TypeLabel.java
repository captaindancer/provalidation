package com.dancer.provalidation.db;

import java.util.HashMap;
import java.util.Map;


public class TypeLabel {
	
	//private static final Logger LOGGER=LoggerFactory.getLogger(BooklistSyncService.class);

	private static final Map<Integer, String> typeValue=new HashMap<Integer,String>(30);
	
	static{
		//初始化typeValue：type值对应的默认label
		typeValue.put(1, "高V精选");
		typeValue.put(2, "经典书单");
		typeValue.put(3, "小编推荐");
		typeValue.put(4, "用户书单");
		typeValue.put(5, "作家书单");
		typeValue.put(101, "玄幻书单");
		typeValue.put(102, "奇幻书单");
		typeValue.put(103, "武侠书单");
		typeValue.put(104, "仙侠书单");
		typeValue.put(105, "都市书单");
		typeValue.put(106, "现实书单");
		typeValue.put(107, "军事书单");
		typeValue.put(108, "历史书单");
		typeValue.put(109, "游戏书单");
		typeValue.put(110, "体育书单");
		typeValue.put(111, "科幻书单");
		typeValue.put(112, "灵异书单");
		typeValue.put(113, "二次元");
		typeValue.put(114, "短篇书单");
		typeValue.put(115, "古代言情");
		typeValue.put(116, "仙侠奇缘");
		typeValue.put(117, "现代言情");
		typeValue.put(118, "浪漫青春");
		typeValue.put(119, "玄幻言情");
		typeValue.put(120, "悬疑灵异");
		typeValue.put(121, "科幻空间");
		typeValue.put(122, "游戏竞技");
		typeValue.put(123, "N次元");
	}
	
	public static String getDefaultLabelValue(int index){
		String result=typeValue.get(index);
		return result;
	}
	
}
