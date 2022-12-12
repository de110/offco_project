package com.offco.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.offco.project.domain.Calendar;
import com.offco.project.domain.ChatRoom;
import com.offco.project.repository.CalendarRepository;
import com.offco.project.repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final ChatRepository chatRepository;

    @Transactional
    public Long save(Calendar calendar) {
        ChatRoom chatRoom = chatRepository.findById(1L).get();
        return calendarRepository.save(Calendar.builder()
                .title(calendar.getTitle())
                .createdAt(calendar.getCreatedAt())
                .chatRoom(chatRoom)
                .build()).getId();
    }

    public List<Calendar> getCalendar() {
        return calendarRepository.findAll();

    }

    public List<Calendar> getCalendarByRoomId(ChatRoom chatRoom) {
        return calendarRepository.findByChatRoom(chatRoom);

    }
}