package com.example.cdwebbe.payload;

public class ChangeToOrderRequest {
	 private int idProducts[];
	 private int feeTotal;
	 private String address;
	 private String phoneNumber;
	public ChangeToOrderRequest() {
		super();
	}
	public ChangeToOrderRequest(int[] idProducts, int feeTotal, String address, String phoneNumber) {
		super();
		this.idProducts = idProducts;
		this.feeTotal = feeTotal;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public ChangeToOrderRequest(int feeTotal, String address, String phoneNumber) {
		this.feeTotal = feeTotal;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public int[] getIdProducts() {
		return idProducts;
	}
	public void setIdProducts(int[] idProducts) {
		this.idProducts = idProducts;
	}
	public int getFeeTotal() {
		return feeTotal;
	}
	public void setFeeTotal(int feeTotal) {
		this.feeTotal = feeTotal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	 

	 
}
