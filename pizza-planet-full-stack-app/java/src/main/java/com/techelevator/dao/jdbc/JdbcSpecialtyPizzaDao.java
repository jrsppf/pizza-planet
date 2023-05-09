package com.techelevator.dao.jdbc;

import com.techelevator.dao.SpecialtyPizzaDao;
import com.techelevator.model.Pizza;
import com.techelevator.model.SpecialtyPizza;
import com.techelevator.dao.SpecialtyPizzaDao;
import com.techelevator.model.Topping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class JdbcSpecialtyPizzaDao implements SpecialtyPizzaDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSpecialtyPizzaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<SpecialtyPizza> getSpecialtyPizzas() {
        String sql = "SELECT specialty_pizza_id, specialty_pizza_name, specialty_pizza_desc,\n" +
                "base_price, crust_name, sauce_name, is_active, availability_type_desc, image_path\n" +
                "FROM specialty_pizza sp\n" +
                "JOIN crust c ON sp.crust_id = c.crust_id\n" +
                "JOIN sauce s ON sp.sauce_id = s.sauce_id\n" +
                "JOIN availability_type at ON sp.availability = at.availability_type_id\n" +
                "ORDER BY specialty_pizza_id";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        List<SpecialtyPizza> result = new ArrayList<>();

        while (rowSet.next()) {
            result.add(mapRowToSpecialtyPizza(rowSet));
        }
        return result;
    }

    @Transactional
    public SpecialtyPizza insertNewSpecialtyPizza(SpecialtyPizza specialtyPizza) {
        String insertQuery = "INSERT INTO specialty_pizza (specialty_pizza_name, specialty_pizza_desc, crust_id, sauce_id, base_price, is_active, availability, image_path)\n" +
                "VALUES (?, ?, 1, 1, ?, ?, 3, ?)\n" +
                "RETURNING specialty_pizza_id";
        Integer pizzaId = jdbcTemplate.queryForObject(insertQuery, Integer.class, specialtyPizza.getSpecialtyPizzaName(),
                specialtyPizza.getSpecialtyPizzaDesc(),
                specialtyPizza.getBasePrice(), specialtyPizza.getIsActive(),
                specialtyPizza.getImagePath());
        specialtyPizza.setSpecialtyPizzaId(pizzaId);
        
        String updateQuery = "UPDATE specialty_pizza SET crust_id = (SELECT crust_id FROM crust WHERE crust_name = ?),\n" +
                "sauce_id = (SELECT sauce_id FROM sauce WHERE sauce_name = ?),\n" +
                "availability = (SELECT availability_type_id FROM availability_type WHERE availability_type_desc = ?)\n" +
                "WHERE specialty_pizza_id = ?";
        jdbcTemplate.update(updateQuery, specialtyPizza.getCrustName().toUpperCase(),
                specialtyPizza.getSauceName().toUpperCase(), specialtyPizza.getAvailability().toUpperCase(), pizzaId);

        return specialtyPizza;
    }

    public SpecialtyPizza mapRowToSpecialtyPizza(SqlRowSet rowSet) {
        SpecialtyPizza pizza = new SpecialtyPizza();
        pizza.setSpecialtyPizzaId(rowSet.getInt("specialty_pizza_id"));
        pizza.setSpecialtyPizzaName(rowSet.getString("specialty_pizza_name"));
        pizza.setSpecialtyPizzaDesc(rowSet.getString("specialty_pizza_desc"));
        pizza.setCrustName(rowSet.getString("crust_name"));
        pizza.setSauceName(rowSet.getString("sauce_name"));
        pizza.setBasePrice(rowSet.getBigDecimal("base_price"));
        pizza.setIsActive(rowSet.getBoolean("is_active"));
        pizza.setAvailability(rowSet.getString("availability_type_desc"));
        pizza.setImagePath(rowSet.getString("image_path"));

        List<List<Topping>> toppings = createToppingLists(pizza.getSpecialtyPizzaId());
        pizza.setToppings(toppings.get(0));
        pizza.setPremiumToppings(toppings.get(1));

        return pizza;
    }

    public List<List<Topping>> createToppingLists(int pizzaId) {
        List<Topping> nonPremiumToppings = new ArrayList<>();
        List<Topping> premiumToppings = new ArrayList<>();
        List<List<Topping>> result = new ArrayList<>();

        String toppingQuery = "SELECT spt.topping_id, topping_name, is_premium, additional_price, availability_type_desc FROM specialty_pizza sp\n" +
                "JOIN specialty_pizza_topping spt ON sp.specialty_pizza_id = spt.specialty_pizza_id\n" +
                "JOIN topping t ON spt.topping_id = t.topping_id\n" +
                "JOIN availability_type at ON sp.availability = at.availability_type_id\n" +
                "WHERE sp.specialty_pizza_id = ?\n" +
                "ORDER BY spt.topping_id";
        SqlRowSet toppingRowSet = jdbcTemplate.queryForRowSet(toppingQuery, pizzaId);

        while (toppingRowSet.next()) {
            Topping topping = new Topping(toppingRowSet.getInt("topping_id"),
                    toppingRowSet.getString("topping_name"),
                    toppingRowSet.getBoolean("is_premium"),
                    toppingRowSet.getBigDecimal("additional_price"),
                    toppingRowSet.getString("availability_type_desc"));
            if (!topping.getIsPremium()) {
                nonPremiumToppings.add(topping);
            } else {
                premiumToppings.add(topping);
            }
        }
        result.add(nonPremiumToppings);
        result.add(premiumToppings);
        return result;
    }

    public Integer getToppingIdByName(String toppingName) {
        String nameQuery = "SELECT topping_id FROM topping WHERE topping_name = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(nameQuery, toppingName.toUpperCase());
        if (rowSet.next()) {
            return rowSet.getInt("topping_id");
        }
        return null;
    }

    public void addToppingToSpecialty(Integer specialtyPizzaId, Integer toppingId) {
        jdbcTemplate.update("INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)\n" +
                        "VALUES (?, ?)", specialtyPizzaId, toppingId);
    }

    public void changeActiveStatus(int id, boolean isActive) {
        jdbcTemplate.update("UPDATE specialty_pizza SET is_active = ?\n" +
                "WHERE specialty_pizza_id = ?", isActive, id);
    }
}
