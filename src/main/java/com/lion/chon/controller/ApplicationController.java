package com.lion.chon.controller;

import com.lion.chon.entity.ApplicationEntity;
import com.lion.chon.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService){
        this.applicationService = applicationService;
    }

    // 신청
    @PostMapping("/{id}")
    public void application(@PathVariable int id){
        applicationService.application(id);
    }
    
    // 특정 유저 모든 신청 조회
    @GetMapping("/my")
    public List<ApplicationEntity> getAllApplicationsByUser() {
        return applicationService.getAllApplicationsByUser();
    }

    // 특정 게시글 모든 신청 조회
    @GetMapping("/{id}")
    public List<ApplicationEntity> getAllApplicationsByBoard(@PathVariable int id) {
        return applicationService.getAllApplicationsByBoard(id);
    }

}
