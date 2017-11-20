package com.oracleoaec.jdbc;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {
	private Long id;
	private Date orderDate;
	private Date shippedDate;
	private Double total;
	private Set<OrderLine> orderlines=new HashSet<OrderLine>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Set<OrderLine> getOrderlines() {
		return orderlines;
	}
	public void setOrderlines(Set<OrderLine> orderlines) {
		this.orderlines = orderlines;
	}
	
	
	public Order(){}
	public Order(Long id, Date orderDate, Date shippedDate, Double total, Set<OrderLine> orderlines) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.shippedDate = shippedDate;
		this.total = total;
		this.orderlines = orderlines;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", shippedDate=" + shippedDate + ", total=" + total
				+ ", orderlines=" + orderlines + "]";
	} 

	
}
