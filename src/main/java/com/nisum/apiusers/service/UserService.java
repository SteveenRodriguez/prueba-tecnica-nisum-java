package com.nisum.apiusers.service;

import com.nisum.apiusers.entities.dto.RequestDto;
import com.nisum.apiusers.entities.dto.ResponseDto;

import java.util.List;

public interface UserService {
    ResponseDto saveUser(RequestDto requestDto);

    List<ResponseDto> getAllUsers();
}
