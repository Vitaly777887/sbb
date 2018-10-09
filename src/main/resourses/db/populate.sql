INSERT INTO station (name) VALUES
  ('voronezh'),
  ('moscow');

INSERT INTO train (count_passengers, number_train) VALUES
  (76543, 54),
  (16555, 145);

INSERT INTO station_train (train_id, arrival_time, departure_time, station_id) VALUES
  (1, NULL, '2018-05-30 10:05:00', 1),
  (1, '2018-05-30 22:00:00', NULL, 2),
  (2, '2018-05-31 10:00:00', '2018-05-31 10:05:00', 1);

INSERT INTO passenger (birthday, name, surname) VALUES
  ('1997-05-01', 'Vasya', 'Abh'),
  ('1997-06-01', 'Vasya2', 'Abhj');

INSERT INTO ticket (passenger_id, train_id) VALUES
  (1, 1),
  (2, 1);
