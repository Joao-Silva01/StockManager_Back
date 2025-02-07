CREATE TABLE Sales_Order_Product(
    product_id INTEGER NOT NULL,
    sales_order_id INTEGER NOT NULL,
    quantity INTEGER,
    price Double,
    PRIMARY KEY (product_id, sales_order_id),
    CONSTRAINT fk_pr FOREIGN KEY (product_id) REFERENCES Product(id),
    CONSTRAINT fk_sop FOREIGN KEY (sales_order_id) REFERENCES Sales_Order(id) ON DELETE CASCADE
);