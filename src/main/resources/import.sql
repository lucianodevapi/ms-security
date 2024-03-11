
-- create users
INSERT INTO "users" (username, name, password, role) VALUES ('ana', 'ana luiza', '$2a$10$7tzQ3oOlNcVGZBpSvdVam.Pn/Z8c71yvc8zGkt5ErbrcIWmsYQNiK', 'CUSTOMER');
INSERT INTO "users" (username, name, password, role) VALUES ('patricia', 'patricia souza', '$2a$10$7tzQ3oOlNcVGZBpSvdVam.Pn/Z8c71yvc8zGkt5ErbrcIWmsYQNiK', 'ASSISTANT_ADMINISTRATOR');
INSERT INTO "users" (username, name, password, role) VALUES ('luciano', 'luciano de paula', '$2a$10$7tzQ3oOlNcVGZBpSvdVam.Pn/Z8c71yvc8zGkt5ErbrcIWmsYQNiK', 'ADMINISTRATOR');


-- create category
INSERT INTO category (name, status) VALUES ('Electrónica', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Ropa', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Deportes', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Hogar', 'ENABLED');

-- create products
INSERT INTO product (name, price, status, category_id) VALUES ('Smartphone', 500.00, 'ENABLED', 1);
INSERT INTO product (name, price, status, category_id) VALUES ('Auriculares Bluetooth', 50.00, 'DISABLED', 1);
INSERT INTO product (name, price, status, category_id) VALUES ('Tablet', 300.00, 'ENABLED', 1);

INSERT INTO product (name, price, status, category_id) VALUES ('Camiseta', 25.00, 'ENABLED', 2);
INSERT INTO product (name, price, status, category_id) VALUES ('Pantalones', 35.00, 'ENABLED', 2);
INSERT INTO product (name, price, status, category_id) VALUES ('Zapatos', 45.00, 'ENABLED', 2);

INSERT INTO product (name, price, status, category_id) VALUES ('Balón de Fútbol', 20.00, 'ENABLED', 3);
INSERT INTO product (name, price, status, category_id) VALUES ('Raqueta de Tenis', 80.00, 'DISABLED', 3);

INSERT INTO product (name, price, status, category_id) VALUES ('Aspiradora', 120.00, 'ENABLED', 4);
INSERT INTO product (name, price, status, category_id) VALUES ('Licuadora', 50.00, 'ENABLED', 4);
