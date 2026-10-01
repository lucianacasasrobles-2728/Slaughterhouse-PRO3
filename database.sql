CREATE TABLE animal (
                        registration_number INTEGER PRIMARY KEY,
                        weight DOUBLE PRECISION NOT NULL
);

CREATE TABLE product (
                         product_id INTEGER PRIMARY KEY,
                         name VARCHAR(100) NOT NULL
);

CREATE TABLE animal_product (
                                registration_number INTEGER,
                                product_id INTEGER,
                                PRIMARY KEY (registration_number, product_id),
                                FOREIGN KEY (registration_number)
                                    REFERENCES animal(registration_number),
                                FOREIGN KEY (product_id)
                                    REFERENCES product(product_id)
);

INSERT INTO animal VALUES
                       (1001, 450.5),
                       (1002, 380.0),
                       (1003, 410.2);

INSERT INTO product VALUES
                        (501, 'Beef Package'),
                        (502, 'Mixed Package');

INSERT INTO animal_product VALUES
                               (1001, 501),
                               (1002, 501),
                               (1001, 502),
                               (1003, 502);