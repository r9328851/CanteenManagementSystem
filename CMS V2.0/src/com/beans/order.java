package com.beans;

import java.sql.Date;

public class order {
	private int orderId;
	private String vendorId;
	private String customerId;
	private int foodId;
	private int quantity;
	private Date date;
	private double orderValue;
	private String orderStatus;
	public order(int orderId, String vendorId, String customerId, int foodId, int quantity, Date date, double orderValue,
			String orderStatus) {
		super();
		this.orderId = orderId;
		this.vendorId = vendorId;
		this.customerId = customerId;
		this.foodId = foodId;
		this.quantity = quantity;
		this.date = date;
		this.orderValue = orderValue;
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "\n Order Id = " + orderId + "\nVendor Id = " + vendorId + "\nCustomer Id = " + customerId + "\nFood Id = "
				+ foodId + "\nQuantity = " + quantity + "\nOrdered Date = " + date + "\nOrder Value=" + orderValue + "\nOrder Status = "
				+ orderStatus ;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(double orderValue) {
		this.orderValue = orderValue;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}	
}
