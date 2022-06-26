package com.example.cdwebbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Đã Chuyển id -> Long


    private int quantity;

//    @NotBlank
    private double totalPrice;


    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cartId",referencedColumnName = "id")
    private Cart cart;


    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id
                +
                ", productEntities=" + product +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
//                ", cartEntity=" + cartEntity +
                '}'
                ;
    }

    public CartItem(int quantity, double totalPrice, Product product, Cart cart) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.product = product;
        this.cart = cart;
    }

    public CartItem() {
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
