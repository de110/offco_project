package com.example.offco.dto;

import com.example.offco.domain.ChatMember;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RoomMemberDto {
    private String manager;
    private String roomname;

    public RoomMemberDto(String memberId, String roomname) {
        this.manager = memberId;
        this.roomname = roomname;
    }

}
