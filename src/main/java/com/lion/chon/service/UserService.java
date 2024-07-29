package com.lion.chon.service;

import com.lion.chon.dto.UserDTO;
import com.lion.chon.entity.UserEntity;
import com.lion.chon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 사용자 등록
    public UserDTO registerUser(UserDTO userDTO) {
        // ID 중복 검사
        if (userRepository.existsById(userDTO.getId())) {
            throw new RuntimeException("ID already exists");
        }

        // ID가 "admin"인 경우 role을 ADMIN으로, 그렇지 않으면 MEMBER로 설정
        UserEntity.Role role = "admin".equalsIgnoreCase(userDTO.getId()) ? UserEntity.Role.ADMIN : UserEntity.Role.MEMBER;

        UserEntity user = UserEntity.builder()
                .id(userDTO.getId())
                .password(passwordEncoder.encode(userDTO.getPassword())) // 비밀번호 인코딩
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .phoneNum(userDTO.getPhoneNum())
                .residence(userDTO.getResidence())
                .birth(userDTO.getBirth())
                .age(userDTO.getAge())
                .gender(userDTO.getGender())
                .registerDate(LocalDateTime.now())
                .role(role)
                .build();

        UserEntity savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    // 사용자 로그인
    public UserDTO loginUser(String id, String password) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            return convertToDTO(userOptional.get());
        }

        throw new RuntimeException("Invalid ID or password");
    }

    private UserDTO convertToDTO(UserEntity user) {
        return UserDTO.builder()
                .id(user.getId())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNum(user.getPhoneNum())
                .residence(user.getResidence())
                .birth(user.getBirth())
                .age(user.getAge())
                .gender(user.getGender())
                .registerDate(user.getRegisterDate())
                .role(user.getRole().name())
                .build();
    }
}
