package com.offco.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.offco.project.domain.ChatMember;
import com.offco.project.domain.ChatRoom;
import com.offco.project.domain.User;

// @Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    // List<ChatMember> findByUserId(User userId);

    // public List<ChatMember> findByRoomId(Long roomId);
    @Override

    List<ChatMember> findAll();

    List<ChatMember> findByUserId(User userId);

    List<ChatMember> findByroomId(ChatRoom chatroom);
}
