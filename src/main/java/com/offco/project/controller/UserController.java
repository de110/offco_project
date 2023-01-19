package com.offco.project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.offco.project.domain.User;
import com.offco.project.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
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

    // @PostMapping("/login")
    // public UserDetails login(@RequestParam String username) {
    // return userService.loadUserByUsername(username);
    // } //

    @GetMapping("/loging")
    @ResponseBody
    public String currentUser(@AuthenticationPrincipal UserDetails user) {
        return user.getUsername();
    }

}
