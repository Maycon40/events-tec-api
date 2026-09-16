CREATE TABLE address (
  id PRIMARY KEY,
  uf TEXT NOT NULL,
  city TEXT NOT NULL,
  event_id FOREIGN KEY
);