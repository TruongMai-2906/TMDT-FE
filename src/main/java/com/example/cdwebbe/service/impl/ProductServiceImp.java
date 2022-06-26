package com.example.cdwebbe.service.impl;

import com.example.cdwebbe.DTO.CategoryDTO;
import com.example.cdwebbe.DTO.ProductDTO;
import com.example.cdwebbe.model.Product;
import com.example.cdwebbe.payload.GetProductListOutput;
import com.example.cdwebbe.repository.ProductRepository;
import com.example.cdwebbe.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<Product> productList=productRepository.findAll(pageable).getContent();
        for (Product product: productList){
            productDTOList.add(modelMapper.map(product,ProductDTO.class));
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findByName(String name, Pageable pageable) {
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<Product> productList=productRepository.findByNameContainingIgnoreCase(name, pageable).getContent();
        for(Product product: productList){
            productDTOList.add(modelMapper.map(product,ProductDTO.class));
        }
        return productDTOList;
    }


    @Override
    public List<ProductDTO> findByCategory(String keyword, Pageable pageable) {
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<Product> productList=productRepository.findAllByCategoryKeywork(keyword, pageable).getContent();
        for (Product product: productList){
            productDTOList.add(modelMapper.map(product,ProductDTO.class));
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findByPrice(double priceStart, double priceEnd, Pageable pageable) {
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<Product> productList=productRepository.findAllByPriceBetween(priceStart, priceEnd, pageable).getContent();
        for (Product product: productList){
            productDTOList.add(modelMapper.map(product,ProductDTO.class));
        }
        return productDTOList;
    }

    @Override
    public int countByName(String name) {
        return (int)productRepository.countByNameContainingIgnoreCase(name);
    }

    /**
     * - name:
     *  + name
     *  + name + category
     *  + name + category + price
     *  + name + price
     *
     * - category:
     *  + category
     *  + category + price
     *
     * - price:
     *  + price
     *
     * @param name
     * @param category
     * @param price_start
     * @param price_end
     * @param pageable
     * @return
     */
    @Override
    public GetProductListOutput filter(String name, String[] category, double price_start, double price_end, Pageable pageable) {
        int count=0;
        List<ProductDTO> productDTOList=new ArrayList<>();
        if (name != null){ // name ?
            if ( !(category == null) ){ // name + category ?
               if (price_start != 0 || price_end != 100000000){ // name + category + price
                   productDTOList = findByNameAndCategoryAndPrice(name, category, price_start, price_end, pageable);
                   count=productRepository.countByNameContainingIgnoreCaseAndCategoryKeyworkInAndPriceBetween(name, category, price_start, price_end);
               }else { // name + category
                   productDTOList = findByNameAndCategory(name, category, pageable);
                   count=productRepository.countByNameContainingIgnoreCaseAndCategoryKeyworkIn(name, category);
               }
            } else if (price_start != 0 || price_end != 100000000){ // name + price
                    productDTOList = findByNameAndPrice(name, price_start, price_end, pageable);
                    count=productRepository.countByNameContainingIgnoreCaseAndPriceBetween(name, price_start, price_end);
            }else { // name
                productDTOList = findByName(name, pageable);
                count = countByName(name);
            }
        } else if ( !(category == null) ){ //category ?
            if (price_start != 0 || price_end != 100000000){ // category + price
                productDTOList = findByCategoryAndPrice(category, price_start, price_end, pageable);
                count = productRepository.countByCategoryKeyworkInAndPriceBetween(category, price_start, price_end);
            }else { // category
                productDTOList = findByCategoryIn(category, pageable);
                count = productRepository.countByCategoryKeyworkIn(category);
            }
        } else if (price_start != 0 || price_end != 100000000){ // price ?
            productDTOList = findByPrice(price_start, price_end, pageable);
            count = productRepository.countByPriceBetween(price_start, price_end);
        } else {
            productDTOList = findAll(pageable);
            count = (int) productRepository.count();
        }

        int sizeTotal=(int) Math.ceil( (double)count/pageable.getPageSize() );

        GetProductListOutput productListOutput=new GetProductListOutput();
        productListOutput.setProductDTOList(productDTOList);
        productListOutput.setSizeTotal(sizeTotal);
        return productListOutput;
    }

    private List<ProductDTO> findByNameAndPrice(String name, double price_start, double price_end, Pageable pageable) {
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCaseAndPriceBetween(name, price_start, price_end, pageable).getContent();
        for (Product product: productList){
            productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }

    private List<ProductDTO> findByCategoryAndPrice(String[] category, double price_start, double price_end, Pageable pageable) {
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<Product> productList = productRepository.findAllByCategoryKeyworkInAndPriceBetween(category, price_start, price_end, pageable).getContent();
        for (Product product: productList){
            productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }

    private List<ProductDTO> findByNameAndCategoryAndPrice(String name, String[] category, double price_start, double price_end, Pageable pageable) {
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCaseAndCategoryKeyworkInAndPriceBetween(name, category, price_start, price_end, pageable).getContent();
        for (Product product: productList){
            productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }

    /**
     *
     * @param name
     * @param category
     * @param pageable
     * @return
     */
    private List<ProductDTO> findByNameAndCategory(String name, String[] category, Pageable pageable) {
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCaseAndCategoryKeyworkIn(name, category, pageable).getContent();
        for (Product product: productList){
            productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }

    /**
     *
     * @param category (one or many)
     * @param pageable
     * @return
     */
    @Override
    public List<ProductDTO> findByCategoryIn(String[] category, Pageable pageable) {
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<Product> productList = productRepository.findAllByCategoryKeyworkIn(category, pageable).getContent();
        for (Product product: productList){
            productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }


}
