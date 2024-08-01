package com.lion.chon.service;

import com.lion.chon.dto.BoardDTO;
import com.lion.chon.entity.BoardEntity;
import com.lion.chon.entity.UserEntity;
import com.lion.chon.repository.BoardRepository;
import com.lion.chon.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
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
    public BoardDTO createBoard(String userId, BoardDTO boardDTO) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            BoardEntity board = BoardEntity.builder()
                    .userId(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .phoneNum(user.getPhoneNum())
                    .age(user.getAge())
                    .gender(user.getGender())
                    .title(boardDTO.getTitle())
                    .contents(boardDTO.getContents())
                    .location(boardDTO.getLocation())
                    .postDate(LocalDateTime.now())
                    .build();
            BoardEntity savedBoard = boardRepository.save(board);
            return convertToDTO(savedBoard);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    // 글 수정
    public BoardDTO updateBoard(int id, BoardDTO boardDTO) {
        Optional<BoardEntity> existingBoard = boardRepository.findById(id);
        if (existingBoard.isPresent()) {
            BoardEntity board = existingBoard.get();
            board.setTitle(boardDTO.getTitle());
            board.setContents(boardDTO.getContents());
            board.setLocation(boardDTO.getLocation());
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
                .userId(board.getUserId())
                .name(board.getName())
                .email(board.getEmail())
                .phoneNum(board.getPhoneNum())
                .age(board.getAge())
                .gender(board.getGender())
                .title(board.getTitle())
                .contents(board.getContents())
                .location(board.getLocation())
                .postDate(board.getPostDate())
                .build();
    }
}
