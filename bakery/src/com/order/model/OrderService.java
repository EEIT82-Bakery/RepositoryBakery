package com.order.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import com.orderlist.model.OrderListBean;
import com.orderlist.model.OrderListJNDIDAO;


public class OrderService {
	private OrderJNDIDAO dao;

	public OrderService() {
		dao = new OrderJNDIDAO();
	}
	
	public void update(OrderBean bean) {
		if(bean!=null) {
			dao.update(bean);
		}
	}
	public void insertOrder(OrderBean bean, Vector<OrderListBean> beans) {
		int orderId = dao.insertOrder(bean);
		OrderListJNDIDAO orderListDAO = new OrderListJNDIDAO();
		orderListDAO.insertOrderItems(orderId, beans);
	}
	
	public List<OrderBean> select(int OrderId){
		return dao.select(OrderId);
	}
	
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	public java.util.Date convertDate(String data) {
		java.util.Date result = null;
		try {
			result = sFormat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			result = new java.util.Date(0);
		}
		return result;
	}
}
