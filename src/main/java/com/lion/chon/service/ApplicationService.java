package com.lion.chon.service;

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
    public void application(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        String username = userDetails.getUsername();

        ApplicationEntity applicationEntity = new ApplicationEntity();

        BoardEntity board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글에 접근하였습니다."));
        applicationEntity.setBoardEntity(board);

        UserEntity user = userRepository.findById(username).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        applicationEntity.setUserEntity(user);
        applicationRepository.save(applicationEntity);

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
