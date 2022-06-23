package com.example.cdwebbe.payload;

public class ChangeToOrderRequest {
	 private int idProducts[];
	 private int feeTotal;

	public ChangeToOrderRequest() {
		super();
	}

	public ChangeToOrderRequest(int[] idProducts, int feeTotal) {
		super();
		this.idProducts = idProducts;
		this.feeTotal = feeTotal;
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
	
	 

	 
}
