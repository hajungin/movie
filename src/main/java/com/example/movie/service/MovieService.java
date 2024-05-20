package com.example.movie.service;

import com.example.movie.dto.MoviesDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
import com.example.movie.entity.Ticket;
import com.example.movie.entity.User;
import com.example.movie.repository.BoardRepository;
import com.example.movie.repository.MoviesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
        return moviesList;
    }
    public Movies updateGoodPoint(Long movieNo, double goodPoint) {
        // 평균 평점 계산 쿼리
        String sql1 = "SELECT AVG(b.goodPoint) FROM Board b WHERE b.movies.movieNo = :movieNo";
        TypedQuery<Double> query1 = em.createQuery(sql1, Double.class).setParameter("movieNo", movieNo);
        Double avgGoodPoint = null;
        try {
            avgGoodPoint = query1.getSingleResult();
        } catch (NoResultException e) {
            avgGoodPoint = 0.0; // 결과가 없는 경우 기본값 설정
        }
        if (avgGoodPoint == null) {
            avgGoodPoint = goodPoint; // null인 경우 기본값 설정
        }

        // 영화 정보 가져오기 쿼리
        String sql2 = "SELECT m FROM Movies m WHERE m.movieNo = :movieNo";
        TypedQuery<Movies> query2 = em.createQuery(sql2, Movies.class).setParameter("movieNo", movieNo);
        Movies movies = null;
        try {
            movies = query2.getSingleResult();
        } catch (NoResultException e) {
            // 필요한 경우 여기에서 적절한 예외 처리를 할 수 있습니다.
            throw new RuntimeException("영화를 찾을 수 없습니다.");
        }
        movies.setGoodPointAvg(avgGoodPoint);
        return movies;
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
    @Transactional
    public void deleteMovie(Long movieNo) {
        Movies movies = em.find(Movies.class, movieNo);
        // 1. 영화 좌석 정보 삭제
        String seatQuery = "DELETE FROM Seat s WHERE s.ticket.movies = :movie";
        em.createQuery(seatQuery)
                .setParameter("movie", movies)
                .executeUpdate();
        // 2. 영화 티켓 정보 삭제
        String ticketQuery = "DELETE FROM Ticket t WHERE t.movies = :movie";
        em.createQuery(ticketQuery)
                .setParameter("movie", movies)
                .executeUpdate();
        // 3. 영화 게시물 삭제
        String boardQuery = "DELETE FROM Board b WHERE b.movies = :movie";
        em.createQuery(boardQuery)
                .setParameter("movie", movies)
                .executeUpdate();
        // 4. 영화 삭제
        em.remove(movies);
    }



    public void update(MoviesDto moviesDto) {
        Movies movies = Movies.builder()
                .movieNo(moviesDto.getMovieNo())
                .movieTitle(moviesDto.getMovieTitle())
                .movieDate(moviesDto.getMovieDate())
                .movieRate(moviesDto.getMovieRate())
                .goodPointAvg(moviesDto.getGoodPointAvg())
                .img(moviesDto.getImg())
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
                .img(dto.getImg())
                .build();
        moviesRepository.save(movies);
    }

    public List<Movies> RandomMovie() {
        String sql = "SELECT m FROM Movies m ORDER BY RANDOM()";
        TypedQuery<Movies> query = em.createQuery(sql, Movies.class).setMaxResults(3);
        return query.getResultList();
    }

    public List<Movies> GoodMovie() {
        String sql = "SELECT m FROM Movies m ORDER BY m.goodPointAvg DESC LIMIT 3";
        TypedQuery<Movies> query = em.createQuery(sql, Movies.class);
        return query.getResultList();
    }

    public List<Movies> TicketMovie() {
        String sql = "SELECT t.movies FROM Ticket t GROUP BY t.movies " +
                "ORDER BY SUM(t.totalPrice) DESC";
        TypedQuery<Movies> query = em.createQuery(sql, Movies.class);
        query.setMaxResults(3);
        return query.getResultList();
    }
}
