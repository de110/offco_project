package com.offco.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offco.project.domain.ChatRoom;

public interface ChatRepository extends JpaRepository<ChatRoom,Long>{
    @Override
    List<ChatRoom> findAll();

    List<ChatRoom> findByRoomId(Long roomId);

    List<ChatRoom> findByInviteUrl(String inviteUrl);
    
    long deleteByRoomId(String roomId);
}
