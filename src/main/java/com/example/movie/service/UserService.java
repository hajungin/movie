package com.example.movie.service;

import com.example.movie.constant.UserRole;
import com.example.movie.dto.UserDto;
import com.example.movie.entity.User;
import com.example.movie.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

//    @Autowired
//    EntityManager em;

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
                .userName(userDto.getUserName())
                .password(userDto.getPassword1())
                .password(userDto.getPassword2())
                .birth(userDto.getBirth())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .userRole(userDto.getUserRole())
                .build();
        userRepository.save(user);
    }

//    public void delete(Long id) {
//        userRepository.deleteById(id);
//    }

    public List<User> findAllEm() {
        List<User> userList = em.createQuery("SELECT m FROM User m",User.class).getResultList();
        return userList;
    }

    public User getOneUserEm(Long userNo) {
        User user = em.find(User.class,userNo);
        return user;
    }

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    @PersistenceContext
    EntityManager em;
    @Transactional
    public void createUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setPassword(passwordEncoder.encode(userDto.getPassword1()));
        user.setUserName(userDto.getUserName());
        user.setBirth(userDto.getBirth());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        if (userDto.getEmail().toLowerCase().contains("@ezen")){
            user.setUserRole(UserRole.ADMIN);
        }else {
            user.setUserRole(UserRole.USER);
        }
        em.persist(user);
    }


    @Transactional
    public void delete(Long id) {
        User user = em.find(User.class,id);
        em.remove(user);
    }
}
