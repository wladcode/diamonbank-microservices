INSERT INTO loans (loan_number, customer_id, start_date, loan_type, total_loan, amount_paid, outstanding_amount, create_date)
VALUES (1, 1, '2022-04-26', 'Home', 20000, 5000, 15000, '2022-04-26')
ON CONFLICT (loan_number) DO NOTHING;

INSERT INTO loans (loan_number, customer_id, start_date, loan_type, total_loan, amount_paid, outstanding_amount, create_date)
VALUES (2, 1, '2022-01-05', 'Vehicle', 40000, 5000, 35000, '2022-01-05')
ON CONFLICT (loan_number) DO NOTHING;

INSERT INTO loans (loan_number, customer_id, start_date, loan_type, total_loan, amount_paid, outstanding_amount, create_date)
VALUES (3, 1, '2021-04-26', 'Personal', 1000, 900, 100, '2021-04-26')
ON CONFLICT (loan_number) DO NOTHING;