package com.abc.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
