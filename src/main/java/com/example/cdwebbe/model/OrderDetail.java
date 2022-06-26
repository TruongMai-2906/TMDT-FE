package com.example.cdwebbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private int quantity;

    private double totalPrice;
    private boolean status;


    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;



    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="orderId",referencedColumnName = "id")
    private Order order;


	public OrderDetail(Long id,  int quantity,  double totalPrice, Product product, Order order) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.product = product;
		this.order = order;
	}


	public OrderDetail(Long id, int quantity, double totalPrice, boolean status, Product product, Order order) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.status = status;
		this.product = product;
		this.order = order;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public OrderDetail() {
		super();
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
    
}
