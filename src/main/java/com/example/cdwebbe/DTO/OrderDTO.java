package com.example.cdwebbe.DTO;

import java.util.Date;

public class OrderDTO {
	private Long id;
	private String address;
	private Date dateDelivery;
	private Date dateCreate;

	private double totalPriceOrder;

	private double shipfee;

	private String phoneNumber;

	public OrderDTO(Long id, String address, Date dateDelivery, Date dateCreate, double totalPriceOrder, double shipfee,
			String phoneNumber) {
		super();
		this.id = id;
		this.address = address;
		this.dateDelivery = dateDelivery;
		this.dateCreate = dateCreate;
		this.totalPriceOrder = totalPriceOrder;
		this.shipfee = shipfee;
		this.phoneNumber = phoneNumber;
	}

	public OrderDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(Date dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public double getTotalPriceOrder() {
		return totalPriceOrder;
	}

	public void setTotalPriceOrder(double totalPriceOrder) {
		this.totalPriceOrder = totalPriceOrder;
	}

	public double getShipfee() {
		return shipfee;
	}

	public void setShipfee(double shipfee) {
		this.shipfee = shipfee;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", address=" + address + ", dateDelivery=" + dateDelivery + ", dateCreate="
				+ dateCreate + ", totalPriceOrder=" + totalPriceOrder + ", shipfee=" + shipfee + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	

}
