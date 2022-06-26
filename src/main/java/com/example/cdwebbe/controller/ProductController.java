package com.example.cdwebbe.controller;


import com.example.cdwebbe.DTO.ProductDTO;
import com.example.cdwebbe.config.ModelMapperConfig;
import com.example.cdwebbe.model.Product;
import com.example.cdwebbe.payload.ApiResponse;
import com.example.cdwebbe.payload.GetProductListOutput;
import com.example.cdwebbe.repository.ProductRepository;
import com.example.cdwebbe.security.CurrentUser;
import com.example.cdwebbe.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/detail/{id}")
    public ProductDTO getProductbyId(@PathVariable("id") Long id){
        try {
            System.err.println("ID Product là"+id);
            Product product= productRepository.findById(id).get();
            ProductDTO productDto= modelMapper.map(product, ProductDTO.class);
            System.err.println("Product là"+product);
            if (productDto==null){
                return null;
            }else{
                return productDto;
            }
        }catch (Exception ex){
            return null;

        }

    }
    @GetMapping("/getListProduct")
    public ResponseEntity<?> getListProductNek(@RequestParam("type") String type,@RequestParam(defaultValue ="1") int pageIndex){
        try {
            System.err.println("type"+type);
            System.err.println("page index"+pageIndex);

            Sort sort = Sort.by("id").ascending();
            Pageable pageable = PageRequest.of(pageIndex,12,sort);
            List<Product> product = productRepository.findAll(pageable).getContent();
            List<Product> listproduct = productRepository.findAll();
            ProductDTO productDTO = new ProductDTO();
            GetProductListOutput getProductListOutput = new GetProductListOutput();
            for (Product productEntity: product) {
                productDTO=modelMapper.map(productEntity,ProductDTO.class);
                getProductListOutput.getProductDTOList().add(productDTO);

            }
            getProductListOutput.setSizTotal(listproduct.size()/12);
            return ResponseEntity.ok().body(getProductListOutput);

        }catch (Exception e){
            return ResponseEntity.ok().body(new ApiResponse(false,"Có lỗi đã xảy ra thử lại"+e));
        }

    }

    @GetMapping("/search")
    public ResponseEntity<?> getlistkeywork (@RequestParam(value = "keywork",defaultValue = "áo") String keywork,@RequestParam(defaultValue = "0") int pageIndex, @CurrentUser UserPrincipal currentUser){
        try{
            Pageable pageable = PageRequest.of(pageIndex,9);
            List<Product> product = productRepository.findByNameContainingIgnoreCase(keywork,pageable).getContent();
            return ResponseEntity.ok().body(product);
        }catch (Exception e){
            return ResponseEntity.ok().body(new ApiResponse(false,"Có lỗi đã xảy ra thử lại"+e));
        }
    }
}
