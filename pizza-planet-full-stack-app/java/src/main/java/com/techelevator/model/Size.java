package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Size {
    private Integer sizeId;
    @JsonProperty("name")
    private String sizeName;
    @JsonProperty("price")
    private BigDecimal additionalPrice;

    public Size() {

    }

    public Size(Integer sizeId, String sizeName, BigDecimal additionalPrice) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.additionalPrice = additionalPrice;
    }

    public Integer getSizeId() {return sizeId;}

    public void setSizeId(Integer sizeId) {this.sizeId = sizeId;}

    public String getSizeName() {return sizeName;}

    public void setSizeName(String sizeName) {this.sizeName = sizeName;}

    public BigDecimal getAdditionalPrice() {return additionalPrice;}

    public void setAdditionalPrice(BigDecimal sizePrice) {this.additionalPrice = sizePrice;}
}
