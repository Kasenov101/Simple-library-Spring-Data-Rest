package com.kasenov.libpro.simplelibrary.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CannotSaveException.class)
    public ResponseEntity<Object> cannotSaveClient(CannotSaveException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("message", exception.getMessage());
        responseBody.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> cannotFindClientById(NotFoundException exception) {
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("message", exception.getMessage());
        responseBody.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}
