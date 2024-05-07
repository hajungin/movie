package com.example.movie.service;

import com.example.movie.dto.MoviesDto;
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
                .map(x->MoviesDto.fromEntity(x))
                .toList();
    }

    public MoviesDto getOneMovie(Long movieNo) {
        return moviesRepository.findById(movieNo)
                .map(x->MoviesDto.fromEntity(x))
                .orElse(null);
    }

    public void delete(Long movieNo) {
        moviesRepository.deleteById(movieNo);
    }
}
