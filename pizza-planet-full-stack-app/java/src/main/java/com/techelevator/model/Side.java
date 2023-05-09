package com.techelevator.model;

import java.math.BigDecimal;

public class Side extends NonPizzaItem {
    public Side(Integer itemId, String itemName, String itemType, BigDecimal itemPrice, String itemAvailability, String imagePath) {
        super(itemId, itemName, itemType, itemPrice, itemAvailability, imagePath);
    }

    public Side() {

    }
}
