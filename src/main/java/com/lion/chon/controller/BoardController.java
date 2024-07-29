package com.lion.chon.controller;

import com.lion.chon.dto.BoardDTO;
import com.lion.chon.service.ApplicationService;
import com.lion.chon.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 게시판 컨트롤러
@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    private final ApplicationService applicationService;

    @Autowired
    public BoardController(BoardService boardService, ApplicationService applicationService) {
        this.boardService = boardService;
        this.applicationService = applicationService;
    }

    // 전체 글 조회
    @GetMapping
    public List<BoardDTO> getAllBoards() {
        return boardService.getAllBoards();
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

    // 신청
    @PostMapping("/application/{id}")
    public void applicationBoard(@PathVariable int id) {applicationService.application(id);};


}
