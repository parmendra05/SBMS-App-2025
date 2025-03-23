package com.sbms.repository;

import com.sbms.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long > {
    List<Order> findAllByUserId(Long id);
}
