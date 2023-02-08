package com.example.offco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offco.domain.ChatMessage;

public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRoomId(String roomId);
}