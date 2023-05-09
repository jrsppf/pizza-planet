package com.techelevator.dao;

import com.techelevator.model.Pizza;
import com.techelevator.model.SpecialtyPizza;

import java.util.List;

public interface SpecialtyPizzaDao {

    List<SpecialtyPizza> getSpecialtyPizzas();


    SpecialtyPizza insertNewSpecialtyPizza(SpecialtyPizza specialtyPizza);

    Integer getToppingIdByName(String toppingName);

    void addToppingToSpecialty(Integer specialtyPizzaId, Integer toppingId);

    void changeActiveStatus(int id, boolean isActive);
}
