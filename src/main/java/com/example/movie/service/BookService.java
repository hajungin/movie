package com.example.movie.service;

import com.example.movie.dto.SeatDto;
import com.example.movie.entity.Seat;
import com.example.movie.entity.Ticket;
import com.example.movie.repository.SeatRepository;
import com.example.movie.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
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

    public List<SeatDto> findAllSeat() {
        return seatRepository.findAll().stream()
                .map(x->SeatDto.fromSeatEntity(x))
                .toList();
    }

    public List<SeatDto> findSeatByMovieLocationAndDate(Long movieNo, Long locationNo, LocalDate date){
        List<Ticket> ticketList = ticketRepository.searchMovieNoAndLocationNoAndDate(movieNo, locationNo, date);

        List<Seat> seatList = new ArrayList<>();
        for (Ticket ticket : ticketList){
            List<Seat> seats =ticket.getSeatList();
            seatList.addAll(seats);
        }
        return seatList.stream()
                .map(x->SeatDto.fromSeatEntity(x))
                .toList();
    }

}