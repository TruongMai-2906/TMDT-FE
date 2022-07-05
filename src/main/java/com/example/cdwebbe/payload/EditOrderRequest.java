package com.example.cdwebbe.payload;

import org.springframework.lang.Nullable;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class EditOrderRequest {

    private Long id;
    private Date dateDelivery;   // time  bắt đầu vận chuyển

    @Nullable
    @Temporal(TemporalType.DATE)
    private Date dateCreate ; // ngày order

    private String address; // địa
    private double  totalPriceOrder;

    private  double shipfee;

    private String phoneNumber;

    public EditOrderRequest() {
    }

    public EditOrderRequest(Long id, Date dateDelivery, @Nullable Date dateCreate, String address, double totalPriceOrder, double shipfee, String phoneNumber) {
        this.id = id;
        this.dateDelivery = dateDelivery;
        this.dateCreate = dateCreate;
        this.address = address;
        this.totalPriceOrder = totalPriceOrder;
        this.shipfee = shipfee;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    @Nullable
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(@Nullable Date dateCreate) {
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
}
