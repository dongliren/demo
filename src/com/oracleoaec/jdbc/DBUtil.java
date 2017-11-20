package com.oracleoaec.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	/**
	 * 关闭数据资源
	 * */
	
	public static void close(Connection conn,Statement stmt,PreparedStatement pstmt,ResultSet rs){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn){
		close(conn,null,null,null);
	}
	public static void close(Statement stmt){
		close(null,stmt,null,null);
	}
	public static void close(PreparedStatement pstmt){
		close(null,null,pstmt,null);
	}
	public static void close(ResultSet rs){
		close(null,null,null,rs);
	}

}
