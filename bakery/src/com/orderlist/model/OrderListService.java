package com.orderlist.model;

import java.util.List;

import com.order.model.OrderJNDIDAO;

public class OrderListService {
	private OrderListJNDIDAO dao;

	public OrderListService() {
		dao = new OrderListJNDIDAO();
	}
	
	public List<OrderListBean> selectProductList(int OrderId){
		return	dao.selectProductList(OrderId);
	}
	
	public void delete(int orderId){
		dao.delete(orderId);
		OrderJNDIDAO dao1 = new OrderJNDIDAO();
		dao1.delete(orderId);
	}
	
}
