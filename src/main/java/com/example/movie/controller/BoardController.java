package com.example.movie.controller;

import com.example.movie.config.PrincipalDetails;
import com.example.movie.dto.BoardDto;
import com.example.movie.dto.MoviesDto;
import com.example.movie.dto.UserDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Ticket;
import com.example.movie.entity.User;
import com.example.movie.repository.BoardRepository;
import com.example.movie.service.BoardService;
import com.example.movie.service.MovieService;
import com.example.movie.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("board")
@Slf4j
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final MovieService movieService;
    private final UserService userService;

    public BoardController(BoardRepository boardRepository, BoardService boardService, MovieService movieService, UserService userService) {
        this.boardRepository = boardRepository;
        this.boardService = boardService;
        this.movieService = movieService;
        this.userService = userService;
    }

//    @GetMapping("list")
//    public String mainList(Model model,
//                           @PageableDefault(page = 0, size = 10, sort = "boardId",
//                                   direction = Sort.Direction.ASC) Pageable pageable) {
//        Page<BoardDto> boardPage = boardService.viewAllBoard(pageable);
//        int totalPage = boardPage.getTotalPages();
//        List<Integer> barNumbers = boardService.getPaginationBarNumbers(
//                pageable.getPageNumber(), totalPage);
//        model.addAttribute("pagination", barNumbers);
//        model.addAttribute("paging", boardPage);
//
//
//        return "board/list";
//    }

    @GetMapping("list")
    public String boardMainList(Model model,
                                @PageableDefault(page = 0, size = 10, sort = "boardId",
                                direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> boardPage = boardService.viewBoard(pageable);
        int totalPage = boardPage.getTotalPages();
        List<Integer> barNumbers = boardService.getPaginationBarNumbers(
                pageable.getPageNumber(), totalPage);
        model.addAttribute("pagination", barNumbers);
        model.addAttribute("paging", boardPage);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // 사용자 이름 가져오기
            Object principal = authentication.getPrincipal();

            if (principal instanceof PrincipalDetails) {
                PrincipalDetails userDetails = (PrincipalDetails) principal;
                User user = userDetails.getUser();
                Long userNo = user.getUserNo();
                model.addAttribute("userNo", userNo);
            } else {
                // 사용자 정보가 PrincipalDetails가 아닌 경우에 대한 처리
                // 예: 다른 방식으로 사용자 정보 추출
                model.addAttribute("userNo", null);
            }
        } else {
            model.addAttribute("userNo", null);
        }

        return "board/list";
    }

    @GetMapping("insert")
    public String boardInsertForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        model.addAttribute("username", username);

        List<MoviesDto> moviesDtoList = movieService.getAllMovies();
        model.addAttribute("moviesDtoList", moviesDtoList);
        model.addAttribute("boardDto", new BoardDto());
        return "board/insert";
    }

    @PostMapping("/insert")
    public String boardInsertView(@ModelAttribute("boardDto")BoardDto dto) {
        log.info(dto.toString());
        boardService.insert(dto);
        return "redirect:/board/list";
    }

    @GetMapping("update")
    public String boardUpdateForm(@RequestParam("updateId") Long boardId,
                                      Model model) {
        BoardDto boardDto = boardService.getOneBoard(boardId);
        List<MoviesDto> moviesDtoList = movieService.getAllMovies();
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("moviesDtoList", moviesDtoList);
        return "board/update";
    }

    @PostMapping("update")
    public String boardUpdateView(@ModelAttribute("boardDto")BoardDto boardDto) {
        boardService.update(boardDto);
        return "redirect:/board/list";
    }

    @PostMapping("/delete/{deleteId}")
    public String deleteBoard(@PathVariable("deleteId")Long id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/search")
    public String searchBoard(@RequestParam("type")String type,
                              @RequestParam("keyword")String keyword,
                              @PageableDefault(page = 0, size = 10, sort = "board_id",
                                      direction = Sort.Direction.DESC) Pageable pageable,
                              Model model) {
        List<BoardDto> boardDtoList = new ArrayList<>();
        boolean flag = false;

        System.out.println(pageable);

        switch (type) {
            case "movieNo":
                // 영화제목 DB 검색
                Page<Board> boardPage = boardService.viewBoard1(keyword,pageable);
                int totalPage = boardPage.getTotalPages();
                List<Integer> barNumbers = boardService.getPaginationBarNumbers(
                        pageable.getPageNumber(), totalPage);
                model.addAttribute("pagination", barNumbers);
                model.addAttribute("paging", boardPage);
                model.addAttribute("flag", flag);
                model.addAttribute("type", type);
                model.addAttribute("keyword", keyword);
                break;
            case "userNo":
                // 작성자로 DB 검색
                Page<Board> boardPage1 = boardService.viewBoard2(keyword,pageable);
                int totalPage1 = boardPage1.getTotalPages();
                List<Integer> barNumbers1 = boardService.getPaginationBarNumbers(
                        pageable.getPageNumber(), totalPage1);
                model.addAttribute("pagination", barNumbers1);
                model.addAttribute("paging", boardPage1);
                model.addAttribute("flag", flag);
                model.addAttribute("type", type);
                model.addAttribute("keyword", keyword);
                String l =keyword;

                break;
            default:
                // 전체 검색
                boardDtoList = boardRepository.searchQuery()
                        .stream()
                        .map(x -> BoardDto.fromBoardEntity(x))
                        .toList();
                break;
        }
        System.out.println(boardDtoList);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // 사용자 이름 가져오기
            Object principal = authentication.getPrincipal();

            if (principal instanceof PrincipalDetails) {
                PrincipalDetails userDetails = (PrincipalDetails) principal;
                User user = userDetails.getUser();
                Long userNo = user.getUserNo();
                model.addAttribute("userNo", userNo);
            } else {
                // 사용자 정보가 PrincipalDetails가 아닌 경우에 대한 처리
                // 예: 다른 방식으로 사용자 정보 추출
                model.addAttribute("userNo", null);
            }
        } else {
            model.addAttribute("userNo", null);
        }


        return "board/list";
    }
}
