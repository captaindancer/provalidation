package com.dancer.provalidation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCMain {

	public static void selectYWDB() throws SQLException{
//		String url="jdbc:mysql://localhost:3306/ywdb?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
		String url="jdbc:mysql://localhost:3306";
		String username="root";
		String password="liufeng";
		Connection connection=DriverManager.getConnection(url, username, password);
		String querySQL="select count(1) as cnt from ywdb.book_list";
		PreparedStatement preparedStatement=connection.prepareStatement(querySQL);
		ResultSet resultSet=preparedStatement.executeQuery();
		if(!resultSet.next()){
			System.out.println("没有数据");
		}else{
			System.out.println(resultSet.getInt(1));
		}
		String ttq2String="select count(1) as cnt from monitor.ttq2";
		PreparedStatement ttq2PreparedStatement=connection.prepareStatement(ttq2String);
		ResultSet ttq2ResultSet=ttq2PreparedStatement.executeQuery();
		if(!ttq2ResultSet.next()){
			System.out.println("没有数据");
		}else{
			System.out.println(ttq2ResultSet.getInt(1));
		}
	}
	
	public static void insertBooklist() throws SQLException{
		String url="jdbc:mysql://localhost:3306/ywdb?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
		String username="root";
		String password="liufeng";
		Connection connection=DriverManager.getConnection(url, username, password);
		String insertSQL="insert into t_ed_qidian_activeusers(statis_day,userid) values(?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(insertSQL);
		preparedStatement.setInt(1, 20170921);
		preparedStatement.setLong(2, 17);
		int count=preparedStatement.executeUpdate();
		System.out.println("插入了："+count);
	}
	
	public static void main(String[] args) throws SQLException {
//		selectYWDB();
		int code=0;
		try{
			insertBooklist();
		}catch (SQLException e) {
			code=e.getErrorCode();
			System.out.println(code);
			e.printStackTrace();
		}
	}

}
