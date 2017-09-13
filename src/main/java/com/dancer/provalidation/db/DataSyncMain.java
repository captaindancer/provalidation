package com.dancer.provalidation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DataSyncMain {

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("加载mysql驱动类失败！");
			throw new ExceptionInInitializerError();
		}
	}
	
	public static void free(ResultSet resultSet, Statement statement,Connection connection) {
		try {
			if(resultSet!=null){
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("resultset关闭异常");
		}finally{
			try{
				if(statement!=null){
					statement.close();
				}
			}catch(SQLException e){
				System.out.println("statement关闭异常");
			}finally{
				try {
					if(connection!=null)
						connection.close();
				} catch (SQLException e) {
					System.out.println("connection关闭异常");
				}
			}
		}
	}
	
	public static void selectLatestTime(int type) throws SQLException{
		String url="jdbc:mysql://localhost:3306/ywdb?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
		String username="root";
		String password="liufeng";
		Connection connection=DriverManager.getConnection(url, username, password);
		String query="select recordtime from t_sd_qidian_booklist_timemark where"
				+ " id=(select max(id) from t_sd_qidian_booklist_timemark where type=?)";
		Date date=null;
		PreparedStatement preparedStatemen=connection.prepareStatement(query);
		preparedStatemen.setInt(1, type);
		ResultSet resultSet=preparedStatemen.executeQuery();
		if(!resultSet.next()){
			System.out.println("没有数据");
		}else{
			do{
				date=resultSet.getTimestamp(1);
				System.out.println(date);
			}while(resultSet.next());
		}
		free(resultSet, preparedStatemen, connection);
		System.out.println(connection.isClosed());
		System.out.println(connection);
		connection.close();
	}
	
	public static void delete() throws SQLException{
		String url="jdbc:mysql://localhost:3306/ywdb?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
		String username="root";
		String password="liufeng";
		Connection connection=DriverManager.getConnection(url, username, password);
		String deleteSQL="delete from t_sd_qidian_booklistbooks where book_list_id=?";
		PreparedStatement deleteStatement=connection.prepareStatement(deleteSQL);
		System.out.println(connection.getMetaData().supportsTransactions());
		connection.setAutoCommit(false);
		for(int index=534019;index<=534047;index++){
			deleteStatement.setLong(1, index);
			
			deleteStatement.addBatch();
		}
		int[] deletedData=deleteStatement.executeBatch();
		for(int deletedElement : deletedData){
			System.out.println(deletedElement);
		}
		System.out.println("删除的记录数为："+deletedData.length);
		System.out.println("事务提交");
//		connection.commit();

		free(null, deleteStatement, connection);
	}
	
	public static void update() throws SQLException{
		String url="jdbc:mysql://localhost:3306/ywdb?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
		String username="root";
		String password="liufeng";
		Connection connection=DriverManager.getConnection(url, username, password);
		String updateSQL="update t_sd_qidian_booklistsync set owner_name=? where book_list_id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(updateSQL);
		int recordCount=0;
		for(int index=10;index<20;index++){
			preparedStatement.setString(1, "maodaye");
			preparedStatement.setLong(2, index);
			
			preparedStatement.addBatch();
			recordCount++;
			if(recordCount>=5){
				recordCount=0;
				System.out.println(preparedStatement.executeBatch().length);
			}
		}
		int[] result=preparedStatement.executeBatch();
		System.out.println(result.length);
		for(int element : result){
			System.out.println(element);
		}
		
		free(null, preparedStatement, connection);
	}
	
	public static void select() throws SQLException{
		String url="jdbc:mysql://localhost:3306/ywdb?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
		String username="root";
		String password="liufeng";
		Connection connection=DriverManager.getConnection(url, username, password);
		String query="select recordtime from t_sd_qidian_booklist_timemark where"
				+ " id=(select max(id) from t_sd_qidian_booklist_timemark)";
		Date date=null;
		PreparedStatement preparedStatemen=connection.prepareStatement(query);
		ResultSet resultSet=preparedStatemen.executeQuery();
		if(!resultSet.next()){
			System.out.println("没有数据");
		}else{
			do{
				date=resultSet.getTimestamp(1);
				System.out.println(date);
			}while(resultSet.next());
		}
		free(resultSet, preparedStatemen, null);
		
		String booklistQuery="select id,owner_id,book_list_name,book_list_description,"
				+ "owner_description,label,type,collect_count,comment_count,del_flag,check_flag,show_flag,"
				+ "create_time,update_time,show_time,operator,operate_time,book_add_count,book_count,fresh_time "
				+ " from book_list where fresh_time >= ?";
		PreparedStatement booklistStatement=connection.prepareStatement(booklistQuery);
		Timestamp timestamp=new Timestamp(date.getTime());
		System.out.println(timestamp);
		booklistStatement.setTimestamp(1, timestamp);
		ResultSet booklistRS=booklistStatement.executeQuery();
		
		int count=0;
		Booklist booklist=null;
		List<Booklist> list=new ArrayList<Booklist>();
		while(booklistRS.next()){
			booklist=new Booklist();
			booklist.setId(booklistRS.getLong(1));
			booklist.setOwner_id(booklistRS.getLong(2));
			booklist.setBook_list_name(booklistRS.getString(3));
			booklist.setBook_list_description(booklistRS.getString(4));
			booklist.setOwner_description(booklistRS.getString(5));
			//如果label为""的话，根据type值取默认的label
			int type=booklistRS.getInt(7);
			String label=booklistRS.getString(6);
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
			booklist.setCollect_count(booklistRS.getLong(8));
			booklist.setComment_count(booklistRS.getLong(9));
			booklist.setDel_flag(booklistRS.getInt(10));
			booklist.setCheck_flag(booklistRS.getInt(11));
			booklist.setShow_flag(booklistRS.getInt(12));
			booklist.setCreate_time(booklistRS.getTimestamp(13));
			booklist.setUpdate_time(booklistRS.getTimestamp(14));
			booklist.setShow_time(booklistRS.getTimestamp(15));
			booklist.setOperator(booklistRS.getString(16));
			booklist.setOperate_time(booklistRS.getTimestamp(17));
			booklist.setBook_add_count(booklistRS.getInt(18));
			booklist.setBook_count(booklistRS.getLong(19));
			booklist.setFresh_time(booklistRS.getTimestamp(20));
			
//			System.out.println(booklist);
			list.add(booklist);
			count++;
		}
		System.out.println(count);
		int okcount=0;
		int blankcount=0;
		for(Booklist book:list){
			if(book.getDel_flag()==0 && book.getCheck_flag() == 1 && book.getShow_flag() ==1){
				okcount++;
			}
			if(StringUtils.isBlank(book.getLabel())){
				System.out.println(book);
				blankcount++;
			}
			if(book.getOperate_time() == null){
				System.out.println(book);
			}
		}
		System.out.println(okcount);
		System.out.println(blankcount);
		free(booklistRS, booklistStatement, connection);
	}
	
	public static int insert() throws SQLException{
		String url="jdbc:mysql://localhost:3306/ywdb?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
		String username="root";
		String password="liufeng";
		Connection connection=DriverManager.getConnection(url, username, password);
		String insert="insert into t_sd_qidian_booklist_timemark(recordtime,type) values(?,?)";
		PreparedStatement insertStatement=connection.prepareStatement(insert);
		insertStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
		insertStatement.setInt(2, 1);
		int count=insertStatement.executeUpdate();
		System.out.println("插入了："+count);
		free(null, insertStatement, connection);
		return count;
	}
	
	public static void insertBooklistSync() throws SQLException{
		String url="jdbc:mysql://localhost:3306/ywdb?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
		String username="root";
		String password="liufeng";
		Connection connection=DriverManager.getConnection(url, username, password);
		connection.prepareStatement("set names utf8mb4").executeQuery();
		String insertSQL="insert into t_sd_qidian_booklistsync(owner_id,owner_name,book_list_id,"
				+ "book_list_name,book_list_description,owner_description,label,type,collect_count,comment_count,"
				+ "create_time,update_time,show_time,operator,operate_time,book_add_count,"
				+ "book_count,fresh_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(insertSQL);
		for(int index=20;index<30;index++){
			preparedStatement.setLong(1, 3);
			preparedStatement.setString(2, "3");
			preparedStatement.setLong(3, index);
			preparedStatement.setString(4, "3");
			preparedStatement.setString(5, "😊📣😢");
			preparedStatement.setString(6, "3");
			preparedStatement.setString(7, "3");
			preparedStatement.setInt(8, 3);
			preparedStatement.setLong(9, 3);
			preparedStatement.setLong(10, 3);
			preparedStatement.setTimestamp(11, new Timestamp(0));
			preparedStatement.setTimestamp(12, new Timestamp(0));
			preparedStatement.setTimestamp(13, new Timestamp(0));
			preparedStatement.setString(14, null);
			preparedStatement.setString(15, null);
			preparedStatement.setInt(16, 3);
			preparedStatement.setLong(17, 3);
			preparedStatement.setTimestamp(18, null);
			
			preparedStatement.addBatch();
		}
		int[] result=preparedStatement.executeBatch();
		for(int element : result){
			System.out.println(element);
		}
//		System.out.println(preparedStatement.executeUpdate());
		free(null, preparedStatement, connection);
	}
	
	public static void selectBooklistSync() throws SQLException{
		String url="jdbc:mysql://localhost:3306/ywdb?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
		String username="root";
		String password="liufeng";
		Connection connection=DriverManager.getConnection(url, username, password);
		String selectSQL="select owner_id,owner_name,book_list_id,book_list_name,book_list_description,owner_description,label,type,collect_count,comment_count,create_time,update_time,show_time,operator,operate_time,book_add_count,book_count,fresh_time from t_sd_qidian_booklistsync where book_list_id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(selectSQL);
		preparedStatement.setLong(1, 4);
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()){
			long owner_id=resultSet.getLong(1);
			System.out.println(owner_id);
			String owner_name=resultSet.getString(2);
			System.out.println(owner_name);
			long book_list_id=resultSet.getLong(3);
			System.out.println(book_list_id);
			String book_list_name=resultSet.getString(4);
			System.out.println(book_list_name);
			String book_description=resultSet.getString(5);
			System.out.println(book_description);
			String owner_description=resultSet.getString(6);
			System.out.println(owner_description);
			String label=resultSet.getString(7);
			System.out.println(label);
			int type=resultSet.getInt(8);
			System.out.println(type);
			long collect_count=resultSet.getLong(9);
			System.out.println(collect_count);
			long comment_count=resultSet.getLong(10);
			System.out.println(comment_count);
			Date create_time=resultSet.getTimestamp(11);
			System.out.println(create_time);
			Date update_time=resultSet.getTimestamp(12);
			System.out.println(update_time);
			Date show_time=resultSet.getTimestamp(13);
			System.out.println(show_time);
			System.out.println(show_time == null);
			String operator=resultSet.getString(14);
			System.out.println(operator);
			System.out.println(operator == null);
			Date operate_time=resultSet.getTimestamp(15);
			System.out.println(operate_time);
			System.out.println(operate_time == null);
			int book_add_count=resultSet.getInt(16);
			System.out.println(book_add_count);
			long book_count=resultSet.getLong(17);
			System.out.println(book_count);
			Date fresh_time=resultSet.getTimestamp(18);
			System.out.println(fresh_time);
		}
		free(resultSet, preparedStatement, connection);
	}
	
	public static void main(String[] args) throws SQLException {
		
//		insert();
//		query="select recordtime from t_sd_qidian_booklist_timemark";
		
		
		/*List<BooklistSync> syncList=ConvertUtils.sqlresult2BooklistSync(booklistRS);
		System.out.println(syncList.size());*/
		
//		selectLatestTime(0);
//		delete();
		update();
//		selectBooklistSync();
//		insertBooklistSync();
	}

}
