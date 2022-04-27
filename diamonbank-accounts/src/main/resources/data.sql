INSERT  INTO customers (name, email, mobile_number, created_date)
VALUES('Wladimir Lopez', 'wladdylopez@hotmail.com', '0968407668', CURRENT_DATE);

INSERT INTO accounts(customer_id, account_number, account_type, branch_address, created_date )
VALUES(1, 1716568256, 'SAVINGS', 'JUAN CAMACARO, QUITO', CURRENT_DATE);
