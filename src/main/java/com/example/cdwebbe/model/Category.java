package com.example.cdwebbe.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//Đã sửa int -> Long.

    @Nullable
    private String description;

    @Nullable
    private String type;

    @Nullable
    private String keywork;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private List<Product> productList=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(@Nullable String type) {
        this.type = type;
    }

    @Nullable
    public String getKeywork() {
        return keywork;
    }

    public void setKeywork(@Nullable String keywork) {
        this.keywork = keywork;
    }

    public List<Product> getProductList() {
        return productList;
    }

//    @Override
//    public String toString() {
//        return "Category{" +
//                "id=" + id +
//                ", description='" + description + '\'' +
//                ", type='" + type + '\'' +
//                ", keywork='" + keywork + '\'' +
//                ", productList=" + productList +
//                '}';
//    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
