DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS accounts;

CREATE TABLE customers (
    customer_id numeric AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    mobile_number varchar(100) NOT NULL,
    created_date date DEFAULT NULL
);


CREATE TABLE accounts (
    account_id numeric AUTO_INCREMENT PRIMARY KEY,
    account_number varchar(20) NOT NULL,
    account_type varchar(100) NOT NULL,
    branch_address varchar(200) NOT NULL,
    created_date date DEFAULT NULL,
    customer_id int NOT NULL
);