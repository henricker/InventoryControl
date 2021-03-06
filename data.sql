CREATE TABLE provider (
	id SERIAL PRIMARY KEY,
  	name VARCHAR(60) NOT NULL,
  	cnpj VARCHAR(14) NOT NULL
);

CREATE TABLE category (
	id SERIAL PRIMARY KEY,
  	name VARCHAR(60) NOT NULL
);

CREATE TABLE product (
	id SERIAL PRIMARY KEY,
  	name VARCHAR(60) NOT NULL,
  	category_id INTEGER REFERENCES category (id) ON UPDATE CASCADE ON DELETE SET NULL,
  	provider_id INTEGER REFERENCES provider (id) ON UPDATE CASCADE ON DELETE SET NULL,
  	inventory_min INTEGER NOT NULL,
  	quantity INTEGER DEFAULT 0,
  	cost_price DECIMAL NOT NULL,
  	sale_price DECIMAL NOT NULL,
  	description TEXT NOT NULL
);

CREATE TABLE movement (
  id SERIAL PRIMARY KEY,
  product_id INTEGER REFERENCES product (id) ON UPDATE CASCADE ON DELETE SET NULL,
  quantity_movement INTEGER NOT NULL,
  type varchar(3) NOT NULL,
  price DECIMAL,
  date_movement TIMESTAMP DEFAULT NOW()
);

CREATE TABLE log_movement (
	id SERIAL PRIMARY KEY,
  	description TEXT NOT NULL,
  	date_log TIMESTAMP DEFAULT NOW()
);

-- This trigger update quantity in products table
CREATE OR REPLACE FUNCTION updateQuantityProductsAndPriceMoviment()
RETURNS TRIGGER AS $$
DECLARE
	prod RECORD;
BEGIN
	SELECT quantity, cost_price, sale_price INTO prod FROM product WHERE id = NEW.product_id;
	IF (NEW.type = 'IN') THEN
		UPDATE product
        SET quantity = quantity + NEW.quantity_movement
        WHERE id = NEW.product_id;
        
        NEW.price = prod.cost_price * NEW.quantity_movement;
    ELSIF(NEW.type = 'OUT') THEN
    	IF (prod.quantity < NEW.quantity_movement) THEN
        	RAISE 'Quantity of movement less than the quantity of the product!';
        END IF;
    	UPDATE product
        SET quantity = quantity - NEW.quantity_movement
        WHERE id = NEW.product_id;
        NEW.price = prod.sale_price * NEW.quantity_movement;
    ELSE
    	RAISE 'Invalid type value, only "IN" and "OUT" values';
    END IF;
	RETURN NEW;
END;
$$
LANGUAGE PLPGSQL;

-- Log movement
CREATE OR REPLACE FUNCTION logMoviment()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO log_movement(description) VALUES ('Moviment inserted { moviment_id: ' || NEW.id || ', price: ' || NEW.price || ', type: ' || NEW.type || ' }');
	RETURN NEW;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER log_moviment_trigger
AFTER INSERT on movement
FOR EACH ROW
EXECUTE PROCEDURE logMoviment();

CREATE TRIGGER update_quantity_products_and_price_moviment_trigger
BEFORE INSERT on movement
FOR EACH ROW
EXECUTE PROCEDURE updateQuantityProductsAndPriceMoviment();

--Category inserts
INSERT INTO category(name) 
VALUES 
	('perec??veis'),
	('eletrodom??sticos'),
	('m??veis'),
	('celulares');

--Provider inserts
INSERT INTO provider (name, cnpj) 
VALUES 
	('PhoneBusiness', '11111111111111'),
    ('FoodBusiness', '22222222222222'),
    ('HomeAppliancesBusiness', '33333333333333'),
    ('FurnitureBusiness', '44444444444444');

--Product inserts
INSERT INTO product (name, description, category_id, provider_id, inventory_min, cost_price, sale_price)
VALUES 
	('Motog', '2gb RAM 64GB', 4, 1, 20, 300.00, 1200.00),
    ('Arroz Fazenda', '1 kg', 1, 2, 500, 2.87, 6.78)
