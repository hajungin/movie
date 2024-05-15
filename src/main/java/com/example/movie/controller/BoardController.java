package com.example.movie.controller;

import com.example.movie.dto.BoardDto;
import com.example.movie.dto.MoviesDto;
import com.example.movie.entity.Board;
import com.example.movie.repository.BoardRepository;
import com.example.movie.service.BoardService;
import com.example.movie.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("board")
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final MovieService movieService;

    public BoardController(BoardRepository boardRepository, BoardService boardService, MovieService movieService) {
        this.boardRepository = boardRepository;
        this.boardService = boardService;
        this.movieService = movieService;
    }

//    @GetMapping("list")
//    public String mainList(Model model,
//                           @PageableDefault(page = 0, size = 10, sort = "boardId",
//                           direction = Sort.Direction.ASC)Pageable pageable) {
//        Page<Board> boardPage = boardService.pageList(pageable);
//
//        int totalPage = boardPage.getTotalPages();
//        List<Integer> barNumbers = boardService.getPaginationBarNumbers(
//                pageable.getPageNumber(), totalPage);
//        model.addAttribute("pagination", barNumbers);
//        model.addAttribute("paging", boardPage);
//        return "board/list";
//    }

    @GetMapping("list")
    public String mainList(Model model) {
        List<BoardDto> boardDtoList = boardService.viewAllBoard();
        model.addAttribute("boardDto", boardDtoList);
        return "board/list";
    }

    @GetMapping("insert")
    public String boardInsertForm(Model model) {
        List<MoviesDto> moviesDtoList = movieService.getAllMovies();
        model.addAttribute("moviesDtoList", moviesDtoList);
        model.addAttribute("boardDto", new BoardDto());
        return "board/insert";
    }

    @PostMapping("insert")
    public String boardInsertView(@ModelAttribute("boardDto")BoardDto dto) {
        boardService.insert(dto);
        boardService.updateGoodPoint(dto.getMovieNo());
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
}
