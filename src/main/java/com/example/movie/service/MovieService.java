package com.example.movie.service;

import com.example.movie.dto.MoviesDto;
import com.example.movie.entity.Movies;
import com.example.movie.repository.MoviesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    
    private final MoviesRepository moviesRepository;

    public MovieService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<MoviesDto> findAll() {
        List<MoviesDto> moviesDtoList = new ArrayList<>();
        return moviesRepository.findAll()
                .stream()
                .map(x->MoviesDto.fromMoviesEntity(x))
                .toList();
    }

    public MoviesDto getOneMovie(Long movieNo) {
        return moviesRepository.findById(movieNo)
                .map(x->MoviesDto.fromMoviesEntity(x))
                .orElse(null);
    }

    public void delete(Long movieNo) {
        moviesRepository.deleteById(movieNo);
    }

    public void update(MoviesDto moviesDto) {
        Movies movies = Movies.builder()
                .movieNo(moviesDto.getMovieNo())
                .movieTitle(moviesDto.getMovieTitle())
                .movieDate(moviesDto.getMovieDate())
                .movieRate(moviesDto.getMovieRate())
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
    // 게시판에 영화제목 선택을 위해 추가함
}
