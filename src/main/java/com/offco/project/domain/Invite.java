package com.offco.project.domain;

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
    private String inviteUrl;

    @Column
    private Boolean useToken;

    @Builder
    public Invite(String inviteUrl, Boolean useToken) {
        this.inviteUrl = inviteUrl;
        this.useToken = useToken;
    }
}
