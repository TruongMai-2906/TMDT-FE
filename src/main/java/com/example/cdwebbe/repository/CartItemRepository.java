package com.example.cdwebbe.repository;

import com.example.cdwebbe.model.Cart;
import com.example.cdwebbe.model.CartItem;
import com.example.cdwebbe.model.Product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query(value = "Select e from cart e where e.cartid=?1",nativeQuery = true)
    List<CartItem> getListItemByCartId(Long cartId);
    List<CartItem> findAllByCartId(Long cartId);
    CartItem findOneByProductId(Long id);
    Boolean existsCartItemByProductId(Long id);

    Boolean existsCartItemByProductIdAndCartId(Long idProduct,Long idCart );

    CartItem findOneCartItemByProductIdAndCartId(Long idProduct,Long idCart);


    void  deleteCartItemByProductIdAndCartId(Long productid,Long cartid);
    @Transactional
    void deleteAllByCart(Cart cart);

    CartItem findByCartAndProduct(Cart cartEntity, Product productEntity);
}
