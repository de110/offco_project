package com.offco.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offco.project.domain.ChatMember;
import com.offco.project.domain.User;

@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember,Long> {
    List<ChatMember> findByUserId(User userId);
    // List<ChatMember> findByRoomId(Long roomId);


}
