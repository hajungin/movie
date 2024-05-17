package com.example.movie.repository;

import com.example.movie.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 전체 검색
    @Query(value = "select * from board order by board_id", nativeQuery = true)
    List<Board> searchQuery();


    // 영화제목 검색
    @Query(value = "select * \n" +
            "from board \n" +
            "where movie_no in (\n" +
            "    select movie_no\n" +
            "    from movies \n" +
            "    where movie_title like '%:keyword%'\n" +
            ") \n" +
            "order by board_id;\n", nativeQuery = true)
    List<Board> searchMovieTitle(@Param("keyword")String keyword);

    // 유저정보 검색
    @Query(value = "select * from board where user_no in " +
            "(select user_no from user where username like %:keyword%) " +
            "order by board_id", nativeQuery = true)
    List<Board> searchUser(@Param("keyword") String keyword);

}
