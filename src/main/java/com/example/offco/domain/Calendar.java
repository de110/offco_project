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

@Getter
@Entity
@Setter
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Builder
    public Calendar(String title, String createdAt, Room room) {
        this.title = title;
        this.createdAt = createdAt;
        this.room = room;
    }
}
