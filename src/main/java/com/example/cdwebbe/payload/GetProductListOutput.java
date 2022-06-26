package com.example.cdwebbe.payload;

import com.example.cdwebbe.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class GetProductListOutput {


    private int sizeTotal;
    private List<ProductDTO> productDTOList = new ArrayList<>() {};

    public GetProductListOutput(int sizeTotal, List<ProductDTO> productDTOList) {
        this.sizeTotal = sizeTotal;
        this.productDTOList = productDTOList;
    }

    public GetProductListOutput() {
    }


    public int getSizeTotal() {
        return sizeTotal;
    }

    public void setSizeTotal(int sizeTotal) {
        this.sizeTotal = sizeTotal;

    }

    public List<ProductDTO> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }
}
