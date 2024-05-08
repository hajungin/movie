package com.example.movie.controller;

import com.example.movie.dto.BoardDto;
import com.example.movie.repository.BoardRepository;
import com.example.movie.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    public BoardController(BoardRepository boardRepository, BoardService boardService) {
        this.boardRepository = boardRepository;
        this.boardService = boardService;
    }

    @GetMapping("board_view")
    public String viewAll(Model model) {
        List<BoardDto> boardDtoList = boardService.viewAllBoard();
        model.addAttribute("boardDto", boardDtoList);
        return "board/board_view";
    }

    @GetMapping("/board_insert")
    public String showBoardInsertForm(Model model) {
        model.addAttribute("boardDto", new BoardDto());
        return "board/board_insert";
    }

    @PostMapping("board_insert")
    public String boardInsert(@ModelAttribute("boardDto")BoardDto dto) {
        boardService.boardInsert(dto);
        return "redirect:/board/board_view";
    }

//    @GetMapping("/updade")
//    public String boardUpdate(@RequestParam("updateId")Long id,
//                              Model model) {
//    }
}
