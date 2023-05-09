package com.techelevator.dao;

import com.techelevator.model.Crust;
import com.techelevator.model.Sauce;
import com.techelevator.model.Size;
import com.techelevator.model.Topping;

import java.util.List;

public interface PizzaOptionDao {
    List<Topping> getAllToppings();

    List<Topping> getToppings(boolean isPremium);

    List<Crust> getCrusts();

    List<Sauce> getSauces();

    List<Size> getSizes();

    Integer insertNewCrustOption(Crust crust);

    Integer insertNewSauceOption(Sauce sauce);

    Integer insertNewSizeOption(Size size);

    Integer insertNewToppingOption(Topping topping);

    void updateSauceAvailability(int sauceId, Sauce sauce);

    void updateCrustAvailability(int crustId, Crust crust);

    void updateToppingAvailability(int toppingId, Topping topping);



}
