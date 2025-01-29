CREATE TABLE Client(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    cpf_Or_Cnpj VARCHAR(14),
    email VARCHAR(100),
    register_Moment TIMESTAMP,
    type INTEGER
);