package com.offco.project.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offco.project.domain.Calendar;
import com.offco.project.domain.ChatRoom;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findByChatRoom(ChatRoom chatRoom);
}
