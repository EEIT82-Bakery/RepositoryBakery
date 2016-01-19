package com.order.model;

import java.util.List;

import com.product.model.ProductBean;

public interface OrderDAO_interface {

	public abstract List<OrderBean> select(int OrderId);
	public List<OrderBean> selectMember(int memberId);
	public abstract List<OrderBean> selectList();
	public abstract int insertOrder(OrderBean bean);
	public abstract void delete(int orderId); 

}
