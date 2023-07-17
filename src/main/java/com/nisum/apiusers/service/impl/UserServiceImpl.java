package com.nisum.apiusers.service.impl;

import com.nisum.apiusers.entities.Phone;
import com.nisum.apiusers.entities.User;
import com.nisum.apiusers.entities.dto.PhoneDto;
import com.nisum.apiusers.entities.dto.RequestDto;
import com.nisum.apiusers.entities.dto.ResponseDto;
import com.nisum.apiusers.repository.PhoneRepository;
import com.nisum.apiusers.repository.UserRepository;
import com.nisum.apiusers.service.UserService;
import com.nisum.apiusers.util.ErrorMessages;
import com.nisum.apiusers.util.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Value("${userapi.constants.passwordRegexp}")
    private String passwordRegex;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public ResponseDto saveUser(RequestDto requestDto) {
        if (!isPasswordValid(requestDto.getPassword())) {
            return ResponseDto.builder()
                    .mensaje(ErrorMessages.REGEX_PASSWORD)
                    .build();
        }
        if(requestDto.getName() == null) {
            return ResponseDto.builder()
                    .mensaje(ErrorMessages.BLANK_NAME)
                    .build();
        }
        Optional<User> userValidate = userRepository.findByEmail(requestDto.getEmail());
        if (userValidate.isPresent()) {
            return ResponseDto.builder()
                    .mensaje(ErrorMessages.EXIST_MAIL)
                    .build();
        }
        String encoderPassword = this.passwordEncoder.encode(requestDto.getPassword());
        requestDto.setPassword(encoderPassword);
        User user = userRepository.save(UserMapper.mapToUser(requestDto));
        addPhones(user, requestDto.getPhones());
        return ResponseDto.builder()
                .user(UserMapper.mapToUserDto(user))
                .build();
    }

    @Override
    public List<ResponseDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<ResponseDto> responseList = new ArrayList<>();
        for (User user : users) {
            ResponseDto responseDto = ResponseDto.builder()
                    .user(UserMapper.mapToUserDto(user))
                    .build();
            responseList.add(responseDto);
        }
        return responseList;
    }

    private boolean isPasswordValid(String password) {
        passwordRegex = passwordRegex != null ? passwordRegex : "[A-Za-z0-9]*";
        return password.matches(passwordRegex);
    }

    private void addPhones(User user, List<PhoneDto> phoneDtos) {
        for (PhoneDto phoneDto : phoneDtos) {
            Phone phone = Phone.builder()
                    .number(phoneDto.getNumber())
                    .cityCode(phoneDto.getCityCode())
                    .countryCode(phoneDto.getCountryCode())
                    .user(user)
                    .build();
            phoneRepository.save(phone);
        }
    }
}