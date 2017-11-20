package com.oracleoaec.jdbc;

import java.util.Calendar;
import java.sql.Date;

public class Text {

	public static void main(String[] args) {
		OneToManyDemo otmd=new OneToManyDemo();
		Order order=new Order();
		
		order.setId(3L);
		order.setOrderDate(new Date(System.currentTimeMillis()));//ͨ��Date��ȡϵͳ��ǰʱ��
		Calendar cl=Calendar.getInstance();//�ӿ�    ʹ��Ĭ��ʱ�������Ի������һ��������
		cl.set(Calendar.DAY_OF_MONTH, cl.get(Calendar.DAY_OF_MONTH)+3);//ͨ��cl���õ�ǰʱ��
		    //DAY_OF_MONTHָʾһ�����е�ĳ��    �ѵ�ǰ����һ�����������Ϊ��ǰ����
		
		//Date(long date) ���� Date ���󲢳�ʼ���˶���
		//�Ա�ʾ�Դӱ�׼��׼ʱ�䣨��Ϊ����Ԫ��epoch�������� 1970 �� 1 �� 1 �� 00:00:00 GMT��
		//������ָ����������
		//getTime() ����һ����ʾ�� Calendar ʱ��ֵ������Ԫ�����ڵĺ���ƫ�������� Date ����
		order.setShippedDate(new Date(cl.getTime().getTime()));
		
		OrderLine ol1=new OrderLine();
		ol1.setProduct("��ѧ");
		ol1.setPrice(20.44);
		ol1.setQuantity(100L);
		
		OrderLine ol2=new OrderLine();
		ol2.setProduct("Ӣ��");
		ol2.setPrice(15.22); 
		ol2.setQuantity(10L);
		
		
		ol1.setOrder(order);
		ol2.setOrder(order);
		order.getOrderlines().add(ol1);
		order.getOrderlines().add(ol2);
		double total=0.0;
		//��ol1,ol2���뼯��  ���ܼ��ϱ���
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
