CREATE TABLE Product(
    id INTEGER auto_increment primary key,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100),
    price DOUBLE NOT NULL,
    codCategory INTEGER NOT NULL
);