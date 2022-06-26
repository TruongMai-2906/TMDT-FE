package com.example.cdwebbe.DTO;

import com.example.cdwebbe.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CatagoryDTO {
    private Long id;//Đã sửa int -> Long.
    private String description;
    private String type;
    private String keywork;
    private List<Product> productList=new ArrayList<>();




}
