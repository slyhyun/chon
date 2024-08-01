package com.lion.chon.controller;

import com.lion.chon.dto.BoardDTO;
import com.lion.chon.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 페이징된 전체 글 조회
    @GetMapping
    public String getAllBoards(@RequestParam(defaultValue = "0") int page, Model model) {
        int size = 10; // 한 페이지에 10개의 게시글
        Page<BoardDTO> boardPage = boardService.getAllBoards(page, size);
        model.addAttribute("boards", boardPage.getContent());
        return "boards"; // boards.html 템플릿 반환
    }

    // 특정 글 조회
    @GetMapping("/{id}")
    public String getBoardById(@PathVariable int id, Model model) {
        BoardDTO boardDTO = boardService.getBoardById(id);
        model.addAttribute("board", boardDTO);
        return "boardDetail"; // boardDetail.html 템플릿 반환
    }

    // 글 저장
    @PostMapping
    public String createBoard(@ModelAttribute BoardDTO boardDTO, Principal principal) {
        String userId = principal.getName();
        boardService.createBoard(userId, boardDTO);
        return "redirect:/boards";
    }

    // 글 수정
    @PostMapping("/{id}")
    public String updateBoard(@PathVariable int id, @ModelAttribute BoardDTO boardDTO) {
        boardService.updateBoard(id, boardDTO);
        return "redirect:/boards/" + id;
    }

    // 글 삭제
    @PostMapping("/{id}/delete")
    public String deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }

    // 게시글 등록 페이지
    @GetMapping("/new")
    public String newBoard() {
        return "newBoard"; // newBoard.html 템플릿 반환
    }

    // 게시글 수정 페이지
    @GetMapping("/{id}/edit")
    public String editBoard(@PathVariable int id, Model model) {
        BoardDTO boardDTO = boardService.getBoardById(id);
        model.addAttribute("board", boardDTO);
        return "editBoard"; // editBoard.html 템플릿 반환
    }
}
