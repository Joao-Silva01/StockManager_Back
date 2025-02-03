CREATE TABLE Product(
    id INTEGER auto_increment primary key,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100),
    price DOUBLE NOT NULL,
    category_id INTEGER NOT NULL,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES Category(code)
);