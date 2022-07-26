package com.beans;

public class Customer {
	private String id;
	private String name;
	private String coupon; 
	private double walletBalance; 

    public Customer(String id, String name, String coupon, double walletBalance) {
    	this.id=id;
    	this.name=name;
    	this.coupon=coupon;
    	this.walletBalance=walletBalance;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", coupon=" + coupon + ", walletBalance=" + walletBalance
				+ "]";
	} 
	
}

