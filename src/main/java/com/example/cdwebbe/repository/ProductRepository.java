package com.example.cdwebbe.repository;

import com.example.cdwebbe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findAll(Pageable pageable);


    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    public long countByNameContainingIgnoreCase(String name);


    Product findOneById(Long id);

    public Page<Product> findAllByCategoryKeywork(String keywork, Pageable pageable);

    public int countByCategoryKeywork(String keyword);


    public Page<Product> findAllByCategoryKeyworkIn(String[]keywork, Pageable pageable);

    public int countByCategoryKeyworkIn(String[] keyword);


    public Page<Product> findAllByNameContainingIgnoreCaseAndCategoryKeyworkIn(String name, String[]keywork, Pageable pageable);

    public int countByNameContainingIgnoreCaseAndCategoryKeyworkIn(String name, String[]keywork);


    public Page<Product> findAllByPriceBetween(double priceStart, double priceEnd, Pageable pageable);

    public int countByPriceBetween(double priceStart, double priceEnd);


    public Page<Product> findAllByNameContainingIgnoreCaseAndCategoryKeyworkInAndPriceBetween(String name, String[]keywork, double priceStart, double priceEnd, Pageable pageable);

    public int countByNameContainingIgnoreCaseAndCategoryKeyworkInAndPriceBetween(String name, String[]keywork, double priceStart, double priceEnd);


    public Page<Product> findAllByCategoryKeyworkInAndPriceBetween(String[]keywork, double priceStart, double priceEnd, Pageable pageable);

    public int countByCategoryKeyworkInAndPriceBetween(String[]keywork, double priceStart, double priceEnd);


    public Page<Product> findAllByNameContainingIgnoreCaseAndPriceBetween(String name, double priceStart, double priceEnd, Pageable pageable);

    public int countByNameContainingIgnoreCaseAndPriceBetween(String name, double priceStart, double priceEnd);

    /**
     *
     *      + name + type ?
     *          + name + type + category_keyword ?
     *              + name + type + category_keyword + price ?
     *  + type ?
     *      + type + category_keyword ?
     *          + type + category_keyword + price ?
     *      + type + price ?
     * @param name
     * @param categoryKeyword
     * @param price_start
     * @param price_end
     * @param pageable
     * @return
     */

    //name + type + category_keyword + price
    /**
     * find product by name + type + category_keyword + price
     * findAllBy:
     * @param name: NameContainingIgnoreCase
     * @param type: CategoryTypeIn
     * @param keywork : CategoryKeyworkIn
     * @param priceStart: PriceBetween
     * @param priceEnd: PriceBetween
     * @param pageable
     * @return
     */
    public Page<Product> findAllByNameContainingIgnoreCaseAndCategoryTypeInAndCategoryKeyworkInAndPriceBetween( String name, String[]type, String[]keywork, double priceStart, double priceEnd, Pageable pageable );
    public int countByNameContainingIgnoreCaseAndCategoryTypeInAndCategoryKeyworkInAndPriceBetween( String name, String[]type, String[]keywork, double priceStart, double priceEnd);

    //name + type + category_keyword
    /**
     * find product by name + type + category_keyword
     * findAllBy
     * @param name: NameContainingIgnoreCase
     * @param type: CategoryTypeIn
     * @param keywork : CategoryKeyworkIn
     * @param pageable
     * @return
     */
    public Page<Product> findAllByNameContainingIgnoreCaseAndCategoryTypeInAndCategoryKeyworkIn( String name, String[]type, String[]keywork, Pageable pageable );
    public int countByNameContainingIgnoreCaseAndCategoryTypeInAndCategoryKeyworkIn( String name, String[]type, String[]keywork);

    //name + type
    /**
     * find product by name + type
     * findAllBy
     * @param name: NameContainingIgnoreCase
     * @param type: CategoryTypeIn
     * @param pageable
     * @return
     */
    public Page<Product> findAllByNameContainingIgnoreCaseAndCategoryTypeIn( String name, String[]type, Pageable pageable );
    public int countByNameContainingIgnoreCaseAndCategoryTypeIn( String name, String[]type);

    //type + category_keyword + price
    /**
     * find product by type + category_keyword + price
     * findAllBy
     * @param type: CategoryTypeIn
     * @param keywork : CategoryKeyworkIn
     * @param priceStart: PriceBetween
     * @param priceEnd: PriceBetween
     * @param pageable
     * @return
     */
    public Page<Product> findAllByCategoryTypeInAndCategoryKeyworkInAndPriceBetween(String[]type, String[]keywork, double priceStart, double priceEnd, Pageable pageable );
    public int countByCategoryTypeInAndCategoryKeyworkInAndPriceBetween(String[]type, String[]keywork, double priceStart, double priceEnd);

    //type + category_keyword
    /**
     * find product by type + category_keyword
     * findAllBy
     * @param type: CategoryTypeIn
     * @param keywork : CategoryKeyworkIn
     * @param pageable
     * @return
     */
    public Page<Product> findAllByCategoryTypeInAndCategoryKeyworkIn(String[]type, String[]keywork, Pageable pageable );
    public int countByCategoryTypeInAndCategoryKeyworkIn(String[]type, String[]keywork);

    //type + price
    /**
     * find product by type + price
     * findAllBy
     * @param type: CategoryTypeIn
     * @param priceStart: PriceBetween
     * @param priceEnd: PriceBetween
     * @param pageable
     * @return
     */
    public Page<Product> findAllByCategoryTypeInAndPriceBetween(String[]type, double priceStart, double priceEnd, Pageable pageable );
    public int countByCategoryTypeInAndPriceBetween(String[]type, double priceStart, double priceEnd);

    //type
    /**
     * find product by type
     * findAllBy
     * @param type: CategoryTypeIn
     * @param pageable
     * @return
     */
    public Page<Product> findAllByCategoryTypeIn(String[]type, Pageable pageable );
    public int countByCategoryTypeIn(String[]type);

    @Transactional
	void deleteOneById(Long id);

}
