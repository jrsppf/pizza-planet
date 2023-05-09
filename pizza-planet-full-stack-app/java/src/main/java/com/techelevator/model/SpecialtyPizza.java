package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpecialtyPizza {
    @JsonProperty("itemId")
    private Integer specialtyPizzaId;
    @NotNull
    @JsonProperty("itemName")
    private String specialtyPizzaName;
    @NotNull
    @JsonProperty("itemDesc")
    private String specialtyPizzaDesc;
    @NotNull
    private String crustName;
    @NotNull
    private String sauceName;
    @NotNull
    @JsonProperty("itemPrice")
    private BigDecimal basePrice;
    @NotNull
    private boolean isActive;
    @NotNull
    private String availability;
    @NotNull
    private String imagePath;

    private List<Topping> toppings;

    private List<Topping> premiumToppings;

    public SpecialtyPizza() {}

    public SpecialtyPizza(Integer specialtyPizzaId, String specialtyPizzaName,
                          String specialtyPizzaDesc, String crustName,
                          String sauceName, BigDecimal basePrice, boolean isActive,
                          String availability, String imagePath,
                          List<Topping> toppings, List<Topping> premiumToppings) {

        this.specialtyPizzaId = specialtyPizzaId;
        this.specialtyPizzaName = specialtyPizzaName;
        this.specialtyPizzaDesc = specialtyPizzaDesc;
        this.crustName = crustName;
        this.sauceName = sauceName;
        this.basePrice = basePrice;
        this.isActive = isActive;
        this.availability = availability;
        this.imagePath =imagePath;
        this.toppings = toppings;
        this.premiumToppings = premiumToppings;
    }

    public Integer getSpecialtyPizzaId() {
        return specialtyPizzaId;
    }

    public void setSpecialtyPizzaId(Integer specialtyPizzaId) {
        this.specialtyPizzaId = specialtyPizzaId;
    }

    public String getSpecialtyPizzaName() {
        return specialtyPizzaName;
    }

    public void setSpecialtyPizzaName(String specialtyPizzaName) {
        this.specialtyPizzaName = specialtyPizzaName;
    }

    public String getSpecialtyPizzaDesc() {
        return specialtyPizzaDesc;
    }

    public void setSpecialtyPizzaDesc(String specialtyPizzaDesc) {
        this.specialtyPizzaDesc = specialtyPizzaDesc;
    }

    public String getCrustName() {
        return crustName;
    }

    public void setCrustName(String crustName) {
        this.crustName = crustName;
    }

    public String getSauceName() {
        return sauceName;
    }

    public void setSauceName(String sauceName) {
        this.sauceName = sauceName;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public List<Topping> getPremiumToppings() {
        return premiumToppings;
    }

    public void setPremiumToppings(List<Topping> premiumToppings) {
        this.premiumToppings = premiumToppings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialtyPizza that = (SpecialtyPizza) o;
        return Objects.equals(specialtyPizzaId, that.specialtyPizzaId) && Objects.equals(specialtyPizzaName, that.specialtyPizzaName) && Objects.equals(specialtyPizzaDesc, that.specialtyPizzaDesc) && Objects.equals(crustName, that.crustName) && Objects.equals(sauceName, that.sauceName) && Objects.equals(basePrice, that.basePrice) && Objects.equals(isActive, that.isActive) && Objects.equals(availability, that.availability) && Objects.equals(imagePath, that.imagePath) && Objects.equals(toppings, that.toppings) && Objects.equals(premiumToppings, that.premiumToppings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialtyPizzaId, specialtyPizzaName, specialtyPizzaDesc, crustName, sauceName, basePrice, isActive, availability, imagePath, toppings, premiumToppings);
    }
}
