package com.beans;

public class fooditem {
	private int foodId;
	private String foodName;
	private String vendorId;
	private double price;
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public fooditem(int foodId, String foodName, String vendorId, double price) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.vendorId = vendorId;
		this.price = price;
	}
	@Override
	public String toString() {
		return "fooditem [foodId=" + foodId + ", foodName=" + foodName + ", vendorId=" + vendorId + ", price=" + price
				+ "]";
	}
}
