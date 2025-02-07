CREATE TABLE Sales_Order(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    price_total Double,
    date_moment TIMESTAMP,
    status INTEGER,
    client INTEGER ,
    delivery_address INTEGER ,
    phone INTEGER ,
    CONSTRAINT fk_client_order FOREIGN KEY (client) REFERENCES Client(id) ,
    CONSTRAINT fk_delivery_address FOREIGN KEY (delivery_address) REFERENCES Address(id) ,
    CONSTRAINT fk_order_phone FOREIGN KEY (phone) REFERENCES Phone(id)
);