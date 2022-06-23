package com.example.cdwebbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cdwebbe.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
