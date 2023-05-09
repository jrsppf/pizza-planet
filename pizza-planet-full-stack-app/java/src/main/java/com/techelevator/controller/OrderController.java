package com.techelevator.controller;

import com.techelevator.model.*;
import com.techelevator.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin()
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Order> getOrders(@RequestParam(required = false) String status,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false) String phoneNumber) {
        return orderService.getOrders(status, email, phoneNumber);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable int orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("/{orderId}/status")
    public String getOrderStatus(@PathVariable int orderId) {
        return orderService.getOrderStatus(orderId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Order addOrder(@RequestBody Order newOrder) {
        return orderService.addOrder(newOrder);
    }

    @PutMapping("/{orderId}")
    public void updateOrder(@PathVariable int orderId, @RequestBody Order updatedOrder) {
        orderService.updateOrder(orderId, updatedOrder);
    }

    @PutMapping("/{orderId}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable int orderId, @RequestBody Order order) {
        orderService.updateOrderStatus(orderId, order);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }
}
