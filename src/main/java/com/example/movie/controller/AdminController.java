package com.example.movie.controller;

import com.example.movie.constant.TotalPrice;
import com.example.movie.dto.BoardDto;
import com.example.movie.dto.MoviesDto;
import com.example.movie.dto.TicketDto;
import com.example.movie.dto.UserDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
import com.example.movie.entity.Ticket;
import com.example.movie.entity.User;
import com.example.movie.service.BoardService;
import com.example.movie.service.BookService;
import com.example.movie.service.MovieService;
import com.example.movie.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("admin")
public class AdminController {
//    @Autowired
//    EntityManager em;
//    private String uploadDir;
    private final UserService userService;
    private final MovieService movieService;
    private final BoardService boardService;
    private final BookService bookService;

    public AdminController(UserService userService, MovieService movieService, BoardService boardService, BookService bookService) {
        this.userService = userService;
        this.movieService = movieService;
        this.boardService = boardService;
        this.bookService = bookService;
    }

    @GetMapping("")
//    관리자 페이지 메인 화면, 쿼리 사용 인기 영화 티켓판매
    public String adminView(Model model) {
        List<Movies> moviesList = movieService.GoodMovie();
        model.addAttribute("movie",moviesList);

        List<Movies> movies = movieService.TicketMovie();
        model.addAttribute("sell",movies);
        return "admin/main";
    }

    @GetMapping("user")
//    관리자페이지 회원관리 화면
    public String userView(Model model) {
        List<UserDto> userDtoList = userService.findAll();
        model.addAttribute("user", userDtoList);
        return "admin/user";
    }

    @GetMapping("update")
//    관리자페이지 회원 수정화면
    public String updateView(@RequestParam("updateId") Long userNo,
                             Model model) {
        UserDto userDto = userService.getOneUser(userNo);
        model.addAttribute("userDto", userDto);
//        생년월일 현재 날짜 이후로 선택 불가능
        model.addAttribute("maxDate", LocalDate.now().toString());
        return "admin/user_update";
    }
    @PostMapping("update")
//    builder 사용
    public String updateView(@ModelAttribute("userDto") UserDto userDto) {
        userService.update(userDto);
        return "redirect:/admin/user";
    }

    @PostMapping("/delete-user/{deleteId}")
//    관리자페이지 회원삭제
    public String delete(@PathVariable("deleteId") Long id) {
//        userService.delete(id);
//        테이블이 foreign key 로 연결되어 있어 엔티티 매니저를 사용하여 삭제
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }

    @GetMapping("movie")
//    관리자페이지 영화관리 화면, 게시판 별점 바로 반영
    public String movieView(Model model) {
        List<MoviesDto> moviesDtoList = movieService.findAll();
        model.addAttribute("movie", moviesDtoList);
//        List<Movies> moviesList = movieService.findAllEm();
//        model.addAttribute("movie", moviesList);
        return "admin/movie";
    }


    @GetMapping("movie_update")
//    관리자페이지 영화 수정화면
    public String movieUpdateView(@RequestParam("updateId") Long movieNo,
                                  Model model) {
        MoviesDto moviesDto = movieService.getOneMovie(movieNo);
        model.addAttribute("movie", moviesDto);
        return "admin/movie_update";
    }
    @PostMapping("movie_update")
    public String movieUpdateView(@ModelAttribute("moviesDto") MoviesDto moviesDto) {
        movieService.update(moviesDto);
        return "redirect:/admin/movie";
    }

    @PostMapping("/deleted-movie/{deleteId}")
//    관리자페이지 영화삭제 화면
    public String deleteMovie(@PathVariable("deleteId") Long movieNo) {
        movieService.deleteMovie(movieNo);
        return "redirect:/admin/movie";
    }

    @GetMapping("insert")
//    관리자페이지 영화 등록 화면
    public String insertMovie(Model model) {
        model.addAttribute("movie", new MoviesDto());
        return "admin/movie_insert";
    }

    @PostMapping("insert")
    public String insertMovie(@ModelAttribute("movie") MoviesDto dto) {
        movieService.insert(dto);
        return "redirect:/admin/movie";
    }


    @GetMapping("board")
    public String board(Model model,
                        @PageableDefault(page = 0, size = 10, sort = "boardId",
                                direction = Sort.Direction.DESC) Pageable pageable) {
        //       넘겨온 페이지 번호로 리스트 받아오기
        Page<Board> boardPage = boardService.pageList(pageable);

        //        페이지 블럭처리
        int totalPage = boardPage.getTotalPages();
        List<Integer> barNumbers = boardService.getPaginationBarNumbers(
                pageable.getPageNumber(), totalPage);
        model.addAttribute("pagination", barNumbers);
        model.addAttribute("paging", boardPage);
        return "admin/board";
    }

    @PostMapping("/deleted-board/{deleteId}")
    public String deleteBoard(@PathVariable("deleteId") Long boardId) {
        boardService.delete(boardId);
        return "redirect:/admin/board";
    }

    @GetMapping("ticket")
    public String ticket(Model model) {
        List<Ticket> ticketDtoList = bookService.viewTicketList();
        model.addAttribute("ticketList", ticketDtoList);
        return "admin/ticket";
    }

    @PostMapping("/deleted-book/{ticketNo}")
    public String deleteTicket(@PathVariable("ticketNo") Long ticketNo) {

        bookService.ticketCancel(ticketNo);

        return "redirect:/admin/ticket";
    }

    @GetMapping("total")
    public String totalMoney(Model model){
        List<TicketDto> ticketDtoList = bookService.findAll();
        log.info(ticketDtoList.toString());
        model.addAttribute("ticket",ticketDtoList);

        Long sum = bookService.All();

        Locale locale = new Locale("ko", "KR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedSum = currencyFormatter.format(sum);
        model.addAttribute("formattedSum", formattedSum);
        return "admin/total_money";
    }
    @GetMapping("total_movie")
    public String totalMovie(Model model){
        List<TotalPrice> ticketName = bookService.findMovie();
        model.addAttribute("ticket",ticketName);

        Long sum = bookService.SumMovie();

        Locale locale = new Locale("ko", "KR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedSum = currencyFormatter.format(sum);
        model.addAttribute("formattedSum", formattedSum);
        return "admin/total/movie";
    }

    @GetMapping("total_name")
    public String totalName(Model model) {
        // 해당 사용자의 매출 정보를 가져옵니다.
        List<TotalPrice> ticketName = bookService.findUser();
        model.addAttribute("ticket", ticketName);

        // 전체 매출 총액을 가져옵니다.
        Long sum = bookService.SumUser();

        // 매출 합계를 한국 원화 형식으로 포맷팅합니다.
        Locale locale = new Locale("ko", "KR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedSum = currencyFormatter.format(sum);
        model.addAttribute("formattedSum", formattedSum);

        return "admin/total/name";
    }

    @GetMapping("total_location")
    public String totalLocation(Model model){
        List<TotalPrice> ticketName = bookService.findLocation();
        model.addAttribute("ticket",ticketName);

        // 전체 매출 총액을 가져옵니다.
        Long sum = bookService.SumLocation();

        // 매출 합계를 한국 원화 형식으로 포맷팅합니다.
        Locale locale = new Locale("ko", "KR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedSum = currencyFormatter.format(sum);
        model.addAttribute("formattedSum", formattedSum);
        return "admin/total/location";
    }

    @GetMapping("total_date")
    public String totalDate(Model model){
        List<TotalPrice> ticketName = bookService.findDate();
        model.addAttribute("ticket",ticketName);

        // 전체 매출 총액을 가져옵니다.
        Long sum = bookService.SumDate();

        // 매출 합계를 한국 원화 형식으로 포맷팅합니다.
        Locale locale = new Locale("ko", "KR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedSum = currencyFormatter.format(sum);
        model.addAttribute("formattedSum", formattedSum);
        return "admin/total/date";
    }
}
