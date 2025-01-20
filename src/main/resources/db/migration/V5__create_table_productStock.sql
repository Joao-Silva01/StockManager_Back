CREATE TABLE Product_Stock (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    product_id INT UNIQUE,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES Product(id)
);