package com.example.cdwebbe.payload;

public class CartRequest {
    private Long productid;
    private int quantity;

    public CartRequest(Long productid) {
        this.productid = productid;
    }

    public CartRequest(Long productid, int quantity) {
        this.productid = productid;
        this.quantity = quantity;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    @Override
    public String toString() {
        return "CartRequest{" +
                "productid=" + productid +
                ", quantity=" + quantity +
                '}';
    }
}
