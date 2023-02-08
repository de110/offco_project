package com.example.offco.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.offco.domain.ChatMessage;
import com.example.offco.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    // 채팅방 메시지 불러오기
    public List<ChatMessage> findByRoomId(String roomId) {
        return messageRepository.findByRoomId(roomId);
    }
}