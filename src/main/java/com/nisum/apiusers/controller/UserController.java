package com.nisum.apiusers.controller;

import com.nisum.apiusers.entities.dto.RequestDto;
import com.nisum.apiusers.entities.dto.ResponseDto;
import com.nisum.apiusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> createRegister(
            @Valid @RequestBody RequestDto requestDto
    ) throws Exception {
        ResponseDto responseDto = userService.saveUser(requestDto);
        HttpStatus status = responseDto.getUser() != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responseDto, status);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseDto>> getAllRegister() {
        List<ResponseDto> responseDto = userService.getAllUsers();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
