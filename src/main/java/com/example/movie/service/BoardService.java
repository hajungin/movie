package com.example.movie.service;

import com.example.movie.config.PrincipalDetails;
import com.example.movie.dto.BoardDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
import com.example.movie.entity.User;
import com.example.movie.repository.BoardRepository;
import com.example.movie.repository.MoviesRepository;
import com.example.movie.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

//    public List<BoardDto> viewAllBoard(Pageable pageable) {
//        List<BoardDto> boardDtoList = new ArrayList<>();
//        return boardRepository.findAll(pageable)
//                .stream()
//                .map(x -> BoardDto.fromBoardEntity(x))
//                .toList();
//    }

    public Page<BoardDto> viewAllBoard(Pageable pageable) {
        return boardRepository.findAll(pageable)
                .map(BoardDto::fromBoardEntity);
    }

    public Page<Board> viewBoard(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public void insert(BoardDto dto) {
        Movies movies = em.find(Movies.class, dto.getMovieNo());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        User user1 = userDetails.getUser();
        Long userNo = user1.getUserNo();
        User user = em.find(User.class, userNo);


        Board board = Board.builder()
                .boardId(dto.getBoardId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .movies(movies)
                .user(user)
                .build();
        boardRepository.save(board);
    }

    public void update(BoardDto boardDto) {
        Board board = Board.builder()
                .boardId(boardDto.getBoardId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .movies(Movies.builder().movieNo(boardDto.getMovieNo()).build())
                .user(User.builder().userNo(boardDto.getUserNo()).build())
                .build();
        boardRepository.save(board);
    }

    public void updateGoodPoint(Long movieNo){
        String sql1 = "SELECT AVG(b.goodPoint) FROM Board b WHERE b.movies.movieNO:movieNo";
        TypedQuery<Double> query1 = em.createQuery(sql1, Double.class).setParameter("movieNo", movieNo);

        String sql2 = "SELECT m FROM Movies m WHERE m.movieNo:movieNo";
        TypedQuery<Movies> query2 = em.createQuery(sql2, Movies.class).setParameter("movieNo", movieNo);
        Movies movies = query2.getSingleResult();
        movies.setGoodPointAvg(query1.getSingleResult());
        em.persist(movies);
            }


    @Transactional
    public void delete(Long id) {
        Board board = em.find(Board.class, id);
        board.setMovies(null);
        board.setUser(null);
        em.remove(board);
    }

    public List<BoardDto> findAll() {
        List<BoardDto> boardDtoList = new ArrayList<>();
        return boardRepository.findAll()
                .stream()
                .map(x->BoardDto.fromBoardEntity(x))
                .toList();
    }

    @Autowired
    EntityManager em;
    public List<Board> findAllem() {
        String sql = "SELECT b FROM Board b";
        Query query = em.createQuery(sql);
        List<Board> boardList = query.getResultList();
        return boardList;
    }

    public Page<Board> pageList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    private static final int BAR_LENGTH=5;
    public List<Integer> getPaginationBarNumbers(int pageNumber, int totalPage) {
        int startNumber = Math.max(pageNumber-(BAR_LENGTH/2),0);

        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPage);

        return IntStream.range(startNumber,endNumber).boxed().toList();
    }

    public BoardDto getOneBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .map(x->BoardDto.fromBoardEntity(x))
                .orElse(null);
    }

    public List<Board> movie() {
        String sql = "SELECT b.movieTitle  FROM Board b";
        TypedQuery<Board> query = em.createQuery(sql, Board.class);

        List<Board> movie = query.getResultList();
        return movie;
    }

    public List<Board> title(Long movieNo) {
        String sql = "SELECT b FROM Board b WHERE b.movies.movieNo = :movieNo";
        TypedQuery<Board> query = em.createQuery(sql, Board.class).setParameter("movieNo", movieNo);
        return query.getResultList();
    }

}
