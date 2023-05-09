package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Sauce {
    private Integer sauceId;
    @JsonProperty("name")
    private String sauceName;
    @JsonProperty("price")
    private BigDecimal additionalPrice;
    private String availability;

    public Sauce() {

    }

    public Sauce(Integer sauceId, String sauceName, BigDecimal additionalPrice, String availability) {
        this.sauceId = sauceId;
        this.sauceName = sauceName;
        this.additionalPrice = additionalPrice;
        this.availability = availability;
    }

    public Integer getSauceId() {
        return sauceId;
    }

    public void setSauceId(Integer sauceId) {
        this.sauceId = sauceId;
    }

    public String getSauceName() {
        return sauceName;
    }

    public void setSauceName(String sauceName) {
        this.sauceName = sauceName;
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
}
