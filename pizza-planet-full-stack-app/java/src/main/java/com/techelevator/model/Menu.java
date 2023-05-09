package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class Menu  {
    private List<SpecialtyPizza> pizzas = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Appetizer> appetizers = new ArrayList<>();
    private List<Salad> salads = new ArrayList<>();
    private List<Side> sides = new ArrayList<>();

    public Menu() {

    }

    public Menu(List<SpecialtyPizza> pizzas, List<Drink> drinks,
                List<Appetizer> appetizers, List<Salad> salads, List<Side> sides) {

        this.pizzas = pizzas;
        this.appetizers = appetizers;
        this.salads = salads;
        this.sides = sides;
        this.drinks = drinks;
    }

    public List<SpecialtyPizza> getPizzas() {return pizzas;}

    public void setPizzas(List<SpecialtyPizza> pizzas) {this.pizzas = pizzas;}

    public List<Drink> getDrinks() {return drinks;}

    public void setDrinks(List<Drink> drinks) {this.drinks = drinks;}

    public List<Appetizer> getAppetizers() {return appetizers;}

    public void setAppetizers(List<Appetizer> appetizers) {this.appetizers = appetizers;}

    public List<Salad> getSalads() {return salads;}

    public void setSalads(List<Salad> salads) {this.salads = salads;}

    public List<Side> getSides() {return sides;}

    public void setSides(List<Side> sides) {this.sides = sides;}
}
