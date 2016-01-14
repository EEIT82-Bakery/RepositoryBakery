package com.orderlist.model;

public class OrderListBean {
	private int orderId;
	private int productId;
	private int count;
	
	
	
	@Override
	public String toString() {
		return "OrderListBean [orderId=" + orderId + ", productId=" + productId + ", count=" + count + "]";
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}

	