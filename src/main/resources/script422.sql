CREATE TABLE cars(
    id INTEGER PRIMARY KEY NOT NULL ,
    brand VARCHAR(255),
    model VARCHAR(255),
    cost INTEGER
);

CREATE TABLE driver(
    id INTEGER PRIMARY KEY  NOT NULL,
    name VARCHAR(255),
    age  INTEGER,
    has_license BOOLEAN DEFAULT FALSE,
    car_id INTEGER REFERENCES cars(id)
)