package com.example.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    ////지점 location_no(pk), movie_no(fk), location_name,
    @Id
    @Column(name = "location_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //사용해야할 DB가 db= postgreSQL라서  나중에 AUTO로 전환
    private Long locationNo;

    @Column(name = "movie_no")
    private Long movieNo;

    @Column(name = "location_name")
    private String locationName;
}
