package com.dancer.provalidation.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class ConvertUtils {

	/**
	 * 将booklist转化为booklistSync，其中owner_name字段需要另外设置
	 * @param booklist 传进来的booklist对象
	 * @return 转化成的booklistSync对象
	 */
	public static BooklistSync booklist2BooklistSync(Booklist booklist){
		BooklistSync booklistSync=new BooklistSync();
		booklistSync.setBook_add_count(booklist.getBook_add_count());
		booklistSync.setBook_count(booklist.getBook_count());
		booklistSync.setBook_list_description(booklist.getBook_list_description());
		booklistSync.setBook_list_id(booklist.getId());
		booklistSync.setBook_list_name(booklist.getBook_list_name());
		booklistSync.setCollect_count(booklist.getCollect_count());
		booklistSync.setComment_count(booklist.getComment_count());
		booklistSync.setCreate_time(booklist.getCreate_time());
		booklistSync.setFresh_time(booklist.getFresh_time());
		booklistSync.setLabel(booklist.getLabel());
		booklistSync.setOperate_time(booklist.getOperate_time());
		booklistSync.setOperator(booklist.getOperator());
		booklistSync.setOwner_description(booklist.getOwner_description());
		booklistSync.setOwner_id(booklist.getOwner_id());
		booklistSync.setShow_time(booklist.getShow_time());
		booklistSync.setType(booklist.getType());
		booklistSync.setUpdate_time(booklist.getUpdate_time());
		return booklistSync;
	}
	
	/**
	 * 将SQL查询结果转化为booklist集合。<em> 注意：booklist对象的label属性如果为空的话，根据type值取默认值</em>
	 * @param resultSet 从book_list表执行产生的SQL查询的结果
	 * @return 生成的booklist集合
	 * @throws SQLException
	 */
	public static List<Booklist> sqlresult2BooKlist(ResultSet resultSet) throws SQLException{
		List<Booklist> list=new ArrayList<Booklist>();
		Booklist booklist=null;
		while(resultSet.next()){
			booklist=new Booklist();
			booklist.setId(resultSet.getLong(1));
			booklist.setOwner_id(resultSet.getLong(2));
			booklist.setBook_list_name(resultSet.getString(3));
			booklist.setBook_list_description(resultSet.getString(4));
			booklist.setOwner_description(resultSet.getString(5));
			//如果label为""的话，根据type值取默认的label
			int type=resultSet.getInt(7);
			String label=resultSet.getString(6);
			if(StringUtils.isBlank(label)){
				if(StringUtils.isBlank(TypeLabel.getDefaultLabelValue(type))){
					booklist.setLabel("");
				}else{
					booklist.setLabel(TypeLabel.getDefaultLabelValue(type));
				}
			}else{
				booklist.setLabel(label);
			}
			booklist.setType(type);
			booklist.setCollect_count(resultSet.getLong(8));
			booklist.setComment_count(resultSet.getLong(9));
			booklist.setDel_flag(resultSet.getInt(10));
			booklist.setCheck_flag(resultSet.getInt(11));
			booklist.setShow_flag(resultSet.getInt(12));
			booklist.setCreate_time(resultSet.getTimestamp(13));
			booklist.setUpdate_time(resultSet.getTimestamp(14));
			booklist.setShow_time(resultSet.getTimestamp(15));
			booklist.setOperator(resultSet.getString(16));
			booklist.setOperate_time(resultSet.getTimestamp(17));
			booklist.setBook_add_count(resultSet.getInt(18));
			booklist.setBook_count(resultSet.getLong(19));
			booklist.setFresh_time(resultSet.getTimestamp(20));
			
			list.add(booklist);
		}
		return list;
	}
	
	/**
	 * 根据SQL查询结果生成booklist对象，并将booklist对象转换为booklistSync对象，返回booklistSync对象集合
	 * @param resultSet 从book_list表执行产生的SQL查询的结果
	 * @return booklistSync对象集合
	 * @throws SQLException
	 */
	public static List<BooklistSync> sqlresult2BooklistSync(ResultSet resultSet) throws SQLException{
		List<BooklistSync> syncList=new ArrayList<BooklistSync>();
		Booklist booklist=null;
		BooklistSync booklistSync=null;
		while(resultSet.next()){
			booklist=new Booklist();
			booklist.setId(resultSet.getLong(1));
			booklist.setOwner_id(resultSet.getLong(2));
			booklist.setBook_list_name(resultSet.getString(3));
			booklist.setBook_list_description(resultSet.getString(4));
			booklist.setOwner_description(resultSet.getString(5));
			//如果label为""的话，根据type值取默认的label
			int type=resultSet.getInt(7);
			String label=resultSet.getString(6);
			if(StringUtils.isBlank(label)){
				if(StringUtils.isBlank(TypeLabel.getDefaultLabelValue(type))){
					booklist.setLabel("");
				}else{
					booklist.setLabel(TypeLabel.getDefaultLabelValue(type));
				}
			}else{
				booklist.setLabel(label);
			}
			booklist.setType(type);
			booklist.setCollect_count(resultSet.getLong(8));
			booklist.setComment_count(resultSet.getLong(9));
			booklist.setDel_flag(resultSet.getInt(10));
			booklist.setCheck_flag(resultSet.getInt(11));
			booklist.setShow_flag(resultSet.getInt(12));
			booklist.setCreate_time(resultSet.getTimestamp(13));
			booklist.setUpdate_time(resultSet.getTimestamp(14));
			booklist.setShow_time(resultSet.getTimestamp(15));
			booklist.setOperator(resultSet.getString(16));
			booklist.setOperate_time(resultSet.getTimestamp(17));
			booklist.setBook_add_count(resultSet.getInt(18));
			booklist.setBook_count(resultSet.getLong(19));
			booklist.setFresh_time(resultSet.getTimestamp(20));
			
			booklistSync=booklist2BooklistSync(booklist);
			syncList.add(booklistSync);
		}
		return syncList;
	}
}
