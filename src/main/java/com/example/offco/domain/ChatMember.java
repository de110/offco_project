package com.example.offco.domain;

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
    @Column(name = "MEM_ID")
    private Long id;

    // @ManyToOne
    // @JoinColumn(name = "member_id")
    // private Member memberId;
    private String memberId;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room roomId;

    @Builder
    public ChatMember(String memberId, Room roomId) {
        this.memberId = memberId;
        this.roomId = roomId;
    }
}