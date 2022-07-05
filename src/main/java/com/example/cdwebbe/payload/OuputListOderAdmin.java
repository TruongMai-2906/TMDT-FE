package com.example.cdwebbe.payload;

public class OuputListOderAdmin {
    public int id ;
    public String address;
    public String date;
    public String phone_number;
    public double shipfee;
    public double total_price_order;
    public String userid;

    public OuputListOderAdmin() {
    }

    public OuputListOderAdmin(int id, String address, String date, String phone_number, double shipfee, double total_price_order, String userid) {
        this.id = id;
        this.address = address;
        this.date = date;
        this.phone_number = phone_number;
        this.shipfee = shipfee;
        this.total_price_order = total_price_order;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public double getShipfee() {
        return shipfee;
    }

    public void setShipfee(double shipfee) {
        this.shipfee = shipfee;
    }

    public double getTotal_price_order() {
        return total_price_order;
    }

    public void setTotal_price_order(double total_price_order) {
        this.total_price_order = total_price_order;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
