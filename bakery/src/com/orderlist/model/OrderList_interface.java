package com.orderlist.model;

import java.util.List;
import java.util.Vector;

public interface OrderList_interface {
	public void insertOrderItems(int orderId, Vector<OrderListBean> beans);
	public void delete(int orderId);
}
