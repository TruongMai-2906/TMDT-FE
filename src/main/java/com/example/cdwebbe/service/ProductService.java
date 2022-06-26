package com.example.cdwebbe.service;

import com.example.cdwebbe.DTO.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    /**
     *
     * @param pageable
     * @return
     */
    public List<ProductDTO> findAll(Pageable pageable);

    /**
     *
     * @param name
     * @param pageable
     * @return
     */
    public List<ProductDTO> findByName(String name, Pageable pageable);

    /**
     * Request: find products by property:Category.keyword; And products pagination;
     * @param keyword
     * @param pageable
     * @return
     */
    public List<ProductDTO> findByCategory(String keyword, Pageable pageable);

    /**
     * Request: 1.find products by property:price with: priceStart < price  < priceEnd ;
     *          2.And products pagination;
     * @param priceStart
     * @param priceEnd
     * @param pageable
     * @return
     */
    public List<ProductDTO> findByPrice(double priceStart, double priceEnd, Pageable pageable);

    /**
     *
     * @param name
     * @return
     */
    public int countByName(String name);
}
