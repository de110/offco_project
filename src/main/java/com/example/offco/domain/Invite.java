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
@Setter
@NoArgsConstructor
@Entity
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TokenId")
    private Long id;

    @Column
    private String token;

    @Column
    private Boolean useToken;

    @Builder
    public Invite(String token, Boolean useToken) {
        this.token = token;
        this.useToken = useToken;
    }
}
