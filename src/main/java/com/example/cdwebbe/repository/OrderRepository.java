package com.example.cdwebbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cdwebbe.model.Order;
import com.example.cdwebbe.model.User;

import javax.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long>{
	List<Order> findAllByUser(User user);
	List<Order> findByUser(User user);
	List<Order> findByUserId(Long id);

	Order findByUserIdAndId(Long userid,Long orderId);

	@Transactional
	void deleteByUserIdAndId(Long Userid,Long id);
}
