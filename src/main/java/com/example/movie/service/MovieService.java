package com.example.movie.service;

import com.example.movie.dto.MoviesDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
import com.example.movie.entity.Ticket;
import com.example.movie.repository.BoardRepository;
import com.example.movie.repository.MoviesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    EntityManager em;
    
    private final MoviesRepository moviesRepository;
    private final BoardRepository boardRepository;

    public MovieService(MoviesRepository moviesRepository, BoardRepository boardRepository) {
        this.moviesRepository = moviesRepository;
        this.boardRepository = boardRepository;
    }

    public List<MoviesDto> findAll() {
        List<MoviesDto> moviesDtoList = new ArrayList<>();
        return moviesRepository.findAll()
                .stream()
                .map(x->MoviesDto.fromMoviesEntity(x))
                .toList();
    }

    public List<Movies> findAllEm(){
        List<Movies> moviesList = em.createQuery("SELECT m FROM Movies m ORDER BY m.movieNo", Movies.class).getResultList();
//        Long result = em.createQuery("SELECT AVG(b.good_point) Board b", Long.class);
//        moviesList.set(boardList.)
        return moviesList;
    }

    public MoviesDto getOneMovie(Long movieNo) {
        return moviesRepository.findById(movieNo)
                .map(x->MoviesDto.fromMoviesEntity(x))
                .orElse(null);
    }
    @Transactional
    public void delete(Long movieNo) {
        Movies movies = em.find(Movies.class, movieNo);

        String sql1 = "SELECT b FROM Board b WHERE b.movies.movieNo=:movieNo";
        TypedQuery<Board> query1 = em.createQuery(sql1, Board.class)
                .setParameter("movieNo", movieNo);
        List<Board> boardList1 = query1.getResultList();
        for (Board board : boardList1){
            board.setMovies(null);
        }
        String sql2 = "SELECT t FROM Ticket t WHERE t.movies.movieNo=:movieNo";
        TypedQuery<Ticket> query2 = em.createQuery(sql2, Ticket.class)
                .setParameter("movieNo", movieNo);
        List<Ticket> boardList2 = query2.getResultList();
        for (Ticket ticket : boardList2){
            ticket.setMovies(null);
        }
        em.remove(movies);
//        moviesRepository.deleteById(movieNo);
    }



    public void update(MoviesDto moviesDto) {
        Movies movies = Movies.builder()
                .movieNo(moviesDto.getMovieNo())
                .movieTitle(moviesDto.getMovieTitle())
                .movieDate(moviesDto.getMovieDate())
                .movieRate(moviesDto.getMovieRate())
                .goodPointAvg(moviesDto.getGoodPointAvg())
                .build();
        moviesRepository.save(movies);
    }

    public List<MoviesDto> getAllMovies() {
        List<MoviesDto> moviesDtoList = new ArrayList<>();
        moviesRepository.findAll().forEach(
                movies -> moviesDtoList.add(MoviesDto.fromMoviesEntity(movies))
        );
        return moviesDtoList;
    }

    public void insert(MoviesDto dto) {
        Movies movies = Movies.builder()
                .movieTitle(dto.getMovieTitle())
                .movieDate(dto.getMovieDate())
                .movieRate(dto.getMovieRate())
//                .goodPointAvg(dto.getGoodPointAvg())
                .build();
        moviesRepository.save(movies);
    }
    // 게시판에 영화제목 선택을 위해 추가함
}
