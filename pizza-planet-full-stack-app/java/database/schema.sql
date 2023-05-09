BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, status_type, availability_type, size,
	crust, sauce, topping, specialty_pizza,
	specialty_pizza_topping,
	non_pizza_item_type, non_pizza_item, orders, pizza, pizza_topping, order_non_pizza_item CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username VARCHAR(50) NOT NULL UNIQUE,
	password_hash VARCHAR(200) NOT NULL,
	role VARCHAR(50) NOT NULL,
	CONSTRAINT pk_users PRIMARY KEY (user_id)
);

CREATE TABLE status_type (
	status_id SERIAL,
	status_desc VARCHAR(100) NOT NULL,
	CONSTRAINT pk_status_type PRIMARY KEY (status_id)
);

CREATE TABLE availability_type (
	availability_type_id SERIAL,
	availability_type_desc VARCHAR(1000) NOT NULL UNIQUE,
	CONSTRAINT pk_availability_type PRIMARY KEY (availability_type_id) 
);

CREATE TABLE size (
	size_id SERIAL,
	size_name VARCHAR(50) NOT NULL UNIQUE,
	additional_price NUMERIC(6,2) NOT NULL,
	CONSTRAINT pk_size PRIMARY KEY (size_id)
);

CREATE TABLE crust (
	crust_id SERIAL,
	crust_name VARCHAR(100) NOT NULL,
	additional_price NUMERIC(6,2) NOT NULL,
	availability INTEGER NOT NULL,
	CONSTRAINT pk_crust PRIMARY KEY (crust_id),
	CONSTRAINT fk_crust FOREIGN KEY (availability) REFERENCES availability_type (availability_type_id)
);

CREATE TABLE sauce (
	sauce_id SERIAL,
	sauce_name VARCHAR(100) NOT NULL UNIQUE,
	additional_price NUMERIC(6,2) NOT NULL,
	availability INTEGER NOT NULL,
	CONSTRAINT pk_sauce PRIMARY KEY (sauce_id),
	CONSTRAINT fk_sauce FOREIGN KEY (availability) REFERENCES availability_type (availability_type_id)
);

CREATE TABLE topping (
	topping_id SERIAL,
	topping_name VARCHAR(100) NOT NULL,
	is_premium BOOLEAN NOT NULL,
	additional_price NUMERIC(6,2) NOT NULL,
	availability INTEGER NOT NULL,
	CONSTRAINT pk_topping PRIMARY KEY (topping_id),
	CONSTRAINT fk_topping FOREIGN KEY (availability) REFERENCES availability_type (availability_type_id)
);

CREATE TABLE specialty_pizza (
	specialty_pizza_id SERIAL NOT NULL,
	specialty_pizza_name VARCHAR(100) NOT NULL UNIQUE,
	specialty_pizza_desc VARCHAR(1000) NOT NULL,
	crust_id INTEGER NOT NULL,
	sauce_id INTEGER NOT NULL,
	base_price NUMERIC(6,2) NOT NULL,
	is_active BOOLEAN NOT NULL,
	availability INTEGER NOT NULL,
	image_path VARCHAR(1000) NOT NULL,
	CONSTRAINT pk_specialty_pizza PRIMARY KEY (specialty_pizza_id),
	CONSTRAINT fk1_specialty_pizza FOREIGN KEY (crust_id) REFERENCES crust (crust_id),
	CONSTRAINT fk2_specialty_pizza FOREIGN KEY (sauce_id) REFERENCES sauce (sauce_id),
	CONSTRAINT fk3_specialty_pizza FOREIGN KEY (availability) REFERENCES availability_type (availability_type_id)
);

CREATE TABLE specialty_pizza_topping (
	specialty_pizza_id INTEGER NOT NULL,
	topping_id INTEGER NOT NULL,
	CONSTRAINT pks_specialty_pizza_topping PRIMARY KEY (specialty_pizza_id, topping_id),
	CONSTRAINT fk1_specialty_pizza_topping FOREIGN KEY (specialty_pizza_id) REFERENCES specialty_pizza (specialty_pizza_id),
	CONSTRAINT fk2_specialty_pizza_topping FOREIGN KEY (topping_id) REFERENCES topping (topping_id)
);

CREATE TABLE non_pizza_item_type (
	item_type_id SERIAL,
	item_type_name VARCHAR(100) NOT NULL,
	CONSTRAINT pk_non_pizza_item_type PRIMARY KEY (item_type_id)
);

CREATE TABLE non_pizza_item (
	item_id SERIAL,
	item_name VARCHAR(100) NOT NULL,
	item_type INTEGER NOT NULL,
	price NUMERIC(6,2) NOT NULL,
	availability INTEGER NOT NULL,
	image_path VARCHAR(1000) NOT NULL,
	CONSTRAINT pk_non_pizza_item PRIMARY KEY (item_id), 
	CONSTRAINT fk1_non_pizza_item FOREIGN KEY (item_type) REFERENCES non_pizza_item_type (item_type_id),
	CONSTRAINT fk2_non_pizza_item FOREIGN KEY (availability) REFERENCES availability_type (availability_type_id)
);

CREATE TABLE orders (
	order_id SERIAL,
	status_id INTEGER NOT NULL,
	total NUMERIC(6, 2),
	address VARCHAR(2048),
	email VARCHAR(100),
	card_info VARCHAR(2048),
	phone_number VARCHAR(100),
	CONSTRAINT pk_orders PRIMARY KEY (order_id),
	CONSTRAINT fk_orders FOREIGN KEY (status_id) REFERENCES status_type (status_id)
);

CREATE TABLE pizza (
	pizza_id SERIAL,
	order_id INTEGER NOT NULL,
	size_id INTEGER NOT NULL,
	crust_id INTEGER NOT NULL,
	sauce_id INTEGER NOT NULL,
	CONSTRAINT pk_pizza PRIMARY KEY (pizza_id),
	CONSTRAINT fk1_pizza FOREIGN KEY (order_id) REFERENCES orders (order_id),
	CONSTRAINT fk2_pizza FOREIGN KEY (size_id) REFERENCES size (size_id),
	CONSTRAINT fk3_pizza FOREIGN KEY (crust_id) REFERENCES crust (crust_id),
	CONSTRAINT fk4_pizza FOREIGN KEY (sauce_id) REFERENCES sauce (sauce_id)
);

CREATE TABLE pizza_topping (
	pizza_id INTEGER NOT NULL,
	topping_id INTEGER NOT NULL,
	CONSTRAINT pks_pizza_topping PRIMARY KEY (pizza_id, topping_id),
	CONSTRAINT fk1_pizza_topping FOREIGN KEY (pizza_id) REFERENCES pizza (pizza_id),
	CONSTRAINT fk2_pizza_topping FOREIGN KEY (topping_id) REFERENCES topping (topping_id)
);

CREATE TABLE order_non_pizza_item (
	order_id INTEGER NOT NULL,
	item_id INTEGER NOT NULL,
	CONSTRAINT pks_order_non_pizza_item PRIMARY KEY (order_id, item_id),
	CONSTRAINT fk1_order_non_pizza_item FOREIGN KEY (order_id) REFERENCES orders (order_id),
	CONSTRAINT fk2_order_non_pizza_item FOREIGN KEY (item_id) REFERENCES non_pizza_item (item_id)
);

COMMIT TRANSACTION;