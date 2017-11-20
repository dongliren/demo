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
		//ͨ��������̬����������properties�ļ�
		Properties pro=new Properties();
		//���������ļ���ת������
		InputStream is=ConnectionFactory.class.getResourceAsStream("jdbcinfo.properties");
		try {
			//����������ȡ�����ã�ͨ����ֵ�Ի�����ò���
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
	 * ͨ��������ȡ���ݿ�����  
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
