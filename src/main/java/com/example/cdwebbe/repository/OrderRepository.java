package com.example.cdwebbe.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cdwebbe.model.Order;
import com.example.cdwebbe.model.User;

public interface OrderRepository extends JpaRepository<Order, Long>{
	List<Order> findAllByUser(User user);
	List<Order> findByUser(User user);
	List<Order> findByUserId(Long id);

	@Query("select a from Order a where a.dateCreate between ?1 and ?2")
	List<Order> findAllByDateCreateBetween(Date dateStart, Date dateEnd);
}
