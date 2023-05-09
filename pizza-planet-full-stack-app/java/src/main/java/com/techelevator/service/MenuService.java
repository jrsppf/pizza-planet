package com.techelevator.service;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    private final SpecialtyPizzaDao specialtyPizzaDao;
    private final NonPizzaItemDao nonPizzaItemDao;
    private final PizzaOptionDao pizzaOptionDao;

    public MenuService(SpecialtyPizzaDao specialtyPizzaDao, NonPizzaItemDao nonPizzaItemDao,
                       PizzaOptionDao pizzaOptionDao) {
        this.specialtyPizzaDao = specialtyPizzaDao;
        this.nonPizzaItemDao = nonPizzaItemDao;
        this.pizzaOptionDao = pizzaOptionDao;
    }

    public Menu getMenu() {
        Menu menu = new Menu();
        List<NonPizzaItem> subMenu = nonPizzaItemDao.getNonPizzaItems();
        List<Appetizer> appetizers = new ArrayList<>();
        List<Salad> salads = new ArrayList<>();
        List<Side> sides = new ArrayList<>();
        List<Drink> drinks = new ArrayList<>();

        for (NonPizzaItem item : subMenu) {
            switch (item.getItemType()) {
                case "APPETIZER":
                    appetizers.add(new Appetizer(item.getItemId(), item.getItemName(), item.getItemType(),
                                   item.getItemPrice(), item.getItemAvailability(), item.getImagePath()));
                    break;
                case "SALAD":
                    salads.add(new Salad(item.getItemId(), item.getItemName(), item.getItemType(),
                            item.getItemPrice(), item.getItemAvailability(), item.getImagePath()));
                    break;
                case "SIDE":
                    sides.add(new Side(item.getItemId(), item.getItemName(), item.getItemType(),
                            item.getItemPrice(), item.getItemAvailability(), item.getImagePath()));
                    break;
                case "DRINK":
                    drinks.add(new Drink(item.getItemId(), item.getItemName(), item.getItemType(),
                            item.getItemPrice(), item.getItemAvailability(), item.getImagePath()));
            }
        }

        menu.setPizzas(getSpecialtyMenu());
        menu.setAppetizers(appetizers);
        menu.setSalads(salads);
        menu.setSides(sides);
        menu.setDrinks(drinks);
        return menu;
    }

    public List<SpecialtyPizza> getSpecialtyMenu() {
        return specialtyPizzaDao.getSpecialtyPizzas();
    }

    @Transactional
    public SpecialtyPizza addSpecialtyPizza(SpecialtyPizza specialtyPizza) {
        specialtyPizza = specialtyPizzaDao.insertNewSpecialtyPizza(specialtyPizza);

        List<Topping> nonPremiumToppings = specialtyPizza.getToppings();
        List<Topping> premiumToppings = specialtyPizza.getPremiumToppings();
        for (Topping topping : nonPremiumToppings) {
            Integer toppingId = specialtyPizzaDao.getToppingIdByName(topping.getToppingName());
            specialtyPizzaDao.addToppingToSpecialty(specialtyPizza.getSpecialtyPizzaId(), toppingId);
        }
        for (Topping topping: premiumToppings) {
            Integer toppingId = specialtyPizzaDao.getToppingIdByName(topping.getToppingName());
            specialtyPizzaDao.addToppingToSpecialty(specialtyPizza.getSpecialtyPizzaId(), toppingId);
        }

        return specialtyPizza;
    }


    public void updateSpecialtyPizzaStatus(int id, SpecialtyPizza specialtyPizza) {
        specialtyPizzaDao.changeActiveStatus(id, specialtyPizza.getIsActive());
    }


    //TODO
    public void deleteSpecialtyPizza(int id) { }

    //TODO
    public List<Appetizer> getAppetizers() {
        return null;
    }

    //TODO
    public Appetizer addAppetizer(Appetizer appetizer) {
        return appetizer;
    }

    //TODO
    public void updateAppetizer(int id, Appetizer appetizer) {

    }

    //TODO
    public void deleteAppetizer(int id) {

    }

    //TODO
    public List<Side> getSides() {
        List<Side> sides = new ArrayList<>();
        return sides;
    }

    //TODO
    public Side addSide(Side side) {
        return side;
    }

    //TODO
    public void updateSide(int id, Side side) {

    }

    //TODO
    public void deleteSide(int id) {

    }

    //TODO
    public List<Salad> getSalads() {
        List<Salad> salads = new ArrayList<>();
        return salads;
    }

    //TODO
    public Salad addSalad(Salad salad) {
        return salad;
    }

    //TODO
    public void updateSalad(int id, Salad salad) {

    }

    //TODO
    public void deleteSalad(int id) {

    }

    //TODO
    public List<Drink> getDrinks() {
        List<Drink> drinks = new ArrayList<>();
        return drinks;
    }

    //TODO
    public Drink addDrink(Drink drink) {
        return drink;
    }

    //TODO
    public void updateDrink(int id, Drink drink) {

    }

    //TODO
    public void deleteDrink(int id) {

    }


    public List<Topping> getToppings(String type) {
        if (type.equals("premium")) {
            return pizzaOptionDao.getToppings(true);
        } else if (type.equals("regular")) {
            return pizzaOptionDao.getToppings(false);
        } else {
            return pizzaOptionDao.getAllToppings();
        }

    }


    public Topping addTopping(Topping topping) {
        topping.setToppingId(pizzaOptionDao.insertNewToppingOption(topping));
        return topping;
    }


    public void updateToppingAvailability(int id, Topping topping) {
        pizzaOptionDao.updateToppingAvailability(id, topping);
    }

    //TODO
    public void deleteTopping(int id) {

    }

    //TODO
    public List<Crust> getCrusts() {
        return pizzaOptionDao.getCrusts();
    }


    public Crust addCrust(Crust crust) {
        crust.setCrustId(pizzaOptionDao.insertNewCrustOption(crust));
        return crust;
    }


    public void updateCrustAvailability(int id, Crust crust) {
        pizzaOptionDao.updateCrustAvailability(id, crust);
    }
/*
    //TODO
    public void deleteCrust(int id) {

    }
*/

    public List<Sauce> getSauces() {
        return pizzaOptionDao.getSauces();
    }


    public Sauce addSauce(Sauce sauce) {
        sauce.setSauceId(pizzaOptionDao.insertNewSauceOption(sauce));
        return sauce;
    }

    public void updateSauceAvailability(int id, Sauce sauce) {
        pizzaOptionDao.updateSauceAvailability(id, sauce);
    }
/*
    //TODO
    public void deleteSauce(int id) {

    }
*/
    public List<Size> getSizes() {
        return pizzaOptionDao.getSizes();
    }


    public Size addSize(Size size) {
        size.setSizeId(pizzaOptionDao.insertNewSizeOption(size));
        return size;
    }

/*
    //TODO
    public void deleteSize(int id) {

    }*/
}
