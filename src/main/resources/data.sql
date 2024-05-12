INSERT INTO `user` (`birth`, `user_id`, `password`, `phone`, `user_name`, `user_role`, `email`)
VALUES
('1990-01-01', 'john_doe', 'password123', '010-1234-5678', 'John Doe', 'ADMIN', 'john@example.com'),
('1985-05-15', 'jame_amith', 'abc123', '010-9876-5432', 'Jane Smith', 'USER', 'qwer@example.com'),
('1985-03-15', 'sane_gsih', 'abc12314', '010-5556-5432', 'Sane Gsih', 'USER', 'asdf@example.com'),
('1985-04-15', 'gane_vmith', 'abc1231234', '010-5555-5432', 'Gane Smizh', 'USER', 'zxcv@example.com'),
('1978-12-30', 'bob_johnson', 'bobpass', NULL, 'Bob Johnson', 'USER', 'bob@example.com');
INSERT INTO movies (good_point_avg, movie_date, movie_rate, movie_title)
VALUES
(4.5, '20240501', 'PG-13', 'The Avengers'),
(3.8, '20240502', 'PG', 'Toy Story'),
(4.2, '20240503', 'PG-13', 'Jurassic Park');

insert into location (location_name) values ('서울');
insert into location (location_name) values ('일산');
insert into location (location_name) values ('용인');
insert into location (location_name) values ('대전');
insert into location (location_name) values ('부산');
insert into location (location_name) values ('광주');
insert into location (location_name) values ('춘천');

insert into ticket (movie_no, user_no, location_no, book_date) values (1, 1, 1, '2024-05-09');
insert into ticket (movie_no, user_no, location_no, book_date) values (1, 2, 1, '2024-05-09');
insert into ticket (movie_no, user_no, location_no, book_date) values (1, 3, 1, '2024-05-09');
insert into ticket (movie_no, user_no, location_no, book_date) values (1, 4, 1, '2024-05-09');
insert into ticket (movie_no, user_no, location_no, book_date) values (1, 5, 1, '2024-05-09');
insert into ticket (movie_no, user_no, location_no, book_date) values (1, 1, 3, '2024-05-09');
insert into ticket (movie_no, user_no, location_no, book_date) values (2, 1, 3, '2024-05-10');
insert into ticket (movie_no, user_no, location_no, book_date) values (2, 2, 3, '2024-05-10');
insert into ticket (movie_no, user_no, location_no, book_date) values (1, 1, 2, '2024-05-09');
insert into ticket (movie_no, user_no, location_no, book_date) values (2, 1, 2, '2024-05-10');
insert into ticket (movie_no, user_no, location_no, book_date) values (2, 2, 2, '2024-05-10');



insert into seat (seat_row_no, seat_column_no, ticket_no) values (0, 0, 1);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (1, 2, 1);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (1, 3, 1);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (2, 1, 1);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (3, 1, 1);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (4, 4, 2);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (5, 3, 7);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (4, 3, 7);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (3, 4, 7);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (4, 4, 10);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (5, 3, 11);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (4, 3, 11);
insert into seat (seat_row_no, seat_column_no, ticket_no) values (3, 4, 11);

INSERT INTO board (good_point, board_id, movie_no, user_id, title, content, movie_title)
VALUES
(4.5, 1, 1, 1, '좋은 영화입니다.', '이 영화 정말 좋아요!', 'The Avengers'),
(3.8, 2, 2, 2, '재미있어요!', '꼭 보세요!', 'Toy Story'),
(4.2, 3, 3, 3, '감동적입니다.', '많은 생각을 하게 만드는 영화예요.', 'Jurassic Park'),
(4.7, 4, 1, 4, '추천합니다.', '이 영화 정말 재밌어요!', 'The Avengers'),
(4.0, 5, 2, 5, '꿀잼입니다!', '내 인생 영화입니다.', 'Toy Story'),
(3.8, 6, 3, 1, '볼만 합니다.', '좋은 영화입니다.', 'Jurassic Park'),
(4.5, 7, 1, 2, '꼭 보세요!', '놓치지 마세요!', 'The Avengers'),
(4.2, 8, 2, 3, '마음이 따뜻해지는 영화입니다.', '꼭 보세요!', 'Toy Story'),
(4.9, 9, 3, 4, '인생 영화입니다.', '마음이 따뜻해지는 영화예요.', 'Jurassic Park'),
(4.3, 10, 1, 5, '감명 깊은 영화예요.', '재미와 감동이 있는 영화입니다.', 'The Avengers'),
(4.8, 11, 2, 1, '꿀잼입니다!', '재미와 감동이 있는 영화입니다.', 'Toy Story'),
(3.5, 12, 3, 2, '보통입니다.', '평범한 영화예요.', 'Jurassic Park'),
(4.6, 13, 1, 3, '훌륭합니다!', '꼭 보세요!', 'The Avengers'),
(4.1, 14, 2, 4, '추천합니다.', '재미와 감동이 있는 영화입니다.', 'Toy Story'),
(3.9, 15, 3, 5, '재밌어요!', '즐겁게 볼 수 있는 영화예요.', 'Jurassic Park'),
(4.4, 16, 1, 1, '꼭 보세요!', '재미와 감동이 있는 영화입니다.', 'The Avengers'),
(4.7, 17, 2, 2, '추천합니다.', '마음이 따뜻해지는 영화입니다.', 'Toy Story'),
(3.6, 18, 3, 3, '재밌어요!', '재미와 감동이 있는 영화입니다.', 'Jurassic Park'),
(4.2, 19, 1, 4, '재미있어요!', '많은 생각을 하게 만드는 영화예요.', 'The Avengers'),
(4.8, 20, 2, 5, '꿀잼입니다!', '많은 생각을 하게 만드는 영화예요.', 'Toy Story');


