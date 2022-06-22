package com.example.cdwebbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column
    private Date lastModified;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "id")
    private User user;   // id vs user là khóa chính , 1 user có nhiều đơn hàng

    @JsonIgnore
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartItem> cartItemList;

    public Cart(Long id, Date lastModified, User user, List<CartItem> cartItemList) {
        this.id = id;
        this.lastModified = lastModified;
        this.user = user;
        this.cartItemList = cartItemList;
    }
    public Cart(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", lastModified=" + lastModified +
                ", user=" + user +
                ", cartItemEntity=" + cartItemList +
                '}';
    }
}
