CREATE TABLE Sales_Order(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    price_total Double,
    date_moment TIMESTAMP,
    status INTEGER,
    client_id INTEGER NOT NULL,
    delivery_address INTEGER NOT NULL,
    phone INTEGER NOT NULL,
    CONSTRAINT fk_client_order FOREIGN KEY (client_id) REFERENCES Client(id),
    CONSTRAINT fk_delivery_address FOREIGN KEY (delivery_address) REFERENCES Address(id),
    CONSTRAINT fk_order_phone FOREIGN KEY (phone) REFERENCES Phone(id)
);