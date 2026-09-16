CREATE TABLE event (
  id PRIMARY KEY,
  title TEXT NOT NULL,
  description TEXT,
  img_url TEXT,
  event_url TEXT NOT NULL,
  remote BOOLEAN NOT NULL,
  date DATETIME NOT NULL
);