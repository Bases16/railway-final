-- STATIONS
INSERT INTO station(id, name) VALUES
(13, 'New-York'),
(14, 'Astana'),
(15, 'Myshkin'),
(16, 'Moscow'),
(17, 'Warsaw'),
(18, 'Liski'),
(19, 'Genoa'),
(20, 'Hong-Kong');


-- GenericTrain 1   Moscow - Myshkin - Astana - Hong-Kong
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




-- GenericTrain 2    Liski - Genoa
INSERT INTO generic_train(id, number, route, num_of_coope_cars, num_of_seats_in_coope_car, num_of_plazkart_cars,
num_of_seats_in_plazkart_car, num_of_sw_cars, num_of_seats_in_sw_car,
week_periodicity, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
VALUES (2, '1488Н', 'route', 1, 2, 1, 3, 1, 1, 3, false, false , false, false, true, false, false);

INSERT INTO route_point(id, generic_train_id, station_id, order_of_station, arrival_time, days_from_train_depart_to_arrival_here,
                        depart_time, days_from_train_depart_to_depart_from_here) VALUES
(100, 1, 16, 1,   null , null, '15:30',  0   ),
(101, 1, 15, 2, '23:55',  0  , '02:00',  1   ),
(102, 1, 14, 3, '11:10',  2  , '11:58',  2   ),
(103, 1, 20, 4, '22:00',  4  ,  null  , null );

