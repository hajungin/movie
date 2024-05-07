package com.example.movie.service;

import com.example.movie.dto.MoviesDto;
import com.example.movie.repository.MoviesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesService {
    private final MoviesRepository moviesRepository;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<MoviesDto> findAll() {
        return moviesRepository.findAll().stream()
                .map(x->MoviesDto.fromMoviesEntity(x))
                .toList();
    }
}
