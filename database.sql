
-- PRO3 Slaughterhouse - Database
-- Parts 2, 3 and 4

-- STATION 1: Animal Registration

CREATE TABLE IF NOT EXISTS animal (
                                      registration_number INTEGER PRIMARY KEY,
                                      weight DOUBLE PRECISION NOT NULL CHECK (weight > 0),
                                      date DATE NOT NULL,
                                      origin VARCHAR(100) NOT NULL
);

-- STATION 2: Cutting

CREATE TABLE IF NOT EXISTS animal_part (
                                           part_id SERIAL PRIMARY KEY,
                                           type VARCHAR(100) NOT NULL,
                                           weight DOUBLE PRECISION NOT NULL CHECK (weight > 0),
                                           animal_registration_number INTEGER NOT NULL,
                                           FOREIGN KEY (animal_registration_number)
                                               REFERENCES animal(registration_number)
);

CREATE TABLE IF NOT EXISTS tray (
                                    tray_id SERIAL PRIMARY KEY,
                                    part_type VARCHAR(100) NOT NULL,
                                    max_weight DOUBLE PRECISION NOT NULL CHECK (max_weight > 0)
);

CREATE TABLE IF NOT EXISTS tray_part (
                                         tray_id INTEGER NOT NULL,
                                         part_id INTEGER NOT NULL UNIQUE,
                                         PRIMARY KEY (tray_id, part_id),
                                         FOREIGN KEY (tray_id)
                                             REFERENCES tray(tray_id),
                                         FOREIGN KEY (part_id)
                                             REFERENCES animal_part(part_id)
);

-- STATION 3: Packaging

CREATE TABLE IF NOT EXISTS product (
                                       product_id INTEGER PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS product_tray (
                                            product_id INTEGER NOT NULL,
                                            tray_id INTEGER NOT NULL,
                                            PRIMARY KEY (product_id, tray_id),
                                            FOREIGN KEY (product_id)
                                                REFERENCES product(product_id),
                                            FOREIGN KEY (tray_id)
                                                REFERENCES tray(tray_id)
);

-- Legacy relationship from Part 2

CREATE TABLE IF NOT EXISTS animal_product (
                                              registration_number INTEGER NOT NULL,
                                              product_id INTEGER NOT NULL,
                                              PRIMARY KEY (registration_number, product_id),
                                              FOREIGN KEY (registration_number)
                                                  REFERENCES animal(registration_number),
                                              FOREIGN KEY (product_id)
                                                  REFERENCES product(product_id)
);

-- Example animals
-- Safe to execute more than once

INSERT INTO animal
(registration_number, weight, date, origin)
VALUES
    (1001, 450.5, '2026-01-10', 'Farm A'),
    (1002, 380.0, '2026-01-10', 'Farm B'),
    (1003, 410.2, '2026-01-11', 'Farm C')
ON CONFLICT (registration_number) DO NOTHING;
