package com.example.cdwebbe.payload;

import com.example.cdwebbe.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class ListCartItemRespon {
    List<CartItem> cartItemList = new ArrayList<>();
    double totalCart;

    public ListCartItemRespon(List<CartItem> cartItemList, double totalCart) {
        this.cartItemList = cartItemList;
        this.totalCart = totalCart;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public double getTotalCart() {
        return totalCart;
    }
}
