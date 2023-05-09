BEGIN TRANSACTION;

INSERT INTO users (username, password_hash, role) VALUES ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('admin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');

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

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('PEPPERONI', false, 1.00, 3);

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('MUSHROOMS', false, 1.00, 3);

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('ONIONS', false, 1.00, 3);

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('PEPPERS', false, 1.00, 3);

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('BLACK OLIVES', false, 1.00, 3);

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('CHICKEN', true, 2.00, 3);

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('HAM & PINEAPPLE', true, 2.00, 3);

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('BACON', true, 2.00, 3);

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('SAUSAGE', true, 2.00, 3);

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('EXTRA CHEESE', true, 2.00, 3);

INSERT INTO topping (topping_name, is_premium, additional_price, availability)
VALUES ('ANCHOVIES', true, 4.00, 2);

INSERT INTO specialty_pizza (specialty_pizza_name, specialty_pizza_desc, crust_id, sauce_id, base_price, is_active, availability, image_path)
VALUES ('Make Your Own', 'Self-explanatory', 1, 1, 10.00, true, 3, 'pizza1.png');

INSERT INTO specialty_pizza (specialty_pizza_name, specialty_pizza_desc, crust_id, sauce_id, base_price, is_active, availability, image_path)
VALUES ('Alex The Silician', 'Thick crust square cut pizza. Topped with anchovies and mushrooms.', 3, 1, 12.00, true, 3, 'pizza2.png');

INSERT INTO specialty_pizza (specialty_pizza_name, specialty_pizza_desc, crust_id, sauce_id, base_price, is_active, availability, image_path)
VALUES ('Breakfast of Chase', 'The daily breakfast of our beloved colleague and former U165 wrestling champion, Chase! ' ||
        'With double stuffed crust, white sauce and extra cheese, topped with bacon and sausage, ' ||
		'this specialty is sure to leave you satisfied until lunch.', 3, 2, 12.00, true, 3, 'pizza3.png');
		
INSERT INTO specialty_pizza (specialty_pizza_name, specialty_pizza_desc, crust_id, sauce_id, base_price, is_active, availability, image_path)
VALUES ('HAwaiian', 'A fresh breeze of the ocean. With ham, pineapple, double stuffed crust and marinara sauce.', 3, 3, 12.00, true, 3, 'pizza4.png');

INSERT INTO specialty_pizza (specialty_pizza_name, specialty_pizza_desc, crust_id, sauce_id, base_price, is_active, availability, image_path)
VALUES ('Misty Margherita ', 'Misty''s favorite. Changes are liable to be made at any time, so we don''t bother to give it a description.', 2, 3, 12.00, true, 3, 'pizza5.png');

INSERT INTO specialty_pizza (specialty_pizza_name, specialty_pizza_desc, crust_id, sauce_id, base_price, is_active, availability, image_path)
VALUES ('Jacob''s Favorite Pepperoni', 'It''s a classic pepperoni pizza, what more can you ask for?', 1, 1, 12.00, true, 3, 'pizza6.png');

INSERT INTO specialty_pizza (specialty_pizza_name, specialty_pizza_desc, crust_id, sauce_id, base_price, is_active, availability, image_path)
VALUES ('Shawn''s Signature Pizza', 'Best of TE. Order it to find out how it''s made!', 3, 1, 12.00, true, 3, 'pizza5.png');

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (2, 2);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (2, 11);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (3, 8);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (3, 9);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (3, 10);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (4, 7);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (5, 3);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (5, 5);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (5, 6);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (5, 8);

INSERT INTO specialty_pizza_topping (specialty_pizza_id, topping_id)
VALUES (6, 1);

INSERT INTO non_pizza_item_type (item_type_name)
VALUES ('APPETIZER');

INSERT INTO non_pizza_item_type (item_type_name)
VALUES ('SALAD');

INSERT INTO non_pizza_item_type (item_type_name)
VALUES ('SIDE');

INSERT INTO non_pizza_item_type (item_type_name)
VALUES ('DRINK');

INSERT INTO non_pizza_item (item_name, item_type, price, availability, image_path)
VALUES ('Mozzarella Sticks', 1, 4.00, 3, 'mozzarella-sticks.png');

INSERT INTO non_pizza_item (item_name, item_type, price, availability, image_path)
VALUES ('Caesar Salad', 2, 3.00, 3, 'ceasar-salad.jpg');

INSERT INTO non_pizza_item (item_name, item_type, price, availability, image_path)
VALUES ('Greek Salad', 2, 3.00, 3, 'greek-salad.jpg');

INSERT INTO non_pizza_item (item_name, item_type, price, availability, image_path)
VALUES ('Cobb Salad', 2, 2.50, 3, 'salad.png');

INSERT INTO non_pizza_item (item_name, item_type, price, availability, image_path)
VALUES ('French Fries', 3, 4.50, 3, 'french fries.png');

INSERT INTO non_pizza_item (item_name, item_type, price, availability, image_path)
VALUES ('Sprite', 4, 1.50, 3, 'sprite.png');

INSERT INTO non_pizza_item (item_name, item_type, price, availability, image_path)
VALUES ('Pepsi', 4, 1.50, 3, 'pepsi.jpg');

INSERT INTO non_pizza_item (item_name, item_type, price, availability, image_path)
VALUES ('Dr. Pepper', 4, 1.50, 3, 'dr-pepper.jpg');

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

INSERT INTO pizza_topping (pizza_id, topping_id)
VALUES (1, 6);

INSERT INTO order_non_pizza_item (order_id, item_id)
VALUES (1, 1);

INSERT INTO order_non_pizza_item (order_id, item_id)
VALUES (1, 2);

INSERT INTO order_non_pizza_item (order_id, item_id)
VALUES (1, 5);

INSERT INTO order_non_pizza_item (order_id, item_id)
VALUES (1, 6);

COMMIT TRANSACTION;

