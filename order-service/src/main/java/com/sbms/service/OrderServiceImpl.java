package com.sbms.service;

import com.sbms.entity.Order;
import com.sbms.pojo.Response;
import com.sbms.pojo.User;
import com.sbms.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private RestTemplate restTemplate;
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
    public Response getOrderByUserId(Long id) {
        User user = restTemplate.getForEntity("http://localhost:8081/api/users/"+id , User.class).getBody();
        System.out.println("User : "+user);

        List<Order> orders = orderRepository.findAllByUserId(id);
        System.out.println("Order : "+orders);

        Response response = new Response();
        response.setUser(user);
        response.setOrders(orders);
        return response;
    }
}
