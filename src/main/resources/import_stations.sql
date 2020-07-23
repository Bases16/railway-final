
-- ---------------- STATIONS --------------------
INSERT INTO station(id, name) VALUES
(13, 'New-York'),
(14, 'Astana'),
(15, 'Myshkin'),
(16, 'Moscow'),
(17, 'Warsaw'),
(18, 'Liski'),
(19, 'Genoa'),
(20, 'Hong-Kong');
-------------------------------------------------

-----------------------------------------------------------
-- GenericTrain 1   Moscow - Myshkin - Astana - Hong-Kong
-----------------------------------------------------------
INSERT INTO generic_train(id, number, route, num_of_coope_cars, num_of_seats_in_coope_car, num_of_plazkart_cars,
num_of_seats_in_plazkart_car, num_of_sw_cars, num_of_seats_in_sw_car,
week_periodicity, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
VALUES (1, 'МШМ228', 'route', 1, 2, 1, 3, 1, 1, 2, false , true , false , false , false , false , true);

INSERT INTO route_point(id, generic_train_id, station_id, order_of_station, arrival_time, days_from_train_depart_to_arrival_here,
                        depart_time, days_from_train_depart_to_depart_from_here) VALUES
(100, 1, 16, 1,   null , null, '15:30',  0   ),
(101, 1, 15, 2, '23:55',  0  , '02:00',  1   ),
(102, 1, 14, 3, '11:10',  2  , '11:58',  2   ),
(103, 1, 20, 4, '22:00',  4  ,  null  , null );

INSERT INTO train(id, depart_date) VALUES
(1, '2020-12-01'),
(2, '2020-12-06');

INSERT INTO train_car(id, train_id, order_of_car, type) VALUES
(1, 1, 1, 'PLAZKART'),
(2, 1, 2, 'COOPE'),
(3, 1, 3, 'SW'),

(4, 2, 1, 'PLAZKART'),
(5, 2, 2, 'COOPE'),
(6, 2, 3, 'SW');

INSERT INTO train_car_seats(train_car_id, seat_number, is_reserved) VALUES
(1, 1, false), (1, 2, false), (1, 3, false),
(2, 1, false), (2, 2, false),
(3, 1, false),

(4, 1, false), (4, 2, false), (4, 3, false),
(5, 1, false), (5, 2, false),
(6, 1, false);

INSERT INTO spec_route_point(id, train_id, route_point_id, tickets_left, arrival_datetime, depart_datetime) VALUES
(1, 1, 100, 6,       null        , '2020-12-01 15:30'),
(2, 1, 101, 6, '2020-12-01 23:55', '2020-12-02 02:00'),
(3, 1, 102, 6, '2020-12-03 11:10', '2020-12-03 11:58'),
(4, 1, 103, 6, '2020-12-05 22:00',        null       ),

(5, 2, 100, 6,       null        , '2020-12-06 15:30'),
(6, 2, 101, 6, '2020-12-06 23:55', '2020-12-07 02:00'),
(7, 2, 102, 6, '2020-12-08 11:10', '2020-12-08 11:58'),
(8, 2, 103, 6, '2020-12-10 22:00',        null       );
-- ===========================================================================

-- 0000000000000000000000000000000000000000000000000000000000000000000000000000000000000 --

-----------------------------------------------------------
-- GenericTrain 2    Liski - Myshkin - Warsaw - Genoa
-----------------------------------------------------------
INSERT INTO generic_train(id, number, route, num_of_coope_cars, num_of_seats_in_coope_car, num_of_plazkart_cars,
num_of_seats_in_plazkart_car, num_of_sw_cars, num_of_seats_in_sw_car,
week_periodicity, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
VALUES (2, '1488НH', 'route', 1, 2, 1, 3, 1, 1, 3, true, false , false, false, true, false, false);

INSERT INTO route_point(id, generic_train_id, station_id, order_of_station, arrival_time, days_from_train_depart_to_arrival_here,
                        depart_time, days_from_train_depart_to_depart_from_here) VALUES
(104, 2, 18, 1,   null , null, '19:30',  0   ),
(105, 2, 15, 2, '23:55',  0  , '01:00',  1   ),
(106, 2, 17, 3, '11:10',  2  , '11:30',  2   ),
(107, 2, 19, 4, '22:00',  3  ,  null  , null );

INSERT INTO train(id, depart_date) VALUES
(3, '2020-11-30'),
(4, '2020-12-04');

INSERT INTO train_car(id, train_id, order_of_car, type) VALUES
(7, 3, 1, 'PLAZKART'),
(8, 3, 2, 'COOPE'),
(9, 3, 3, 'SW'),

(10, 4, 1, 'PLAZKART'),
(11, 4, 2, 'COOPE'),
(12, 4, 3, 'SW');

INSERT INTO train_car_seats(train_car_id, seat_number, is_reserved) VALUES
(7, 1, false), (7, 2, false), (7, 3, false),
(8, 1, false), (8, 2, false),
(9, 1, false),

(10, 1, false), (10, 2, false), (10, 3, false),
(11, 1, false), (11, 2, false),
(12, 1, false);

INSERT INTO spec_route_point(id, train_id, route_point_id, tickets_left, arrival_datetime, depart_datetime) VALUES
(9,  3, 104, 6,       null        , '2020-11-30 19:30'),
(10, 3, 105, 6, '2020-11-30 23:55', '2020-12-01 01:00'),
(11, 3, 106, 6, '2020-12-02 11:10', '2020-12-02 11:30'),
(12, 3, 107, 6, '2020-12-03 22:00',        null       ),

(13, 4, 104, 6,       null        , '2020-12-04 19:30'),
(14, 4, 105, 6, '2020-12-04 23:55', '2020-12-05 01:00'),
(15, 4, 106, 6, '2020-12-06 11:10', '2020-12-06 11:30'),
(16, 4, 107, 6, '2020-12-07 22:00',        null       );
-- =====================================================================================