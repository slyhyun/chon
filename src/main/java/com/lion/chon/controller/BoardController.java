package com.lion.chon.controller;

import com.lion.chon.dto.BoardDTO;
import com.lion.chon.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public ResponseEntity<Page<BoardDTO>> getAllBoards(@RequestParam(defaultValue = "0") int page) {
        int size = 10; // 한 페이지에 10개의 게시글
        Page<BoardDTO> boardPage = boardService.getAllBoards(page, size);
        return ResponseEntity.ok(boardPage);
    }

    // 특정 글 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable int id) {
        BoardDTO boardDTO = boardService.getBoardById(id);
        return ResponseEntity.ok(boardDTO);
    }

    // 글 저장
    @PostMapping
    public ResponseEntity<String> createBoard(@RequestBody BoardDTO boardDTO, Principal principal) {
        String userId = principal.getName();
        boardService.createBoard(userId, boardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시글이 성공적으로 생성되었습니다.");
    }

    // 글 수정
    @PostMapping("/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable int id, @ModelAttribute BoardDTO boardDTO) {
        boardService.updateBoard(id, boardDTO);
        return ResponseEntity.ok("게시글이 성공적으로 업데이트 되었습니다.");
    }

    // 글 삭제
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok("Board deleted successfully");
    }

//    // 게시글 등록 페이지
//    @GetMapping("/new")
//    public String newBoard() {
//        return "newBoard"; // newBoard.html 템플릿 반환
//    }
//
//    // 게시글 수정 페이지
//    @GetMapping("/{id}/edit")
//    public String editBoard(@PathVariable int id, Model model) {
//        BoardDTO boardDTO = boardService.getBoardById(id);
//        model.addAttribute("board", boardDTO);
//        return "editBoard"; // editBoard.html 템플릿 반환
//    }
}
