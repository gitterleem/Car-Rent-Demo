CREATE TABLE Manufacturer (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

INSERT INTO Manufacturer (name)
VALUES
    ('Acura'),
    ('Alfa Romeo'),
    ('Aston Martin'),
    ('Audi'),
    ('Bentley'),
    ('BMW'),
    ('Bugatti'),
    ('Cadillac'),
    ('Chevrolet'),
    ('Chrysler'),
    ('Citroen'),
    ('Cupra'),
    ('Dacia'),
    ('Dodge'),
    ('DS Automobiles'),
    ('Fiat'),
    ('Ford'),
    ('GMC'),
    ('Honda'),
    ('Hyundai'),
    ('Jaguar'),
    ('Kia'),
    ('Mazda'),
    ('Mercedes Benz'),
    ('Mitsubishi'),
    ('Nissan'),
    ('Peugeot'),
    ('Renault'),
    ('Saab'),
    ('Seat'),
    ('Skoda'),
    ('Toyota'),
    ('Volvo'),
    ('VW');



CREATE TABLE Car (
    id BIGSERIAL PRIMARY KEY,
    manufacturer_id BIGINT NOT NULL REFERENCES Manufacturer(id),
    model TEXT NOT NULL,
    transmission TEXT NOT NULL,
    fuel TEXT NOT NULL,
    seat_count SMALLINT NOT NULL,
    door_count SMALLINT NOT NULL
);

INSERT INTO Car (manufacturer_id, model, transmission, fuel, seat_count, door_count)
VALUES
    (27, '206 SW', 'MANUAL', 'DIESEL', 5, 5);

CREATE TABLE Address (
    id BIGSERIAL PRIMARY KEY,
    street TEXT NOT NULL,
    street_number TEXT NOT NULL,
    zip TEXT NOT NULL,
    city TEXT NOT NULL,
    country TEXT NOT NULL
);

CREATE TABLE Client (
    id BIGSERIAL PRIMARY KEY,
    title TEXT,
    suffix TEXT,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    date_of_birth DATE NOT NULL,
    email TEXT NOT NULL,
    address_id BIGINT REFERENCES Address (id),
    billing_address_id BIGINT REFERENCES Address (id)
);

INSERT INTO Client (first_name, last_name, date_of_birth, email)
VALUES ('Emanuel', 'Gitterle', '1997-03-15', 'emanuel.gitterle@outlook.at');

CREATE TABLE Rental (
    id BIGSERIAL PRIMARY KEY,
    car_id BIGINT NOT NULL REFERENCES Car (id),
    client_id BIGINT NOT NULL REFERENCES Client (id),
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP,
    start_kilometer NUMERIC(19,2) NOT NULL,
    end_kilometer NUMERIC(19,2),
    rate_per_day NUMERIC(19,2) NOT NULL,
    included_kilometer NUMERIC(19,2) NOT NULL,
    rate_per_kilometer NUMERIC(19,2) NOT NULL,
    start_address_id BIGINT REFERENCES Address (id),
    end_address_id BIGINT REFERENCES Address (id)
);