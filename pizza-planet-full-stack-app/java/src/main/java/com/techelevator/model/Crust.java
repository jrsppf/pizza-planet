package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Crust {
    private Integer crustId;
    @JsonProperty("name")
    private String crustName;
    @JsonProperty("price")
    private BigDecimal additionalPrice;
    private String availability;

    public Crust () {

    }

    public Crust(Integer crustId, String crustName, BigDecimal additionalPrice, String availability) {
        this.crustId = crustId;
        this.crustName = crustName;
        this.additionalPrice = additionalPrice;
        this.availability = availability;
    }

    public Integer getCrustId() {
        return crustId;
    }

    public void setCrustId(Integer crustId) {
        this.crustId = crustId;
    }

    public String getCrustName() {
        return crustName;
    }

    public void setCrustName(String crustName) {
        this.crustName = crustName;
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
