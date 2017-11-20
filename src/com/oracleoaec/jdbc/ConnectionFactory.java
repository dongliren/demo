package com.oracleoaec.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	
	static{
		//通过创建静态方法，解析properties文件
		Properties pro=new Properties();
		//解析配置文件，转换成流
		InputStream is=ConnectionFactory.class.getResourceAsStream("jdbcinfo.properties");
		try {
			//加载流，读取流放置，通过键值对获得配置参数
			pro.load(is);
			DRIVER=pro.getProperty("jdbc.driver");
			URL=pro.getProperty("jdbc.url");
			USER=pro.getProperty("jdbc.user");
			PASSWORD=pro.getProperty("jdbc.password");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 通过工厂获取数据库连接  
	 * @return Connection
	 * */
	
	public static Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	/*public static void main(String[] args) {
		System.out.println(getConnection());
	}*/
	

}
