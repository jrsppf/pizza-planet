package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Topping {
    private int toppingId;
    @NotNull
    @JsonProperty("name")
    private String toppingName;
    private boolean isPremium;
    @JsonProperty("price")
    private BigDecimal additionalPrice;
    private String availability;

    public Topping(int toppingId, String toppingName, boolean isPremium, BigDecimal price, String availability) {
        this.toppingId = toppingId;
        this.toppingName = toppingName;
        this.isPremium = isPremium;
        this.additionalPrice = price;
        this.availability = availability;
    }

    public Topping() {

    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public boolean getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(boolean premium) {
        isPremium = premium;
    }

    public BigDecimal getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(BigDecimal additionalPrice) {
        this.additionalPrice = additionalPrice;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topping topping = (Topping) o;
        return toppingId == topping.toppingId && isPremium == topping.isPremium && Objects.equals(toppingName, topping.toppingName) && Objects.equals(additionalPrice, topping.additionalPrice) && Objects.equals(availability, topping.availability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toppingId, toppingName, isPremium, additionalPrice, availability);
    }
}
