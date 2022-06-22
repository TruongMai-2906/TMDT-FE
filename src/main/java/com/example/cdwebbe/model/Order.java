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

    @NotBlank
    private String address; // địa chỉ nhận hàng

    @Nullable
    @Temporal(TemporalType.DATE)
    private Date dateDelivery;   // time  bắt đầu vận chuyển

    @Nullable
    @Temporal(TemporalType.DATE)
    private Date dateCreate ; // ngày order

    @NotBlank
    private double  totalPriceOrder;

    @NotBlank
    private  double shipfee;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetailList=new ArrayList<>();

    public Order(){

    }


}
