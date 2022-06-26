package com.example.cdwebbe.repository;

import com.example.cdwebbe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    /**
     *
     * @param pageable
     * @return
     */
    Page<Product> findAll(Pageable pageable);

    /**
     *
     * @param name
     * @param pageable
     * @return
     */
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     *
     * @param id
     * @return
     */
    Product findOneById(Long id);


    /**
     *
     * @param keywork
     * @param pageable
     * @return
     */
    public Page<Product> findAllByCategoryKeywork(String keywork, Pageable pageable);

    /**
     *
     * @param priceStart
     * @param priceEnd
     * @param pageable
     * @return
     */
    public Page<Product> findAllByPriceBetween(double priceStart, double priceEnd, Pageable pageable);

    /**
     *
     * @param name
     * @return
     */
    public long countByNameContainingIgnoreCase(String name);

    /**
     *
     * @param keyword
     * @return
     */
    public int countByCategoryKeywork(String keyword);

    /**
     *
     * @param priceStart
     * @param priceEnd
     * @return
     */
    public int countByPriceBetween(double priceStart, double priceEnd);



}
