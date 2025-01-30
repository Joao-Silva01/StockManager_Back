CREATE TABLE Phone(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(11) NOT NULL,
    client_id INTEGER NOT NULL,
    CONSTRAINT fk_clientId FOREIGN KEY (client_id) REFERENCES Client(id)
);