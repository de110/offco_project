package com.example.offco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.offco.domain.Calendar;
import com.example.offco.domain.Room;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findByRoom(Room room);
}
