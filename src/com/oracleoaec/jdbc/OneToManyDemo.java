package com.oracleoaec.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OneToManyDemo {
	Connection conn=null;
	@SuppressWarnings("resource")
	
	public void savePeople(Order order){
		PreparedStatement pstmt =null;
		PreparedStatement pstmt2 =null;
		ResultSet rs =null;
		conn=ConnectionFactory.getConnection();
	    //主键不要在sql中使用
		try {
			//conn.setAutoCommit(false);   自动提交的值默认为true
			String selectSQL="select order_s.nextval from dual";
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			Long orderid=0L;
			if(rs.next()){
				orderid=rs.getLong(1);
			}
			String insertSQL="insert into t_order values (?,?,?,?)";
			pstmt=conn.prepareStatement(insertSQL);
			pstmt.setLong(1,orderid);
			pstmt.setDate(2,order.getOrderDate());
			pstmt.setDate(3,order.getShippedDate());
			pstmt.setDouble(4, order.getTotal());
			pstmt.executeQuery();
			
			insertSQL="insert into t_orderline values(?,?,?,?,?)";
			pstmt=conn.prepareStatement(insertSQL);
			
			//根据order.getOrderlines()集合 循环获取orderlineId
			selectSQL="select orderline_s.nextval from dual";
			Long orderlineId=0L;
			pstmt2=conn.prepareStatement(selectSQL);  //执行
			for(OrderLine ol:order.getOrderlines()){
				rs=pstmt2.executeQuery();//循环获取结果集
				if(rs.next()){
					orderlineId=rs.getLong(1);
				}
				pstmt.setLong(1,orderlineId);
				pstmt.setDouble(2,ol.getPrice());
				pstmt.setLong(3,ol.getQuantity());
				pstmt.setString(4,ol.getProduct());
				pstmt.setLong(5, orderid);
				pstmt.executeQuery();
			}
			System.out.println("成功插入");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, null, pstmt, rs);
		}
	}
	
	@SuppressWarnings("resource")
	public void deleteOrder(Long id){
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		conn=ConnectionFactory.getConnection();
	    //主键不要在sql中使用
		try {
			String deleteSQL="delete t_orderline where oder_id=?";
			pstmt=conn.prepareStatement(deleteSQL);
			pstmt.setLong(1,id);
			pstmt.executeUpdate();
			
			deleteSQL="delete t_order where id=?";
			pstmt=conn.prepareStatement(deleteSQL);
			pstmt.setLong(1,id);
			pstmt.executeUpdate();
			
			System.out.println("成功删除信息");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, null, pstmt, rs);
		}
	}
	
	
	@SuppressWarnings("resource")
	public void updateOrder(Order order){
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		conn=ConnectionFactory.getConnection();
	    //主键不要在sql中使用
		try {
			
			String updateSQL="update t_order set shipped_date = ?,total = ? where id = ?";
			pstmt=conn.prepareStatement(updateSQL);
			pstmt.setDate(1, order.getShippedDate());
			pstmt.setDouble(2, order.getTotal());
			pstmt.setLong(3,order.getId());
			pstmt.executeUpdate();
		
			updateSQL="update t_orderline set price = ?,quantity = ? where oder_id = ?";
			pstmt=conn.prepareStatement(updateSQL);
			for(OrderLine ol:order.getOrderlines()){
				pstmt.setDouble(1, ol.getPrice());
				pstmt.setDouble(2, ol.getQuantity());
				pstmt.setLong(3, order.getId());
				pstmt.executeUpdate();
			}
		    System.out.println("已经成功修改");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, null, pstmt, rs);
		}
	}
	
	@SuppressWarnings("resource")
	public Order findOrder(Long id){
		Set<OrderLine> set=new HashSet<OrderLine>();
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		Order order=new Order();
		conn=ConnectionFactory.getConnection();
	    //主键不要在sql中使用
		try {
			String findSQL="select id,ordered_date,shipped_date,total from t_order where id = ?";
			pstmt=conn.prepareStatement(findSQL);
			pstmt.setLong(1, id);;
			rs = pstmt.executeQuery();
			if(rs.next()){
				order.setId(rs.getLong(1));
				order.setOrderDate(rs.getDate(2));
				order.setShippedDate(rs.getDate(3));
				order.setTotal(rs.getDouble(4));
				//pstmt.executeUpdate();
			}
			//order.setOrderlines(orderlines);
			
			findSQL="select id,price,quantity,product from t_orderline where Oder_id = ?";
			pstmt=conn.prepareStatement(findSQL);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				OrderLine ol=new OrderLine();
				ol.setId(rs.getLong(1));
				ol.setPrice(rs.getDouble(2));
				ol.setQuantity(rs.getLong(3));
				ol.setProduct(rs.getString(4));
				//pstmt.executeUpdate();
				ol.setOrder(order);
				order.getOrderlines().add(ol);
			}
			System.out.println("查找成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, null, pstmt, rs);
		}	
		return order;
	}
	
	public List<Order> findOrders(){
		List<Order> list=new ArrayList<Order>();
		Set<OrderLine> set=new HashSet<OrderLine>();
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		Order order=new Order();
		conn=ConnectionFactory.getConnection();
	    //主键不要在sql中使用
		try {
			String findSQL="select id,ordered_date,shipped_date,total from t_order";
			pstmt=conn.prepareStatement(findSQL);
			rs = pstmt.executeQuery();
			if(rs.next()){
				order.setId(rs.getLong(1));
				order.setOrderDate(rs.getDate(2));
				order.setShippedDate(rs.getDate(3));
				order.setTotal(rs.getDouble(4));
				//pstmt.executeUpdate();
			}
			//order.setOrderlines(orderlines);
			
			findSQL="select id,price,quantity,product from t_orderline where Oder_id = ?";
			pstmt=conn.prepareStatement(findSQL);
			//pstmt.setLong(1, id);;
			rs = pstmt.executeQuery();
			while(rs.next()){
				OrderLine ol=new OrderLine();
				ol.setId(rs.getLong(1));
				ol.setPrice(rs.getDouble(2));
				ol.setQuantity(rs.getLong(3));
				ol.setProduct(rs.getString(4));
				//pstmt.executeUpdate();
				ol.setOrder(order);
				order.getOrderlines().add(ol);
			}
			System.out.println("查找成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, null, pstmt, rs);
		}	
		return null;
	}
	
}
