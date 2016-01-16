package com.order.model;

import java.util.List;

public interface OrderDAO_interface {

	public List<OrderBean> select(int OrderId);
	public int insertOrder(OrderBean bean);
	public void delete(int orderId); 

}
