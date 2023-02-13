package com.example.offco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.offco.domain.ChatRoom;
import com.example.offco.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    @PostMapping("/chat")
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    @GetMapping("/chatuser")
    public Optional<ChatRoom> checkChat(@RequestParam String userid, @RequestParam Long homeId) {
        return chatRoomRepository.findById(homeId);
    }

    @GetMapping("/chat")
    public Optional<ChatRoom> setChat(@RequestParam Long id) {
        return chatRoomRepository.findById(id);
    }
}
