package com.offco.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offco.project.domain.ChatRoom;
import com.offco.project.domain.Invite;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
    @Override
    List<ChatRoom> findAll();

    List<ChatRoom> findByRoomId(Long roomId);

    Optional<ChatRoom> findByRoomname(String roomname);

    List<ChatRoom> findByInvite(Invite invite);

    // List<ChatRoom> findByInviteUrl(String inviteUrl);
    long deleteByRoomId(String roomId);
}
