DROP SEQUENCE IF EXISTS railway_sequence;
DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS spec_route_point;
DROP TABLE IF EXISTS seat_state;
DROP TABLE IF EXISTS seats_state_at_point;
DROP TABLE IF EXISTS train_car;
DROP TABLE IF EXISTS train;
DROP TABLE IF EXISTS route_point;
DROP TABLE IF EXISTS passenger;
DROP TABLE IF EXISTS generic_train;
DROP TABLE IF EXISTS station;


CREATE TABLE IF NOT EXISTS station
(
    id bigint NOT NULL,
    name varchar(40),
    CONSTRAINT station_pkey PRIMARY KEY (id),
    CONSTRAINT uk_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS generic_train
(
    id bigint NOT NULL,
    "number" varchar(10) NOT NULL,
    route varchar(70) NOT NULL,
    num_of_coope_cars smallint NOT NULL,
    num_of_plazkart_cars smallint NOT NULL,
    num_of_seats_in_coope_car smallint NOT NULL,
    num_of_seats_in_plazkart_car smallint NOT NULL,
    num_of_seats_in_sw_car smallint NOT NULL,
    num_of_sw_cars smallint NOT NULL,
    -- schedule
    monday boolean NOT NULL,
    tuesday boolean NOT NULL,
    wednesday boolean NOT NULL,
    thursday boolean NOT NULL,
    friday boolean NOT NULL,
    saturday boolean NOT NULL,
    sunday boolean NOT NULL,
    week_periodicity smallint NOT NULL,

    CONSTRAINT generic_train_pkey PRIMARY KEY (id),
    CONSTRAINT uk_number UNIQUE ("number")
);

CREATE TABLE IF NOT EXISTS passenger
(
    id bigint NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    date_of_birth date NOT NULL,
    CONSTRAINT passenger_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS route_point
(
    id bigint NOT NULL,
    generic_train_id bigint NOT NULL,
    station_id bigint NOT NULL,
    arrival_time time without time zone,
    depart_time time without time zone,
    days_from_train_depart_to_arrival_here smallint,
    days_from_train_depart_to_depart_from_here smallint,
    order_of_route_point smallint NOT NULL,
    CONSTRAINT route_point_pkey PRIMARY KEY (id),

    CONSTRAINT fk_from_rp_to_gen_train FOREIGN KEY (generic_train_id)
        REFERENCES generic_train (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_from_rp_to_station FOREIGN KEY (station_id)
        REFERENCES station (id)
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS train
(
    id bigint NOT NULL,
    generic_train_id bigint NOT NULL,
    depart_date date NOT NULL,
    CONSTRAINT train_pkey PRIMARY KEY (id),
    CONSTRAINT fk_from_train_to_gen_train FOREIGN KEY (generic_train_id)
        REFERENCES generic_train (id)
);

CREATE TABLE IF NOT EXISTS train_car
(
    id bigint NOT NULL,
    train_id bigint NOT NULL,
    type varchar(8),
    order_of_car smallint NOT NULL,
    CONSTRAINT train_car_pkey PRIMARY KEY (id),
    CONSTRAINT fk_from_train_car_to_train FOREIGN KEY (train_id)
        REFERENCES train (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS seats_state_at_point
(
    id bigint NOT NULL,
    train_car_id bigint NOT NULL,
    order_of_station smallint NOT NULL ,
    CONSTRAINT seats_state_at_point_pkey PRIMARY KEY (id),
    CONSTRAINT fk_from_seats_state_at_point_to_train_car FOREIGN KEY (train_car_id)
        REFERENCES train_car (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS seat_state
(
    seats_state_at_point_id bigint NOT NULL,
    seat_state boolean,
    seat_number smallint NOT NULL,
    CONSTRAINT seat_state_pkey PRIMARY KEY (seats_state_at_point_id, seat_number),
    CONSTRAINT fk_from_seat_place_to_seats_state_at_point FOREIGN KEY (seats_state_at_point_id)
        REFERENCES seats_state_at_point (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS spec_route_point
(
    id bigint NOT NULL,
    train_id bigint NOT NULL,
    arrival_datetime timestamp without time zone,
    depart_datetime timestamp without time zone,
    route_point_id bigint NOT NULL,
    order_of_spec_route_point smallint NOT NULL ,
    CONSTRAINT spec_route_point_pkey PRIMARY KEY (id),
    CONSTRAINT fk_from_spec_rp_to_rp FOREIGN KEY (route_point_id)
        REFERENCES route_point (id)
        ON DELETE CASCADE ,
    CONSTRAINT fk_from_spec_rp_to_train FOREIGN KEY (train_id)
        REFERENCES train (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ticket
(
    id bigint NOT NULL,
    train_id bigint NOT NULL,
    passenger_id bigint NOT NULL,
    station_from_id bigint NOT NULL,
    station_to_id bigint NOT NULL,
    arrival_date_time timestamp without time zone NOT NULL,
    departure_date_time timestamp without time zone NOT NULL,
    number_of_seat smallint NOT NULL,
    number_of_train_car smallint NOT NULL,

    cost real,

    CONSTRAINT ticket_pkey PRIMARY KEY (id),
    CONSTRAINT fk_from_ticket_to_passenger FOREIGN KEY (passenger_id)
        REFERENCES passenger (id),
    CONSTRAINT fk_from_ticket_to_train FOREIGN KEY (train_id)
        REFERENCES train (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_from_ticket_to_station_from FOREIGN KEY (station_from_id)
        REFERENCES station (id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_from_ticket_to_station_to FOREIGN KEY (station_to_id)
        REFERENCES station (id)
        ON DELETE RESTRICT
);

CREATE SEQUENCE IF NOT EXISTS railway_sequence START 999;


