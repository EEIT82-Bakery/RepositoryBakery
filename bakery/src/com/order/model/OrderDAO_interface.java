package com.order.model;

import java.util.List;

public interface OrderDAO_interface {
	public void getConnection();
	public int insertOrder(OrderBean bean);
	public void delete(int orderId); 

}
