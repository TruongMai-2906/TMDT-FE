package com.example.cdwebbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String address; // địa chỉ nhận hàng

    @Nullable
    @Temporal(TemporalType.DATE)
    private Date dateDelivery;   // time  bắt đầu vận chuyển

    @Nullable
    @Temporal(TemporalType.DATE)
    private Date dateCreate ; // ngày order


    private double  totalPriceOrder;


    private  double shipfee;
    
    private String phoneNumber;
    


    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Order(Long id, String address, Date dateDelivery, Date dateCreate, double totalPriceOrder, double shipfee,
			String phoneNumber, User user, List<OrderDetail> orderDetailList) {
		super();
		this.id = id;
		this.address = address;
		this.dateDelivery = dateDelivery;
		this.dateCreate = dateCreate;
		this.totalPriceOrder = totalPriceOrder;
		this.shipfee = shipfee;
		this.phoneNumber = phoneNumber;
		this.user = user;
		this.orderDetailList = orderDetailList;
	}

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetailList=new ArrayList<>();

    public Order(){

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", address=" + address + ", dateDelivery=" + dateDelivery + ", dateCreate="
				+ dateCreate + ", totalPriceOrder=" + totalPriceOrder + ", shipfee=" + shipfee + ", phoneNumber="
				+ phoneNumber + ", user=" + user + ", orderDetailList=" + orderDetailList + "]";
	}
    


}
