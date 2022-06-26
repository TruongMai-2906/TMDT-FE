package com.example.cdwebbe.payload;

import com.example.cdwebbe.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class GetProductListOutput {

    private int sizTotal;
    private List<ProductDTO> productDTOList = new ArrayList<>() {};

    public GetProductListOutput(int sizTotal, List<ProductDTO> productDTOList) {
        this.sizTotal = sizTotal;
        this.productDTOList = productDTOList;
    }

    public GetProductListOutput() {
    }

    public int getSizTotal() {
        return sizTotal;
    }

    public void setSizTotal(int sizTotal) {
        this.sizTotal = sizTotal;
    }

    public List<ProductDTO> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }
}
