package com.example.movie.repository;

import com.example.movie.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    //좌석:seat_id(pk), location_no(fk), movie_no(fk), seat_row_no(a, b ,c), seat_column_no(1번석 2번석..)

}
