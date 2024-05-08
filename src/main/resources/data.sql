INSERT INTO `user` (`birth`, `user_id`, `password`, `phone`, `user_name`, `user_role`, `email`)
VALUES
('1990-01-01', 'john_doe', 'password123', '010-1234-5678', 'John Doe', 'ADMIN', 'john@example.com'),
('1985-05-15', 'jame_amith', 'abc123', '010-9876-5432', 'Jane Smith', 'USER', 'qwer@example.com'),
('1985-03-15', 'sane_gsih', 'abc12314', '010-5556-5432', 'Sane Gsih', 'USER', 'asdf@example.com'),
('1985-04-15', 'gane_vmith', 'abc1231234', '010-5555-5432', 'Gane Smizh', 'USER', 'zxcv@example.com'),
('1978-12-30', 'bob_johnson', 'bobpass', NULL, 'Bob Johnson', 'USER', 'bob@example.com');
INSERT INTO movies (good_point_avg, movie_date, movie_rate, movie_title)
VALUES
(4.5, '2024-05-01 12:00:00', 'PG-13', 'The Avengers'),
(3.8, '2024-05-02 13:00:00', 'PG', 'Toy Story'),
(4.2, '2024-05-03 14:00:00', 'PG-13', 'Jurassic Park');

insert into location (location_name) values ('서울');
insert into location (location_name) values ('일산');
insert into location (location_name) values ('용인');
insert into location (location_name) values ('대전');
insert into location (location_name) values ('부산');
insert into location (location_name) values ('광주');
insert into location (location_name) values ('춘천');

insert into seat (seat_row_no, seat_column_no) values (1, 1);
insert into seat (seat_row_no, seat_column_no) values (1, 2);
insert into seat (seat_row_no, seat_column_no) values (1, 3);
insert into seat (seat_row_no, seat_column_no) values (2, 1);
insert into seat (seat_row_no, seat_column_no) values (3, 1);
insert into seat (seat_row_no, seat_column_no) values (4, 4);
insert into seat (seat_row_no, seat_column_no) values (6, 6);
insert into seat (seat_row_no, seat_column_no) values (4, 3);
insert into seat (seat_row_no, seat_column_no) values (3, 4);


insert into ticket (movie_no, user_no, seat_id, location_no, book_date) values (1, 1, 1, 1, '2024-01-01');
insert into ticket (movie_no, user_no, seat_id, location_no, book_date) values (1, 2, 2, 1, '2024-01-01');
insert into ticket (movie_no, user_no, seat_id, location_no, book_date) values (1, 3, 3, 1, '2024-01-01');
insert into ticket (movie_no, user_no, seat_id, location_no, book_date) values (1, 4, 4, 1, '2024-01-01');
insert into ticket (movie_no, user_no, seat_id, location_no, book_date) values (1, 5, 5, 1, '2024-01-01');


