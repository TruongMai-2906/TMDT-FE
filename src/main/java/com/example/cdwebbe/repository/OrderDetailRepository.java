package com.example.cdwebbe.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cdwebbe.model.Cart;
import com.example.cdwebbe.model.OrderDetail;


import java.util.List;

public interface OrderDetailRepository  extends JpaRepository<OrderDetail,Long>{
    List<OrderDetail> findAllByOrderId(Long orderid);

}
