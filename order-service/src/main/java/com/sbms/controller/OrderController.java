package com.sbms.controller;

import com.sbms.entity.Order;
import com.sbms.pojo.Response;
import com.sbms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return "Order deleted successfully";
    }
    @GetMapping("/user/{id}")
    public Response getOrdersByUserId(@PathVariable Long id) {
        return orderService.getOrderByUserId(id);
    }
}
