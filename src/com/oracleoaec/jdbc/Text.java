package com.oracleoaec.jdbc;

import java.util.Calendar;
import java.sql.Date;

public class Text {

	public static void main(String[] args) {
		OneToManyDemo otmd=new OneToManyDemo();
		Order order=new Order();
		
		order.setId(3L);
		order.setOrderDate(new Date(System.currentTimeMillis()));//通过Date获取系统当前时间
		Calendar cl=Calendar.getInstance();//接口    使用默认时区和语言环境获得一个日历。
		cl.set(Calendar.DAY_OF_MONTH, cl.get(Calendar.DAY_OF_MONTH)+3);//通过cl设置当前时间
		    //DAY_OF_MONTH指示一个月中的某天    把当前的这一天加两天设置为当前天数
		
		//Date(long date) 分配 Date 对象并初始化此对象，
		//以表示自从标准基准时间（称为“历元（epoch）”，即 1970 年 1 月 1 日 00:00:00 GMT）
		//以来的指定毫秒数。
		//getTime() 返回一个表示此 Calendar 时间值（从历元至现在的毫秒偏移量）的 Date 对象。
		order.setShippedDate(new Date(cl.getTime().getTime()));
		
		OrderLine ol1=new OrderLine();
		ol1.setProduct("数学");
		ol1.setPrice(20.44);
		ol1.setQuantity(100L);
		
		OrderLine ol2=new OrderLine();
		ol2.setProduct("英语");
		ol2.setPrice(15.22); 
		ol2.setQuantity(10L);
		
		
		ol1.setOrder(order);
		ol2.setOrder(order);
		order.getOrderlines().add(ol1);
		order.getOrderlines().add(ol2);
		double total=0.0;
		//先ol1,ol2加入集合  才能集合遍历
		for(OrderLine ol:order.getOrderlines()){
			total+=ol.getPrice()*ol.getQuantity();
		}
		order.setTotal(total);
		//otmd.savePeople(order);
		
		
		//otmd.deleteOrder(order.getId());
		
		otmd.updateOrder(order);  
		
		//Order order1=otmd.findOrder(order.getId());
		//System.out.println(order1);

	}

}
