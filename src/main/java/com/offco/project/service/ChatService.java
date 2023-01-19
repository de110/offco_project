package com.offco.project.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.offco.project.domain.ChatMember;
import com.offco.project.domain.ChatMessage;
import com.offco.project.domain.ChatRoom;
import com.offco.project.domain.Invite;
import com.offco.project.domain.User;
import com.offco.project.repository.ChatMemberRepository;
import com.offco.project.repository.ChatRepository;
import com.offco.project.repository.InviteRepository;
import com.offco.project.repository.MessageRepository;
import com.offco.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final UserRepository userRepository;
    private final InviteRepository inviteRepository;

    public Optional<ChatRoom> findRoom(String roomname) {
        return chatRepository.findByRoomname(roomname);
    }

    @Transactional
    public ChatRoom save(Long id, Invite invite) {
        ChatRoom chatRoom = chatRepository.findById(id).get();
        inviteRepository.save(invite);
        chatRoom.setInvite(invite);
        return chatRepository.save(chatRoom);
    }

    @Transactional
    public ChatRoom create(ChatRoom chatRoom, String username) {
        User user = userRepository.findByUsername(username).get();
        chatRoom.setUser(user);
        ChatMember chatMember = new ChatMember(); // room member 설정
        chatMember.setRoomId(chatRoom);
        chatMember.setUserId(user);
        chatMemberRepository.save(chatMember);
        return chatRepository.save(chatRoom);
    }

    // public ChatRoom test(ChatRoom chatRoom, String username) {
    // User user = userRepository.findByUsername(username).get();
    // chatRoom.setUser(user);
    // return chatRepository.save(chatRoom);
    // }

    @Transactional
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        return messageRepository.save(chatMessage);
    }

    public List<ChatMessage> findAllMsgs() {
        List<ChatMessage> result = messageRepository.findAll();
        return result;
    }

    @Transactional
    public void deleteChatRoom(String roomId) {
        chatRepository.deleteByRoomId(roomId);
    }

    @Transactional
    public List<ChatMember> allmembyroomId() {
        return chatMemberRepository.findAll();
    }

}