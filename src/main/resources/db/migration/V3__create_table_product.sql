CREATE TABLE Product(
    id INTEGER auto_increment primary key,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100),
    price DECIMAL(10,2) NOT NULL,
    category_id INTEGER ,
    CONSTRAINT fk_category FOREIGN KEY (Category_id) REFERENCES Category(code)
);