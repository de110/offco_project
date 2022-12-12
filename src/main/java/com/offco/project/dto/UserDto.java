package com.offco.project.dto;

import org.apache.catalina.User;

import lombok.Getter;

@Getter
public class UserDto {
    private User user;

    public UserDto(User user) {
        this.user = user;
    }
}
