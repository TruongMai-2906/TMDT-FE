package com.example.cdwebbe.repository;


import com.example.cdwebbe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cdwebbe.model.Cart;
import com.example.cdwebbe.model.OrderDetail;


import java.util.List;

public interface OrderDetailRepository  extends JpaRepository<OrderDetail,Long>{
    List<OrderDetail> findAllByOrderId(Long orderid);

    List<OrderDetail> findAllByProductCategory(Category category);

}
