package com.offco.project.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.offco.project.domain.ChatMember;
import com.offco.project.domain.ChatMessage;
import com.offco.project.domain.ChatRoom;
import com.offco.project.domain.User;
import com.offco.project.repository.ChatMemberRepository;
import com.offco.project.repository.ChatRepository;
import com.offco.project.repository.MessageRepository;
import com.offco.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final UserRepository userRepository;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    // 의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    // 채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        // 채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = chatRepository.findAll();
        // Collections.reverse(result);
        // List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        // Collections.reverse(result);

        // return result;
        log.info("result: {}", result);
        return result;
    }

    // // 채팅방 하나 불러오기
    // public List<ChatMember> findByRoomId(Long roomId) {
    //     // return chatRepository.findByRoomId(roomId);
    //     return chatMemberRepository.findByRoom_Id(roomId);
    // }

    public List<ChatRoom> findByInviteUrl(String url) {
        return chatRepository.findByInviteUrl(url);
    }
    // @Transactional
    // public ChatRoom createRoom(String name, String host, String guest) {
    //     ChatRoom chatRoom = ChatRoom.create(name, host, guest);
    //     // return chatRoom;
    //     // chatRooms.put(chatRoom.getRoomId(), chatRoom);

    //     return chatRepository.save(chatRoom);

    // }

    @Transactional
    public ChatRoom save(String inviteUrl) {
        ChatRoom chatRoom = chatRepository.findById(1L).get();
        chatRoom.setInviteUrl(inviteUrl);
        return chatRepository.save(chatRoom);
    }

    @Transactional
    public ChatRoom create(ChatRoom chatRoom, User user) {
        chatRepository.save(chatRoom); // room 정보 저장

        ChatMember chatMember = new ChatMember(); // room member 설정
        chatMember.setRoomId(chatRoom);

        // user = userRepository.findById(user.getId()).get();
        chatMember.setUserId(user);

        chatMemberRepository.save(chatMember);

        return chatRoom;
    }

    @Transactional
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        // ChatMessage chatMessage = new ChatMessage();
        // chatRooms.put(chatRoom.getRoomId(), chatMessage);
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

    // @Transactional
    // public List<ChatMember> findAllMembers(Long roomId) {
    //     // return chatMemberRepository.findByUserId(user.getId());
    //     // return chatMemberRepository.findByUserId(user);
    //     return chatMemberRepository.findByRoom_Id(roomId);
    // }

    @Transactional
    public List<ChatMember> allmem() {
        return chatMemberRepository.findAll();
    }

}