package com.example.offco.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long chatId;

    @Column
    private String chatname;

    @Column
    private String manager; // who made room

    @Column
    private Long roomId;

    @Builder
    public ChatRoom(String chatname, String manager, Long roomId) {
        this.chatname = chatname;
        this.manager = manager;
        this.roomId = roomId;
    }
}
