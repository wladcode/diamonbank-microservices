CREATE TABLE IF NOT EXISTS customers (
    customer_id numeric PRIMARY KEY,
    name varchar(100) NOT NULL,
    email varchar(50) NOT NULL,
    mobile_number varchar(15) NULL,
    created_date DATE DEFAULT CURRENT_DATE
);


CREATE TABLE IF NOT EXISTS accounts (
    account_id numeric PRIMARY KEY,
    account_number varchar(20) NOT NULL,
    account_type varchar(100) NOT NULL,
    branch_address varchar(200) NOT NULL,
    created_date timestamp DEFAULT NULL,
    customer_id int NOT NULL
);