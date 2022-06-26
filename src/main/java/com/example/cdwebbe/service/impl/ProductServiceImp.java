//package com.example.cdwebbe.service.impl;
//
//import com.example.cdwebbe.DTO.ProductDTO;
//import com.example.cdwebbe.model.Product;
//import com.example.cdwebbe.repository.ProductRepository;
//import com.example.cdwebbe.service.ProductService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//@Service
//public class ProductServiceImp implements ProductService {
//    @Autowired
//    ProductRepository productRepository;
//    @Autowired
//    ModelMapper modelMapper;
//
//    @Override
//    public List<ProductDTO> findAll(Pageable pageable) {
//        List<ProductDTO> productDTOList=new ArrayList<>();
//        List<Product> productList=productRepository.findAll(pageable).getContent();
//        for (Product product: productList){
//            productDTOList.add(modelMapper.map(product,ProductDTO.class));
//        }
//        return productDTOList;
//    }
//
//    @Override
//    public List<ProductDTO> findByName(String name, Pageable pageable) {
//        List<ProductDTO> productDTOList=new ArrayList<>();
//        List<Product> productList=productRepository.findByNameContainingIgnoreCase(name, pageable).getContent();
//        for(Product product: productList){
//            productDTOList.add(modelMapper.map(product,ProductDTO.class));
//        }
//        return productDTOList;
//    }
//
//
//    @Override
//    public List<ProductDTO> findByCategory(String keyword, Pageable pageable) {
//        List<ProductDTO> productDTOList=new ArrayList<>();
//        List<Product> productList=productRepository.findAllByCategoryKeywork(keyword, pageable).getContent();
//        for (Product product: productList){
//            productDTOList.add(modelMapper.map(product,ProductDTO.class));
//        }
//        return productDTOList;
//    }
//
//    @Override
//    public List<ProductDTO> findByPrice(double priceStart, double priceEnd, Pageable pageable) {
//        List<ProductDTO> productDTOList=new ArrayList<>();
//        List<Product> productList=productRepository.findAllByPriceBetween(priceStart, priceEnd, pageable).getContent();
//        for (Product product: productList){
//            productDTOList.add(modelMapper.map(product,ProductDTO.class));
//        }
//        return productDTOList;
//    }
//
//    @Override
//    public int countByName(String name) {
//        return (int)productRepository.countByNameContainingIgnoreCase(name);
//    }
//
//
//}
