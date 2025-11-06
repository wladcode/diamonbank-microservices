INSERT INTO cards (card_id, card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_date)
 VALUES (1, '4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURRENT_DATE)
 ON CONFLICT (card_id) DO NOTHING;

INSERT INTO cards (card_id, card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_date)
 VALUES (2, '3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURRENT_DATE)
 ON CONFLICT (card_id) DO NOTHING;
 
INSERT INTO cards (card_id, card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_date)
 VALUES (3, '2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURRENT_DATE)
 ON CONFLICT (card_id) DO NOTHING;