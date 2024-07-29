package com.lion.chon.controller;

import com.lion.chon.dto.BoardDTO;
import com.lion.chon.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 페이징된 전체 글 조회
    @GetMapping
    public Page<BoardDTO> getAllBoards(@RequestParam(defaultValue = "0") int page) {
        int size = 10; // 한 페이지에 10개의 게시글
        return boardService.getAllBoards(page, size);
    }

    // 특정 글 조회
    @GetMapping("/{id}")
    public BoardDTO getBoardById(@PathVariable int id) {
        return boardService.getBoardById(id);
    }

    // 글 저장
    @PostMapping
    public BoardDTO createBoard(@RequestBody BoardDTO boardDTO) {
        return boardService.createBoard(boardDTO);
    }

    // 글 수정
    @PutMapping("/{id}")
    public BoardDTO updateBoard(@PathVariable int id, @RequestBody BoardDTO boardDTO) {
        return boardService.updateBoard(id, boardDTO);
    }

    // 글 삭제
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
    }
}
