CREATE TABLE Client(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    document VARCHAR(14) UNIQUE,
    email VARCHAR(100),
    password VARCHAR(100) ,
    register_Moment TIMESTAMP,
    type INTEGER
);