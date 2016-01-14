package com.order.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderBean implements java.io.Serializable{
	private int orderId;
	private String orderName;
	private String orderPhone;
	private String orderAddress;
	private int totalAmount;
	private java.util.Date orderDate;
	private java.util.Date shipDate;
	private java.util.Date cancelDate;
	private int memberId;
	private int orderStaus;
	

	
	
	@Override
	public String toString() {
		return "OrderBean [orderId=" + orderId + ", orderName=" + orderName + ", orderPhone=" + orderPhone
				+ ", orderAddress=" + orderAddress + ", totalAmount=" + totalAmount + ", orderDate=" + orderDate
				+ ", shipDate=" + shipDate + ", cancelDate=" + cancelDate + ", memberId=" + memberId + ", OrderStaus="
				+ orderStaus + "]";
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderPhone() {
		return orderPhone;
	}
	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public java.util.Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}
	public java.util.Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(java.util.Date shipDate) {
		this.shipDate = shipDate;
	}
	public java.util.Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(java.util.Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getOrderStaus() {
		return orderStaus;
	}
	public void setOrderStaus(int orderStaus) {
		this.orderStaus = orderStaus;
	}
	
	
}