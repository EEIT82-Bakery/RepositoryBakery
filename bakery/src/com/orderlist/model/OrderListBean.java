package com.orderlist.model;

public class OrderListBean implements java.io.Serializable{
	private int orderId;
	private int productId;
	private int quantity;
	
	
	
	
	@Override
	public String toString() {
		return "OrderListBean [orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + "]";
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}

	