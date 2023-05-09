package com.techelevator.dao.jdbc;

import com.techelevator.dao.NonPizzaItemDao;
import com.techelevator.dao.OrderDao;
import com.techelevator.model.*;
import com.techelevator.service.OrderService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOrderDao implements OrderDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Order> getAllOrders() {
        List<Order> result = new ArrayList<>();
        String sql = "SELECT * FROM orders o\n" +
        "JOIN status_type st ON o.status_id = st.status_id\n" +
        "ORDER BY order_id";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            result.add(mapRowToOrder(rowSet));
        }
        return result;
    }

    public Order getOrder(int orderId) {
        String sql = "SELECT order_id, (SELECT status_desc FROM status_type st JOIN orders o ON o.status_id = st.status_id WHERE o.order_id = ?), total, address, email, card_info, phone_number FROM orders\n" +
                     "WHERE order_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, orderId, orderId);

        Order order = new Order();

        if (rowSet.next()) {
            order = mapRowToOrder(rowSet);
        }
        return order;
    }

    public List<Order> getOrdersByStatus(String status) {
        List<Order> result = new ArrayList<>();
        String sql = "SELECT * FROM orders o\n" +
                "JOIN status_type st ON o.status_id = st.status_id\n" +
                "WHERE st.status_desc LIKE ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, status);

        while (rowSet.next()) {
            result.add(mapRowToOrder(rowSet));
        }
        return result;
    }

    public List<Pizza> getPizzasByOrderId(int orderId) {

        List<Pizza> pizzas = new ArrayList<>();

        String sql = "SELECT * FROM pizza WHERE pizza.order_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, orderId);
        while (rowSet.next()) {
            pizzas.add(mapRowToPizza(rowSet, orderId));
        }
        return pizzas;
    }

    public Size getSizeBySizeId(int sizeId) {
        Size size = new Size();

        String sql = "SELECT * FROM size WHERE size_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, sizeId);
        if(rowSet.next()) {
            size = mapRowToSize(rowSet);
        }
        return size;
    }

    public Sauce getSauceBySauceId(int sauceId) {
        Sauce sauce = new Sauce();

        String sql = "SELECT * FROM sauce WHERE sauce_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, sauceId);
        if(rowSet.next()) {
            sauce = mapRowToSauce(rowSet);
        }
        return sauce;
    }

    public Crust getCrustFromCrustId(int crustId) {
        Crust crust = new Crust();

        String sql = "SELECT * FROM crust WHERE crust_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, crustId);
        if(rowSet.next()) {
            crust = mapRowToCrust(rowSet);
        }
        return crust;
    }

    public String getAvailabilityNameByAvailabilityId(int availabilityId) {
        String availabilityName = "";

        String sql = "SELECT availability_type_desc FROM availability_type WHERE availability_type_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, availabilityId);
        if(rowSet.next()) {
            availabilityName = rowSet.getString("availability_type_desc");
        }
        return availabilityName;
    }

    public List<Topping> getNonPremiumToppings(int pizzaId) {

        List<Topping> toppings = new ArrayList<>();

        String sql = "SELECT t.topping_id, topping_name, is_premium, additional_price\n" +
                "FROM topping t\n" +
                "JOIN pizza_topping pt ON pt.topping_id = t.topping_id\n" +
                "JOIN pizza p ON p.pizza_id = pt.pizza_id\n" +
                "WHERE p.pizza_id = ? AND t.is_premium = false";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, pizzaId);

        while (rowSet.next()) {
            toppings.add(mapRowToTopping(rowSet));
        }
        return toppings;
    }

    public List<Topping> getPremiumToppings(int pizzaId) {
        List<Topping> premiumToppings = new ArrayList<>();
        String sql = "SELECT t.topping_id, topping_name, is_premium, additional_price\n" +
                "FROM topping t\n" +
                "JOIN pizza_topping pt ON pt.topping_id = t.topping_id\n" +
                "JOIN pizza p ON p.pizza_id = pt.pizza_id\n" +
                "WHERE p.pizza_id = ? AND t.is_premium = true";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, pizzaId);

        while (rowSet.next()) {
            premiumToppings.add(mapRowToTopping(rowSet));
        }
        return premiumToppings;
    }

    public List<Appetizer> getAppetizersByOrderId(int orderId) {
        List<Appetizer> appetizers = new ArrayList<>();
        String sql = "SELECT npi.item_id, item_name, item_type, price,\n" +
                            "(SELECT availability_type_desc FROM availability_type at\n" +
                            "JOIN non_pizza_item npi ON npi.availability = at.availability_type_id\n" +
                            "WHERE npi.item_id = ?)\n" +
                        "FROM non_pizza_item npi\n" +
                        "JOIN non_pizza_item_type npit ON npit.item_type_id = npi.item_type\n" +
                        "JOIN order_non_pizza_item onpi ON onpi.item_id = npi.item_id\n" +
                        "JOIN orders o ON o.order_id = onpi.order_id\n" +
                        "WHERE o.order_id = ? AND item_type_name ILIKE 'APPETIZER'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, orderId, orderId);

        while (rowSet.next()) {
            appetizers.add(mapRowToAppetizer(rowSet));
        }
        return appetizers;
    }

    public List<Side> getSidesByOrderId(int orderId) {
        List<Side> sides = new ArrayList<>();
        String sql = "SELECT npi.item_id, item_name, item_type, price,\n" +
                        "(SELECT availability_type_desc FROM availability_type at\n" +
                        "JOIN non_pizza_item npi ON npi.availability = at.availability_type_id\n" +
                        "WHERE npi.item_id = ?)\n" +
                    "FROM non_pizza_item npi\n" +
                    "JOIN non_pizza_item_type npit ON npit.item_type_id = npi.item_type\n" +
                    "JOIN order_non_pizza_item onpi ON onpi.item_id = npi.item_id\n" +
                    "JOIN orders o ON o.order_id = onpi.order_id\n" +
                    "WHERE o.order_id = ? AND item_type_name ILIKE 'SIDE'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, orderId, orderId);

        while (rowSet.next()) {
            sides.add(mapRowToSide(rowSet));
        }
        return sides;
    }

    public List<Salad> getSaladsByOrderId(int orderId) {
        List<Salad> salads = new ArrayList<>();
        String sql = "SELECT npi.item_id, item_name, item_type, price,\n" +
                        "(SELECT availability_type_desc FROM availability_type at\n" +
                        "JOIN non_pizza_item npi ON npi.availability = at.availability_type_id\n" +
                        "WHERE npi.item_id = ?)\n" +
                    "FROM non_pizza_item npi\n" +
                    "JOIN non_pizza_item_type npit ON npit.item_type_id = npi.item_type\n" +
                    "JOIN order_non_pizza_item onpi ON onpi.item_id = npi.item_id\n" +
                    "JOIN orders o ON o.order_id = onpi.order_id\n" +
                    "WHERE o.order_id = ? AND item_type_name ILIKE 'SALAD'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, orderId, orderId);

        while (rowSet.next()) {
            salads.add(mapRowToSalad(rowSet));
        }
        return salads;
    }

    public List<Drink> getDrinksByOrderId(int orderId) {
        List<Drink> drinks = new ArrayList<>();
        String sql = "SELECT npi.item_id, item_name, item_type, price,\n" +
                        "(SELECT availability_type_desc FROM availability_type at\n" +
                        "JOIN non_pizza_item npi ON npi.availability = at.availability_type_id\n" +
                        "WHERE npi.item_id = ?)\n" +
                    "FROM non_pizza_item npi\n" +
                    "JOIN non_pizza_item_type npit ON npit.item_type_id = npi.item_type\n" +
                    "JOIN order_non_pizza_item onpi ON onpi.item_id = npi.item_id\n" +
                    "JOIN orders o ON o.order_id = onpi.order_id\n" +
                    "WHERE o.order_id = ? AND item_type_name ILIKE 'DRINK'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, orderId, orderId);

        while (rowSet.next()) {
            drinks.add(mapRowToDrink(rowSet));
        }
        return drinks;
    }

    public void addOrder(Order newOrder) {
        Integer statusId = jdbcTemplate.queryForObject("SELECT status_id FROM status_type WHERE status_desc = ?",
                Integer.class, newOrder.getStatus());

        String sql = "INSERT INTO orders (status_id, total, address, email, card_info, phone_number)\n" +
                     "VALUES (?, ?, ?, ?, ?, ?)\n" +
                     "RETURNING order_id";

        Address orderAddress = newOrder.getAddress();
        CardInfo cardInfo = newOrder.getCardInfo();
        String addressString = String.format("%s %s %s %s %s", orderAddress.getName(), orderAddress.getStreet(),
                orderAddress.getCity(), orderAddress.getState(), orderAddress.getZip());
        String cardInfoString = String.format("%s %s %s %s %s", cardInfo.getName(), cardInfo.getCardNumber(),
                cardInfo.getExpirationDate(), cardInfo.getSecurityCode(), cardInfo.getBillingZip());

        Integer orderId = jdbcTemplate.queryForObject(sql, Integer.class,
                statusId, newOrder.getTotal(), addressString,
                newOrder.getEmail(), cardInfoString, newOrder.getPhone());
        newOrder.setOrderId(orderId);

        insertPizzaItems(orderId, newOrder.getPizzas());
        insertNonPizzaItems(orderId, newOrder.getAppetizers(), newOrder.getSalads(),
                            newOrder.getSides(), newOrder.getDrinks());
    }

    public void insertPizzaItems(Integer orderId, List<Pizza> pizzas) {
        for (Pizza pizza : pizzas) {
            pizza.setOrderId(orderId);
//            SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT crust_id, sauce_id, size_id\n" +
//                "FROM crust\n" +
//                "JOIN sauce ON true\n" +
//                "JOIN size ON true\n" +
//                "WHERE crust_name = ?\n" +
//                "AND sauce_name = ?\n" +
//                "AND size_name = ?", pizza.getCrust().toUpperCase(),
//                    pizza.getSauce().toUpperCase(), pizza.getSize().toUpperCase());
//            Integer crustId = null;
//            Integer sauceId = null;
//            Integer sizeId = null;
//            if (rowSet.next()) {
//                crustId = rowSet.getInt("crust_id");
//                sauceId = rowSet.getInt("sauce_id");
//                sizeId = rowSet.getInt("size_id");
//            }
            Integer crustId = pizza.getCrust().getCrustId();
            Integer sauceId = pizza.getSauce().getSauceId();
            Integer sizeId = pizza.getSize().getSizeId();

            //todo add price to database
            Integer pizzaId = jdbcTemplate.queryForObject("INSERT INTO pizza (order_id, size_id, crust_id, sauce_id)\n" +
                    "VALUES (?, ?, ?, ?)\n" +
                    "RETURNING pizza_id", Integer.class, orderId, sizeId, crustId, sauceId);
            pizza.setPizzaId(pizzaId);
            insertPizzaToppings(pizzaId, pizza.getToppings(), pizza.getPremiumToppings());
        }
    }

    public void insertPizzaToppings(Integer pizzaId, List<Topping> nonPremiumToppings, List<Topping> premiumToppings) {
        for (Topping topping : nonPremiumToppings) {
            Integer toppingId = jdbcTemplate.queryForObject("SELECT topping_id FROM topping\n" +
                    "WHERE topping_name = ?", Integer.class, topping.getToppingName());
            jdbcTemplate.update("INSERT INTO pizza_topping (pizza_id, topping_id)\n" +
                    "VALUES (?, ?)", pizzaId, toppingId);
        }
        for (Topping premiumTopping : premiumToppings) {
            Integer toppingId = jdbcTemplate.queryForObject("SELECT topping_id FROM topping\n" +
                    "WHERE topping_name = ?", Integer.class, premiumTopping.getToppingName());
            jdbcTemplate.update("INSERT INTO pizza_topping (pizza_id, topping_id)\n" +
                    "VALUES (?, ?)", pizzaId, toppingId);
        }
    }

    public void insertNonPizzaItems(Integer orderId, List<Appetizer> appetizers, List<Salad> salads, List<Side> sides,
                                    List<Drink> drinks) {
        for (Appetizer appetizer : appetizers) {
            Integer appetizerId = jdbcTemplate.queryForObject("SELECT item_id FROM non_pizza_item\n" +
                    "WHERE item_name ILIKE ?", Integer.class, appetizer.getItemName());
            jdbcTemplate.update("INSERT INTO order_non_pizza_item (order_id, item_id)\n" +
                    "VALUES (?, ?)", orderId, appetizerId);
        }
        for (Salad salad : salads) {
            Integer saladId = jdbcTemplate.queryForObject("SELECT item_id FROM non_pizza_item\n" +
                    "WHERE item_name ILIKE ?", Integer.class, salad.getItemName());
            jdbcTemplate.update("INSERT INTO order_non_pizza_item (order_id, item_id)\n" +
                    "VALUES (?, ?)", orderId, saladId);
        }
        for (Side side : sides) {
            Integer sideId = jdbcTemplate.queryForObject("SELECT item_id FROM non_pizza_item\n" +
                    "WHERE item_name ILIKE ?", Integer.class, side.getItemName());
            jdbcTemplate.update("INSERT INTO order_non_pizza_item (order_id, item_id)\n" +
                    "VALUES (?, ?)", orderId, sideId);
        }
        for (Drink drink : drinks) {
            Integer drinkId = jdbcTemplate.queryForObject("SELECT item_id FROM non_pizza_item\n" +
                    "WHERE item_name ILIKE ?", Integer.class, drink.getItemName());
            jdbcTemplate.update("INSERT INTO order_non_pizza_item (order_id, item_id)\n" +
                    "VALUES (?, ?)", orderId, drinkId);
        }
    }

    public void updateOrder(int orderId, Order order) {

        String sql = "UPDATE orders\n" +
                     "SET order_id = ?, status = ?, total = ?, address = ?, email = ?, card_info = ?, phone_number = ?\n" +
                     "WHERE order_id = ?";

        jdbcTemplate.update(sql, order.getOrderId(), order.getStatus(), order.getTotal(), order.getAddress(), order.getEmail(), order.getCardInfo(), order.getPhone(), orderId);
    }

    public void updateOrderStatus(int orderId, Order order) {

        String sql = "UPDATE orders\n" +
                "SET status_id = (SELECT st.status_id FROM status_type st WHERE st.status_desc ILIKE ?)\n" +
                "WHERE order_id = ?";

        jdbcTemplate.update(sql, order.getStatus(), orderId);
    }

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders\n" +
                     "WHERE order_id = ?";

        jdbcTemplate.update(sql, orderId);
    }

    public Order mapRowToOrder(SqlRowSet rowSet) {
        Order order = new Order();
        int orderId = rowSet.getInt("order_id");
        order.setOrderId(orderId);
        order.setStatusId(rowSet.getString("status_desc"));
        order.setTotal(rowSet.getBigDecimal("total"));
        order.setAddress(new Address(null, rowSet.getString("address"), null, null, null));
        order.setEmail(rowSet.getString("email"));
        order.setCardInfo(new CardInfo(null, rowSet.getString("card_info"), null, null, null));
        order.setPhone(rowSet.getString("phone_number"));
        order.setPizzas(getPizzasByOrderId(orderId));
        order.setAppetizers(getAppetizersByOrderId(orderId));
        order.setSides(getSidesByOrderId(orderId));
        order.setSalads(getSaladsByOrderId(orderId));
        order.setDrinks(getDrinksByOrderId(orderId));
        return order;
    }

    public Topping mapRowToTopping (SqlRowSet rowSet) {
        Topping topping = new Topping();
        topping.setToppingName(rowSet.getString("topping_name"));
        return topping;
    }

    public Pizza mapRowToPizza(SqlRowSet rowSet, int orderId) {
        Pizza pizza = new Pizza();
        pizza.setPizzaId(rowSet.getInt("pizza_id"));
        pizza.setOrderId(rowSet.getInt("order_id"));
        Sauce sauce = getSauceBySauceId(rowSet.getInt("sauce_id"));
        pizza.setSauce(sauce);
        Crust crust = getCrustFromCrustId(rowSet.getInt("crust_id"));
        pizza.setCrust(crust);
        Size size = getSizeBySizeId(rowSet.getInt("size_id"));
        pizza.setSize(size);
        pizza.setToppings(getNonPremiumToppings(pizza.getPizzaId()));
        pizza.setPremiumToppings(getPremiumToppings(pizza.getPizzaId()));
        return pizza;
    }

    public Appetizer mapRowToAppetizer(SqlRowSet rowSet) {
        Appetizer appetizer = new Appetizer();
        appetizer.setItemId(rowSet.getInt("item_id"));
        appetizer.setItemName(rowSet.getString("item_name"));
        appetizer.setItemPrice(rowSet.getBigDecimal("price"));
        appetizer.setItemAvailability(rowSet.getString("availability_type_desc"));
        return appetizer;
    }

    public Side mapRowToSide(SqlRowSet rowSet) {
        Side side = new Side();
        side.setItemId(rowSet.getInt("item_id"));
        side.setItemName(rowSet.getString("item_name"));
        side.setItemPrice(rowSet.getBigDecimal("price"));
        side.setItemAvailability(rowSet.getString("availability_type_desc"));
        return side;
    }

    public Salad mapRowToSalad(SqlRowSet rowSet) {
        Salad salad = new Salad();
        salad.setItemId(rowSet.getInt("item_id"));
        salad.setItemName(rowSet.getString("item_name"));
        salad.setItemPrice(rowSet.getBigDecimal("price"));
        salad.setItemAvailability(rowSet.getString("availability_type_desc"));
        return salad;
    }

    public Drink mapRowToDrink(SqlRowSet rowSet) {
        Drink drink = new Drink();
        drink.setItemId(rowSet.getInt("item_id"));
        drink.setItemName(rowSet.getString("item_name"));
        drink.setItemPrice(rowSet.getBigDecimal("price"));
        drink.setItemAvailability(rowSet.getString("availability_type_desc"));
        return drink;
    }

    public Size mapRowToSize(SqlRowSet rowSet) {
        Size size = new Size();
        size.setSizeName(rowSet.getString("size_name"));
        size.setSizeId(rowSet.getInt("size_id"));
        size.setAdditionalPrice(rowSet.getBigDecimal("additional_price"));
        return size;
    }

    public Sauce mapRowToSauce(SqlRowSet rowSet) {
        Sauce sauce = new Sauce();
        sauce.setSauceId(rowSet.getInt("sauce_id"));
        sauce.setSauceName(rowSet.getString("sauce_name"));
        sauce.setAdditionalPrice(rowSet.getBigDecimal("additional_price"));
        String availability = getAvailabilityNameByAvailabilityId(rowSet.getInt("availability"));
        sauce.setAvailability(availability);
        return sauce;
    }

    public Crust mapRowToCrust(SqlRowSet rowSet) {
        Crust crust = new Crust();
        crust.setCrustId(rowSet.getInt("crust_id"));
        crust.setCrustName(rowSet.getString("crust_name"));
        crust.setAdditionalPrice(rowSet.getBigDecimal("additional_price"));
        String availability = getAvailabilityNameByAvailabilityId(rowSet.getInt("availability"));
        crust.setAvailability(availability);
        return crust;
    }
}
