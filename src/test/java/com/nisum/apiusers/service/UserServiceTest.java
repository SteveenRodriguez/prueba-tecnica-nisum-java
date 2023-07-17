package com.nisum.apiusers.service;

import com.nisum.apiusers.entities.Phone;
import com.nisum.apiusers.entities.User;
import com.nisum.apiusers.entities.dto.PhoneDto;
import com.nisum.apiusers.entities.dto.RequestDto;
import com.nisum.apiusers.entities.dto.ResponseDto;
import com.nisum.apiusers.entities.dto.UserDto;
import com.nisum.apiusers.repository.PhoneRepository;
import com.nisum.apiusers.repository.UserRepository;
import com.nisum.apiusers.service.impl.UserServiceImpl;
import com.nisum.apiusers.util.ErrorMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Test")
class UserServiceTest {
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepositoryMock;

    @Mock
    PhoneRepository phoneRepositoryMock;

    @Test
    void saveRegister() {
        List<PhoneDto> phoneList = new ArrayList<>();
        phoneList.add(PhoneDto.builder()
                .number("3012329933")
                .cityCode(1)
                .countryCode(57)
                .build());
        RequestDto requestRegister = RequestDto.builder()
                .name("Stick Torres")
                .email("stick@mail.com")
                .password("MyPasswordSecret")
                .phones(phoneList)
                .build();
        ResponseDto responseUserExpected = ResponseDto.builder()
                .user(UserDto.builder()
                        .id("11ec0abc-7a1f-449a-9025-8e07b2652534")
                        .token("eyJhbGciOiJub25lIn0.eyJuYW1lIjoiSnVhbiBSb2RyaWd1ZXoiLCJlbWFpbCI6Im" +
                                "p1YW5Acm9kcmlndWV6Lm9yZyIsInN1YiI6Ikp1YW4gUm9kcmlndWV6IiwianRpIjo" +
                                "iODU1YzI5MDctMGU1Zi00MzY2LWExNzktOGI1YmVmZWY4YzRkIiwiaWF0IjoxNjc3" +
                                "NjExMDY5LCJleHAiOjE2Nzc2MTE5Njl9.")
                        .isActive(Boolean.TRUE)
                        .build())
                .build();
        Mockito.when(userRepositoryMock.findByEmail(Mockito.anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(userRepositoryMock.save(Mockito.any(User.class)))
                .thenReturn(User.builder()
                        .idUser("11ec0abc-7a1f-449a-9025-8e07b2652534")
                        .token("eyJhbGciOiJub25lIn0.eyJuYW1lIjoiSnVhbiBSb2RyaWd1ZXoiLCJlbWFpbCI6Im" +
                                "p1YW5Acm9kcmlndWV6Lm9yZyIsInN1YiI6Ikp1YW4gUm9kcmlndWV6IiwianRpIjo" +
                                "iODU1YzI5MDctMGU1Zi00MzY2LWExNzktOGI1YmVmZWY4YzRkIiwiaWF0IjoxNjc3" +
                                "NjExMDY5LCJleHAiOjE2Nzc2MTE5Njl9.")
                        .isActive(Boolean.TRUE)
                        .build());
        Mockito.when(phoneRepositoryMock.save(Mockito.any(Phone.class)))
                .thenReturn(new Phone());
        ResponseDto responseDto = userServiceImpl.saveUser(requestRegister);
        assertNotNull(responseDto);
        assertNotNull(responseDto.getUser());
        assertEquals(responseDto.getUser().getId(), responseUserExpected.getUser().getId());
        assertEquals(responseDto.getUser().getToken(), responseUserExpected.getUser().getToken());
        assertEquals(responseDto.getUser().getIsActive(),
                responseUserExpected.getUser().getIsActive());
        assertNull(responseDto.getMensaje());
    }

    @Test
    void saveRegisterPasswordInvalid() {
        List<PhoneDto> phoneList = new ArrayList<>();
        phoneList.add(PhoneDto.builder()
                .number("3012329933")
                .cityCode(1)
                .countryCode(57)
                .build());
        RequestDto requestDto = RequestDto.builder()
                .name("Stick Torres")
                .email("stick@mail.com")
                .password("MySecretPassword3$%&")
                .phones(phoneList)
                .build();
        ResponseDto responseUserExpected = ResponseDto.builder()
                .mensaje(ErrorMessages.REGEX_PASSWORD)
                .build();
        ResponseDto responseDto = userServiceImpl.saveUser(requestDto);
        assertNotNull(responseDto);
        assertNull(responseDto.getUser());
        assertNotNull(responseDto.getMensaje());
        assertEquals(responseDto.getMensaje(), ErrorMessages.REGEX_PASSWORD);
    }

    @Test
    void saveUser_shoulReturnEmailExist() {
        List<PhoneDto> phoneList = new ArrayList<>();
        phoneList.add(PhoneDto.builder()
                .number("3012329933")
                .cityCode(1)
                .countryCode(57)
                .build());
        RequestDto requestRegister = RequestDto.builder()
                .name("Stick Torres")
                .email("stick@mail.com")
                .password("MyPasswordSecret")
                .phones(phoneList)
                .build();
        ResponseDto responseUserExpected = ResponseDto.builder()
                .mensaje(ErrorMessages.EXIST_MAIL)
                .build();
        Mockito.when(userRepositoryMock.findByEmail(Mockito.anyString()))
                .thenReturn(Optional.of(User.builder()
                        .name("Stick Torres")
                        .email("stick@mail.com")
                        .password("MyPasswordSecret")
                        .build()));
        ResponseDto responseDto = userServiceImpl.saveUser(requestRegister);
        assertNotNull(responseDto);
        assertNull(responseDto.getUser());
        assertNotNull(responseDto.getMensaje());
        assertEquals(responseDto.getMensaje(), ErrorMessages.EXIST_MAIL);
    }
}
