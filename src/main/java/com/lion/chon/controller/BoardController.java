package com.lion.chon.controller;

import com.lion.chon.dto.BoardDTO;
import com.lion.chon.entity.UserEntity;
import com.lion.chon.service.BoardService;
import com.lion.chon.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public BoardController(BoardService boardService, CustomUserDetailsService userDetailsService) {
        this.boardService = boardService;
        this.userDetailsService = userDetailsService;
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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        UserEntity user = userDetailsService.loadUserEntityByUsername(userDetails.getUsername());

        boardDTO.setMine(user.getId().equals(boardDTO.getUserId()));
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
    public ResponseEntity<String> updateBoard(@PathVariable int id, @RequestBody BoardDTO boardDTO) {
        BoardDTO existBoardDTO = boardService.getBoardById(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        UserEntity user = userDetailsService.loadUserEntityByUsername(userDetails.getUsername());

        if(existBoardDTO.getUserId().equals(user.getId()) || user.getRole().equals(UserEntity.Role.ADMIN)){
            boardService.updateBoard(id, boardDTO);
            return ResponseEntity.ok("게시글이 성공적으로 업데이트 되었습니다.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글을 수정할 권한이 없습니다.");
        }


    }

    // 글 삭제
    @PostMapping("/{id}/delete")
    public ResponseEntity<String> deleteBoard(@PathVariable int id) {
        BoardDTO existBoardDTO = boardService.getBoardById(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        UserEntity user = userDetailsService.loadUserEntityByUsername(userDetails.getUsername());

        if(existBoardDTO.getUserId().equals(user.getId()) || user.getRole().equals(UserEntity.Role.ADMIN)){
            boardService.deleteBoard(id);
            return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글을 삭제할 권한이 없습니다.");
        }

    }
}
