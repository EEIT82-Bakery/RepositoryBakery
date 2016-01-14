package com.order.model;

import java.util.List;

import com.orderlist.model.OrderListBean;
import com.orderlist.model.OrderListDAOJNDI;

public class OrderService {
	private OrderDAOJNDI dao;

	public OrderService() {
		dao = new OrderDAOJNDI();
	}

	public void insertOrder(int orderName, List<OrderListBean> beans) {
		int orderId = dao.insertOrder(orderName);
		OrderListDAOJNDI orderListDAO = new OrderListDAOJNDI();
		orderListDAO.insertOrderItems(orderId, beans);
	}
}
