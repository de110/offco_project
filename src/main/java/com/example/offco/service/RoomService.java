package com.example.offco.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.offco.domain.ChatMember;
import com.example.offco.domain.ChatMessage;
import com.example.offco.domain.Invite;
import com.example.offco.domain.Room;
import com.example.offco.repository.ChatMemberRepository;
import com.example.offco.repository.InviteRepository;
import com.example.offco.repository.MemberRepository;
import com.example.offco.repository.MessageRepository;
import com.example.offco.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final MemberRepository memberRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final RoomRepository roomRepository;
    private final InviteRepository inviteRepository;
    private final MessageRepository messageRepository;

    // 방 생성
    // @Transactional
    // public Room create(Room Room) {
    // ChatMember cmember = new ChatMember();
    // chatMemberRepository.save(cmember);
    // return roomRepository.save(Room);
    // }

    /* */

    // @Transactional
    // public Room create(String memberId, String roomId) {

    // }

    /* */

    @Transactional
    public Room savetoken(Long id, Invite invite) {
        Room Room = roomRepository.findById(id).get();
        inviteRepository.save(invite);
        Room.setToken(invite);
        return roomRepository.save(Room);
    }

    public Boolean checkToken(String token) {
        Optional<Invite> invite = inviteRepository.findByToken(token);
        return invite.get().getUseToken();
    }

    @Transactional
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        return messageRepository.save(chatMessage);
    }

    public List<ChatMessage> findAllMsgs() {
        List<ChatMessage> result = messageRepository.findAll();
        return result;
    }

    @Transactional
    public void deleteRoom(String roomId) {
        roomRepository.deleteByRoomId(roomId);
    }

    @Transactional
    public List<ChatMember> allmembyroomId() {
        return chatMemberRepository.findAll();
    }

}
