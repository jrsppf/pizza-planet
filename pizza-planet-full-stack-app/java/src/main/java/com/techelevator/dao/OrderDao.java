package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface OrderDao {

    List<Order> getAllOrders();

    Order getOrder(int orderId);

    List<Order> getOrdersByStatus(String status);

    void addOrder(Order newOrder);

    void insertPizzaItems(Integer orderId, List<Pizza> pizzas);

    void insertPizzaToppings(Integer pizzaId, List<Topping> nonPremiumToppings, List<Topping> premiumToppings);

    void insertNonPizzaItems(Integer orderId, List<Appetizer> appetizers, List<Salad> salads, List<Side> sides,
                             List<Drink> drinks);

    void updateOrderStatus(int orderId, Order order);

    void updateOrder(int orderId, Order order);

    void deleteOrder(int orderId);
}
