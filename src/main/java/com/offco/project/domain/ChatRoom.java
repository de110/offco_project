package com.offco.project.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    @Column(name = "ROOM_ID")
    private Long roomId;

    @Column
    private String roomname;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user; // who made room

    @OneToOne
    @JoinColumn(name = "token_id")
    private Invite invite;

    public static ChatRoom create(String name, User user, Invite invite) {
        ChatRoom room = new ChatRoom();
        room.roomname = name;
        room.user = user;
        room.invite = invite;
        return room;
    }

    @Builder
    public ChatRoom(String roomname, Invite invite, User user) {
        this.roomname = roomname;
        this.user = user;
        this.invite = invite;

    }
}
