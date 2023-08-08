CREATE TABLE IF NOT EXISTS foods (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    dish_type VARCHAR(50),
    energy INT NOT NULL,
    protein INT,
    fat INT,
    carbohydrate INT,
    fiber INT,
    nacl INT NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    birth_year INT(4) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role_id INT NOT NULL,
    enabled BOOLEAN NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);