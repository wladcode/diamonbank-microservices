CREATE TABLE IF NOT EXISTS cards (
  card_id bigint NOT NULL,
  card_number varchar(100) NOT NULL,
  customer_id bigint NOT NULL,
  card_type varchar(100) NOT NULL,
  total_limit int NOT NULL,
  amount_used int NOT NULL,
  available_amount int NOT NULL,
  create_date date DEFAULT NULL,
  PRIMARY KEY (card_id)
);
