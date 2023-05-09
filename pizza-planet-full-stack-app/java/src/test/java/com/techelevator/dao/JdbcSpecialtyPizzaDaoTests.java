package com.techelevator.dao;

import com.techelevator.dao.jdbc.JdbcSpecialtyPizzaDao;
import com.techelevator.dao.jdbc.JdbcUserDao;
import com.techelevator.model.SpecialtyPizza;
import com.techelevator.model.Topping;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcSpecialtyPizzaDaoTests extends BaseDaoTests {


    private JdbcSpecialtyPizzaDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcSpecialtyPizzaDao(jdbcTemplate);
    }

    /*@Test
    public void mapRowToSpecialtyPizza_happy_path() {

        String sql = "SELECT specialty_pizza_id, specialty_pizza_name, specialty_pizza_desc,\n" +
                "base_price, crust_name, sauce_name, active\n" +
                "FROM specialty_pizza sp\n" +
                "JOIN crust c ON sp.crust_id = c.crust_id\n" +
                "JOIN sauce s ON sp.sauce_id = s.sauce_id\n" +
                "WHERE specialty_pizza_id = ?\n" +
                "ORDER BY specialty_pizza_id";
        List<Topping> toppingList = new ArrayList<>();
        //toppingList.add(new Topping(1, "PEPPERONI", new BigDecimal("1.00"), null));
        //toppingList.add(new Topping(2, "MUSHROOMS", new BigDecimal("1.00"), null));

        List<Topping> premiumToppingList = new ArrayList<>();
        //premiumToppingList.add(new Topping(3, "BACON", new BigDecimal("2.00"), null));

        SpecialtyPizza expected = new SpecialtyPizza(1, "Sicilian", "Thick crust square cut pizza.",
                 "REGULAR", "MARINARA", new BigDecimal("12.00"),true, "IN STOCK", "pizza1.png", toppingList, premiumToppingList);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Assert.assertEquals(expected, sut.mapRowToSpecialtyPizza(jdbcTemplate.queryForRowSet(sql, 1)));
    }

    @Test
    public void createToppingList_happy_path() {
        String sql = "SELECT spt.topping_id, topping_name, additional_price, availability FROM specialty_pizza sp\n" +
                "JOIN specialty_pizza_topping spt ON sp.specialty_pizza_id = spt.specialty_pizza_id\n" +
                "JOIN topping t ON spt.topping_id = t.topping_id\n" +
                "WHERE sp.specialty_pizza_id = ?\n" +
                "ORDER BY spt.topping_id";
        int pizzaId = 1;

        List<Topping> expectedList = new ArrayList<>();
        expectedList.add(new Topping(1, "PEPPERONI", new BigDecimal("1.00"), null));
        expectedList.add(new Topping(2, "MUSHROOMS", new BigDecimal("1.00"), null));

        Assert.assertEquals(expectedList, sut.createToppingList(sql, pizzaId, true));
    }
*/
    /*@Test
    public void createToppingList_happy_path() {
        String sql = "SELECT sppt.premium_topping_id, premium_topping_name, additional_price, availability FROM specialty_pizza sp\n" +
                "JOIN specialty_pizza_premium_topping sppt ON sp.specialty_pizza_id = sppt.specialty_pizza_id\n" +
                "JOIN premium_topping pt ON sppt.premium_topping_id = pt.premium_topping_id\n" +
                "WHERE sp.specialty_pizza_id = ?\n" +
                "ORDER BY sppt.premium_topping_id";
        int pizzaId = 1;

        List<Topping> expectedList = new ArrayList<>();
        expectedList.add(new Topping(3, "BACON", new BigDecimal("2.00"), null));

        Assert.assertEquals(expectedList, sut.createToppingLists(sql, pizzaId, true));
    }*/
}
