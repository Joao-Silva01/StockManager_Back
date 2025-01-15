CREATE TABLE Product(
    id INTEGER auto_increment primary key,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100),
    price DECIMAL(10,2) NOT NULL,
    Category INTEGER NOT NULL
);