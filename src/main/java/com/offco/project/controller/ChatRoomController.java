package com.offco.project.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import com.offco.project.domain.ChatMember;
import com.offco.project.domain.ChatRoom;
import com.offco.project.domain.Invite;
import com.offco.project.domain.User;
import com.offco.project.repository.ChatMemberRepository;
import com.offco.project.repository.ChatRepository;
import com.offco.project.repository.InviteRepository;
import com.offco.project.repository.UserRepository;
import com.offco.project.service.ChatService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
// @RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;
    private final UserRepository userRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final ChatRepository chatRepository;

    @PostMapping("/api/room")
    public void create(@RequestBody ChatRoom chatRoom, @AuthenticationPrincipal UserDetails user) {
        chatService.create(chatRoom, user.getUsername());
    }

    @GetMapping(path = "/api/rooms")
    public List<ChatMember> rooms(@RequestParam String username) {
        User user = userRepository.findByUsername(username).get();
        return chatMemberRepository.findByUserId(user);
    }

    @GetMapping(path = "/rooms", params = "roomname")
    public List<ChatRoom> goToRoom() {
        return chatService.findAllRoom();
    }

    @GetMapping("/api/room")
    public Optional<ChatRoom> room(@RequestParam String id) {
        return chatRepository.findById(1L);
    }

    @GetMapping("/api/roomId")
    public List<ChatMember> roomId(@RequestParam Long id) {
        ChatRoom chatRoom = chatRepository.findById(id).get();
        return chatMemberRepository.findByroomId(chatRoom);
    }

    @PatchMapping("/api/rooms/invite")
    public ChatRoom inviteUrl(@RequestBody Invite invite) {
        return chatService.save(invite);
    }

    @GetMapping(path = "/api/users")
    public User users(@RequestParam String username) {
        User user = userRepository.findByUsername(username).get();
        return user;
    }

    @PostMapping("/api/nuser")
    public ChatMember nuser(@RequestParam String username) {
        ChatMember chatMember = new ChatMember();
        ChatRoom chatRoom = chatRepository.findById(1L).get();
        chatMember.setRoomId(chatRoom);
        User user = userRepository.findByUsername(username).get();
        chatMember.setUserId(user);
        return chatMemberRepository.save(chatMember);
    }

}