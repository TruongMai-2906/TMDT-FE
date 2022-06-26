package com.example.cdwebbe.repository;

import com.example.cdwebbe.model.Cart;
import com.example.cdwebbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

//@Query(value = "Select id from cart where userid=?1",nativeQuery = true)
//    List<Long> findByUser(Long userid);

    List<Cart> findAllByUserId(Long userid);
    Cart findByUser(User user);
}
