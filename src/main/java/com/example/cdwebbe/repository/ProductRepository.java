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
     * @param keyword
     * @return
     */
    public int countByCategoryKeywork(String keyword);

    /**
     * find Products by list category.
     * @param keywork
     * @param pageable
     * @return
     */
    public Page<Product> findAllByCategoryKeyworkIn(String[]keywork, Pageable pageable);

    /**
     *
     * @param keyword
     * @return
     */
    public int countByCategoryKeyworkIn(String[] keyword);

    /**
     *
     * @param name
     * @param keywork
     * @param pageable
     * @return
     */
    public Page<Product> findAllByNameContainingIgnoreCaseAndCategoryKeyworkIn(String name, String[]keywork, Pageable pageable);

    /**
     *
     * @param name
     * @param keywork
     * @return
     */
    public int countByNameContainingIgnoreCaseAndCategoryKeyworkIn(String name, String[]keywork);

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
     * @param keywork
     * @param priceStart
     * @param priceEnd
     * @param pageable
     * @return
     */
    public Page<Product> findAllByNameContainingIgnoreCaseAndCategoryKeyworkInAndPriceBetween(String name, String[]keywork, double priceStart, double priceEnd, Pageable pageable);

    /**
     *
     * @param name
     * @param keywork
     * @param priceStart
     * @param priceEnd
     * @return
     */
    public int countByNameContainingIgnoreCaseAndCategoryKeyworkInAndPriceBetween(String name, String[]keywork, double priceStart, double priceEnd);

    /**
     *
     * @param keywork
     * @param priceStart
     * @param priceEnd
     * @param pageable
     * @return
     */
    public Page<Product> findAllByCategoryKeyworkInAndPriceBetween(String[]keywork, double priceStart, double priceEnd, Pageable pageable);

    /**
     *
     * @param keywork
     * @param priceStart
     * @param priceEnd
     * @return
     */
    public int countByCategoryKeyworkInAndPriceBetween(String[]keywork, double priceStart, double priceEnd);

    /**
     *
     * @param name
     * @param priceStart
     * @param priceEnd
     * @param pageable
     * @return
     */
    public Page<Product> findAllByNameContainingIgnoreCaseAndPriceBetween(String name, double priceStart, double priceEnd, Pageable pageable);

    /**
     *
     * @param name
     * @param priceStart
     * @param priceEnd
     * @return
     */
    public int countByNameContainingIgnoreCaseAndPriceBetween(String name, double priceStart, double priceEnd);

    /**
     *
     * @param priceStart
     * @param priceEnd
     * @return
     */
    public int countByPriceBetween(double priceStart, double priceEnd);

    /**
     *
     * @param name
     * @return
     */
    public long countByNameContainingIgnoreCase(String name);


}
