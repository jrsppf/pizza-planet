package com.techelevator.dao.jdbc;

import com.techelevator.dao.PizzaOptionDao;
import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPizzaOptionDao implements PizzaOptionDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPizzaOptionDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    //todo
    public List<Topping> getAllToppings() {
        return null;
    }

    public List<Topping> getToppings(boolean isPremium) {
        List<Topping> toppings = new ArrayList<>();
        String sql = "SELECT topping_id, topping_name, is_premium, additional_price, availability_type_desc\n" +
                     "FROM topping t JOIN availability_type at ON t.availability = at.availability_type_id\n" +
                     "WHERE is_premium = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, isPremium);
        while (rowSet.next()) {
            toppings.add(new Topping(rowSet.getInt("topping_id"), rowSet.getString("topping_name"),
                    rowSet.getBoolean("is_premium"), rowSet.getBigDecimal("additional_price"),
                    rowSet.getString("availability_type_desc")));
        }
        return toppings;
    }

    public List<Crust> getCrusts() {
        List<Crust> crusts = new ArrayList<>();
        String sql = "SELECT crust_id, crust_name, additional_price, availability_type_desc\n" +
                "FROM crust c JOIN availability_type at ON c.availability = at.availability_type_id;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            crusts.add(new Crust(rowSet.getInt("crust_id"), rowSet.getString("crust_name"),
                    rowSet.getBigDecimal("additional_price"),
                    rowSet.getString("availability_type_desc")));
        }
        return crusts;
    }

    public List<Sauce> getSauces() {
        List<Sauce> sauces = new ArrayList<>();
        String sql = "SELECT sauce_id, sauce_name, additional_price, availability_type_desc\n" +
                "FROM sauce s JOIN availability_type at ON s.availability = at.availability_type_id;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            sauces.add(new Sauce(rowSet.getInt("sauce_id"), rowSet.getString("sauce_name"),
                    rowSet.getBigDecimal("additional_price"),
                    rowSet.getString("availability_type_desc")));
        }
        return sauces;
    }

    public List<Size> getSizes() {
        List<Size> sizes = new ArrayList<>();
        String sql = "SELECT size_id, size_name, additional_price\n" +
                "FROM size";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            sizes.add(new Size(rowSet.getInt("size_id"), rowSet.getString("size_name"),
                    rowSet.getBigDecimal("additional_price")));
        }
        return sizes;
    }

    public Integer insertNewCrustOption(Crust crust) {
        Integer crustId = jdbcTemplate.queryForObject("INSERT INTO crust (crust_name, additional_price, availability)\n" +
                "VALUES (?, ?, 3) RETURNING crust_id", Integer.class, crust.getCrustName().toUpperCase(),
                crust.getAdditionalPrice());
        jdbcTemplate.update("UPDATE crust\n" +
                "SET availability = (SELECT availability_type_id\n" +
                "FROM availability_type\n" +
                "WHERE availability_type_desc = ?)\n" +
                "WHERE crust_id = ?", crust.getAvailability().toUpperCase(), crustId);
        return crustId;
    }

    public Integer insertNewSauceOption(Sauce sauce) {
        Integer sauceId = jdbcTemplate.queryForObject("INSERT INTO sauce (sauce_name, additional_price, availability)\n" +
                        "VALUES (?, ?, 3) RETURNING sauce_id", Integer.class, sauce.getSauceName().toUpperCase(),
                sauce.getAdditionalPrice());
        jdbcTemplate.update("UPDATE sauce\n" +
                "SET availability = (SELECT availability_type_id\n" +
                "FROM availability_type\n" +
                "WHERE availability_type_desc = ?)\n" +
                "WHERE sauce_id = ?", sauce.getAvailability().toUpperCase(), sauceId);
        return sauceId;
    }

    public Integer insertNewSizeOption(Size size) {
        return jdbcTemplate.queryForObject("INSERT INTO size (size_name, additional_price)\n" +
                        "VALUES (?, ?) RETURNING size_id", Integer.class, size.getSizeName().toUpperCase(),
                size.getAdditionalPrice());
    }

    public Integer insertNewToppingOption(Topping topping) {
        Integer toppingId = jdbcTemplate.queryForObject("INSERT INTO topping (topping_name, is_premium, additional_price, availability)\n" +
                        "VALUES (?, ?, ?, 3) RETURNING topping_id", Integer.class, topping.getToppingName().toUpperCase(),
                topping.getIsPremium(), topping.getAdditionalPrice());
        jdbcTemplate.update("UPDATE topping\n" +
                "SET availability = (SELECT availability_type_id\n" +
                "FROM availability_type\n" +
                "WHERE availability_type_desc = ?)\n" +
                "WHERE topping_id = ?", topping.getAvailability().toUpperCase(), toppingId);
        return toppingId;
    }

    public void updateSauceAvailability(int sauceId, Sauce sauce) {
        Integer availabilityTypeId = jdbcTemplate.queryForObject("SELECT availability_type_id\n" +
                "FROM availability_type WHERE availability_type_desc ILIKE ?", Integer.class, sauce.getAvailability());
        jdbcTemplate.update("UPDATE sauce SET availability = ? WHERE sauce_id = ?", availabilityTypeId, sauceId);
    }

    public void updateCrustAvailability(int crustId, Crust crust) {
        Integer availabilityTypeId = jdbcTemplate.queryForObject("SELECT availability_type_id\n" +
                "FROM availability_type WHERE availability_type_desc ILIKE ?", Integer.class, crust.getAvailability());
        jdbcTemplate.update("UPDATE crust SET availability = ? WHERE crust_id = ?", availabilityTypeId, crustId);
    }

    public void updateToppingAvailability(int toppingId, Topping topping) {
        Integer availabilityTypeId = jdbcTemplate.queryForObject("SELECT availability_type_id\n" +
                "FROM availability_type WHERE availability_type_desc ILIKE ?", Integer.class, topping.getAvailability());
        jdbcTemplate.update("UPDATE topping SET availability = ? WHERE topping_id = ?", availabilityTypeId, toppingId);
    }
}
