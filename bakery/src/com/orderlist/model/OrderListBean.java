package com.orderlist.model;

public class OrderListBean implements java.io.Serializable{
	private int orderId;
	private int productId;
	private int quantity;
	private String productName;
	private String productStatus;
	
	
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	
	
}

	