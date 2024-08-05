package com.lion.chon.controller;

import com.lion.chon.dto.ApplicationDTO;
import com.lion.chon.entity.ApplicationEntity;
import com.lion.chon.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService){
        this.applicationService = applicationService;
    }

    // 신청
    @PostMapping("/{id}")
    public ResponseEntity<ApplicationDTO> application(@PathVariable int id){
        return ResponseEntity.ok(applicationService.application(id));
    }
    
    // 특정 유저 모든 신청 조회
    @GetMapping("/my")
    public ResponseEntity<List<ApplicationEntity>> getAllApplicationsByUser() {
        List<ApplicationEntity> applications = applicationService.getAllApplicationsByUser();
        return ResponseEntity.ok(applications);
    }

    // 특정 게시글 모든 신청 조회
    @GetMapping("/{id}")
    public ResponseEntity<List<ApplicationEntity>> getAllApplicationsByBoard(@PathVariable int id) {
        List<ApplicationEntity> applications = applicationService.getAllApplicationsByBoard(id);
        return ResponseEntity.ok(applications);
    }

}
