package com.example.offco.dto;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String memberId;
    private String password;
    // private Boolean setlogin;
    private String usermail;
    private String username;
}