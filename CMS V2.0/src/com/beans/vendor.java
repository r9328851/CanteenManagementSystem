package com.beans;

public class vendor {
	private String vendorId;
	private String name;
	private String phone;
	private String specs;
	public vendor(String vendorId, String name, String phone, String specs) {
		this.vendorId = vendorId;
		this.name = name;
		this.phone = phone;
		this.specs = specs;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	@Override
	public String toString() {
		return "vendor [vendorId=" + vendorId + ", name=" + name + ", phone=" + phone + ", specs=" + specs + "]";
	}
}
