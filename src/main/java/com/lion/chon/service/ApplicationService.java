package com.lion.chon.service;

import com.lion.chon.dto.ApplicationDTO;
import com.lion.chon.entity.ApplicationEntity;
import com.lion.chon.entity.BoardEntity;
import com.lion.chon.entity.UserEntity;
import com.lion.chon.repository.ApplicationRepository;
import com.lion.chon.repository.BoardRepository;
import com.lion.chon.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final ApplicationRepository applicationRepository;

    public ApplicationService(BoardRepository boardRepository, UserRepository userRepository, ApplicationRepository applicationRepository){
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
    }

    // 신청
    public ApplicationDTO application(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        String username = userDetails.getUsername();

        ApplicationEntity applicationEntity = new ApplicationEntity();

        Optional<BoardEntity> existingBoard = boardRepository.findById(id);
        Optional<UserEntity> existingUser = userRepository.findById(username);

        if(existingBoard.isPresent() && existingUser.isPresent()){ // TODO: 이미 신청한 사람인지 조건 체크, 신청자 초과 됐는지 백엔드에서도 체크
            BoardEntity board = existingBoard.get();
            UserEntity user = existingUser.get();
            applicationEntity.setBoardEntity(board);
            applicationEntity.setUserEntity(user);

            int applicationPeople = board.getApplicationPeople();
            applicationPeople += 1;
            board.setApplicationPeople(applicationPeople);

            ApplicationDTO applicationDTO = new ApplicationDTO();
            applicationDTO.setMaximumPeople(board.getMaximumPeople());
            applicationDTO.setApplicationPeople(board.getApplicationPeople());
            
            boardRepository.save(board);
            applicationRepository.save(applicationEntity);
            
            return applicationDTO; // 현재 인원과 최대 인원을 프론트엔드로 보내 프론트엔드 단에서 초과 시 경고 문구 출력하도록
        }else{
            throw new RuntimeException("게시글이나 유저가 존재하지 않습니다.");
        }

    }
    
    // 특정 유저 모든 신청 조회
    public List<ApplicationEntity> getAllApplicationsByUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        String username = userDetails.getUsername();
        return applicationRepository.findByUserEntity_Id(username);
    }

    
    // 특정 게시글 모든 신청 조회
    public List<ApplicationEntity> getAllApplicationsByBoard(int id) {
        return applicationRepository.findByBoardEntity_Id(id);
    }
}
