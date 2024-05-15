package com.example.movie.service;

import com.example.movie.dto.BoardDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
import com.example.movie.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<BoardDto> viewAllBoard() {
        List<BoardDto> boardDtoList = new ArrayList<>();
        return boardRepository.findAll()
                .stream()
                .map(x -> BoardDto.fromBoardEntity(x))
                .toList();
    }

    public void insert(BoardDto dto) {
        Board board = Board.builder()
                .boardId(dto.getBoardId())
                .title(dto.getTitle())
                .content(dto.getContent())
//                .movies(dto.getMovieNo())
//                .userId(dto.getUser().)
//                .goodPoint(dto.getGoodPoint())
//                .movieTitle(dto.getMovieTitle())
                .build();
        boardRepository.save(board);
    }

//    public BoardDto getOneBoard(Long id) {
//        return boardRepository.findById(id)
//                .map(x -> BoardDto.fromBoardEntity(x))
//                .orElse(null);
//    }

    public void update(BoardDto boardDto) {
        Board board = Board.builder()
                .boardId(boardDto.getBoardId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
//                .userId(boardDto.getUser())
//                .goodPoint(boardDto.getGoodPoint())
//                .movieTitle(boardDto.getMovieTitle())
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

//        public void delete(Long id) {
//            Board board = boardRepository.findById(id).orElse(null);
//            if (board != null) {
//                board.setMovies(null); // movies 필드를 null로 설정
//                board.setUser(null);   // user 필드를 null로 설정
//                boardRepository.deleteById(id); // board 엔티티 삭제
//            }
//        }


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


    public Board title(Long boardId) {
        String sql = "SELECT b FROM Board b WHERE b.boardId = :boardId";
        TypedQuery<Board> query = em.createQuery(sql, Board.class).setParameter("boardId", boardId);
        return query.getSingleResult();
    }

}
