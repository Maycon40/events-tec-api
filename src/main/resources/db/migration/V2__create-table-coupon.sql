CREATE TABLE coupon (
  id PRIMARY KEY,
  discount INTEGER NOT NULL,
  code TEXT NOT NULL,
  valid DATETIME NOT NULL,
  event_id FOREIGN KEY
);