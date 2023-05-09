BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO status_type (status_desc)
VALUES ('PENDING');

INSERT INTO status_type (status_desc)
VALUES ('READY');

INSERT INTO status_type (status_desc)
VALUES ('COMPLETE');

INSERT INTO status_type (status_desc)
VALUES ('CANCELLED');

INSERT INTO availability_type (availability_type_desc)
VALUES ('UNAVAILABLE');

INSERT INTO availability_type (availability_type_desc)
VALUES ('LOW STOCK');

INSERT INTO availability_type (availability_type_desc)
VALUES ('IN STOCK');

INSERT INTO size (size_name, additional_price)
VALUES ('SMALL', 0.00);

INSERT INTO size (size_name, additional_price)
VALUES ('MEDIUM', 2.00);

INSERT INTO size (size_name, additional_price)
VALUES ('LARGE', 4.00);

INSERT INTO crust (crust_name, additional_price, availability)
VALUES ('REGULAR', 0.00, 3);

INSERT INTO crust (crust_name, additional_price, availability)
VALUES ('THIN', 1.00, 3);

INSERT INTO crust (crust_name, additional_price, availability)
VALUES ('DOUBLE STUFFED', 2.00, 3);

INSERT INTO sauce (sauce_name, additional_price, availability)
VALUES ('RED', 0.00, 3);

INSERT INTO sauce (sauce_name, additional_price, availability)
VALUES ('WHITE', 1.00, 3);

INSERT INTO sauce (sauce_name, additional_price, availability)
VALUES ('MARINARA', 1.00, 3);

INSERT INTO topping (topping_name, additional_price, availability)
VALUES ('PEPPERONI', 1.00, 3);

INSERT INTO topping (topping_name, additional_price, availability)
VALUES ('MUSHROOMS', 1.00, 3);

INSERT INTO topping (topping_name, additional_price, availability)
VALUES ('ONIONS', 1.00, 3);

INSERT INTO topping (topping_name, additional_price, availability)
VALUES ('PEPPERS', 1.00, 3);

INSERT INTO topping (topping_name, additional_price, availability)
VALUES ('BLACK OLIVES', 1.00, 3);

INSERT INTO premium_topping (premium_topping_name, additional_price, availability)
VALUES ('CHICKEN', 2.00, 3);

INSERT INTO premium_topping (premium_topping_name, additional_price, availability)
VALUES ('HAM & PINEAPPLE', 2.00, 3);

INSERT INTO premium_topping (premium_topping_name, additional_price, availability)
VALUES ('BACON', 2.00, 3);

INSERT INTO premium_topping (premium_topping_name, additional_price, availability)
VALUES ('SAUSAGE', 2.00, 3);

INSERT INTO premium_topping (premium_topping_name, additional_price, availability)
VALUES ('EXTRA CHEESE', 2.00, 3);

INSERT INTO specialty_pizza (specialty_pizza_name, specialty_pizza_desc, crust_id, sauce_id, base_price, active)
VALUES ('Silician', 'Thick crust square cut pizza.', 1, 3, 12.00, true);

INSERT INTO specialty_pizza (specialty_pizza_name, specialty_pizza_desc, crust_id, sauce_id, base_price, active)
VALUES ('Breakfast of Chase', 'The daily breakfast of our beloved colleague and former U165 wrestling ' ||
		'champion, Chase!', 3, 2, 12.00, true);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (1, 1);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (1, 2);

INSERT INTO specialty_pizza_premium_topping (specialty_pizza_id, premium_topping_id)
VALUES (1, 3);

INSERT INTO non_pizza_item_type (item_type_name)
VALUES ('APPETIZER');

INSERT INTO non_pizza_item_type (item_type_name)
VALUES ('SALAD');

INSERT INTO non_pizza_item_type (item_type_name)
VALUES ('SIDE');

INSERT INTO non_pizza_item_type (item_type_name)
VALUES ('DRINK');

INSERT INTO non_pizza_item (item_name, item_type, price, availability)
VALUES ('Mozzarella sticks', 1, 4.00, 3);

INSERT INTO non_pizza_item (item_name, item_type, price, availability)
VALUES ('Caesar', 2, 3.00, 3);

INSERT INTO non_pizza_item (item_name, item_type, price, availability)
VALUES ('Greek', 2, 3.00, 3);

INSERT INTO non_pizza_item (item_name, item_type, price, availability)
VALUES ('Cobb', 2, 2.50, 3);

INSERT INTO non_pizza_item (item_name, item_type, price, availability)
VALUES ('French fries', 3, 4.50, 3);

INSERT INTO non_pizza_item (item_name, item_type, price, availability)
VALUES ('Sprite', 4, 1.50, 3);

INSERT INTO non_pizza_item (item_name, item_type, price, availability)
VALUES ('Pepsi', 4, 1.50, 3);

INSERT INTO non_pizza_item (item_name, item_type, price, availability)
VALUES ('Dr. Pepper', 4, 1.50, 3);

--Below we have mock data--

INSERT INTO orders (status_id, total, address, email, card_info, phone_number)
VALUES (1, NULL, '1141 Hillcrest Road', 'chasedevitto@gmail.com', '3485493294857', '610-455-0292');

INSERT INTO orders (status_id, total, address, email, card_info, phone_number)
VALUES (1, NULL, '1234 Updock Lane', 'aoiewroaigetto@gmail.com', '548234858', '610-333-3445');

INSERT INTO orders (status_id, total, address, email, card_info, phone_number)
VALUES (3, NULL, '33 Timber Road', 'awefjiaerig@aol.com', '1234435483', '610-454-0442');

INSERT INTO pizza (order_id, size_id, crust_id, sauce_id)
VALUES (1, 2, 2, 3);

INSERT INTO pizza_topping (pizza_id, topping_id)
VALUES (1, 1);

INSERT INTO pizza_premium_topping (pizza_id, premium_topping_id)
VALUES (1, 1);

INSERT INTO order_non_pizza_item (order_id, item_id)
VALUES (1, 1);

INSERT INTO order_non_pizza_item (order_id, item_id)
VALUES (1, 2);

INSERT INTO order_non_pizza_item (order_id, item_id)
VALUES (1, 5);

INSERT INTO order_non_pizza_item (order_id, item_id)
VALUES (1, 6);

COMMIT TRANSACTION;
