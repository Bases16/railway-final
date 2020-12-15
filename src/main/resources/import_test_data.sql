
-- ---------------- USERS --------------------
-- password is 'pass' for everybody
INSERT INTO users(email, role, status, passenger_id, password) VALUES
('admin@email.com', 'ADMIN', 'ACTIVE', null, '$2y$12$myGBsnhBGHaGG3akE4ttEuDVBed5iz73JfnMF2eL3kzHylLbBHwqq'),
('user@email.com', 'USER', 'ACTIVE', null, '$2y$12$myGBsnhBGHaGG3akE4ttEuDVBed5iz73JfnMF2eL3kzHylLbBHwqq'),
('banned@email.com', 'USER', 'BANNED', null, '$2y$12$myGBsnhBGHaGG3akE4ttEuDVBed5iz73JfnMF2eL3kzHylLbBHwqq');
-- ---------------- STATIONS --------------------
INSERT INTO stations(id, name) VALUES
(13, 'New-York'),
(14, 'Astana'),
(15, 'Myshkin'),
(16, 'Moscow'),
(17, 'Warsaw'),
(18, 'Liski'),
(19, 'Genoa'),
(20, 'Hong-Kong');

-- ---------------- PASSENGERS --------------------
INSERT INTO passengers(id, first_name, last_name, date_of_birth) VALUES
(1, 'f_name1', 'l_name1', '1990-12-01'),
(2, 'f_name2', 'l_name2', '1990-12-01'),
(3, 'f_name3', 'l_name3', '1990-12-01'),
(4, 'f_name4', 'l_name4', '1990-12-01'),
(5, 'f_name5', 'l_name5', '1990-12-01'),
(6, 'f_name6', 'l_name6', '1990-12-01'),
(7, 'f_name7', 'l_name7', '1990-12-01'),
(8, 'f_name8', 'l_name8', '1990-12-01');

-----------------------------------------------------------
-- GenericTrain 1   Moscow - Myshkin - Astana - Hong-Kong
-----------------------------------------------------------
INSERT INTO generic_trains(id, number, route, num_of_coope_cars, num_of_seats_in_coope_car, num_of_plazkart_cars,
num_of_seats_in_plazkart_car, num_of_sw_cars, num_of_seats_in_sw_car,
week_periodicity, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
VALUES (1, 'МШМ228', 'Moscow - Hong-Kong', 1, 2, 1, 3, 1, 1, 2, false , true , false , false , false , false , true);

INSERT INTO route_points(id, generic_train_id, station_id, order_of_route_point, arrival_time, days_from_train_depart_to_arrival_here,
                        depart_time, days_from_train_depart_to_depart_from_here) VALUES
(100, 1, 16, 0,   null , null, '15:30',  0   ),
(101, 1, 15, 1, '23:55',  0  , '02:00',  1   ),
(102, 1, 14, 2, '11:10',  2  , '11:58',  2   ),
(103, 1, 20, 3, '22:00',  4  ,  null  , null );

INSERT INTO trains(id, generic_train_id, depart_date) VALUES
(1, 1, '2025-12-02'), --  #1 train
(2, 1, '2025-12-07'); --  #2 train

INSERT INTO train_cars(id, train_id, order_of_car, type) VALUES
--  #1 train
(1, 1, 0, 'PLAZKART'),
(2, 1, 1, 'COOPE'),
(3, 1, 2, 'SW'),
--  #2 train
(4, 2, 0, 'PLAZKART'),
(5, 2, 1, 'COOPE'),
(6, 2, 2, 'SW');

INSERT INTO tickets(id, train_id, passenger_id, station_from_id, station_to_id, departure_date_time,
                                               arrival_date_time, number_of_train_car, number_of_seat) VALUES
-- #1 train  Moscow - Myshkin - Astana - Hong-Kong
       -- PLAZKART --
(1, 1, 1, 16, 20, '2025-12-02 15:30', '2025-12-06 22:00', 1, 1), -- Moscow - Hong-Kong
(2, 1, 2, 16, 14, '2025-12-02 15:30', '2025-12-04 11:10', 1, 2), -- Moscow - Astana
(3, 1, 3, 16, 15, '2025-12-02 15:30', '2025-12-02 23:55', 1, 3), -- Moscow - Myshkin
       --  COOPE   --
(4, 1, 4, 16, 15, '2025-12-02 15:30', '2025-12-02 23:55', 2, 1), -- Moscow - Myshkin
(5, 1, 5, 15, 20, '2025-12-03 02:00', '2025-12-06 22:00', 2, 1), -- Myshkin - Hong-Kong
       --    SW    --
(6, 1, 6, 15, 14, '2025-12-03 02:00', '2025-12-04 11:10', 3, 1); -- Myshkin - Astana


INSERT INTO seats_state_at_point(id, train_car_id, order_of_station) VALUES
--  #1 train
(1, 1, 0), (2, 1, 1), (3, 1, 2),    -- #1 traincar
(4, 2, 0), (5, 2, 1), (6, 2, 2),    -- #2 traincar
(7, 3, 0), (8, 3, 1), (9, 3, 2),    -- #3 traincar
--  #2 train
(10, 4, 0), (11, 4, 1), (12, 4, 2), -- #1 traincar
(13, 5, 0), (14, 5, 1), (15, 5, 2), -- #2 traincar
(16, 6, 0), (17, 6, 1), (18, 6, 2); -- #3 traincar

INSERT INTO seat_state(seats_state_at_point_id, seat_state, seat_number) VALUES
-- #1 TRAIN --
-- #1 traincar - plazkart
(1, true,  0), (1, true, 1), (1, true,  2),  -- Moscow
(2, true,  0), (2, true, 1), (2, false, 2),  -- Myshkin
(3, false, 0), (3, true, 1), (3, false, 2),  -- Astana
-- #2 traincar - coope
(4, true, 0), (4, false, 1),  -- Moscow
(5, true, 0), (5, false, 1),  -- Myshkin
(6, true, 0), (6, false,  1),  -- Astana
-- #3 traincar - sw
(7, false, 0),  -- Moscow
(8, true,  0),  -- Myshkin
(9, false, 0),  -- Astana

-- #2 TRAIN --
-- #1 traincar - plazkart
(10, false, 0), (10, false, 1), (10, false, 2),
(11, false, 0), (11, false, 1), (11, false, 2),
(12, false, 0), (12, false, 1), (12, false, 2),
-- #2 traincar - coope
(13, false, 0), (13, false, 1),
(14, false, 0), (14, false, 1),
(15, false, 0), (15, false, 1),
-- #3 traincar - sw
(16, false, 0),
(17, false, 0),
(18, false, 0);

INSERT INTO spec_route_points(id, train_id, route_point_id, order_of_spec_route_point, arrival_datetime, depart_datetime) VALUES
(1, 1, 100, 0,       null        , '2025-12-02 15:30'), -- Moscow
(2, 1, 101, 1, '2025-12-02 23:55', '2025-12-03 02:00'), -- Myshkin
(3, 1, 102, 2, '2025-12-04 11:10', '2025-12-04 11:58'), -- Astana
(4, 1, 103, 3, '2025-12-06 22:00',        null       ), -- Hong-Kong

(5, 2, 100, 0,       null        , '2025-12-07 15:30'), -- Moscow
(6, 2, 101, 1, '2025-12-07 23:55', '2025-12-08 02:00'), -- Myshkin
(7, 2, 102, 2, '2025-12-09 11:10', '2025-12-09 11:58'), -- Astana
(8, 2, 103, 3, '2025-12-11 22:00',        null       ); -- Hong-Kong
-- ===========================================================================

-- 0000000000000000000000000000000000000000000000000000000000000000000000000000000000000 --

-----------------------------------------------------------
-- GenericTrain 2    Liski - Myshkin - Warsaw - Genoa
-----------------------------------------------------------
INSERT INTO generic_trains(id, number, route, num_of_coope_cars, num_of_seats_in_coope_car, num_of_plazkart_cars,
num_of_seats_in_plazkart_car, num_of_sw_cars, num_of_seats_in_sw_car,
week_periodicity, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
VALUES (2, '1488HH', 'Liski - Genoa', 1, 2, 1, 3, 1, 1, 3, true, false , false, false, true, false, false);

INSERT INTO route_points(id, generic_train_id, station_id, order_of_route_point, arrival_time, days_from_train_depart_to_arrival_here,
                        depart_time, days_from_train_depart_to_depart_from_here) VALUES
(104, 2, 18, 0,   null , null, '19:30',  0   ),
(105, 2, 15, 1, '23:55',  0  , '01:00',  1   ),
(106, 2, 17, 2, '11:10',  2  , '11:30',  2   ),
(107, 2, 19, 3, '22:00',  3  ,  null  , null );

INSERT INTO trains(id, generic_train_id, depart_date) VALUES
(3, 2, '2025-12-01'),
(4, 2, '2025-12-05');

INSERT INTO train_cars(id, train_id, order_of_car, type) VALUES
(7, 3, 0, 'PLAZKART'),
(8, 3, 1, 'COOPE'),
(9, 3, 2, 'SW'),

(10, 4, 0, 'PLAZKART'),
(11, 4, 1, 'COOPE'),
(12, 4, 2, 'SW');

INSERT INTO seats_state_at_point(id, train_car_id, order_of_station) VALUES
--  #1 train
(19, 7, 0), (20, 7, 1), (21, 7, 2),    -- #1 traincar
(22, 8, 0), (23, 8, 1), (24, 8, 2),    -- #2 traincar
(25, 9, 0), (26, 9, 1), (27, 9, 2),    -- #3 traincar
--  #2 train
(28, 10, 0), (29, 10, 1), (30, 10, 2), -- #1 traincar
(31, 11, 0), (32, 11, 1), (33, 11, 2), -- #2 traincar
(34, 12, 0), (35, 12, 1), (36, 12, 2); -- #3 traincar

INSERT INTO seat_state(seats_state_at_point_id, seat_state, seat_number) VALUES
-- #1 TRAIN --
-- #1 traincar - plazkart
(19, false, 0), (19, false, 1), (19, false, 2),
(20, false, 0), (20, false, 1), (20, false, 2),
(21, false, 0), (21, false, 1), (21, false, 2),
-- #2 traincar - coope
(22, false, 0), (22, false, 1),
(23, false, 0), (23, false, 1),
(24, false, 0), (24, false, 1),
-- #3 traincar - sw
(25, false, 0),
(26, false, 0),
(27, false, 0),
-- #2 TRAIN --
-- #1 traincar - plazkart
(28, false, 0), (28, false, 1), (28, false, 2),
(29, false, 0), (29, false, 1), (29, false, 2),
(30, false, 0), (30, false, 1), (30, false, 2),
-- #2 traincar - coope
(31, false, 0), (31, false, 1),
(32, false, 0), (32, false, 1),
(33, false, 0), (33, false, 1),
-- #3 traincar - sw
(34, false, 0),
(35, false, 0),
(36, false, 0);


INSERT INTO spec_route_points(id, train_id, route_point_id, order_of_spec_route_point, arrival_datetime, depart_datetime) VALUES
(9,  3, 104, 0,       null        , '2025-12-01 19:30'),
(10, 3, 105, 1, '2025-12-01 23:55', '2025-12-02 01:00'),
(11, 3, 106, 2, '2025-12-03 11:10', '2025-12-03 11:30'),
(12, 3, 107, 3, '2025-12-04 22:00',        null       ),

(13, 4, 104, 0,       null        , '2025-12-05 19:30'),
(14, 4, 105, 1, '2025-12-05 23:55', '2025-12-06 01:00'),
(15, 4, 106, 2, '2025-12-07 11:10', '2025-12-07 11:30'),
(16, 4, 107, 3, '2025-12-08 22:00',        null       );
-- =====================================================================================