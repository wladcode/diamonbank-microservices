DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS accounts;

CREATE TABLE customers (
    customer_id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    mobile_number varchar(100) NOT NULL,
    created_date date DEFAULT NULL
);


CREATE TABLE accounts (
    account_number int AUTO_INCREMENT PRIMARY KEY,
    customer_id int NOT NULL,
    account_type varchar(100) NOT NULL,
    branch_address varchar(200) NOT NULL,
    created_date date DEFAULT NULL
);