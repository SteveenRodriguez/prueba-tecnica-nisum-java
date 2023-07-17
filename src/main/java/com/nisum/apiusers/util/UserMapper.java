package com.nisum.apiusers.util;

import com.nisum.apiusers.entities.User;
import com.nisum.apiusers.entities.dto.RequestDto;
import com.nisum.apiusers.entities.dto.UserDto;

import java.util.Date;
import java.util.UUID;

public class UserMapper {
    public static User mapToUser(RequestDto requestDto) {
        User user = User.builder()
                .idUser(UUID.randomUUID().toString())
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .created(new Date())
                .modified(new Date())
                .lastLogin(new Date())
                .isActive(Boolean.TRUE)
                .build();
        user.setToken(TokenUtil.generateToken(user));
        return user;
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getIdUser())
                .created(user.getCreated())
                .modified(user.getModified())
                .last_login(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.getIsActive())
                .build();
    }
}
