package com.lion.chon.service;

import com.lion.chon.dto.BoardDTO;
import com.lion.chon.entity.ApplicationEntity;
import com.lion.chon.entity.BoardEntity;
import com.lion.chon.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 전체 글 조회
    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 글 조회
    public BoardDTO getBoardById(int id) {
        Optional<BoardEntity> board = boardRepository.findById(id);
        return board.map(this::convertToDTO).orElse(null);
    }

    // 글 저장
    public BoardDTO createBoard(BoardDTO boardDTO) {
        BoardEntity board = convertToEntity(boardDTO);
        board.setPostDate(LocalDateTime.now());  // 현재 시간으로 게시일 설정
        BoardEntity savedBoard = boardRepository.save(board);
        return convertToDTO(savedBoard);
    }

    // 글 수정
    public BoardDTO updateBoard(int id, BoardDTO boardDTO) {
        Optional<BoardEntity> existingBoard = boardRepository.findById(id);
        if (existingBoard.isPresent()) {
            BoardEntity board = existingBoard.get();
            board.setTitle(boardDTO.getTitle());
            board.setContents(boardDTO.getContents());
            // 필요한 경우 다른 필드도 업데이트

            BoardEntity updatedBoard = boardRepository.save(board);
            return convertToDTO(updatedBoard);
        } else {
            return null; // 또는 적절한 예외 처리
        }
    }

    // 글 삭제
    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }

    private BoardDTO convertToDTO(BoardEntity board) {
        return BoardDTO.builder()
                .id(board.getId())
                .email(board.getEmail())
                .title(board.getTitle())
                .contents(board.getContents())
                .postDate(board.getPostDate())
                .build();
    }

    private BoardEntity convertToEntity(BoardDTO boardDTO) {
        return BoardEntity.builder()
                .id(boardDTO.getId())
                .email(boardDTO.getEmail())
                .title(boardDTO.getTitle())
                .contents(boardDTO.getContents())
                .postDate(boardDTO.getPostDate())  // DTO에서 제공된 시간을 사용
                .build();
    }


}
