package com.techelevator.service;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.util.List;

@Service
public class OrderService {
    private final SpecialtyPizzaDao specialtyPizzaDao;
    private final NonPizzaItemDao nonPizzaItemDao;
    private final OrderDao orderDao;

    private RestTemplate restTemplate = new RestTemplate();

    public OrderService(SpecialtyPizzaDao specialtyPizzaDao, NonPizzaItemDao nonPizzaItemDao, OrderDao orderDao) {
        this.specialtyPizzaDao = specialtyPizzaDao;
        this.nonPizzaItemDao = nonPizzaItemDao;
        this.orderDao = orderDao;
    }

    @PreAuthorize("isAuthenticated()")
    public List<Order> getOrders(String status, String email, String phone) {
        if (status == null) {
            return orderDao.getAllOrders();
        } else {
            return orderDao.getOrdersByStatus(status.toUpperCase());
        }
    }

    //TODO
    @PreAuthorize("isAuthenticated()")
    public Order getOrder(int id) {
        Order order = orderDao.getOrder(id);
        return order;
    }

    //TODO
    @PreAuthorize("isAuthenticated()")
    public String getOrderStatus(int id) {
        Order order = getOrder(id);
        String orderStatus = order.getStatus();
        return orderStatus;
    }

    public Order addOrder(Order newOrder) {
        orderDao.addOrder(newOrder);
        return newOrder;
    }

    //TODO
    @PreAuthorize("isAuthenticated()")
    public void updateOrder(int orderId, Order order) {
        orderDao.updateOrder(orderId, order);
    }

    //TODO
    @PreAuthorize("isAuthenticated()")
    public void updateOrderStatus(int orderId, Order order) {
        orderDao.updateOrderStatus(orderId, order);
    }

    //TODO
    @PreAuthorize("isAuthenticated()")
    public void deleteOrder(int orderId) {
        orderDao.deleteOrder(orderId);
    }
}
