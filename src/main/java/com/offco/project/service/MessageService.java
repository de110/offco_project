package com.offco.project.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.offco.project.domain.ChatMessage;
import com.offco.project.repository.MessageRepository;

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