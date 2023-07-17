package com.nisum.apiusers.exceptions;

import com.nisum.apiusers.entities.dto.ResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionUserController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ResponseDto.builder()
                .mensaje(ex.getAllErrors().get(0).getDefaultMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseDto> handleDataAccessException(DataAccessException ex) {
        return new ResponseEntity<>(ResponseDto.builder()
                .mensaje(ex.getCause().getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDto> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>(ResponseDto.builder()
                .mensaje(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
