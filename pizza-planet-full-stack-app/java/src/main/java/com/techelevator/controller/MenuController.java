package com.techelevator.controller;

import com.techelevator.model.*;
import com.techelevator.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping()
    public Menu getMenu() {
        return menuService.getMenu();
    }

    @GetMapping("/specialties")
    public List<SpecialtyPizza> getSpecialtyMenu() {
        return menuService.getSpecialtyMenu();
    }

    @PostMapping("/specialties")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialtyPizza addSpecialtyPizza(@RequestBody SpecialtyPizza specialtyPizza) {
        return menuService.addSpecialtyPizza(specialtyPizza);
    }

    @PutMapping("/specialties/{specialtyId}/status")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSpecialtyPizzaStatus(@PathVariable int specialtyId, @RequestBody SpecialtyPizza updatedSpecialtyPizza) {
        menuService.updateSpecialtyPizzaStatus(specialtyId, updatedSpecialtyPizza);
    }

    @DeleteMapping("/specialties/{specialtyId}")
    @PreAuthorize("isAuthenticated()")
    public void deleteSpecialtyPizza(@PathVariable int specialtyId) {
        menuService.deleteSpecialtyPizza(specialtyId);
    }


    @GetMapping("/appetizers")
    public List<Appetizer> getAppetizers() {
        return menuService.getAppetizers();
    }

    @PostMapping("/appetizers")
    @PreAuthorize("isAuthenticated()")
    public Appetizer addAppetizer(@RequestBody Appetizer newAppetizer) {
        return menuService.addAppetizer(newAppetizer);
    }

    @PutMapping("/appetizers/{appetizerId}")
    public void updateAppetizer(@PathVariable int appetizerId, @RequestBody Appetizer updatedAppetizer) {
        menuService.updateAppetizer(appetizerId, updatedAppetizer);
    }

    @DeleteMapping("/appetizers/{appetizerId}")
    public void deleteAppetizer(@PathVariable int appetizerId) {
        menuService.deleteAppetizer(appetizerId);
    }

    @GetMapping("/sides")
    public List<Side> getSides() {
        return menuService.getSides();
    }

    @PostMapping("/sides")
    public Side addSide(@RequestBody Side newSide) {
        return menuService.addSide(newSide);
    }

    @PutMapping("/sides/{sideId}")
    public void updateSide(@PathVariable int sideId, @RequestBody Side updatedSide) {
        menuService.updateSide(sideId, updatedSide);
    }

    @DeleteMapping("/sides/{sideId}")
    public void deleteSide(@PathVariable int sideId) {
        menuService.deleteSide(sideId);
    }

    @GetMapping("/salads")
    public List<Salad> getSalads() {
        return menuService.getSalads();
    }

    @PostMapping("/salads")
    public Salad addSalad(@RequestBody Salad newSalad) {
        return menuService.addSalad(newSalad);
    }

    @PutMapping("/salads{saladId}")
    public void updateSalad(@PathVariable int saladId, @RequestBody Salad updatedSalad) {
        menuService.updateSalad(saladId, updatedSalad);
    }

    @DeleteMapping("/salads/{saladId}")
    public void deleteSalad(@PathVariable int saladId) {
        menuService.deleteSalad(saladId);
    }

    @GetMapping("/drinks")
    public List<Drink> getDrinks() {
        return menuService.getDrinks();
    }

    @PostMapping("/drinks")
    public Drink addDrink(@RequestBody Drink newDrink) {
        return menuService.addDrink(newDrink);
    }

    @PutMapping("/drinks/{drinkId}")
    public void updateDrink(@PathVariable int drinkId, @RequestBody Drink updatedDrink) {
        menuService.updateDrink(drinkId, updatedDrink);
    }

    @DeleteMapping("/drinks/{drinkId}")
    public void deleteDrink(@PathVariable int drinkId) {
        menuService.deleteDrink(drinkId);
    }

    @GetMapping("/toppings")
    public List<Topping> getToppings(@RequestParam(required = false) String type) {
        return menuService.getToppings(type);
    }

    @PostMapping("/toppings")
    @ResponseStatus(HttpStatus.CREATED)
    public Topping addTopping(@RequestBody Topping newTopping) {
        return menuService.addTopping(newTopping);
    }

    @PutMapping("/toppings/{toppingId}/status")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateToppingAvailability(@PathVariable int toppingId, @RequestBody Topping updatedTopping) {
        menuService.updateToppingAvailability(toppingId, updatedTopping);
    }
/*
    @DeleteMapping("/toppings/{toppingId}")
    public void deleteTopping(@PathVariable int toppingId) {
        menuService.deleteTopping(toppingId);
    }*/

    @GetMapping("/crusts")
    public List<Crust> getCrusts() {
        return menuService.getCrusts();
    }

    @PostMapping("/crusts")
    @ResponseStatus(HttpStatus.CREATED)
    public Crust addCrust(@RequestBody Crust newCrust) {
        return menuService.addCrust(newCrust);
    }

    @PutMapping("/crusts/{crustId}/status")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCrustAvailability(@PathVariable int crustId, @RequestBody Crust updatedCrust) {
        menuService.updateCrustAvailability(crustId, updatedCrust);
    }
/*
    @DeleteMapping("/crusts/{crustId}")
    public void deleteCrust(@PathVariable int crustId) {
        menuService.deleteCrust(crustId);
    }*/

    @GetMapping("/sauces")
    public List<Sauce> getSauces() {
        return menuService.getSauces();
    }

    @PostMapping("/sauces")
    @ResponseStatus(HttpStatus.CREATED)
    public Sauce addSauce(@RequestBody Sauce newSauce) {
        return menuService.addSauce(newSauce);
    }

    @PutMapping("/sauces/{sauceId}/status")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSauceAvailability(@PathVariable int sauceId, @RequestBody Sauce updatedSauce) {
        menuService.updateSauceAvailability(sauceId, updatedSauce);
    }
/*
    @DeleteMapping("/sauces/{sauceId}")
    public void deleteSauce(@PathVariable int sauceId) {
        menuService.deleteSauce(sauceId);
    }*/

    @GetMapping("/sizes")
    public List<Size> getSizes() {
        return menuService.getSizes();
    }

    @PostMapping("/sizes")
    @ResponseStatus(HttpStatus.CREATED)
    public Size addSize(@RequestBody Size newSize) {
        return menuService.addSize(newSize);
    }

/*
    @DeleteMapping("/sizes/{sizeId}")
    public void deleteSize(@PathVariable int sizeId) {
        menuService.deleteSize(sizeId);
    }*/
}
