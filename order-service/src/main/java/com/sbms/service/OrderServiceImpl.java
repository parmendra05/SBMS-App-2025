package com.sbms.service;

import com.sbms.entity.Order;
import com.sbms.pojo.Response;
import com.sbms.pojo.User;
import com.sbms.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.hibernate.collection.spi.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private WebClient webClient;
    private final String USER_SERVICE_URL ="http://localhost:8081/api/users/";

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Not Found"));
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    @CircuitBreaker(name = "USER-SERVICE" , fallbackMethod = "fallbackGetUserDetails")
    public Response getOrderByUserId(Long id) {

        User user = webClient.get().uri(USER_SERVICE_URL+id).retrieve().bodyToMono(User.class).block();

        System.out.println("User : "+user);

        List<Order> orders = orderRepository.findAllByUserId(id);
        System.out.println("Order : "+orders);

        Response response = new Response();
        response.setUser(user);
        response.setOrders(orders);
        return response;
    }

    public Response fallbackGetUserDetails(Long id, Exception e) {
        System.out.println("User Service is down! Returning default response...");

        User defaultUser = new User();
        defaultUser.setId(0L);
        defaultUser.setName("Default User");

        Response response = new Response();
        response.setOrders(new ArrayList<>());
        response.setUser(defaultUser);

        return response;
    }

}
