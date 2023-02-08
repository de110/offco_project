package com.example.offco.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offco.domain.ChatMember;
import com.example.offco.domain.Member;
import com.example.offco.domain.Room;

// @Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    @Override

    List<ChatMember> findAll();

    List<ChatMember> findByMemberId(String member);

    List<ChatMember> findByRoomId(Room Room);

    // Optional<ChatMember> findByRoomId(Room Room);

}
