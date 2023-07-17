package com.nisum.apiusers.controller;

import com.nisum.apiusers.entities.dto.PhoneDto;
import com.nisum.apiusers.entities.dto.RequestDto;
import com.nisum.apiusers.entities.dto.ResponseDto;
import com.nisum.apiusers.entities.dto.UserDto;
import com.nisum.apiusers.service.UserService;
import com.nisum.apiusers.util.ErrorMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserController Test")
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userServiceMock;

    @Test
    protected void createRegister_shouldReturnExpectedResponse() throws Exception {
        // Arrange
        List<PhoneDto> phoneList = new ArrayList<>();
        phoneList.add(PhoneDto.builder()
                .number("3012329933")
                .cityCode(1)
                .countryCode(57)
                .build());
        RequestDto requestDto = RequestDto.builder()
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

        Mockito.when(userServiceMock.saveUser(Mockito.any(RequestDto.class)))
                .thenReturn(ResponseDto.builder()
                        .user(UserDto.builder()
                                .id("11ec0abc-7a1f-449a-9025-8e07b2652534")
                                .token("eyJhbGciOiJub25lIn0.eyJuYW1lIjoiSnVhbiBSb2RyaWd1ZXoiLCJlbWFpbCI6Im" +
                                        "p1YW5Acm9kcmlndWV6Lm9yZyIsInN1YiI6Ikp1YW4gUm9kcmlndWV6IiwianRpIjo" +
                                        "iODU1YzI5MDctMGU1Zi00MzY2LWExNzktOGI1YmVmZWY4YzRkIiwiaWF0IjoxNjc3" +
                                        "NjExMDY5LCJleHAiOjE2Nzc2MTE5Njl9.")
                                .isActive(Boolean.TRUE)
                                .build())
                        .build());

        // Act
        ResponseEntity<ResponseDto> responseEntity = userController.createRegister(requestDto);

        // Assert
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody().getUser());
        assertEquals(responseEntity.getBody().getUser().getId(), responseUserExpected.getUser().getId());
        assertEquals(responseEntity.getBody().getUser().getToken(), responseUserExpected.getUser().getToken());
        assertEquals(responseEntity.getBody().getUser().getIsActive(),
                responseUserExpected.getUser().getIsActive());
        assertNull(responseEntity.getBody().getMensaje());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    protected void createRegister_shouldReturnEmailExist() throws Exception {
        // Arrange
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

        Mockito.when(userServiceMock.saveUser(Mockito.any(RequestDto.class)))
                .thenReturn(ResponseDto.builder()
                        .mensaje(ErrorMessages.EXIST_MAIL)
                        .build());

        // Act
        ResponseEntity<ResponseDto> responseEntity = userController.createRegister(requestRegister);

        // Assert
        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody().getUser());
        assertNotNull(responseEntity.getBody().getMensaje());
        assertEquals(responseEntity.getBody().getMensaje(), ErrorMessages.EXIST_MAIL);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void getAllRegister_shouldReturnListOfResponseDto() {
        // Arrange
        ResponseDto user1 =ResponseDto.builder()
                .user(UserDto.builder()
                        .id("11ec0abc-7a1f-449a-9025-8e07b2652534")
                        .token("eyJhbGciOiJub25lIn0.eyJuYW1lIjoiSnVhbiBSb2RyaWd1ZXoiLCJlbWFpbCI6Im" +
                                "p1YW5Acm9kcmlndWV6Lm9yZyIsInN1YiI6Ikp1YW4gUm9kcmlndWV6IiwianRpIjo" +
                                "iODU1YzI5MDctMGU1Zi00MzY2LWExNzktOGI1YmVmZWY4YzRkIiwiaWF0IjoxNjc3" +
                                "NjExMDY5LCJleHAiOjE2Nzc2MTE5Njl1.")
                        .isActive(Boolean.TRUE)
                        .build())
                .build();
        ResponseDto user2 =ResponseDto.builder()
                .user(UserDto.builder()
                        .id("13Sec0abc-7a1f-449a-9025-8e07b2652534")
                        .token("eyJhbGciOiJub25lIn0.eyJuYW1lIjoiSnVhbiBSb2RyaWd1ZXoiLCJlbWFpbCI6Im" +
                                "p1YW5Acm9kcmlndWV6Lm9yZyIsInN1YiI6Ikp1YW4gUm9kcmlndWV6IiwianRpIjo" +
                                "iODU1YzI5MDctMGU1Zi00MzY2LWExNzktOGI1YmVmZWY4YzRkIiwiaWF0IjoxNjc3" +
                                "NjExMDY5LCJleHAiOjE2Nzc2MTE5Njl2.")
                        .isActive(Boolean.TRUE)
                        .build())
                .build();
        List<ResponseDto> expectedResponse = new ArrayList<>();
        expectedResponse.add(user1);
        expectedResponse.add(user2);
        Mockito.when(userServiceMock.getAllUsers()).thenReturn(expectedResponse);

        // Act
        ResponseEntity<List<ResponseDto>> responseEntity = userController.getAllRegister();

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(expectedResponse.size(), 2);
    }
}
