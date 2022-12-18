package com.offco.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offco.project.domain.Calendar;
import com.offco.project.domain.ChatRoom;
import com.offco.project.service.CalendarService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping("/api/todolist")
    public void saveCalendar(@RequestBody Calendar calendar, @RequestParam Long id) { // 회원 추가
        calendarService.save(calendar, id);

    }

    @GetMapping("/todolist")
    public List<Calendar> getCalendarByroomId(@RequestParam Long id) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomId(id);
        return calendarService.getCalendarByRoomId(chatRoom);
    }
}