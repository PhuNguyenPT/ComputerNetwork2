package com.example.jpa.exception;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityException(DataIntegrityViolationException e) {
        LoggerFactory.getLogger(e.getClass()).error(e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseException(
                        "Duplicated file name that already exists",
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"))
                ));
    }
}
