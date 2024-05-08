package com.example.movie.service;

import com.example.movie.dto.UserDto;
import com.example.movie.entity.User;
import com.example.movie.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        return userRepository.findAll()
                .stream()
                .map(x->UserDto.fromEntity(x))
                .toList();
    }

    public UserDto getOneUser(Long userNo) {
        return userRepository.findById(userNo)
                .map(x->UserDto.fromEntity(x))
                .orElse(null);
    }

    public void update(UserDto userDto) {
        User user = User.builder()
                .userNo(userDto.getUserNo())
                .userId(userDto.getUserId())
                .password(userDto.getPassword1())
                .birth(userDto.getBirth())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .userRole(userDto.getUserRole())
                .build();
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
