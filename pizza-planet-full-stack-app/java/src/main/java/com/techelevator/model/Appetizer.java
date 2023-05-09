package com.techelevator.model;

import java.math.BigDecimal;

public class Appetizer extends NonPizzaItem {
    public Appetizer(Integer itemId, String itemName, String itemType, BigDecimal itemPrice,
                     String itemAvailability, String imagePath) {
        super(itemId, itemName, itemType, itemPrice, itemAvailability, imagePath);
    }

    public Appetizer() {

    }
}
