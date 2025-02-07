CREATE TABLE Address(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    street_name VARCHAR(100) NOT NULL,
    complement VARCHAR(150),
    neighborhood_name VARCHAR(100) NOT NULL,
    number INTEGER NOT NULL,
    cep VARCHAR(8) NOT NULL,
    client INTEGER NOT NULL,
    CONSTRAINT fk_clientIdAddress FOREIGN KEY (client) REFERENCES Client(id) ON DELETE CASCADE
);