package com.example.movie.service;

import com.example.movie.constant.SeatCoordinates;
import com.example.movie.constant.TotalPrice;
import com.example.movie.dto.SeatDto;
import com.example.movie.dto.TicketDto;
import com.example.movie.entity.*;
import com.example.movie.repository.SeatRepository;
import com.example.movie.repository.TicketRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    EntityManager em;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;

    public BookService(TicketRepository ticketRepository, SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
    }
//    public List<SeatDto> findAllSeats() {
//        return seatRepository.findAll().stream()
//                .map(x->SeatDto.fromSeatEntity(x)).toList();
//    }

    public boolean[][] convertToSeatArray(List<SeatDto> seatDtoList, int numRows, int numColumns) {
        // 2차원 배열 초기화
        boolean[][] seat = new boolean[numRows][numColumns];

        // 모든 좌석을 사용 가능 상태로 초기화 (true로 설정)
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                seat[i][j] = true;  // 모든 좌석을 사용 가능(true) 상태로 초기화
            }
        }

        // seatDtoList의 각 SeatDto를 순회하면서 실제 사용 중인 좌석을 false로 설정
        for (SeatDto seatDto : seatDtoList) {
            int row = seatDto.getSeatRowNo();
            int column = seatDto.getSeatColumnNo();

            // 좌석이 사용 중인 경우 false로 설정
            seat[row][column] = false;
        }

        return seat;
    }

    //JPQL
    public List<SeatDto> searchSeatByMovieLocationAndDate(Long movieNo, Long locationNo, LocalDate date) {
        String sql = "SELECT s FROM Seat s WHERE s.ticket.movies.movieNo = :movieNo " +
                "AND s.ticket.location.locationNo = :locationNo AND s.ticket.bookDate = :date";
        TypedQuery<Seat> query = em.createQuery(sql, Seat.class)
                .setParameter("movieNo", movieNo)
                .setParameter("locationNo", locationNo)
                .setParameter("date", date);
        List<Seat> seatList = query.getResultList();

        return seatList.stream()
                .map(x -> SeatDto.fromSeatEntity(x))
                .toList();
    }

    @Transactional
    public List<Ticket> viewReservationDetails(Long userNo) {

        String sql = "SELECT t FROM Ticket t WHERE t.user.userNo = :userNo";
        TypedQuery<Ticket> query = em.createQuery(sql, Ticket.class)
                .setParameter("userNo", userNo);
        List<Ticket> ticketInformation = query.getResultList();

        return ticketInformation;
    }

    @Transactional
    public List<Ticket> viewTicketList() {
        String sql = "SELECT t FROM Ticket t";
        TypedQuery<Ticket> query = em.createQuery(sql, Ticket.class);
        List<Ticket> ticketList = query.getResultList();
        return ticketList;
    }

    @Transactional
    public void ticketCancel(Long ticketNo) {
        Ticket ticket = em.find(Ticket.class, ticketNo);
        int cancel = ticket.getUser().getMoney() + ticket.getTotalPrice();
        User user = em.find(User.class, ticket.getUser().getUserNo());
        user.setMoney(cancel);
        em.persist(user);
        em.remove(ticket);
    }

    @Transactional
    public void ticketBookService(Long movieNo, Long locationNo, Long userNo, LocalDate date, String selectedSeats, int totalPrice) {

        Movies movies = em.find(Movies.class, movieNo);
        Location location = em.find(Location.class, locationNo);
        User user = em.find(User.class, userNo);
        List<SeatCoordinates> seatCoordinatesList = arrangeSeats(selectedSeats);

        Ticket ticket = new Ticket();
        ticket.setMovies(movies);
        ticket.setLocation(location);
        ticket.setBookDate(date);
        ticket.setUser(user);
        ticket.setSeatList(new ArrayList<>());
        ticket.setTotalPrice(totalPrice);

        for (SeatCoordinates seatCoordinates : seatCoordinatesList) {
            Seat seat = new Seat();
            seat.setTicket(ticket);
            seat.setSeatRowNo(seatCoordinates.getRow());
            seat.setSeatColumnNo(seatCoordinates.getColumn());
            ticket.getSeatList().add(seat);
        }

        // Persist the ticket and associated seats using EntityManager
        em.persist(ticket);


    }

    //JSON을 사용하지 못 해서 어거지로 문자열로 form 받고 그 form 받은 String 문자열을 분리해서 다시 int로 전환 후 row, column 값에 넣어줌
    public List<SeatCoordinates> arrangeSeats(String selectedSeats) {
        // 쉼표를 기준으로 문자열을 분리
        String[] parts = selectedSeats.split(",");

        // SeatCoordinates 객체를 저장할 List 생성
        List<SeatCoordinates> seatList = new ArrayList<>();

        // 분리된 문자열을 순회하면서 SeatCoordinates 객체 생성 후 List에 추가
        for (String part : parts) {
            // 각 문자열을 첫번째 자리와 두번째 자리로 분리
            String rowStr = part.substring(0, 1); // 첫번째 자리
            String columnStr = part.substring(1); // 두번째 자리

            // 문자열을 숫자로 변환하여 SeatCoordinates 객체 생성
            int row = Integer.parseInt(rowStr);
            int column = Integer.parseInt(columnStr);
            SeatCoordinates seat = new SeatCoordinates(row, column);
            // List에 SeatCoordinates 객체 추가
            seatList.add(seat);
        }
        return seatList;
    }

    public List<TicketDto> findAll() {
        List<TicketDto> ticketDtoList = ticketRepository.findAll()
                .stream()
                .map(x -> TicketDto.fromTicketEntity(x))
                .toList();
        return ticketDtoList;

    }

    public List<TotalPrice> findMovie() {
        String sql = "SELECT t.movies.movieTitle, SUM(t.totalPrice)  FROM Ticket t GROUP BY t.movies.movieTitle ";
        TypedQuery<TotalPrice> query = em.createQuery(sql, TotalPrice.class);
        return query.getResultList();
    }

    public List<TotalPrice> findUser() {
        String sql = "SELECT t.user.userName, SUM(t.totalPrice)  FROM Ticket t GROUP BY t.user.userName ";
        TypedQuery<TotalPrice> query = em.createQuery(sql, TotalPrice.class);
        return query.getResultList();
    }

    public List<TotalPrice> findLocation() {
        String sql = "SELECT t.location.locationName, SUM(t.totalPrice)  FROM Ticket t GROUP BY t.location.locationName ";
        TypedQuery<TotalPrice> query = em.createQuery(sql, TotalPrice.class);
        return query.getResultList();
    }

    public List<TotalPrice> findDate() {
        String sql = "SELECT t.bookDate, SUM(t.totalPrice)  FROM Ticket t GROUP BY t.bookDate ";
        TypedQuery<TotalPrice> query = em.createQuery(sql, TotalPrice.class);
        return query.getResultList();
    }


}

//    public List<Ticket> findSum() {
//        String sql = "SELECT SUM(t.totalPrice) FROM Ticket t ORDER BY t.movies.movieNo";
//        TypedQuery<Ticket>
//    }
//}


//JPQL 사용 전 짠 쿼리

//    @Query("SELECT t FROM Ticket t WHERE t.movies.movieNo = :movieNo AND t.location.locationNo = :locationNo AND t.bookDate = :date")
//    List<Ticket> searchMovieNoAndLocationNoAndDate(@Param("movieNo") Long movieNo,
//                                                   @Param("locationNo") Long locationNo,
//                                                   @Param("date") LocalDate date);

//    public List<SeatDto> findSeatByMovieLocationAndDate(Long movieNo, Long locationNo, LocalDate date){
//        List<Ticket> ticketList = ticketRepository.searchMovieNoAndLocationNoAndDate(movieNo, locationNo, date);
//
//        List<Seat> seatList = new ArrayList<>();
//        for (Ticket ticket : ticketList){
//            List<Seat> seats =ticket.getSeatList();
//            seatList.addAll(seats);
//        }
//        return seatList.stream()
//                .map(x->SeatDto.fromSeatEntity(x))
//                .toList();
//    }
