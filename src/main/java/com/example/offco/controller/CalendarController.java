package com.example.offco.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.offco.domain.Calendar;
import com.example.offco.domain.Room;
import com.example.offco.repository.CalendarRepository;
import com.example.offco.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarRepository calendarRepository;
    private final RoomRepository roomRepository;

    @PostMapping("/todolist")
    public Calendar createTodo(@RequestBody Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    @GetMapping("/todolist")
    public List<Calendar> getTodo(@RequestParam Long calendarId) {
        Room room = roomRepository.findById(calendarId).get();
        return calendarRepository.findByRoom(room);
    }
}
