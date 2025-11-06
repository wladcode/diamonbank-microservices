CREATE TABLE IF NOT EXISTS loans (
    loan_number bigint PRIMARY KEY,
    customer_id bigint NOT NULL,
    start_date timestamp NOT NULL,
    loan_type varchar(100) NOT NULL,
    total_loan int NOT NULL,
    amount_paid int NOT NULL,
    outstanding_amount int NOT NULL,
    create_date timestamp DEFAULT NULL
);

