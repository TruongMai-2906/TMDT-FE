package com.example.cdwebbe.controller;


import com.example.cdwebbe.DTO.ProductDTO;


import com.example.cdwebbe.model.Product;
import com.example.cdwebbe.payload.ApiResponse;
import com.example.cdwebbe.payload.GetProductListOutput;
import com.example.cdwebbe.repository.ProductRepository;
import com.example.cdwebbe.security.CurrentUser;
import com.example.cdwebbe.security.UserPrincipal;

import com.example.cdwebbe.service.ProductService;
import com.example.cdwebbe.service.impl.ProductServiceImp;
import org.hibernate.boot.model.source.spi.Sortable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired

    ProductService productService;
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


    /**
     * - Hiển thị tất cả các sản phẩm.
     * - Tìm sản phẩm theo tên.
     * - Lọc sản phẩm theo:
     *      + category,
     *      + theo mức giá;
     * - Xắp xếp tất cả sản phẩm với thứ tự: tăng dần (ASC), giảm dần (DESC);
     * - Có thể Xắp xếp theo: id, name, price, price sale, score;
     *
     * Làm thêm:
     * - Lọc theo name
     *  + name +
     * @param name
     * @param categoryKeyword
     * @param page
     * @param limit
     * @param sortName
     * @param sortBy
     * @param price_start
     * @param price_end
     * @return
     */
    @GetMapping("products")
    public ResponseEntity<?> getProductList(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "category", required = false) String [] categoryKeyword,
            @RequestParam(name = "type", required = false) String [] categoryType,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = "12") Integer limit,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sortName,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") String sortBy,
            @RequestParam(name = "price_start", required = false, defaultValue = "0") Double price_start,
            @RequestParam(name = "price_end", required = false, defaultValue = "100000000") Double price_end
    ){
        Sort sort=null;
        if(sortBy.equalsIgnoreCase("ASC")){
            sort=Sort.by(sortName).ascending();
        }
        if(sortBy.equalsIgnoreCase("DESC")){
            sort=Sort.by(sortName).descending();
        }

        Pageable pageable=PageRequest.of(page-1, limit, sort);

        GetProductListOutput productListOutput= productService.filter(name, categoryKeyword, categoryType, price_start,price_end, pageable);
        return ResponseEntity.ok().body(productListOutput);
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

            getProductListOutput.setSizeTotal(listproduct.size()/12);
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
