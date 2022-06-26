package com.example.cdwebbe.payload;

import com.example.cdwebbe.model.OrderDetail;
import com.example.cdwebbe.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseOrderUser {
    private Long orderId;
    private Date timemua;
    List<OrderDetail> listoderDetail = new ArrayList<>();

    public ResponseOrderUser(Long orderId, Date timemua, List<OrderDetail> listoderDetail) {
        this.orderId = orderId;
        this.timemua = timemua;
        this.listoderDetail = listoderDetail;
    }

    public ResponseOrderUser(Date timemua, List<OrderDetail> listoderDetail) {
        this.timemua = timemua;
        this.listoderDetail = listoderDetail;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getTimemua() {
        return timemua;
    }

    public void setTimemua(Date timemua) {
        this.timemua = timemua;
    }

    public List<OrderDetail> getListoderDetail() {
        return listoderDetail;
    }

    public void setListoderDetail(List<OrderDetail> listoderDetail) {
        this.listoderDetail = listoderDetail;
    }
}
