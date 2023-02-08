package com.example.offco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offco.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByRoomId(Long id);
}
