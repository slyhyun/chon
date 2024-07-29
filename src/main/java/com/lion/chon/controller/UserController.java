package com.lion.chon.controller;

import com.lion.chon.dto.UserDTO;
import com.lion.chon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public UserDTO loginUser(@RequestBody UserDTO userDTO) {
        return userService.loginUser(userDTO.getId(), userDTO.getPassword());
    }
}
