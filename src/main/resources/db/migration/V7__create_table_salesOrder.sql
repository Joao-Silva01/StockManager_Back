CREATE TABLE Sales_Order(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    price_total Double NOT NULL,
    date_moment TIMESTAMP,
    status INTEGER,
    client_id INTEGER NOT NULL,
    CONSTRAINT fk_client_order FOREIGN KEY (client_id) REFERENCES Client(id)
);