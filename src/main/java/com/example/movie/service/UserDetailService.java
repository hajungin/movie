//package com.example.movie.service;
//
//import com.example.movie.config.PrincipalDetails;
//import com.example.movie.repository.UserRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserDetailService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    public UserDetailService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findById(username);
//        if (user.isEmpty()){
//            throw  new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
//        }
//        User user1 = user.get();
//        return new PrincipalDetails(user1);
//    }
//}
