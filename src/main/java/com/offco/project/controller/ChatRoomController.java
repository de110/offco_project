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
import com.offco.project.domain.User;
import com.offco.project.repository.ChatMemberRepository;
import com.offco.project.repository.ChatRepository;
import com.offco.project.repository.UserRepository;
import com.offco.project.service.ChatService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
// @RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;
    private Map<String, ChatRoom> chatRooms;
    private final UserRepository userRepository;
    private final ChatMemberRepository chatMemberRepository;


    @PostConstruct
    // 의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    @GetMapping("/room")
    @ResponseBody
    public List<ChatRoom> roomname(@RequestParam(value="id") Long id) {
        return chatService.findByRoomId(id);
    }

    @GetMapping("/roomId")
    @ResponseBody
    public List<ChatMember> rooms(@RequestParam(value = "id") User user) {
        return chatService.findAllMembers(user);
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom create(@RequestBody ChatRoom chatRoom ) {
        return chatService.create(chatRoom);
    }

    // RoomId 로 특정 채팅방 조회
    @GetMapping("/roominfo")
    @ResponseBody
    public List<ChatRoom> getRoomParam(@RequestParam Long roomId) {
        return chatService.findByRoomId(roomId);
    }

    @DeleteMapping("/room/delete")
    public void deleteChatRoom(@RequestParam String roomId) {
        chatService.deleteChatRoom(roomId);
    }

    @PatchMapping("/rooms/invite")
    @ResponseBody
    public ChatRoom inviteToken(@RequestBody ChatRoom chatRoom) {
        return chatService.save(chatRoom.getInviteUrl());
    }

    @GetMapping("/invite")
    @ResponseBody
    public List<ChatRoom> checkToken(@RequestParam String url) {
        return chatService.findByInviteUrl(url);
    }


    @GetMapping("/testmember")
    @ResponseBody
    public List<ChatMember> getAllMember(@RequestBody User user) {
        // return chatService.findAllMembers(1L);
        // user = userRepository.findById(user.getId()).get();
        return chatMemberRepository.findByUserId(user);
        // "id":1
    }

    @GetMapping("/allmember")
    @ResponseBody
    public List<ChatMember> gallme() {
        return chatService.allmem();
    }   
    
}