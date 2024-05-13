INSERT INTO `user` (`birth`, `user_id`, `password`, `phone`, `user_name`, `user_role`, `email`)
VALUES
('1990-01-01', 'john_doe', 'password123', '010-1234-5678', 'John Doe', 'ADMIN', 'john@example.com'),
('1985-05-15', 'jame_amith', 'abc123', '010-9876-5432', 'Jane Smith', 'USER', 'qwer@example.com'),
('1985-03-15', 'sane_gsih', 'abc12314', '010-5556-5432', 'Sane Gsih', 'USER', 'asdf@example.com'),
('1985-04-15', 'gane_vmith', 'abc1231234', '010-5555-5432', 'Gane Smizh', 'USER', 'zxcv@example.com'),
('1978-12-30', 'bob_johnson', 'bobpass', NULL, 'Bob Johnson', 'USER', 'bob@example.com');

INSERT INTO movies (good_point_avg, movie_date, movie_rate, movie_title) VALUES
(4.5, '2024-05-01', 'PG-13', 'The Avengers'),
(3.8, '2024-05-02', 'PG', 'Toy Story'),
(4.2, '2024-05-03', 'PG-13', 'Jurassic Park'),
(4.1, '2024-05-04', 'PG-13', 'Pirates of the Caribbean'),
(3.9, '2024-05-05', 'PG', 'Harry Potter and the Sorcerer''s Stone'),
(4.3, '2024-05-06', 'PG-13', 'The Lord of the Rings: The Fellowship of the Ring'),
(4.0, '2024-05-07', 'PG', 'Finding Nemo'),
(4.4, '2024-05-08', 'PG-13', 'The Matrix'),
(3.7, '2024-05-09', 'PG-13', 'Avatar'),
(4.2, '2024-05-10', 'PG-13', 'The Dark Knight'),
(3.6, '2024-05-11', 'PG', 'Forrest Gump'),
(4.5, '2024-05-12', 'PG-13', 'Inception'),
(3.8, '2024-05-13', 'PG', 'Frozen'),
(4.1, '2024-05-14', 'PG-13', 'Interstellar'),
(3.9, '2024-05-15', 'PG-13', 'The Lion King'),
(4.3, '2024-05-16', 'PG-13', 'The Shawshank Redemption'),
(4.0, '2024-05-17', 'PG', 'The Incredibles'),
(4.4, '2024-05-18', 'PG-13', 'The Godfather'),
(3.7, '2024-05-19', 'PG-13', 'Gladiator'),
(4.2, '2024-05-20', 'PG-13', 'Braveheart');


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

INSERT INTO board (movie_no, user_no, title, content) VALUES
(1, 1, '새로운 리뷰 1', '이 영화는 정말로 멋지다!'),
(1, 1, '새로운 리뷰 2', '이 영화를 다시 보고 싶어요.'),
(2, 2, '새로운 리뷰 3', '이 영화의 특별한 점은 무엇인가요?'),
(3, 3, '새로운 리뷰 4', '감독의 비전이 확실한 영화입니다.'),
(4, 1, '새로운 리뷰 5', '이 영화의 주제는 무엇인가요?'),
(5, 2, '새로운 리뷰 6', '이 영화의 분위기가 너무 좋아요.'),
(6, 3, '새로운 리뷰 7', '이 영화의 배우들은 훌륭합니다.'),
(7, 1, '새로운 리뷰 8', '이 영화는 매우 감동적입니다.'),
(8, 2, '새로운 리뷰 9', '이 영화를 보고 나서 내 삶이 달라졌어요.'),
(9, 3, '새로운 리뷰 10', '이 영화는 관객들에게 깊은 여운을 남깁니다.'),
(10, 1, '새로운 리뷰 11', '이 영화의 음악은 정말 멋지네요.'),
(11, 2, '새로운 리뷰 12', '이 영화는 많은 생각을 하게 만듭니다.'),
(12, 3, '새로운 리뷰 13', '이 영화의 스토리텔링이 좋습니다.'),
(13, 1, '새로운 리뷰 14', '이 영화의 연기력은 일품입니다.'),
(14, 2, '새로운 리뷰 15', '이 영화의 시각 효과는 놀랍습니다.'),
(15, 3, '새로운 리뷰 16', '이 영화는 여러 감정을 자아냅니다.'),
(16, 1, '새로운 리뷰 17', '이 영화를 보고 나서 나의 가치관이 바뀌었습니다.'),
(17, 2, '새로운 리뷰 18', '이 영화는 놀랍습니다.'),
(18, 3, '새로운 리뷰 19', '이 영화는 현실성이 있습니다.'),
(19, 1, '새로운 리뷰 20', '이 영화의 메시지가 깊습니다.');


INSERT INTO board (movie_no, user_no, title, content) VALUES
(1, 2, '새로운 리뷰 21', '이 영화는 정말 감동적입니다.'),
(1, 3, '새로운 리뷰 22', '이 영화의 스토리는 정말 매력적입니다.'),
(2, 1, '새로운 리뷰 23', '이 영화의 음악은 귀를 매우 즐겁게 합니다.'),
(2, 2, '새로운 리뷰 24', '이 영화는 시간 가는 줄 모르게 만듭니다.'),
(3, 3, '새로운 리뷰 25', '이 영화의 장면 전환은 매우 자연스럽습니다.'),
(3, 1, '새로운 리뷰 26', '이 영화의 연출은 정말 훌륭합니다.'),
(4, 2, '새로운 리뷰 27', '이 영화의 캐릭터는 매우 매력적입니다.'),
(4, 3, '새로운 리뷰 28', '이 영화는 현실적인 상황을 잘 그립니다.'),
(5, 1, '새로운 리뷰 29', '이 영화의 메시지는 누구에게나 다가올 것입니다.'),
(5, 2, '새로운 리뷰 30', '이 영화의 카메라 각도는 매우 창의적입니다.'),
(6, 3, '새로운 리뷰 31', '이 영화의 주제는 매우 중요합니다.'),
(6, 1, '새로운 리뷰 32', '이 영화의 결말은 예상치 못했습니다.'),
(7, 2, '새로운 리뷰 33', '이 영화는 여러 감정을 자아냅니다.'),
(7, 3, '새로운 리뷰 34', '이 영화의 배우들의 연기는 훌륭합니다.'),
(8, 1, '새로운 리뷰 35', '이 영화는 전혀 지루하지 않습니다.'),
(8, 2, '새로운 리뷰 36', '이 영화의 시나리오는 매우 흥미롭습니다.'),
(9, 3, '새로운 리뷰 37', '이 영화의 분위기는 매우 어두운 편입니다.'),
(9, 1, '새로운 리뷰 38', '이 영화의 의미는 매우 깊습니다.'),
(10, 2, '새로운 리뷰 39', '이 영화는 극장에서 봐야 합니다.'),
(10, 3, '새로운 리뷰 40', '이 영화의 색감은 매우 아름답습니다.');




