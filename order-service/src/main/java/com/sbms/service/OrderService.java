package com.sbms.service;

import com.sbms.entity.Order;
import com.sbms.pojo.Response;

import java.util.List;

public interface OrderService {
    Order placeOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void deleteOrderById(Long id);

    Response getOrderByUserId(Long id);
}
