package com.techelevator.model;

import java.math.BigDecimal;

public class Drink extends NonPizzaItem {

    public Drink() {

    }
    public Drink(Integer itemId, String itemName, String itemType, BigDecimal itemPrice, String itemAvailability, String imagePath) {
        super(itemId, itemName, itemType, itemPrice, itemAvailability, imagePath);
    }
}
