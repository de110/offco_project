package com.offco.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ChatMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User userId;

    @ManyToOne
    @JoinColumn(name="ROOM_ID")
    private ChatRoom roomId;

    // @Column
    // private String roomname;

    @Builder
    public ChatMember(User userId, ChatRoom roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }
}
