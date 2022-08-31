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

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final ChatRepository chatRepository;

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
    public Optional<ChatRoom> roomname(@RequestParam Long roomId) {
        // return chatService.findByRoomId(1L);
        return chatRepository.findById(roomId);

    }

    @GetMapping("/room/users")
    @ResponseBody
    public List<ChatMember> roomUsers(@RequestParam ChatRoom chatroom) {
        return chatMemberRepository.findByroomId(chatroom);
    }

    @GetMapping("/loging")
    @ResponseBody
    public String currentUser(@AuthenticationPrincipal User user) {
        // ChatMember chatMember = new ChatMember(); // room member 설정
        // chatMember = chatMemberRepository.findById(1L).get();
        // chatMember.setUserId(user);
        return user.getUsername();
    }

    // @GetMapping("/roomId")
    // @ResponseBody
    // public List<ChatMember> rooms(@RequestParam(value = "id") Long id) {
    //     return chatService.findAllMembers(id);
    // }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public Long create(@RequestBody ChatRoom chatRoom,@AuthenticationPrincipal User user) {
        chatService.create(chatRoom, user);
        return user.getId();
    }

    // RoomId 로 특정 채팅방 조회
    // @GetMapping("/roominfo")
    // @ResponseBody
    // public List<ChatRoom> getRoomParam(@RequestParam Long roomId) {
    //     return chatService.findByRoomId(roomId);
    // }

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


    // @GetMapping("/testmember")
    // @ResponseBody
    // public List<ChatMember> getAllMember(@RequestParam Long id) {
    //     // return chatService.findAllMembers(1L);
    //     // User user = userRepository.findById(1L).get();
    //     // return chatMemberRepository.findByUserId(user);
    //     return chatMemberRepository.findByRoomId(id);
    //     // "id":1
    // }

    @GetMapping("/allmember")
    @ResponseBody
    public List<ChatMember> gallme() {
        return chatService.allmem();
    }


    @GetMapping("/loginuser")
    @ResponseBody
    public String currentUserName() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return users.getUsername();
    }
    
}