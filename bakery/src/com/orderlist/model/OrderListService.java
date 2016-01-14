package com.orderlist.model;

import com.order.model.OrderJNDIDAO;

public class OrderListService {
	private OrderListJNDIDAO dao;

	public OrderListService() {
		dao = new OrderListJNDIDAO();
	}
	public void delete(int orderId){
		dao.delete(orderId);
		OrderJNDIDAO dao1 = new OrderJNDIDAO();
		dao1.delete(orderId);
	}
}
