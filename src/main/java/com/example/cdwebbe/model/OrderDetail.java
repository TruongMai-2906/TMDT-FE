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

    @NotBlank
    private int quantity;

    @NotBlank
    private double totalPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="orderId",referencedColumnName = "id")
    private Order order;
}
