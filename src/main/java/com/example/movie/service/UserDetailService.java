package com.example.movie.service;

import com.example.movie.config.PrincipalDetails;
import com.example.movie.repository.UserRepository;
import com.example.movie.entity.User;
import io.micrometer.common.util.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findById(Long.valueOf(userId));
//        if (user.isEmpty()){
//            throw  new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
//        }
//        com.example.movie.entity.User user1 = user.get();
//        return new PrincipalDetails(user1);
//    }
//
//}
    @Override
    public UserDetails loadUserByUsername(String userNo) throws UsernameNotFoundException {
        if (StringUtils.isBlank(userNo)) {
            throw new UsernameNotFoundException("사용자 아이디가 유효하지 않습니다.");
        }

        try {
            Long userIdLong = Long.parseLong(userNo);
            Optional<User> user = userRepository.findById(userIdLong);
            if (user.isPresent()) {
                return new PrincipalDetails(user.get());
            } else {
                throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
            }
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("사용자 아이디가 유효하지 않습니다.");
        }
    }

}

