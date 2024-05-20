package com.example.movie.service;

import com.example.movie.constant.UserRole;
import com.example.movie.dto.UserDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
import com.example.movie.entity.Ticket;
import com.example.movie.entity.User;
import com.example.movie.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
                .map(x -> UserDto.fromEntity(x))
                .toList();
    }


    public UserDto getOneUser(Long userNo) {
        return userRepository.findById(userNo)
                .map(x -> UserDto.fromEntity(x))
                .orElse(null);
    }

    public void update(UserDto userDto) {
        User user = User.builder()
                .userNo(userDto.getUserNo())
                .userId(userDto.getUserId())
                .userName(userDto.getUserName())
                .password(userDto.getPassword1())
                .birth(userDto.getBirth())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .money(userDto.getMoney())
                .userRole(userDto.getUserRole())
                .build();
        userRepository.save(user);
    }

//    public void delete(Long id) {
//        userRepository.deleteById(id);
//    }

    public List<User> findAllEm() {
        List<User> userList = em.createQuery("SELECT m FROM User m", User.class).getResultList();
        return userList;
    }

    public User getOneUserEm(Long userNo) {
        User user = em.find(User.class, userNo);
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
        if (userDto.getEmail().toLowerCase().contains("@ezen")) {
            user.setUserRole(UserRole.ADMIN);
        } else {
            user.setUserRole(UserRole.USER);
        }
        em.persist(user);
    }


    @Transactional
    public void delete(Long userNo) {
        User user = em.find(User.class, userNo);

        String sql1 = "SELECT b FROM Board b WHERE b.user.userNo=:userNo";
        TypedQuery<Board> query1 = em.createQuery(sql1, Board.class)
                .setParameter("userNo", userNo);
        List<Board> boardList1 = query1.getResultList();
        for (Board board : boardList1) {
            board.setUser(null);
        }
        String sql2 = "SELECT t FROM Ticket t WHERE t.user.userNo=:userNo";
        TypedQuery<Ticket> query2 = em.createQuery(sql2, Ticket.class)
                .setParameter("userNo", userNo);
        List<Ticket> boardList2 = query2.getResultList();
        for (Ticket ticket : boardList2) {
            ticket.setUser(null);
        }
        em.remove(user);
    }

    @Transactional
    public void deleteUser(Long userNo) {
        User user = em.find(User.class, userNo);

        // 1. 사용자가 예약한 좌석 정보 삭제
        String seatQuery = "DELETE FROM Seat s WHERE s.ticket.user = :user";
        em.createQuery(seatQuery)
                .setParameter("user", user)
                .executeUpdate();
        // 2. 사용자가 예약한 티켓 정보 삭제
        String ticketQuery = "DELETE FROM Ticket t WHERE t.user = :user";
        em.createQuery(ticketQuery)
                .setParameter("user", user)
                .executeUpdate();
        // 3. 사용자가 작성한 게시물 삭제
        String boardQuery = "DELETE FROM Board b WHERE b.user = :user";
        em.createQuery(boardQuery)
                .setParameter("user", user)
                .executeUpdate();

        // 4. 사용자 정보 삭제
        em.remove(user);
    }


    @Transactional
    public boolean checkId(String userId) {
        String sql = "SELECT COUNT(u) FROM User u WHERE u.userId = :userId";
        Long count = em.createQuery(sql, Long.class)
                .setParameter("userId", userId)
                .getSingleResult();

        log.info(String.valueOf(count));
        if (count > 0) {
            System.out.println("이미 존재하는 아이디입니다");
            return true;
        } else {
            System.out.println("사용가능한 아이디입니다.");
            return false;
        }
    }

    public void money(UserDto dto) {
        // 기존 사용자 정보 조회
        User user = userRepository.findById(dto.getUserNo()).orElse(null);
        // 기존 금액에 추가할 금액을 더함
        int newMoney = user.getMoney() + dto.getMoney();
        // 엔티티에 새로운 금액 설정
        user.setMoney(newMoney);
        // 엔티티 저장
        userRepository.save(user);
    }

    @Transactional
    public void leftMonet(Long userNo, int leftMoney) {
        User user = em.find(User.class, userNo);
        user.setMoney(leftMoney);
        em.persist(user);
    }
}
