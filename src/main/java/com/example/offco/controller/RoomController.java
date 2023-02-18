package com.example.offco.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.offco.domain.ChatMember;
import com.example.offco.domain.ChatRoom;
import com.example.offco.domain.Invite;
import com.example.offco.domain.Member;
import com.example.offco.domain.Room;
import com.example.offco.dto.RoomMemberDto;
import com.example.offco.repository.ChatMemberRepository;
import com.example.offco.repository.InviteRepository;
import com.example.offco.repository.MemberRepository;
import com.example.offco.repository.RoomRepository;
import com.example.offco.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final MemberRepository memberRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final InviteRepository inviteRepository;

    // 방 생성
    // @PostMapping("/api/room")
    // public Room createRoom(@RequestBody Room Room) {
    // return roomService.create(Room);
    // }

    @PostMapping("/api/room")
    public ChatMember createRoom(@RequestBody RoomMemberDto room) {
        Room rRoom = Room.save(room.getRoomname(), room.getManager());
        roomRepository.save(rRoom);

        ChatMember cMember = new ChatMember();
        cMember.setRoomId(rRoom);
        cMember.setMemberId(room.getManager());

        return chatMemberRepository.save(cMember);
    }

    @PostMapping("/room/{roomId}/user")
    public ChatMember setMember(@PathVariable Long roomId, @RequestBody Member memberId) {
        ChatMember chatMember = new ChatMember();
        Room Room = roomRepository.findById(roomId).get();
        chatMember.setMemberId(memberId.getMemberId());
        chatMember.setRoomId(Room);
        return chatMemberRepository.save(chatMember);
    }

    // 방 이름 설정
    @GetMapping("/room/{roomId}")
    public Optional<Room> setRoomName(@PathVariable Long roomId) {
        return roomRepository.findById(roomId);
    }

    @GetMapping("/user")
    public List<ChatMember> setRoomUser(@RequestParam Long roomId) {
        Room Room = roomRepository.findById(roomId).get();
        return chatMemberRepository.findByRoomId(Room);
    }

    @GetMapping("/api/rooms")
    public List<Room> setRoomList(@RequestParam Long roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    @PatchMapping("/room/{id}")
    public Room inviteUrl(@RequestBody Invite invite, @PathVariable Long id) {
        return roomService.savetoken(id, invite);
    }

    @GetMapping("/room")
    public Long checkToken(@RequestParam String token) {
        Invite inv = inviteRepository.findByToken(token).get();
        return roomRepository.findByToken(inv).get().getRoomId();
    }

}
