package com.example.offco.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long roomId;

    @Column
    private String roomname;

    // @ManyToOne
    // @JoinColumn(name = "manager")
    private String manager; // who made room

    @OneToOne
    @JoinColumn(name = "token")
    private Invite token;

    public static Room create(String name, String manager, Invite token) {
        Room room = new Room();
        room.roomname = name;
        room.manager = manager;
        room.token = token;
        return room;
    }

    @Builder
    public Room(String roomname, String manager, Invite token) {
        this.roomname = roomname;
        this.manager = manager;
        this.token = token;

    }
}
