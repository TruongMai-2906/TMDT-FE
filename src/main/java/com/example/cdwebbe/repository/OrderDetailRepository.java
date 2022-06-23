package com.example.cdwebbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cdwebbe.model.Cart;
import com.example.cdwebbe.model.OrderDetail;

public interface OrderDetailRepository  extends JpaRepository<OrderDetail,Long>{

}
