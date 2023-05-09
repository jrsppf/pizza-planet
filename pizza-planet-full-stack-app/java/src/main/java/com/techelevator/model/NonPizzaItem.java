package com.techelevator.model;

import java.math.BigDecimal;

public class NonPizzaItem {
    private Integer itemId;
    private String itemName;
    private String itemType;
    private BigDecimal itemPrice;
    private String itemAvailability;
    private String imagePath;

    public NonPizzaItem() {

    }

    public NonPizzaItem(Integer itemId, String itemName, String itemType,
                        BigDecimal itemPrice, String itemAvailability, String imagePath) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
        this.itemAvailability = itemAvailability;
        this.imagePath = imagePath;
    }

    public Integer getItemId() {return itemId;}

    public String getItemName() {return itemName;}

    public  BigDecimal getItemPrice() {return itemPrice;}

    public String getItemAvailability() {return itemAvailability;}

    public String getImagePath() {return imagePath;}

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemAvailability(String itemAvailability) {
        this.itemAvailability = itemAvailability;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
