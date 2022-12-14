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
    private final InviteRepository inviteRepository;

    // 방 생성
    @PostMapping("/api/room")
    public void createRoom(@RequestBody ChatRoom chatRoom, @AuthenticationPrincipal User user) {
        chatService.create(chatRoom, user.getUsername());
    }

    // 방 목록
    @GetMapping(path = "/api/rooms")
    public List<ChatMember> getRoomList(@RequestParam String username) {
        User user = userRepository.findByUsername(username).get();
        return chatMemberRepository.findByUserId(user);
    }

    // 채팅방 입장, 방 정보 가져오기
    @GetMapping(path = "/api/rooms", params = "roomname")
    public Optional<ChatRoom> goToRoom(String roomname) {
        return chatService.findRoom(roomname);
    }

    // //
    // @GetMapping("/api/room")
    // public Optional<ChatRoom> room(@RequestParam String id) {
    // return chatRepository.findById(1L);
    // }

    // 채팅, 유저 정보
    @GetMapping("/api/roomId")
    public List<ChatMember> getChatMembers(@RequestParam Long id) {
        ChatRoom chatRoom = chatRepository.findById(id).get();
        return chatMemberRepository.findByroomId(chatRoom);
    }

    @PatchMapping("/api/rooms/{id}")
    public ChatRoom inviteUrl(@RequestBody Invite invite, @PathVariable Long id) {
        return chatService.save(id, invite);
    }

    //
    @GetMapping(path = "/api/users")
    public User users(@RequestParam String username) {
        User user = userRepository.findByUsername(username).get();
        return user;
    }

    @GetMapping("/api/nuser")
    public ChatMember nuser(@RequestParam String token, @AuthenticationPrincipal User user) {
        ChatMember chatMember = new ChatMember();
        Invite inv = inviteRepository.findByInviteUrl(token).get();
        ChatRoom chatRoom = chatRepository.findByInvite(inv).get();
        chatMember.setRoomId(chatRoom);
        user = userRepository.findByUsername(user.getUsername()).get();
        chatMember.setUserId(user);
        return chatMemberRepository.save(chatMember);
    }

}