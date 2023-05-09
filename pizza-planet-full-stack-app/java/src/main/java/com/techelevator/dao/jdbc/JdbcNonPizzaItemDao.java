package com.techelevator.dao.jdbc;

import com.techelevator.dao.NonPizzaItemDao;
import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcNonPizzaItemDao implements NonPizzaItemDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNonPizzaItemDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public List<NonPizzaItem> getNonPizzaItems() {

        List<NonPizzaItem> result = new ArrayList<>();

        String sql = "SELECT item_id, item_name, item_type_name, price, availability_type_desc, image_path\n" +
                "FROM non_pizza_item npi\n" +
                "JOIN non_pizza_item_type npit ON npi.item_type = npit.item_type_id\n" +
                "JOIN availability_type at ON npi.availability = at.availability_type_id\n" +
                "ORDER BY item_id";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            result.add(mapRowToResult(rowSet));
        }

        return result;
    }

    public List<NonPizzaItem> getNonPizzaItemsByType(String type) {
        List<NonPizzaItem> result = new ArrayList<>();

        String sql = "SELECT item_id, item_name, item_type_name, price, availability_type_desc, image_path\n" +
                "FROM non_pizza_item npi\n" +
                "JOIN non_pizza_item_type npit ON npi.item_type = npit.item_type_id\n" +
                "JOIN availability_type at ON npi.availability = at.availability_type_id\n" +
                "WHERE item_type_name = ?\n" +
                "ORDER BY item_id";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, type.toUpperCase());

        while (rowSet.next()) {
            result.add(mapRowToResult(rowSet));
        }

        return result;
    }

    public NonPizzaItem mapRowToResult(SqlRowSet rowSet) {
        return new NonPizzaItem(rowSet.getInt("item_id"),
                rowSet.getString("item_name"), rowSet.getString("item_type_name"),
                rowSet.getBigDecimal("price"), rowSet.getString("availability_type_desc"),
                rowSet.getString("image_path")) {
        };
    }

}
