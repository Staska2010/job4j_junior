CREATE TABLE bodywork (
    serial_number SERIAL PRIMARY KEY,
    type VARCHAR(80) NOT NULL
);

CREATE TABLE engine (
    serial_number SERIAL PRIMARY KEY,
    power INT NOT NULL,
    valves_number INT NOT NULL,
    type VARCHAR(80) NOT NULL
);

CREATE TABLE gearbox (
    serial_number SERIAL PRIMARY KEY,
    gears INT NOT NULL,
    type VARCHAR(80) NOT NULL
);

INSERT INTO bodywork (type) VALUES ('Sedan');
INSERT INTO bodywork (type) VALUES ('Pickup');
INSERT INTO bodywork (type) VALUES ('Wagon');

INSERT INTO engine (power, valves_number, type) VALUES (100, 16, 'R');
INSERT INTO engine (power, valves_number, type) VALUES (120, 16, 'V');
INSERT INTO engine (power, valves_number, type) VALUES (150, 32, 'O');

INSERT INTO gearbox (gears, type) VALUES (4,'MT');
INSERT INTO gearbox (gears, type) VALUES (4,'AT');
INSERT INTO gearbox (gears, type) VALUES (5,'AT');
INSERT INTO gearbox (gears, type) VALUES (5,'MT');

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    model VARCHAR(80),
    gearbox INT NOT NULL REFERENCES gearbox(serial_number),
    engine INT NOT NULL REFERENCES engine(serial_number),
    bodywork INT NOT NULL REFERENCES bodywork(serial_number)
);

INSERT INTO cars AS c (model, gearbox, engine, bodywork) VALUES ('Toyota', 1, 1, 1);
INSERT INTO cars AS c (model, gearbox, engine, bodywork) VALUES ('Mazda', 2, 1, 2);
INSERT INTO cars AS c (model, gearbox, engine, bodywork) VALUES ('VW', 3, 2, 1);
INSERT INTO cars AS c (model, gearbox, engine, bodywork) VALUES ('VW', 2, 1, 3);


