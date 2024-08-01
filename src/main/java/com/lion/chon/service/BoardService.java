package com.lion.chon.service;

import com.lion.chon.dto.BoardDTO;
import com.lion.chon.entity.BoardEntity;
import com.lion.chon.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 페이징된 전체 글 조회
    public Page<BoardDTO> getAllBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return boardRepository.findAll(pageable).map(this::convertToDTO);
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
                .location(board.getLocation())
                .postDate(board.getPostDate())
                .build();
    }

    private BoardEntity convertToEntity(BoardDTO boardDTO) {
        return BoardEntity.builder()
                .id(boardDTO.getId())
                .email(boardDTO.getEmail())
                .title(boardDTO.getTitle())
                .contents(boardDTO.getContents())
                .location(boardDTO.getLocation())
                .postDate(boardDTO.getPostDate())  // DTO에서 제공된 시간을 사용
                .build();
    }
}
