package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.tomcat.util.descriptor.web.ApplicationParameter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Order {

    private Integer orderId;
    private String status;
    private BigDecimal total;
    private Address address;
    private String email;
    private CardInfo cardInfo;
    private String phone;
    private List<Pizza> pizzas;
    private List<Appetizer> appetizers;
    private List<Salad> salads;
    private List<Side> sides;
    private List<Drink> drinks;

    public Order() {

    }

    public Order(Integer orderId, String status, BigDecimal total,
                 Address address, String email, CardInfo cardInfo,
                 String phone, List<Pizza> pizzas, List<Appetizer> appetizers,
                 List<Salad> salads, List<Side> sides, List<Drink> drinks) {

        this.orderId = orderId;
        this.status = status;
        this.total = total;
        this.address = address;
        this.email = email;
        this.cardInfo = cardInfo;
        this.phone = phone;
        this.pizzas = pizzas;
        this.appetizers = appetizers;
        this.salads = salads;
        this.sides = sides;
        this.drinks = drinks;
    }

    public Integer getOrderId() {return orderId;}

    public void setOrderId(Integer orderId) {this.orderId = orderId;}

    public String getStatus() {return status;}

    public void setStatusId(String status) {this.status = status;}

    public BigDecimal getTotal() {return total;}

    public void setTotal(BigDecimal total) {this.total = total;}

    public Address getAddress() {return address;}

    public void setAddress(Address address) {this.address = address;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public CardInfo getCardInfo() {return cardInfo;}

    public void setCardInfo(CardInfo cardInfo) {this.cardInfo = cardInfo;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public List<Pizza> getPizzas() {return pizzas;}

    public void setPizzas(List<Pizza> pizzas) {this.pizzas = pizzas;}

    public List<Appetizer> getAppetizers() {
        return appetizers;
    }

    public void setAppetizers(List<Appetizer> appetizers) {
        this.appetizers = appetizers;
    }

    public List<Salad> getSalads() {
        return salads;
    }

    public void setSalads(List<Salad> salads) {
        this.salads = salads;
    }

    public List<Side> getSides() {
        return sides;
    }

    public void setSides(List<Side> sides) {
        this.sides = sides;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(status, order.status) && Objects.equals(total, order.total) && Objects.equals(address, order.address) && Objects.equals(email, order.email) && Objects.equals(cardInfo, order.cardInfo) && Objects.equals(phone, order.phone) && Objects.equals(pizzas, order.pizzas) && Objects.equals(appetizers, order.appetizers) && Objects.equals(salads, order.salads) && Objects.equals(sides, order.sides) && Objects.equals(drinks, order.drinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, status, total, address, email, cardInfo, phone, pizzas, appetizers, salads, sides, drinks);
    }
}
