DROP TABLE IF EXISTS ticket;

DROP TABLE IF EXISTS passenger;

DROP TABLE IF EXISTS station_train;

DROP TABLE IF EXISTS station;

DROP TABLE IF EXISTS train;

CREATE TABLE passenger (
  id       INTEGER      NOT NULL AUTO_INCREMENT,
  birthday TINYBLOB     NOT NULL,
  name     VARCHAR(255) NOT NULL,
  surname  VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (name, surname)
);

CREATE TABLE station (
  id   INTEGER      NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE station_train (
  station_id     INTEGER NOT NULL,
  arrival_time   TINYBLOB,
  departure_time TINYBLOB,
  train_id       INTEGER NOT NULL,
  PRIMARY KEY (train_id, station_id)
);

CREATE TABLE ticket (
  id           INTEGER NOT NULL AUTO_INCREMENT,
  passenger_id INTEGER NOT NULL,
  train_id     INTEGER NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (train_id, passenger_id)
);

CREATE TABLE train (
  id               INTEGER NOT NULL AUTO_INCREMENT,
  count_passengers INTEGER NOT NULL,
  number_train     INTEGER NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (number_train)
);

ALTER TABLE station_train
  ADD INDEX FKFB9F9F3DD8308207 (station_id),
  ADD CONSTRAINT FKFB9F9F3DD8308207
FOREIGN KEY (station_id)
REFERENCES station (id);

ALTER TABLE station_train
  ADD INDEX FKFB9F9F3D84D1B0C7 (train_id),
  ADD CONSTRAINT FKFB9F9F3D84D1B0C7
FOREIGN KEY (train_id)
REFERENCES train (id);

ALTER TABLE ticket
  ADD INDEX FKCBE86B0C66AA0107 (passenger_id),
  ADD CONSTRAINT FKCBE86B0C66AA0107
FOREIGN KEY (passenger_id)
REFERENCES passenger (id);

ALTER TABLE ticket
  ADD INDEX FKCBE86B0C84D1B0C7 (train_id),
  ADD CONSTRAINT FKCBE86B0C84D1B0C7
FOREIGN KEY (train_id)
REFERENCES train (id);
