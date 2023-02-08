package com.example.offco.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offco.domain.Invite;
import com.example.offco.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Override
    List<Room> findAll();

    List<Room> findByRoomId(Long roomId);

    // Optional<Room> findByRoomname(String roomname);

    Optional<Room> findByToken(Invite invite);

    // // List<Room> findByInviteUrl(String inviteUrl);
    long deleteByRoomId(String roomId);
}
