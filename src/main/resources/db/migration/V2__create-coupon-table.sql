CREATE TABLE coupon (
  id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  discount INTEGER NOT NULL,
  code VARCHAR(100) NOT NULL,
  valid TIMESTAMP NOT NULL,
  event_id UUID,
  FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE
);