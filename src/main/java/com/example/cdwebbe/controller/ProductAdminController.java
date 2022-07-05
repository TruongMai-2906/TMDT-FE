package com.example.cdwebbe.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cdwebbe.DTO.ProductDTO;
import com.example.cdwebbe.model.Product;
import com.example.cdwebbe.repository.ProductRepository;
import com.example.cdwebbe.service.ProductService;

@RestController
@RequestMapping("admin/product")
public class ProductAdminController {
	 	@Autowired
	    ProductService productService;
	    @Autowired
	    ProductRepository productRepository;
	    @Autowired
	    ModelMapper modelMapper;
	    
	    @GetMapping("listProducts")
	    public ResponseEntity<?> getListProductAdmin(@RequestParam(defaultValue = "0") int pageIndex,
	    		@RequestParam(defaultValue = "12") int pageSize,@RequestParam(required = false) List<String> sortBy){
	    		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		    	if(sortBy!=null) {
		    		String sortTheo=sortBy.get(0);
		    		String sortDirection=sortBy.get(1);
		    		switch (sortTheo) {
					case "price":
							if(sortDirection.equals("asc")) {
								System.out.println("price");
								 pageable=PageRequest.of(pageIndex, pageSize,Sort.by("price").ascending());
							}else {
								 pageable=PageRequest.of(pageIndex, pageSize,Sort.by("price").descending());
							}
						break;
					case "name":
						if(sortDirection.equals("asc")) {
							System.out.println("NAME");
							 pageable=PageRequest.of(pageIndex, pageSize,Sort.by("name").ascending());
						}else {
							 pageable=PageRequest.of(pageIndex, pageSize,Sort.by("name").descending());
						}
					break;
					case "id":
						if(sortDirection.equals("asc")) {
							System.out.println("id");
							 pageable=PageRequest.of(pageIndex, pageSize,Sort.by("id").ascending());
						}else {
							 pageable=PageRequest.of(pageIndex, pageSize,Sort.by("id").descending());
						}
					break;
					case "price_sale":
						if(sortDirection.equals("asc")) {
							System.out.println("price_saleNe");
							 pageable=PageRequest.of(pageIndex, pageSize,Sort.by("price_sale").ascending());
						}else {
							 pageable=PageRequest.of(pageIndex, pageSize,Sort.by("price_sale").descending());
						}
					break;
						
					default:
						break;
					}
		    	}
	    		Map<String, Object> listProductAdmin=this.productService.listProductAdmin(pageable);
	    	return ResponseEntity.ok().body(listProductAdmin);
	    }
	    //delete
	    @PostMapping("deleteProduct/{id}")
	    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
	    	System.out.println(id);
	    	productRepository.deleteOneById(id);
			return ResponseEntity.ok().body("Delete Successfull");
	    	
	    }
//	    detail
	    @PostMapping("detailProduct/{id}")
	    public ResponseEntity<?> detailProduct(@PathVariable Long id){
	    	Product product=this.productRepository.findOneById(id);
	    	ProductDTO productDTO=modelMapper.map(product, ProductDTO.class);
	    	return ResponseEntity.ok().body(productDTO);
	    }
//	save Repository
	    @PostMapping("saveProduct")
	    public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productdto){
	    	Long id=productdto.getId();
//	    	Product product=this.productRepository.findOneById(id);
	    	Product product=modelMapper.map(productdto, Product.class);
	    	System.out.println(product);
	    	productRepository.save(product);
	    	
	    	
	    	return ResponseEntity.ok().body("save Successfull");
	    }

}
