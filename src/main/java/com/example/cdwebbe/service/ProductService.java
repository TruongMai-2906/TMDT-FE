package com.example.cdwebbe.service;

import com.example.cdwebbe.DTO.ProductDTO;
import com.example.cdwebbe.payload.GetProductListOutput;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

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

    /**
     *
     * @param name
     * @param categoryKeyword
     * @param price_start
     * @param price_end
     * @param pageable
     * @return
     */
    public GetProductListOutput filter(String name, String[]categoryKeyword, String[]categoryType, double price_start, double price_end, Pageable pageable);

    /**
     * Request: filter product by one or many category.
     * @param category (one or many)
     * @param pageable
     * @return
     */
    public List<ProductDTO> findByCategoryIn(String[]category, Pageable pageable);

	public Map<String, Object> listProductAdmin(Pageable pageable);


}
