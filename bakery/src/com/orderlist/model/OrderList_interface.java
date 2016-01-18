package com.orderlist.model;

import java.util.List;
import java.util.Vector;

public interface OrderList_interface {
	public abstract void insertOrderItems(int orderId, Vector<OrderListBean> beans);
	public abstract void delete(int orderId);
	public abstract List<OrderListBean> selectProductList(int OrderId);
}
