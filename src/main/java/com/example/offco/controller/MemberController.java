package com.example.offco.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.offco.domain.ChatMember;
import com.example.offco.domain.Member;
import com.example.offco.dto.MemberLoginRequestDto;
import com.example.offco.dto.TokenInfo;
import com.example.offco.repository.ChatMemberRepository;
import com.example.offco.repository.MemberRepository;
import com.example.offco.repository.RoomRepository;
import com.example.offco.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// https://gksdudrb922.tistory.com/217
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final ChatMemberRepository chatMemberRepository;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(memberId, password);
        return tokenInfo;
    }

    // @PostMapping("/login")
    // public MemberLoginRequestDto login(@RequestBody MemberLoginRequestDto
    // memberLoginRequestDto) {
    // String memberId = memberLoginRequestDto.getMemberId();
    // String password = memberLoginRequestDto.getPassword();
    // // Boolean setlogin = memberLoginRequestDto.getSetlogin();
    // // TokenInfo tokenInfo = memberService.login(memberId, password);
    // memberService.login(memberId, password);
    // return memberLoginRequestDto;
    // }

    @GetMapping("/login")
    public String rememberId(@RequestParam String id) {
        return "a";
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @PostMapping("/signup")
    public Member signup(@RequestBody Member member) {
        return memberService.signup(member);
    }

    @GetMapping("/signup")
    public Boolean checkUserId(@RequestParam String userid) {
        return memberRepository.existsByMemberId(userid);
    }

    @GetMapping(path = "/rooms", params = "username")
    public List<ChatMember> getRoomList(@RequestParam String username) {
        // Member user = memberRepository.findByMemberId(username).get();
        return chatMemberRepository.findByMemberId(username);
        // return chatMemberRepository.findAll();
    }

}