package com.offco.project.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offco.project.BadRequestException;
import com.offco.project.domain.User;
import com.offco.project.repository.UserRepository;
import com.offco.project.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 회원 가입
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        user = User.builder().username(user.getUsername()).userpassword(passwordEncoder.encode(user.getPassword()))
                .role("USER").build();
        return userService.saveUser(user);
    }

    // 아이디 중복 체크
    @GetMapping("/signup")
    public boolean checkIdDuplicate(@RequestParam(value = "userId") String username) {
        return userService.existByUsername(username);

    }

    @GetMapping("/login")
    public UserDetails login(@RequestParam(value = "userId") String userId) {
        return userService.loadUserByUsername(userId);
    }

    // @GetMapping("/login")
    // public String login(String userId) {
    //     // return userService.loadUserByUsername(userId);
    //     return "";
    // }

    

    // @GetMapping("/signup")
    // public boolean checkIdDuplicate(){
    //     // User user = new User();
    //     if (userRepository.findByUsername("user")) {
    //         return false;
    //     }
    //     return true;
    //     //     return true;
    //     // else return false;
    // }

}
